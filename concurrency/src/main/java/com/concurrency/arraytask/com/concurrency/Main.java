package com.concurrency.arraytask.com.concurrency;

import com.concurrency.arraytask.com.concurrency.cases.*;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        IncrementExample incrementExample = new IncrementExample();
        List<Case> cases = new ArrayList<>();
        cases.add(new Case1(incrementExample));
        cases.add(new Case2(incrementExample));
        cases.add(new Case3(incrementExample));
        cases.add(new Case4(incrementExample));
        cases.add(new Case5(incrementExample));
        cases.add(new Case6(incrementExample));

        System.out.println("========================TASK=========================");
        System.out.println("There is next method that has to calculate long between 0 .. 1 000 000 by increment.\n"
                           + "Stop when long i will be 1 000 000. Share i value and time");


        cases.forEach(c -> {
            IncrementExample.resetValues();
            System.out.println("#");
            System.out.println(c.getDescription());
            long startTime = System.nanoTime();
            c.perform();
            long endTime = System.nanoTime();
            try {
                System.out.println("Nano seconds time: " + (double) (endTime - startTime) + " & i = " + IncrementExample
                    .getNotZeroResult());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}