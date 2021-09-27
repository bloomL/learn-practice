package com.learn.concurrent.beauty.chapter_one.interrupt;

/**
 * Create by liguo on 2021/9/26
 **/
public class InterruptDemo3 {
    public static void main(String[] args) throws InterruptedException {
        Thread threadA = new Thread(() -> {
            for (;;) {

            }
        });
        threadA.start();
        // threadA 中断状态
        threadA.interrupt();
        System.out.println("isInterrupted: " + threadA.isInterrupted());
        //System.out.println("currentThread: " + Thread.currentThread());
        // interrupted() -> currentThread().isInterrupted(true) threadA.interrupted() 获取当前线程的中断状态 等价于 Thread.interrupted();
        System.out.println("isInterrupted: " + threadA.interrupted());
        System.out.println("isInterrupted: " + Thread.interrupted());
        System.out.println("isInterrupted: " + threadA.isInterrupted());
        threadA.join();
        System.out.println("main over");
    }
}
