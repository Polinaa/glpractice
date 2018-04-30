package com.concurrency.arraytask.com.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please, select case: ");
        int caseNum = sc.nextInt();
        String msg = "Case #%d: %s";
        switch (caseNum) {
            case 1: {
                CounterExtThread tr = new CounterExtThread();
                System.out.println(String.format(msg, caseNum, "Single"));
                Thread thread = new Thread(tr);
                long startTime = System.currentTimeMillis();
                thread.start();
                thread.join();
                long endTime = System.currentTimeMillis();
                System.out.println("Long time: "+ (double) (endTime - startTime) + ", i = " + tr.getI());
//                break;
            } case 2: {
                CounterExtThread tr = new CounterExtThread();
                System.out.println(String.format(msg, caseNum, "4 threads, extends Thread"));
                List<Thread> threads = new ArrayList<>();
                threads.add(new Thread(tr));
                threads.add(new Thread(tr));
                threads.add(new Thread(tr));
                threads.add(new Thread(tr));
                long startTime = System.currentTimeMillis();
                threads.forEach(t-> t.start());
                threads.forEach(t -> {
                    try {
                        t.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
                long endTime = System.currentTimeMillis();
                System.out.println("Long time: "+ (double) (endTime - startTime)+ ", i = " + tr.getI());
            }
            case 3: {
                CounterImplRunnable rn = new CounterImplRunnable();
                System.out.println(String.format(msg, caseNum, "4 threads, implements Runnable"));
                List<Thread> threads = new ArrayList<>();
                threads.add(new Thread(rn));
                threads.add(new Thread(rn));
                threads.add(new Thread(rn));
                threads.add(new Thread(rn));
                long startTime = System.currentTimeMillis();
                threads.forEach(t-> t.start());
                threads.forEach(t -> {
                    try {
                        t.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
                long endTime = System.currentTimeMillis();
                System.out.println("Long time: "+ (double) (endTime - startTime)+ ", i = " + rn.getI());
            }
            case 4: {
//                System.out.println(String.format(msg, caseNum, "4 threads, reentrantLock"));
//                ExecutorService executorService = Executors.newFixedThreadPool(4);
//                CounterReentrantLock rl = new CounterReentrantLock();
//                List<Callable> tasks = new ArrayList<>();
//                tasks.add(new Caa(rl));
//                tasks.add(new Thread(rl));
//                tasks.add(new Thread(rl));
//                tasks.add(new Thread(rl));
//                List<Future<Integer>> futureList = executorService.invokeAll(tasks);
            }
            case 5: {

            }
            case 6: {

            }
        }
    }
}