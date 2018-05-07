/*package com.flowergarden.run.TSafeArrayLists;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.log4j.Logger;

public class ArrayListMultithreaded {

    private static Logger log = Logger.getLogger(ArrayListMultithreaded.class);

    private static List<String> list = new ArrayList<>();

    public static void main(String[] args) {

        list.add("Sidun");
        list.add("Malenko");
        list.add("Kvachuk");
        list.add("Shaula");

        long time = 1000;

        Thread t1 = new Thread(() -> {
            list = Collections.synchronizedList(list);
            for (String str : list) {
                log.info("JavaWebGroup Thread1:" + str);
            }
        });
        t1.start();

        try {
            Thread.sleep(time);
            System.out.println("Thread is stopped on " + (double) time / 1000 + " second and added a separation line");
            System.out.println("**********************************************************************************");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread t2 = new Thread(() -> {
            list = Collections.synchronizedList(list);
            list.add("Bandura");
            for (String str : list) {
                log.info("JavaWebGroup Thread2:" + str);
            }
        });
        t2.start();
    }
}*/

