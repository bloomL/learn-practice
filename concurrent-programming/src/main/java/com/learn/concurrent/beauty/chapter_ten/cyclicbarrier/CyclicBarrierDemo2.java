package com.learn.concurrent.beauty.chapter_ten.cyclicbarrier;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * Create by liguo on 2021/10/21
 **/
public class CyclicBarrierDemo2 {
    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(2);

    public static void main(String[] args) {
        ThreadFactory nameFactory = new ThreadFactoryBuilder().setNameFormat("cyclicBarrierDemo-pool-%d").build();
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
                // 可复用性，每个线程都是123的步骤
                try {
                    System.out.println(Thread.currentThread() + " step1");
                    cyclicBarrier.await();
                    System.out.println(Thread.currentThread() + " step2");
                    cyclicBarrier.await();
                    System.out.println(Thread.currentThread() + " step3");
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });
        }
        pool.shutdown();
    }
}
