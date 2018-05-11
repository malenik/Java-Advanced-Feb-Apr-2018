package com.flowergarden.concurrency.CalculateMillion;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import static com.flowergarden.concurrency.CalculateMillion.MillionApp.BenchmarkState.MILLION;

@OutputTimeUnit(TimeUnit.NANOSECONDS)
@BenchmarkMode(Mode.AverageTime)
@Warmup(iterations = 1, time = 1)
@Measurement(iterations = 1, time = 1)
@State(Scope.Thread)
public class MillionApp {

    @State(Scope.Thread)
    public static class BenchmarkState {
        static final long MILLION = 1000000;
        int threadState;

        @Setup(Level.Iteration)
        public void
        initialize() {
            this.threadState = 0;
        }
    }

    @State(Scope.Benchmark)
    public static class LongResourceState {
        LongResource resource = new LongResource();

        @TearDown(Level.Trial)
        public void doTearDown() {
            System.out.print("i=" + resource.lr + "; t=");
        }
    }

    @State(Scope.Benchmark)
    public static class AtomicLongState {
        AtomicLong resource = new AtomicLong();

        @TearDown(Level.Trial)
        public void doTearDown() {
            System.out.print("i=" + resource.get() + "; t=");
        }
    }

    @Benchmark
    @Group("incrementToMillion")
    @GroupThreads()
    public void incrementToMillion(BenchmarkState state, Blackhole bh) {
        long i = 0;
        while (state.threadState < MILLION) {
            i = state.threadState++;
        }
        bh.consume(i);
    }

    @Benchmark
    @Group("through4ThreadsSynchronizedExtendsThread")
    @GroupThreads(4)
    public void through4ThreadsSynchronizedExtendsThread(LongResourceState state) {
        ExtendsThreadPr extendsThreadPr = new ExtendsThreadPr(state.resource);
        extendsThreadPr.start();
    }

    @Benchmark
    @Group("through4ThreadsAndImplementsRunnable")
    @GroupThreads(4)
    public void through4ThreadsAndImplementsRunnable(LongResourceState state) {
        Thread thread = new Thread(new ImplementsRunnablePr(state.resource));
        thread.start();
    }

    @Benchmark
    @Group("byFixedThreadPool4WithFutureWithReentrantLock")
    @GroupThreads(4)
    public void byFixedThreadPool4WithFutureWithReentrantLock(LongResourceState state, Blackhole bh) throws ExecutionException, InterruptedException {
        ReentrantLock reentrantLock = new ReentrantLock();
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        Future<LongResource> future = executorService.submit(new ImplementsCallablePr(state.resource, reentrantLock));
        bh.consume(future.get());

        executorService.shutdown();
    }

    @Benchmark
    @Group("byFixedThreadPool4WithFutureWithReadWriteLock")
    @GroupThreads(4)
    public void byFixedThreadPool4WithFutureWithReadWriteLock(LongResourceState state, Blackhole bh) throws ExecutionException, InterruptedException {
        ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        Future<LongResource> future = executorService.submit(new ImplementsCallablePr(state.resource, readWriteLock.writeLock()));
        bh.consume(future.get());

        executorService.shutdown();
    }

    @Benchmark
    @Group("byFixedThreadPool4WithFutureWithAtomicLong")
    @GroupThreads(4)
    public void byFixedThreadPool4WithFutureWithAtomicLong(AtomicLongState state, Blackhole bh) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        Future<AtomicLong> future = executorService.submit(new AtomicLongPr(state.resource));
        bh.consume(future.get());

        executorService.shutdown();
    }

    static void runTests() throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(".*" + MillionApp.class.getSimpleName() + ".*")
                .forks(1)
                .build();
        new Runner(opt).run();
    }
}

class ExtendsThreadPr extends Thread {
    private final LongResource i;

    ExtendsThreadPr(LongResource i) {
        this.i = i;
    }

    public void run() {
        while (i.lr < MILLION) {
            synchronized (i) {
                if (i.lr < MILLION)
                    i.lr++;
            }
        }
    }
}

class ImplementsRunnablePr implements Runnable {

    private final LongResource i;

    ImplementsRunnablePr(LongResource i) {
        this.i = i;
    }

    @Override
    public void run() {
        while (i.lr < MILLION) {
            synchronized (i) {
                if (i.lr < MILLION)
                    i.lr++;
            }
        }
    }
}

class ImplementsCallablePr implements Callable<LongResource> {

    private final LongResource i;
    private final Lock lock;

    ImplementsCallablePr(LongResource i, Lock lock) {
        this.i = i;
        this.lock = lock;
    }

    @Override
    public LongResource call() {
        while (i.lr < MILLION) {
            lock.lock();
            try {
                if (i.lr < MILLION)
                    i.lr++;
            } finally {
                lock.unlock();
            }
        }
        return i;
    }
}

class AtomicLongPr implements Callable<AtomicLong> {

    private final AtomicLong i;

    AtomicLongPr(AtomicLong i) {
        this.i = i;
    }

    @Override
    public AtomicLong call() {
        while (i.get() < MILLION) {
            i.incrementAndGet();
        }
        return i;
    }
}

class LongResource {
    long lr;
}
