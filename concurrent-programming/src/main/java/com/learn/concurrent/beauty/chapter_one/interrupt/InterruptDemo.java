package com.learn.concurrent.beauty.chapter_one.interrupt;

/**
 * interrupt, 中断标志为true,线程继续执行，若有wait,sleep,join,则抛异常interruptException
 * isInterrupted,是否被中断
 * interrupted，当前线程是否被中断，如果被中断，返回true并清除中断标志
 * Create by liguo on 2021/9/26
 **/
public class InterruptDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread child = new Thread(() ->{
            // 当前线程中断退出循环
            while(!Thread.currentThread().isInterrupted()) {
                System.out.println(Thread.currentThread() + " hello");
            }
        });
        child.start();
        Thread.sleep(100);

        System.out.println("main interrupt child");
        child.interrupt();

        // 主线程阻塞，等待child执行完毕
        child.join();
        System.out.println("main is over");
    }
}
