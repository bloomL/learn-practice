package com.learn.concurrent.beauty.chapter_six;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * Create by liguo on 2021/10/8
 * 自定义不可重入的独占锁
 **/
public class CustomizeNonReentrantLock implements Lock, Serializable {

    private static class Sync extends AbstractQueuedSynchronizer {

        /**
         *  是否被持有 0-无 1-被持有
         * @return
         */
        @Override
        protected boolean isHeldExclusively() {
            return getState() == 1;
        }


        /**
         * 尝试获取锁
         * @param acquires
         * @return
         */
        @Override
        public boolean tryAcquire(int acquires) {
            assert  acquires == 1;
            if (compareAndSetState(0, 1)) {
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        /**
         * 尝试释放锁
         * @param releases
         * @return
         */
        @Override
        protected boolean tryRelease(int releases) {
            assert releases == 1;
            if (getState() == 0 ) {
                throw new IllegalMonitorStateException();
            }
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }

        /**
         * 条件变量接口
         * @return
         */
        Condition newCondition() {
            return new ConditionObject();
        }
    }

    private final Sync sync = new Sync();

    @Override
    public void lock() {
        sync.acquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1, unit.toNanos(time));
    }

    @Override
    public void unlock() {
        sync.release(1);
    }

    @Override
    public Condition newCondition() {
        return sync.newCondition();
    }
}
