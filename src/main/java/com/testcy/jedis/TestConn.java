package com.testcy.jedis;

import com.testcy.jedisPoolUtils.JedisUtils;
import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class TestConn {

    @Test
    public void testJedis01(){

        Jedis jedis = new Jedis("192.168.200.131", 6379);
        System.out.println(jedis.ping());

    }

    @Test
    public void testJedis02(){

        JedisPool jedisPoolInstance = JedisUtils.getJedisPoolInstance();
        Jedis jedis = null;
        try {
            jedis=jedisPoolInstance.getResource();
            jedis.set("aatest", "aatest");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JedisUtils.release(jedisPoolInstance, jedis);
        }

    }

}
