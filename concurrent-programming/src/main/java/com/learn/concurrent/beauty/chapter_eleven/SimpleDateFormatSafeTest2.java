package com.learn.concurrent.beauty.chapter_eleven;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.CountDownLatch;

/**
 * 加锁，高并发情况下响应性能下降
 * Create by liguo on 2021/10/26
 **/
public class SimpleDateFormatSafeTest2 {
    private static CountDownLatch countDownLatch = new CountDownLatch(10);
    public static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final int SIZE = 10;

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        for (int i = 0; i < SIZE; i++) {
            Thread thread = new Thread(() -> {
                try {
                    synchronized (sdf) {
                        System.out.println(sdf.parse("2021-10-25 15:56:58"));
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();
                }
            });
            thread.start();
        }
        countDownLatch.await();
        // 10
        System.out.println("spend total time : " + (System.currentTimeMillis() - start));
    }
}
