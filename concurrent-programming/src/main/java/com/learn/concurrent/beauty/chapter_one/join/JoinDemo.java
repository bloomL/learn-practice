package com.learn.concurrent.beauty.chapter_one.join;

/**
 * Create by liguo on 2021/9/26
 **/
public class JoinDemo {

    public static void main(String[] args) throws InterruptedException {
        Thread threadOne = new Thread(() -> {
            try {
                // 不参与CPU调度，但拥有监视器资源，如锁持有
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("threadOne over");
        });

        Thread threadTwo = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("threadTwo over");
        });

        threadOne.start();
        threadTwo.start();

        System.out.println("wait all child thread over");

        // 主线程调用threadOne的join()方法，被阻塞，等待threadOne执行完毕
        threadOne.join();
        threadTwo.join();
        System.out.println("all child thread over");
    }
}
