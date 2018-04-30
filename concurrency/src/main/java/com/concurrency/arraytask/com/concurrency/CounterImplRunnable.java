package com.concurrency.arraytask.com.concurrency;

public class CounterImplRunnable implements Runnable, Counter {

    private static long i;

    @Override
    public void run() {
        sumTo1000000();
    }

    public static synchronized void sumTo1000000() {
        while (i < MAX) {
            i++;
        }
    }

    @Override
    public long getI() {
        return i;
    }
}
