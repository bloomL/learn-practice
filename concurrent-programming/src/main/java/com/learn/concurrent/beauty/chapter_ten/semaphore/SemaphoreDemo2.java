package com.learn.concurrent.beauty.chapter_ten.semaphore;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * 复用
 * Create by liguo on 2021/10/22
 **/
public class SemaphoreDemo2 {
    private static Semaphore semaphore = new Semaphore(0);

    public static void main(String[] args) throws InterruptedException {
        ThreadFactory nameFactory = new ThreadFactoryBuilder().setNameFormat("semaphore-pool-%d").build();
        ExecutorService pool = new ThreadPoolExecutor(2,
                2,
                0,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(1024),
                nameFactory,
                new ThreadPoolExecutor.AbortPolicy());
        int max = 2;
        for (int i = 0; i < max; i++) {
            pool.submit(() -> {
                try {
                    System.out.println(Thread.currentThread() + "sub_A task over");
                    semaphore.release();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        // 返回后 计数器变为初始值
        semaphore.acquire(2);
        System.out.println("task A is over");
        for (int i = 0; i < max; i++) {
            pool.submit(() -> {
                try {
                    System.out.println(Thread.currentThread() + "sub_B task over");
                    semaphore.release();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        semaphore.acquire(2);
        System.out.println("task B is over");
        pool.shutdown();
    }
}
