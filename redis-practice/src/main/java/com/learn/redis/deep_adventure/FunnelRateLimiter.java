package com.learn.redis.deep_adventure;

import java.util.HashMap;
import java.util.Map;

/**
 * 漏斗限流
 * 无法保证整个过程的原子性。从 hash 结构中取值，然后在内存里运算，再回填到 hash 结构，这三个过程无法原子化，
 * 意味着需要进行适当的加锁控制。而一旦加锁，就意味着会有加锁失败，加锁失败就需要选择重试或者放弃
 * redis原子限流操作：cl.throttle
 * Create by liguo on 2021/12/20
 **/
public class FunnelRateLimiter {
    static class Funnel {
        /**
         * 漏斗容量
         */
        int capacity;
        /**
         * 漏嘴流水速率
         */
        float leakingRate;
        /**
         * 剩余空间
         */
        int leftQuota;
        /**
         * 上次漏水时间
         */
        long leakingTs;

        public Funnel(int capacity, float leakingRate) {
            this.capacity = capacity;
            this.leakingRate = leakingRate;
            this.leftQuota = capacity;
            this.leakingTs = System.currentTimeMillis();
        }

        /**
         * 核心
         * 每次灌水前都会被调用以触发漏水
         */
        void makeSpace() {
            long nowTs = System.currentTimeMillis();
            // 距离上次漏水时间过了多久
            long passedTs = nowTs - leakingTs;
            int passedCapacity = (int)(passedTs * leakingRate);
            // 腾出空间太小，最小单位1
            if (passedCapacity < 1) {
                return;
            }
            this.leftQuota += passedCapacity;
            this.leakingTs = nowTs;
            if (this.leftQuota > this.capacity) {
                this.leftQuota = this.capacity;
            }
        }

        boolean watering(int quota) {
            makeSpace();
            // 剩余空间是否足够
            if (this.leftQuota >= quota) {
                this.leftQuota = quota;
                return true;
            }
            return false;
        }
    }

    private Map<String, Funnel> funnelMap = new HashMap<>();

    public boolean isActionAllowed(String userId, String actionKey, int capacity, float leakingRate) {
        String key = String.format("%s:%s", userId, actionKey);
        Funnel funnel = funnelMap.get(key);
        if (funnel == null) {
            funnel = new Funnel(capacity, leakingRate);
            funnelMap.put(key, funnel);
        }
        return funnel.watering(1);
    }
}
