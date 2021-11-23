package com.learn.concurrent.actual_combat.chapter_fifteen.two;

/**
 *
 * Simulated CAS operation
 *
 * Create by liguo on 2021/11/23
 **/
public class SimulatedCAS {
    private int value;

    public SimulatedCAS(int value) {
        this.value = value;
    }

    public synchronized int get() {
        return value;
    }

    public synchronized int compareAndSwap(int expectedValue, int newValue) {
        int oldValue = value;
        if (oldValue == expectedValue) {
            value = newValue;
        }
        return oldValue;
    }

    public synchronized boolean compareAndSet(int expectedValue, int newValue) {
        return expectedValue == compareAndSwap(expectedValue, newValue);
    }
}
