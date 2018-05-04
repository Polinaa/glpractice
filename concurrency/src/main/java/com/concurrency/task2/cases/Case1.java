package com.concurrency.task2.cases;

import com.concurrency.task2.IncrementExample;

public class Case1 implements Case {
    private IncrementExample incrementExample;

    private static final String DESCRIPTION = "Single code";

    public Case1(IncrementExample incrementExample) {
        this.incrementExample = incrementExample;
    }

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }

    @Override
    public void perform() {
        incrementExample.incrementSimple();
    }
}
