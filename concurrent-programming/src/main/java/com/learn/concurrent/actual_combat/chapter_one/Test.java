package com.learn.concurrent.actual_combat.chapter_one;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Create by liguo on 2021/10/27
 **/
public class Test {
    private final static int SIZE = 10;
    static class UnsafeSequence{
        //private static AtomicInteger value = new AtomicInteger(0);
        private static int value;

        public static  int getNext() {
            synchronized (UnsafeSequence.class) {
                return value++;
            }
            //return value.incrementAndGet();
            //return value++;
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < SIZE; i++) {
            Thread thread = new Thread(() -> {
                System.out.println(UnsafeSequence.getNext());
            });
            thread.start();
        }
    }

}
