package com.testcy.jedisPoolUtils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisUtils {

    private static volatile JedisPool jedisPool = null;

    private JedisUtils() {

    }

    public static JedisPool getJedisPoolInstance() {

        if (jedisPool == null) {
            synchronized (JedisUtils.class) {
                JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
                jedisPoolConfig.setMaxTotal(100);
                jedisPoolConfig.setMaxIdle(30);
                jedisPoolConfig.setMaxWaitMillis(1000 * 100);
                jedisPoolConfig.setTestOnBorrow(true);
                jedisPool = new JedisPool(jedisPoolConfig, "192.168.200.131", 6379);
            }
        }

        return jedisPool;
    }

    public static void release(JedisPool jedisPool, Jedis jedis) {
        if (jedisPool != null && jedis != null) {
            jedisPool.returnResource(jedis);
        }
    }

}
