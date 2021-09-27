package com.learn.concurrent.beauty.chapter_one.sleep;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Create by liguo on 2021/9/26
 **/
public class SleepDemo {

    private static final Lock LOCK = new ReentrantLock();

    public static void main(String[] args) {
        Thread thraedA = new Thread(() -> {
            LOCK.lock();
            try {
                System.out.println("threadA is in sleep");
                Thread.sleep(10000);
                System.out.println("threadA is in awaked");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                LOCK.unlock();
            }
        });

        Thread thraedB = new Thread(() -> {
            LOCK.lock();
            try {
                System.out.println("threadB is in sleep");
                Thread.sleep(10000);
                System.out.println("threadB is in awaked");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                LOCK.unlock();
            }
        });

        thraedA.start();
        thraedB.start();
    }
}
