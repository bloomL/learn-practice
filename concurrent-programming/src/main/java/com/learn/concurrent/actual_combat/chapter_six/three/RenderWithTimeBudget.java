package com.learn.concurrent.actual_combat.chapter_six.three;

import java.util.concurrent.*;

/**
 *
 * Fetching an advertisement with a time budget
 *
 * Create by liguo on 2021/11/5
 **/
public class RenderWithTimeBudget {
    private static final Ad DEFAULT_AD = new Ad();
    private static final long TIME_BUDGET = 1000;
    private static final ExecutorService exec = Executors.newCachedThreadPool();

    Page renderPageWithAd() throws InterruptedException{
        long endNano = System.nanoTime() + TIME_BUDGET;
        Future<Ad> future = exec.submit(Ad::new);
        // Render the page while waiting for the ad
        Page page = renderPageBody();
        Ad ad;
        try {
            long timeLeft = endNano - System.nanoTime();
            ad = future.get(timeLeft, TimeUnit.NANOSECONDS);
        } catch (ExecutionException e) {
            ad = DEFAULT_AD;
        } catch (TimeoutException e) {
            ad = DEFAULT_AD;
            future.cancel(true);
        }
        page.setAd(ad);
        return page;
    }

    Page renderPageBody() { return new Page(); }

    static class Ad {
    }

    static class Page {
        public void setAd(Ad ad) { }
    }
}
