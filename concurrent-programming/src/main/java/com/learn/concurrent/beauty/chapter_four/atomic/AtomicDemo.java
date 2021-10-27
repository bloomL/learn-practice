package com.learn.concurrent.beauty.chapter_four.atomic;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Create by liguo on 2021/9/28
 **/
public class AtomicDemo {
    private static AtomicLong aLong = new AtomicLong();
    //private static Long aLong = 0L;
    private static Integer[] arrOne = new Integer[]{0,123,0,3,6,0};
    private static Integer[] arrTwo = new Integer[]{1,23,66,0,6,0,88};

    public static void main(String[] args) throws InterruptedException {
        Thread threadOne = new Thread(() -> {
            Arrays.stream(arrOne).forEach(item -> {
                if (item == 0) {
                    aLong.incrementAndGet();
                    //aLong++;
                }
            });
        });

        Thread threadTwo = new Thread(() -> {
            Arrays.stream(arrTwo).forEach(item -> {
                if (item == 0) {
                    aLong.incrementAndGet();
                    //aLong++;
                }
            });
        });

        threadOne.start();
        threadTwo.start();
        // 线程执行完毕
        threadOne.join();
        threadTwo.join();
        //System.out.println("count 0 : " + aLong.get());
        System.out.println("count 0 : " + aLong);
    }
}
