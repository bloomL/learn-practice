package com.learn.concurrent.actual_combat.chapter_three;

/**
 * Create by liguo on 2021/10/28
 **/
public class NoVisibility {
    private  static boolean ready;
    private  static int number;

    public static void main(String[] args) throws InterruptedException {
        Thread readThread = new Thread(() -> {
            while (!ready) {
                Thread.yield();
                System.out.println(number);
            }
        });
        readThread.start();
        number = 42;
        ready = true;
    }
}
