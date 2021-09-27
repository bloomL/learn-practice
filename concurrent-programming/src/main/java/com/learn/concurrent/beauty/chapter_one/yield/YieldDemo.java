package com.learn.concurrent.beauty.chapter_one.yield;

/**
 * Create by liguo on 2021/9/26
 **/
public class YieldDemo {

    public static class YieldTask implements Runnable {

        public YieldTask() {
            new Thread(this).start();
        }

        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                if (i % 5 ==0) {
                    System.out.println(Thread.currentThread() + " yield CPU");
                    // 让出CPU执行权，放弃时间片，进行下一轮调度
                    Thread.yield();
                }
            }
            System.out.println(Thread.currentThread() + " is over");
        }
    }

    public static void main(String[] args) {
        new YieldTask();
        new YieldTask();
        new YieldTask();
    }

}
