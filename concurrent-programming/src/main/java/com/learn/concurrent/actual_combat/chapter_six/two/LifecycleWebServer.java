package com.learn.concurrent.actual_combat.chapter_six.two;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * Web server with shutdown support
 *
 * Create by liguo on 2021/11/4
 **/
public class LifecycleWebServer {
    private final ExecutorService executor = Executors.newCachedThreadPool();

    public void start() throws IOException {
        ServerSocket socket = new ServerSocket(80);
        while (! executor.isShutdown()) {
            try {
                final Socket conn = socket.accept();
                executor.execute(() -> handleRequest(conn));
            } catch (RejectedExecutionException e) {
                if (!executor.isShutdown()) {
                    log("task submission rejected", e);
                }
            }
        }
    }

    public void stop() {
        executor.shutdown();
    }

    private void log(String msg, Exception e) {
        Logger.getAnonymousLogger().log(Level.WARNING, msg, e);
    }

    void handleRequest(Socket connection) {
        Request req = readRequest(connection);
        if (isShutdownRequest(req)) {
            stop();
        } else {
            dispatchRequest(req);
        }
    }

    interface Request{}

    private Request readRequest(Socket socket) {
        return null;
    }

    private void dispatchRequest(Request request) {

    }

    private boolean isShutdownRequest(Request request) {
        return false;
    }
}
