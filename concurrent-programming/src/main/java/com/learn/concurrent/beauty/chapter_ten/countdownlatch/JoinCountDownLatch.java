package com.learn.concurrent.beauty.chapter_ten.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * Create by liguo on 2021/10/21
 **/
public class JoinCountDownLatch {
    private static volatile CountDownLatch countDownLatch = new CountDownLatch(2);

    public static void main(String[] args) throws InterruptedException {
        Thread threadOne = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                countDownLatch.countDown();
            }
            System.out.println("child threadOne over!");
        });

        Thread threadTwo = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                countDownLatch.countDown();
            }
            System.out.println("child threadTwo over!");
        });

        // 启动子线程
        threadOne.start();
        threadTwo.start();

        System.out.println("wait all child thread over!");

        //等待子线程执行完毕，返回(阻塞，等待countDownLatch内部计数器为0)
        countDownLatch.await();

        System.out.println("all child thread over!");
    }
}
