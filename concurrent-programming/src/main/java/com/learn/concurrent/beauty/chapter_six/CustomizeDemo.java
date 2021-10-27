package com.learn.concurrent.beauty.chapter_six;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.locks.Condition;

/**
 * Create by liguo on 2021/10/8
 **/
public class CustomizeDemo {
    final static CustomizeNonReentrantLock lock = new CustomizeNonReentrantLock();
    final static Condition notFull = lock.newCondition();
    final static Condition notEmpty = lock.newCondition();
    final static Queue<String> queue = new LinkedBlockingDeque<String>();
    volatile static int queueSize = 10;
    public static void main(String[] args) {
        Thread producer = new Thread(() -> {
            lock.lock();
            try {
                // 队列满，等待 不使用if，避免虚假唤醒
                while(queue.size() == queueSize) {
                    notEmpty.await();
                }
                queue.add("hello");
                System.out.println("生产中");
                // 唤醒消费线程
                notFull.signalAll();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });

        Thread consumer = new Thread(() -> {
            lock.lock();
            try {
                while (queue.size() == 0) {
                    notFull.await();
                }
                String element = queue.poll();
                System.out.println("消费了：" + element);
                // 唤醒生产线程
                notEmpty.signalAll();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });

        consumer.start();
        producer.start();

    }
}
