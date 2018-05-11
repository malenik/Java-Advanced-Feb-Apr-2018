package com.flowergarden.concurrency.CalculateMillion;

import org.openjdk.jmh.runner.RunnerException;

public class MillionLauncher {
    public static void main(String[] args) throws RunnerException {
        long javaVersion = 8;
        System.out.print("Welcome to Java " + javaVersion);
        System.out.println("Case with MillionApp: ");
        MillionApp.runTests();
    }
}
