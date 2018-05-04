package com.concurrency.arraytask.com.concurrency.cases;

import com.concurrency.arraytask.com.concurrency.IncrementExample;

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
