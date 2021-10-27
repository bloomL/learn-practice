package com.learn.concurrent.beauty.chapter_ten.cyclicbarrier;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * Create by liguo on 2021/10/21
 **/
public class CyclicBarrierDemo1 {
    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(2, () -> {
        System.out.println(Thread.currentThread() + " task1 merge result");
    });

    public static void main(String[] args) {
        ThreadFactory nameFactory = new ThreadFactoryBuilder().setNameFormat("cyclicBarrierDemo-pool-%d").build();
        ExecutorService pool = new ThreadPoolExecutor(2,
                2,
                0,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(1024),
                nameFactory,
                new ThreadPoolExecutor.AbortPolicy());

        pool.submit(() -> {
            try {
                System.out.println(Thread.currentThread() +" task1-1");
                System.out.println(Thread.currentThread() +" enter in barrier");
                // 计数器值减1，若计数器不为0，当前线程到达屏障点被阻塞；
                // 若为0，执行CyclicBarrier构造器中任务，执行完毕，退出屏障点，并唤醒阻塞的线程
                cyclicBarrier.await();
                System.out.println(Thread.currentThread() +" enter out barrier");
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        });

        pool.submit(() -> {
            try {
                System.out.println(Thread.currentThread() +" task1-2");
                System.out.println(Thread.currentThread() +" enter in barrier");
                cyclicBarrier.await();
                System.out.println(Thread.currentThread() +" enter out barrier");
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        });

        pool.shutdown();
    }
}
