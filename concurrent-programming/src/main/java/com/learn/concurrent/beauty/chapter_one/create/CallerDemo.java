package com.learn.concurrent.beauty.chapter_one.create;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Create by liguo on 2021/9/24
 **/
public class CallerDemo {

    public static class CallerTask implements Callable<String> {

        @Override
        public String call() throws Exception {
            return Thread.currentThread() + " I'm use Caller(FutureTask)";
        }
    }

    public static void main(String[] args) {
        FutureTask<String> futureTask = new FutureTask<>(new CallerTask());
        new Thread(futureTask).start();
        try {
            String result = futureTask.get();
            System.out.println(result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
