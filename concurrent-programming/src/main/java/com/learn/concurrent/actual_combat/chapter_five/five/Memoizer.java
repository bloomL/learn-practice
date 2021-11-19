package com.learn.concurrent.actual_combat.chapter_five.five;

import java.util.Map;
import java.util.concurrent.*;

/**
 * Create by liguo on 2021/11/3
 **/
public class Memoizer<A, V> implements Computable<A, V> {
    private final Map<A, Future<V>> cache = new ConcurrentHashMap<>();
    private final Computable<A, V> c;

    public Memoizer(Computable<A, V> c) {
        this.c = c;
    }
    @Override
    public V compute(A arg) throws InterruptedException {
        while(true) {
            Future<V> future = cache.get(arg);
            if (future == null) {
                Callable<V> eval = () -> c.compute(arg);
                FutureTask<V> fv = new FutureTask<>(eval);
                future = cache.putIfAbsent(arg, fv);
                if (future == null) {
                    future = fv;
                    fv.run();
                }
            }
            try {
                return future.get();
            } catch (CancellationException e) {
                cache.remove(arg,future);
            } catch (ExecutionException e) {
                e.getCause();
            }
        }
    }

}
