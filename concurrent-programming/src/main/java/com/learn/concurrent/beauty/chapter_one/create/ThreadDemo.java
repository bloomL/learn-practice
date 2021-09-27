package com.learn.concurrent.beauty.chapter_one.create;

/**
 * Create by liguo on 2021/9/24
 **/
public class ThreadDemo {

    public static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println(" I'm use Thread !");
        }
    }

    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.start();
    }
}
