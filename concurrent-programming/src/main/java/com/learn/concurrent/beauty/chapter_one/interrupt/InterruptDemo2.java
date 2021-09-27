package com.learn.concurrent.beauty.chapter_one.interrupt;

/**
 * Create by liguo on 2021/9/26
 **/
public class InterruptDemo2 {
    public static void main(String[] args) throws InterruptedException {
        Thread threadA = new Thread(() -> {
            try {
                System.out.println("thraedA begin sleep 20 seconds");
                Thread.sleep(20000);
                System.out.println("thraedA is awaked");
            } catch (InterruptedException e) {
                System.out.println("currentThread: " + Thread.currentThread() + Thread.currentThread().isInterrupted());
                System.out.println("threadA is interrupted while sleeping");
                return;
            }
            //System.out.println("currentThread: " + Thread.currentThread() + " isInterrupted: " + Thread.currentThread().isInterrupted());
            //System.out.println("currentThread: " + Thread.currentThread() + " isInterrupted: " + Thread.currentThread().interrupted());
            //System.out.println("currentThread: " + Thread.currentThread() + " isInterrupted: " +Thread.currentThread().isInterrupted());
            System.out.println("over normal");
        });
        threadA.start();
        // 确保子线程进入休眠状态
        Thread.sleep(1000);
        threadA.interrupt();
        threadA.join();
        System.out.println("main over");
    }
}
