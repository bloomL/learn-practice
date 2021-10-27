package com.learn.concurrent.beauty.chapter_ten.semaphore;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * Create by liguo on 2021/10/22
 **/
public class SemaphoreDemo1 {
    // 信号量计数器为0
    private static Semaphore semaphore = new Semaphore(1);

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
                    System.out.println(Thread.currentThread() + " over");
                    // 计数器递增1
                    semaphore.release();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        // 调用acquire()的线程被阻塞，直到信号量为传入参数，才返回
        semaphore.acquire(3);
        System.out.println("all child thread over");
        pool.shutdown();
    }
}
