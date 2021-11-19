package com.learn.concurrent.actual_combat.chapter_five.five;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Semaphore;

/**
 *
 * Using Semaphore to bound a collection
 *
 * Create by liguo on 2021/11/3
 **/
public class BoundeHashSet<T> {
    private final Set<T> set;
    private final Semaphore sem;

    public BoundeHashSet(int bound) {
        this.set = Collections.synchronizedSet(new HashSet<>());
        sem = new Semaphore(bound);
    }

    public boolean add(T t) throws InterruptedException {
        sem.acquire();
        boolean isAdded = false;
        try {
            isAdded = set.add(t);
            return isAdded;
        }finally {
            if (!isAdded) {
                sem.release();
            }
        }
    }

    public boolean remove(Object o) {
        boolean remove = set.remove(o);
        if (remove) {
            sem.release();
        }
        return remove;
    }

    public static void main(String[] args) throws InterruptedException {
        BoundeHashSet bound = new BoundeHashSet(2);
        Thread threadOne = new Thread(() -> {
            try {
                bound.add(1);
                bound.add(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"threadOne");
        Thread threadTwo = new Thread(() -> {
            try {
                bound.add(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"threadTwo");
        threadOne.start();
        threadTwo.start();
        //threadTwo.interrupt();
    }
}
