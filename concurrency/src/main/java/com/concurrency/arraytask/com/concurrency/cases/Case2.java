package com.concurrency.arraytask.com.concurrency.cases;

import com.concurrency.arraytask.com.concurrency.IncrementExample;

import java.util.ArrayList;
import java.util.List;

public class Case2 implements Case {

    private static final int NUMBER_OF_THREADS = 4;

    private static final String DESCRIPTION = "Synchronized code with 4 threads and extends Thread";

    private IncrementExample incrementExample;

    public Case2(IncrementExample incrementExample) {
        this.incrementExample = incrementExample;
    }

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }

    @Override
    public void perform() {
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < NUMBER_OF_THREADS; i++) {
            threads.add(new Thread() {
                public void run() {
                    incrementExample.incrementSynchronized();
                }
            });
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
