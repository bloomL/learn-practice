package com.learn.concurrent.actual_combat.chapter_six.three;

import java.util.*;
import java.util.concurrent.*;

/**
 *
 * Requesting travel quotes under a time budget
 *
 * Create by liguo on 2021/11/5
 **/
public class TimeBudget {
    private static ExecutorService executor = Executors.newCachedThreadPool();

    public List<TravelQuote> getRankedTravelQuotes(TravelInfo travelInfo, Set<TravelCompany> companies,
                                                   Comparator<TravelQuote> ranking, long time, TimeUnit unit) throws InterruptedException {
        List<QuoteTask> tasks = new ArrayList<>();
        for (TravelCompany company : companies) {
            tasks.add(new QuoteTask(company, travelInfo));
        }
        List<Future<TravelQuote>> futures = executor.invokeAll(tasks, time, unit);
        List<TravelQuote>  quotes = new ArrayList<>(tasks.size());
        Iterator<QuoteTask> taskIter = tasks.iterator();
        for (Future<TravelQuote> future : futures) {
            QuoteTask task = taskIter.next();
            try {
                quotes.add(future.get());
            } catch (ExecutionException e) {
               quotes.add(task.getFailureQuote(e.getCause()));
            } catch (CancellationException e) {
                quotes.add(task.getTimeoutQuote(e));
            }
        }
        quotes.sort(ranking);
        return quotes;
    }

    class QuoteTask implements Callable<TravelQuote> {
        private final TravelCompany company;
        private final TravelInfo travelInfo;

        public QuoteTask(TravelCompany company, TravelInfo travelInfo) {
            this.company = company;
            this.travelInfo = travelInfo;
        }

        TravelQuote getFailureQuote(Throwable t) {
            return null;
        }

        TravelQuote getTimeoutQuote(CancellationException e) {
            return null;
        }

        @Override
        public TravelQuote call() throws Exception {
            return company.solicitQuote(travelInfo);
        }
    }

    interface TravelCompany {
        TravelQuote solicitQuote(TravelInfo travelInfo) throws Exception;
    }

    interface TravelQuote {
    }

    interface TravelInfo {
    }

}
