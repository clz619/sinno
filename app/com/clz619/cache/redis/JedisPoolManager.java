package com.clz619.cache.redis;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.clz619.configs.LoggerConfigs;
import com.clz619.configs.RedisConfigs;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 
 * @project sinno
 * @title JedisPoolManager.java
 * @author lizhong.chen
 * @data 2014年11月23日下午9:59:46
 * @description jedis pool manager
 * @tag
 * @version V1.0
 */
public class JedisPoolManager {

    private static final Logger LOG = LoggerConfigs.CACHE_JEDIS_LOG;

    private static final int RETRY_NUM = 3;
    /**
     * jedis pool 的 map 集合
     */
    private static Map<String, JedisPool> pools = new HashMap<String, JedisPool>();

    private JedisPoolManager() {
    }

    /**
     * 类级的内部类，也就是静态的成员式内部类，该内部类的实例与外部类的实例 没有绑定关系，而且只有被调用到时才会装载，从而实现了延迟加载。
     */
    private static class JedisPoolManagerHolder {
        /**
         * 静态初始化器，由JVM来保证线程安全
         */
        private static JedisPoolManager _instance = new JedisPoolManager();
    }

    /**
     * 当getInstance方法第一次被调用的时候，它第一次读取
     * JedisPoolManagerHolder._instance，导致JedisPoolManagerHolder类得到初始化
     * ；而这个类在装载并被初始化的时候，会初始化它的静
     * 态域，从而创建JedisPoolManager的实例，由于是静态的域，因此只会在虚拟机装载类的时候初始化一次，并由虚拟机来保证它的线程安全性。
     * 这个模式的优势在于，getInstance方法并没有被同步，并且只是执行一个域的访问，因此延迟初始化并没有增加任何访问成本。
     */
    public static JedisPoolManager getInstance() {
        return JedisPoolManagerHolder._instance;
    }

    /**
     * 获取redis pool
     * 
     * @param ip
     * @param port
     * @return
     */
    private static JedisPool getPool(String ip, int port) {
        String key = genrateKey(ip, port);
        JedisPool pool = null;

        if (pools.containsKey(key)) {
            pool = pools.get(key);
        } else {
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxTotal(RedisConfigs.MAX_ACTIVE);
            config.setMaxIdle(RedisConfigs.MAX_IDLE);
            config.setMaxWaitMillis(RedisConfigs.MAX_WAIT);
            config.setTestOnBorrow(RedisConfigs.TEST_ON_BORROW);
            config.setTestOnReturn(RedisConfigs.TEST_ON_TRUE);
            try {
                pool = new JedisPool(config, ip, port, RedisConfigs.TIME_OUT);
            } catch (Exception e) {
                LOG.error(e.getMessage(), e);
            }
        }

        return pool;
    }

    /**
     * 生成key
     * 
     * @param ip
     * @param port
     * @return
     */
    private static String genrateKey(String ip, int port) {
        return ip + ":" + port;
    }

    /**
     * 获取Redis实例.
     * 
     * @return Redis工具类实例
     */
    public Jedis getJedis(String ip, int port, String password) {
        Jedis jedis = null;
        int count = 0;
        do {
            try {
                jedis = getPool(ip, port).getResource();
                jedis.auth(password);
            } catch (Exception e) {
                LOG.error("get redis master1 failed!", e);
                // 销毁对象
                getPool(ip, port).returnBrokenResource(jedis);
            }
            count++;
        } while (jedis == null && count < RETRY_NUM);
        return jedis;
    }

    /**
     * 释放redis实例到连接池.
     * 
     * @param jedis
     *            redis实例
     */
    public void closeJedis(Jedis jedis, String ip, int port) {
        if (jedis != null) {
            JedisPool pool = getPool(ip, port);
            if (pool != null) {
                pool.returnResource(jedis);
            }
        }
    }

    /**
     * 销毁连接池中的所有连接
     * 
     * @param ip
     * @param port
     */
    public void destoryJedis(String ip, int port) {
        JedisPool pool = getPool(ip, port);
        if (null != pool) {
            pool.destroy();
            pools.remove(genrateKey(ip, port));
        }
    }
}
