package com.learn.concurrent.actual_combat.chapter_five.three;

import java.io.File;
import java.io.FileFilter;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 *
 * Producer and consumer tasks in a desktop search application
 *
 * Create by liguo on 2021/11/2
 **/
public class ProducerConsumer {
    static class FileCrawler implements Runnable{
        private final BlockingQueue<File> fileQueue;
        private final FileFilter fileFilter;
        private final File root;

        public FileCrawler(BlockingQueue<File> fileQueue,
                           final FileFilter fileFilter,
                           File root) {
            this.fileQueue = fileQueue;
            this.root = root;
            this.fileFilter = file -> file.isDirectory() || fileFilter.accept(file);
        }

        private boolean alreadyIndexed(File f) {
            return false;
        }

        @Override
        public void run() {
            try {
                craw(root);
            } catch (InterruptedException e) {
                // 恢复被中断状态
                Thread.currentThread().interrupt();
            }
        }

        private void craw(File root) throws InterruptedException {
            File[] entries = root.listFiles(fileFilter);
            if (entries != null) {
                for (File entry: entries) {
                    if (entry.isDirectory()) {
                        craw(entry);
                    } else if(!alreadyIndexed(entry)) {
                        fileQueue.put(entry);
                    }
                }
            }
        }
    }

    static class Indexer implements Runnable{
        private final BlockingQueue<File> queue;

        public Indexer(BlockingQueue<File> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    indexFile(queue.take());
                }
            } catch (InterruptedException e) {
                // 恢复被中断状态
                Thread.currentThread().interrupt();
            }
        }

        public void indexFile(File file) {
            // index the file
        }
    }

    private static final int BOUND = 10;
    private static final int N_CONSUMERS = Runtime.getRuntime().availableProcessors();

    public static void startIndexing(File[] roots) {
        BlockingQueue<File> queue = new LinkedBlockingQueue<>(BOUND);
        FileFilter filter = file -> true;

        for (File root : roots) {
            new Thread(new FileCrawler(queue, filter, root)).start();
        }

        for (int i = 0; i < N_CONSUMERS; i++) {
            new Thread(new Indexer(queue)).start();
        }
    }
}
