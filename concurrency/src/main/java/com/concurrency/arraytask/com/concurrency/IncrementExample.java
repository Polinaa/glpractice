package com.concurrency.arraytask.com.concurrency;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class IncrementExample {

    public static long MAX = 1000000;

    private static int i;

    private static AtomicLong atomicI = new AtomicLong(0);

    private static Lock lock = new ReentrantLock();

    private static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public static void resetValues() {
        i = 0;
        atomicI = new AtomicLong(0);
    }

    public void incrementSimple() {
        while (i < MAX) {
            ++i;
        }
    }

    public synchronized void incrementSynchronized() {
        incrementSimple();
    }

    public void incrementReentrantLock() {
        lock.lock();
        try {
            incrementSimple();
        } finally {
            lock.unlock();
        }
    }

    public void incrementReadWriteLock() {
        readWriteLock.writeLock().lock();
        try {
            incrementSimple();
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public void incrementAtomicLong() {
        while (atomicI.incrementAndGet() < MAX) {
        }
        ;
    }

    public static long getNotZeroResult() throws Exception {
        if (i != 0) {
            return i;
        }
        if (atomicI.get() != 0) {
            return atomicI.get();
        }
        throw new Exception("All values are zero");
    }
}
