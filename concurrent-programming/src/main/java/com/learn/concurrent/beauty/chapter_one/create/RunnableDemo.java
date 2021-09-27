package com.learn.concurrent.beauty.chapter_one.create;

/**
 * Create by liguo on 2021/9/24
 **/
public class RunnableDemo {

    public static class RunnableTask implements Runnable {

        @Override
        public void run() {
            System.out.println(Thread.currentThread() + " I'm use Runnable !");
        }
    }

    public static void main(String[] args) {
        RunnableTask task = new RunnableTask();
        new Thread(task).start();
        new Thread(task).start();
    }
}
