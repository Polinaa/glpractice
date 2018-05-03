package com.concurrency.arraytask.com.concurrency.cases;

import com.concurrency.arraytask.com.concurrency.IncrementExample;

import java.util.ArrayList;
import java.util.List;

public class Case3 implements Case {

    private static final int NUMBER_OF_THREADS = 4;

    private static final String DESCRIPTION = "with 4 threads and implement Runnable";

    private IncrementExample incrementExample;

    public Case3(IncrementExample incrementExample) {
        this.incrementExample = incrementExample;
    }

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }

    @Override
    public void perform() {
        List<Thread> threads = new ArrayList<>();
        Runnable task = () -> incrementExample.incrementSynchronized();
        for (int i = 0; i < NUMBER_OF_THREADS; i++) {
            threads.add(new Thread(task));
        }
        threads.forEach(Thread::start);
        threads.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}