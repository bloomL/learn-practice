package com.learn.redis.deep_adventure;

import com.learn.redis.util.RedisUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;

/**
 * 滑动时间窗口
 * 简单限流
 * 但这种方案也有缺点，因为它要记录时间窗口内所有的行为记录，
 * 如果这个量很大，比如限定 60s 内操作不得超过 100w 次这样的参数，它是不适合做这样的限流的，因为会消耗大量的存储空间
 * Create by liguo on 2021/12/20
 **/
public class SimpleRateLimiter {
    private Jedis jedis;

    public SimpleRateLimiter(Jedis jedis) {
        this.jedis = jedis;
    }

    public boolean isActionAllowed(String userId, String actionKey, int period, int maxCount) {
        String key = String.format("hist:%s:%s", userId, actionKey);
        // 当前时间戳
        long nowTs = System.currentTimeMillis();
        // 几个连续的 Redis 操作都是针对同一个 key 的，使用 pipeline 可以显著提升Redis 存取效率。
        Pipeline pipeline = jedis.pipelined();
        pipeline.multi();
        pipeline.zadd(key, nowTs, "" + nowTs);
        // 删除存储在排序集中的所有元素，其分数介于 min 和 max （含）之间
        pipeline.zremrangeByScore(key, 0, nowTs - period * 1000L);
        Response<Long> count = pipeline.zcard(key);
        pipeline.expire(key, period + 1L);
        pipeline.exec();
        pipeline.close();
        return count.get() <= maxCount;
    }

    public static void main(String[] args) {
        try(Jedis jedis = RedisUtil.getJedis()) {
            SimpleRateLimiter limiter = new SimpleRateLimiter(jedis);
            for (int i = 0; i < 20; i++) {
                System.out.println(limiter.isActionAllowed("lg", "reply", 60, 5));
            }
        }
    }
}
