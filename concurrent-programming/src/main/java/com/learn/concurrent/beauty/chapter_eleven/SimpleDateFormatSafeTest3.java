package com.learn.concurrent.beauty.chapter_eleven;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.CountDownLatch;

/**
 * 节省创建销毁开销，且不需要多个线程同步
 * Create by liguo on 2021/10/26
 **/
public class SimpleDateFormatSafeTest3 {
    private static final CountDownLatch COUNT_DOWN_LATCH = new CountDownLatch(10);
    private static final ThreadLocal<DateFormat> LOCAL = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    private static final int SIZE = 10;

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        for (int i = 0; i < SIZE; i++) {
            Thread thread = new Thread(() -> {
                try {
                    System.out.println(LOCAL.get().parse("2021-10-25 15:56:58"));
                } catch (ParseException e) {
                    e.printStackTrace();
                } finally {
                    COUNT_DOWN_LATCH.countDown();
                    // 清除，避免内存泄露
                    LOCAL.remove();
                }
            });
            thread.start();
        }
        COUNT_DOWN_LATCH.await();
        // 59
        System.out.println("spend total time : " + (System.currentTimeMillis() - start));
    }
}
