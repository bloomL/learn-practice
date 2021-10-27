package com.learn.concurrent.beauty.chapter_eleven;

import java.util.concurrent.*;

/**
 * FutureTask时，使用DiscardPolicy(),DiscardOldestPolicy()时，
 * 在被拒绝的任务的FutureTask上调用get()会导致线程阻塞
 * 重写拒绝策略或使用带超时参数的get()
 * Create by liguo on 2021/10/26
 **/
public class FutureTest {
    private final static ThreadPoolExecutor pool = new ThreadPoolExecutor(1,
            1,
            1,
            TimeUnit.MINUTES,
            new ArrayBlockingQueue<>(1),
            new ThreadPoolExecutor.DiscardPolicy());

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Future futureOne = pool.submit(() -> {
            System.out.println("start runable one");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Future futureTwo = pool.submit(() -> {
            System.out.println("start runable two");
        });

        Future futureThree = null;
        try {
            futureThree = pool.submit(() -> {
                System.out.println("start runable three");
            });
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }

        System.out.println("task one " + futureOne.get());
        System.out.println("task two " + futureTwo.get());
        try {
            System.out.println("task three " + (futureThree == null ? null : futureThree.get(2, TimeUnit.SECONDS)));
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        pool.shutdown();
    }
}
