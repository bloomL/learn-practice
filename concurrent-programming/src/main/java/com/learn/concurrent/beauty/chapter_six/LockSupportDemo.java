package com.learn.concurrent.beauty.chapter_six;

import java.util.concurrent.locks.LockSupport;

/**
 * Create by liguo on 2021/10/8
 **/
public class LockSupportDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread child = new Thread(() -> {
            //LockSupport.unpark(Thread.currentThread());
            System.out.println("child thread begin park");
            // 没有许可证时，挂起自己；否则返回
            LockSupport.park();
            System.out.println("child thread unpark");
        });
        child.start();
        Thread.sleep(1000);
        System.out.println("main thread begin unpark");
        // unpark 让线程持有许可证，park方法返回
        LockSupport.unpark(child);
        // child.interrupt();
    }
}
