package com.learn.concurrent.actual_combat.chapter_six.three;

import java.util.List;
import java.util.concurrent.*;

/**
 *
 * Waiting for image download with \Future
 *
 * Create by liguo on 2021/11/4
 **/
public abstract class Renderer{
    private final ExecutorService executor;

    Renderer(ExecutorService executor) {
        this.executor = executor;
    }

    void renderPage(CharSequence source) {
        final List<ImageInfo> info = scanForImageInfo(source);
        CompletionService<ImageData> completionService = new ExecutorCompletionService<>(executor);
        for (ImageInfo imageInfo : info) {
            completionService.submit(imageInfo::downloadImage);
        }

        renderText(source);

        try {
            for (int i = 0, n = info.size(); i < n; i++) {
                Future<ImageData> future = completionService.take();
                ImageData data = future.get();
                renderImage(data);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    interface ImageData {
    }

    interface ImageInfo {
        ImageData downloadImage();
    }

    abstract void renderText(CharSequence s);

    abstract List<ImageInfo> scanForImageInfo(CharSequence s);

    abstract void renderImage(ImageData i);
}
