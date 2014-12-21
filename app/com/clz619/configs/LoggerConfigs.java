package com.clz619.configs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @project sinno
 * @title LoggerConfigs.java
 * @author lizhong.chen
 * @data 2014年11月23日下午7:29:22
 * @description 日志配置器
 * @tag
 * @version V1.0
 */
public final class LoggerConfigs {

    // ////////cache/////////
    public static final Logger CACHE_JEDIS_LOG = LoggerFactory.getLogger("jedis");

    // ////////ctrl/////////
    public static final Logger BASE_CTRL_LOG = LoggerFactory.getLogger("basectrl");

    // ////////logic/////////
    public static final Logger MOBILE_LOG = LoggerFactory.getLogger("mobile");

    // ////////job/////////
    public static final Logger JOB_LOG = LoggerFactory.getLogger("job");
}
