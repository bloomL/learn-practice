package com.learn.redis.properties;

import lombok.Data;

/**
 * Create by liguo on 2021/12/20
 **/
//@ConfigurationProperties(prefix = RedisProperties.PREFIX)
//@Component
@Data
public class RedisProperties {
    /**
     * Redis服务器IP
     */
    private String ip;
    /**
     * Redis的端口号
     */
    private int port;
    /**
     * 访问密码
     */
    private String password;
    /**
     * 可用连接实例的最大数目，默认值为8；
     * 如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
     */
    private int maxActive = 100;
    /**
     * 控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
     */
    private int maxIdle = 5;
    /**
     * 等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException；
     */
    private int maxWait = 10000;
    /**
     * 超时时间
     */
    private int timeout = 10000;
    /**
     * 缓存库序号
     */
    private int database;
}
