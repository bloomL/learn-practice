package com.learn.concurrent.actual_combat.chapter_eight.eight;

import java.util.concurrent.CountDownLatch;

/**
 *
 * Result-bearing latch used by ConcurrentPuzzleSolver
 *
 * Create by liguo on 2021/11/18
 **/
public class ValueLatch<T> {
    private T value = null;
    private final CountDownLatch done = new CountDownLatch(1);

    public boolean isSet() {
        return done.getCount() == 0;

    }

    public synchronized void setValue(T newValue) {
        if (!isSet()) {
            value = newValue;
            done.countDown();
        }
    }

    public T getValue() throws InterruptedException {
        done.await();
        synchronized (this) {
            return value;
        }
    }
}
