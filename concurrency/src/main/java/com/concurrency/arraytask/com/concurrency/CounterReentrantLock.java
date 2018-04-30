package com.concurrency.arraytask.com.concurrency;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CounterReentrantLock implements Runnable, Counter {

    private static long i;

    private static Lock lock = new ReentrantLock();

    @Override
    public void run() {
        sumTo1000000();
    }

    public static void sumTo1000000() {
        lock.lock();
        try {
            while (i < MAX) {
                i++;
            }
        }finally {
            lock.unlock();
        }
    }

    @Override
    public long getI() {
        return i;
    }
}