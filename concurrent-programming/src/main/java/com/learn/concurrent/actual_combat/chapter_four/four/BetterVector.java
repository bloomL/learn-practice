package com.learn.concurrent.actual_combat.chapter_four.four;

import java.util.Vector;

/**
 * Create by liguo on 2021/11/1
 **/
public class BetterVector<E> extends Vector<E> {
    public synchronized boolean putIfAbsent(E x) {
        boolean absent = !contains(x);
        if (absent) {
            add(x);
        }
        return absent;
    }
}
