package com.learn.concurrent.beauty.chapter_one.wait_notify;

/**
 * Create by liguo on 2021/9/24
 **/
public class Demo2 {

    private static volatile Object resourceA = new Object();
    private static volatile Object resourceB = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread threadA = new Thread(() -> {
            // 获取共享资源A的监视器锁
            synchronized (resourceA) {
                System.out.println("threadA get A lock");
                synchronized (resourceB) {
                    System.out.println("threadA get B lock");
                    System.out.println("ThreadA release A lock");
                    try {
                        // 只会释放共享变量A上的锁，B锁仍然在
                        resourceA.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread threadB = new Thread(() -> {
            synchronized (resourceA) {
                try {
                    Thread.sleep(1000);
                    System.out.println("threadB get A lock");
                    System.out.println("threadB try get B lock");
                    // 被阻塞，
                    synchronized (resourceB) {
                        System.out.println("threadB get B lock");
                        System.out.println("threadB release A lock");
                        resourceA.wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        threadA.start();
        threadB.start();
        threadA.join();
        threadB.join();
        System.out.println("main over");
    }
}
