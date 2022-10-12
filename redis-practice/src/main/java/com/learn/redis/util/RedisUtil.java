package com.learn.redis.util;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.exceptions.JedisConnectionException;

/**
 * Create by liguo on 2021/12/20
 **/
@Slf4j
public class RedisUtil {
    private static JedisPool jedisPool = null;
    /**
     * 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
     */
    private static final boolean TEST_ON_BORROW = false;


    private static void initialPool() {
        try {
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxTotal(100);
            config.setMaxIdle(5);
            config.setMaxWaitMillis(10000);
            config.setTestOnBorrow(TEST_ON_BORROW);
            /*jedisPool = new JedisPool(config, redisProperties.getIp(), redisProperties.getPort(), redisProperties.getTimeout(),
                    StrUtil.isBlank(redisProperties.getPassword()) ? null : redisProperties.getPassword(), redisProperties.getDatabase());*/
            jedisPool = new JedisPool(config, "127.0.0.1", 6379, 10000, "redis", 2);
        } catch (Exception e) {
            log.error("First create JedisPool error : " + e);
        }
    }

    /**
     * 在多线程环境同步初始化
     */
    private static synchronized void poolInit() {
        if (null == jedisPool) {
            initialPool();
        }
    }

    public synchronized static Jedis getJedis() {
        poolInit();
        Jedis jedis = null;
        try {
            if (null != jedisPool) {
                jedis = jedisPool.getResource();
            }
        } catch (JedisConnectionException e) {
            // 重试一次
            jedis = jedisPool.getResource();
        } catch (Exception e) {
            throw new RuntimeException("获取redis实例失败，请检查redis配置", e);
        }
        return jedis;
    }
}
