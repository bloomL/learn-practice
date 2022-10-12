package com.learn.redis.deep_adventure;

import com.learn.redis.util.RedisUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import java.util.List;

/**
 * Create by liguo on 2021/12/21
 **/
public class TransactionDemo {
    public static void main(String[] args) {
        try(Jedis jedis = RedisUtil.getJedis()) {
            String userId = "abc";
            String key = keyFor(userId);
            jedis.setnx(key, String.valueOf(5));
            System.out.println(doubleAccount(jedis, userId));
        }
    }

    private static int doubleAccount(Jedis jedis, String userId) {
        String key = keyFor(userId);
        while (true) {
            jedis.watch(key);
            int value = Integer.parseInt(jedis.get(key));
            value *= 2;
            Transaction tx = jedis.multi();
            tx.set(key, String.valueOf(value));
            List<Object> res = tx.exec();
            // 成功
            if (res != null) {
                break;
            }
        }
        // 重新获取余额
        return Integer.parseInt(jedis.get(key));
    }

    private static String keyFor(String userId) {
        return String.format("account_%s", userId);
    }
}
