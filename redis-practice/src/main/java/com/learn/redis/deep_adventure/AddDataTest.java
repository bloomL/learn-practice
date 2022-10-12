package com.learn.redis.deep_adventure;

import com.learn.redis.util.RedisUtil;
import redis.clients.jedis.Jedis;

/**
 * Create by liguo on 2021/12/21
 **/
public class AddDataTest {
    public static void main(String[] args) {
        try(Jedis jedis = RedisUtil.getJedis()) {
            int max = 10000;
            for (int i = 0; i < max; i++) {
                jedis.set(String.format("key%d", i), String.valueOf(i));
            }
        }
    }
}
