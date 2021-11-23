package com.learn.concurrent.actual_combat.chapter_fifteen.two;

/**
 *
 * Nonblocking counter using CAS
 *
 * Create by liguo on 2021/11/23
 **/
public class CasCounter {
    private SimulatedCAS value;

    public int getValue() {
        return value.get();
    }

    public int increment() {
        int v;
        do {
            v = value.get();
            // !vale.compareAndSet(v , v + 1) 替代 v != value.compareAndSwap(v, v + 1)
        } while (v != value.compareAndSwap(v, v + 1));
        return v + 1;
    }
}
