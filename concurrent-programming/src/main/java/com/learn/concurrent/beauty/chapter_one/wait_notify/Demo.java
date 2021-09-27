package com.learn.concurrent.beauty.chapter_one.wait_notify;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Create by liguo on 2021/9/24
 **/
public class Demo {
    private static final Queue queue = new LinkedBlockingDeque();
    private static int MAX_SIZE = 100;

    public static void producer() {
        synchronized (queue) {
            while (queue.size() == MAX_SIZE) {
                try {
                    // 挂起当前线程，释放queue上的锁，
                    queue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            queue.add("6");
            queue.notifyAll();
        }
    }

    public static void consumer() {
        synchronized (queue) {
            while (queue.size() == 0 ) {
                try {
                    // 挂起当前线程，释放queue上的锁，
                    queue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            queue.element();
            queue.notifyAll();
        }
    }
}
