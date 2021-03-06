package com.concurrency.task2.cases;

import com.concurrency.task2.IncrementExample;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Case6 implements Case {

    private static final int NUMBER_OF_THREADS = 4;

    private static final String DESCRIPTION = "byFixedThreadPool(4) with Future with AtomicLong";

    private IncrementExample incrementExample;

    public Case6(IncrementExample incrementExample) {
        this.incrementExample = incrementExample;
    }

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }

    @Override
    public void perform() {
        ExecutorService executorService = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
        Collection<Callable<String>> tasks = new ArrayList<>();
        for (int i = 0; i < NUMBER_OF_THREADS; i++) {
            tasks.add(() -> {
                String threadName = Thread.currentThread().getName();
                incrementExample.incrementAtomicLong();
                return threadName;
            });
        }
        try {
            executorService.invokeAll(tasks);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
    }
}