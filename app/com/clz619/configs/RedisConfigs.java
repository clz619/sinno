/**
 * 
 */
package com.clz619.configs;

import play.Play;

/**
 * @project sinno
 * @title RedisConfigs.java
 * @author lizhong.chen
 * @data 2014年11月23日下午10:23:47
 * @description redis 配置
 * @tag
 * @version V1.0
 */
public final class RedisConfigs {

    public static final String HOST = Play.configuration.getProperty("redis.host", "sinno");

    public static final int PORT = Integer.valueOf(Play.configuration.getProperty("redis.port", "6379"));

    public static final String PASSWORD = Play.configuration.getProperty("redis.password", "sinno");
    /**
     * 最大激活数
     */
    public static final int MAX_ACTIVE = Integer.valueOf(Play.configuration.getProperty("redis.max.active", "64"));

    public static final int MAX_IDLE = Integer.valueOf(Play.configuration.getProperty("redis.max.idle", "16"));

    public static final int MAX_WAIT = Integer.valueOf(Play.configuration.getProperty("redis.max.wait", "3000"));

    public static final boolean TEST_ON_BORROW = Boolean.valueOf(Play.configuration.getProperty("redis.test.on.borrow",
            "true"));

    public static final boolean TEST_ON_TRUE = Boolean.valueOf(Play.configuration.getProperty("redis.test.on.return",
            "true"));

    public static final int TIME_OUT = Integer.valueOf(Play.configuration.getProperty("redis.time.out", "10000"));

}
