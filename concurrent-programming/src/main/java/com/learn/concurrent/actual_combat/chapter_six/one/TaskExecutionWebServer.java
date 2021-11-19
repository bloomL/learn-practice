package com.learn.concurrent.actual_combat.chapter_six.one;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Create by liguo on 2021/11/4
 **/
public class TaskExecutionWebServer {
    private static final int NTHREADS = 100;
    private static final Executor POOL = Executors.newFixedThreadPool(NTHREADS);

    public static void main(String[] args) throws IOException {
        ServerSocket socket = new ServerSocket(80);
        while(true) {
            final Socket connection = socket.accept();
            Runnable task = () -> handleRequest(connection);
            POOL.execute(task);
        }
    }

    private static void handleRequest(Socket connection) {
        // request-handling logic here
    }
}
