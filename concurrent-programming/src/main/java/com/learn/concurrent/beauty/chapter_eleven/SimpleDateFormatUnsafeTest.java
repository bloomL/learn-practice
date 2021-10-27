package com.learn.concurrent.beauty.chapter_eleven;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.CountDownLatch;

/**
 * Create by liguo on 2021/10/25
 **/
public class SimpleDateFormatUnsafeTest {
    private static CountDownLatch countDownLatch = new CountDownLatch(10);
    public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final int SIZE = 10;

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        for (int i = 0; i < SIZE; i++) {
            Thread thread = new Thread(() -> {
                try {
                    System.out.println(sdf.parse("2021-10-25 15:56:58"));
                } catch (ParseException e) {
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();
                }
            });
            thread.start();
        }
        countDownLatch.await();
        System.out.println("spend total time : " + (System.currentTimeMillis() - start));
    }
}
