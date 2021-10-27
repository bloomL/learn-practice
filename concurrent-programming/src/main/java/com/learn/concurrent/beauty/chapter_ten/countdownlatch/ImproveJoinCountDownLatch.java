package com.learn.concurrent.beauty.chapter_ten.countdownlatch;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * Create by liguo on 2021/10/21
 **/
public class ImproveJoinCountDownLatch {
    private static CountDownLatch countDownLatch = new CountDownLatch(2);

    public static void main(String[] args) throws InterruptedException {
        ThreadFactory nameFactory = new ThreadFactoryBuilder().setNameFormat("countDownDemo-pool-%d").build();
        ExecutorService pool = new ThreadPoolExecutor(2,
                2,
                0L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(),
                nameFactory,
                new ThreadPoolExecutor.AbortPolicy());
        pool.submit(() ->{
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                countDownLatch.countDown();
            }
            System.out.println("child threadOne over!");
        });
        pool.submit(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                countDownLatch.countDown();
            }
            System.out.println("child threadOne over!");
        });
        System.out.println("wait all child thread over!");
        //等待子线程执行完毕，返回(阻塞，等待countDownLatch内部计数器为0)
        countDownLatch.await();
        System.out.println("all child thread over!");
        // 若不调用，jvm会一直运行，线程池资源不会被释放；shutdown使核心线程终止
        pool.shutdown();
    }
}
