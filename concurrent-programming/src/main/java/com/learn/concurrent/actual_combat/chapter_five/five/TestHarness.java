package com.learn.concurrent.actual_combat.chapter_five.five;

import java.util.concurrent.CountDownLatch;

/**
 *
 * Using CountDownLatch for starting and stopping threads in timing tests
 *
 * Create by liguo on 2021/11/2
 **/
public class TestHarness {
    public long timeTasks(int nThread, final Runnable task) throws InterruptedException {
        final CountDownLatch startGate = new CountDownLatch(1);
        final CountDownLatch endGate = new CountDownLatch(nThread);

        for (int i = 0; i < nThread; i++) {
            Thread thread = new Thread(() -> {
                try {
                    // 等待所有线程就绪
                    startGate.await();
                    try {
                        task.run();
                    } finally {
                        endGate.countDown();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            thread.start();
        }
        long start = System.nanoTime();
        startGate.countDown();
        endGate.await();
        long end = System.nanoTime();
        return end-start;
    }
}
