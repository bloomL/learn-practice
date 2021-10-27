package com.learn.concurrent.beauty.chapter_eleven;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

/**
 * 每次new一个对象，使用后没有其它引用，需要回收，开销大
 * Create by liguo on 2021/10/26
 **/
public class SimpleDateFormatSafeTest1 {
    private static Semaphore semaphore = new Semaphore(0);
    private static final int SIZE = 10;

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        for (int i = 0; i < SIZE; i++) {
            Thread thread = new Thread(() -> {
                try {
                    System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2021-10-25 15:56:58"));
                } catch (ParseException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
            });
            thread.start();
        }
        semaphore.acquire(10);
        // 59
        System.out.println("spend total time : " + (System.currentTimeMillis() - start));
    }
}
