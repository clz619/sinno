package com.clz619.sinno.mobile.logic.impl;

import org.apache.commons.lang.StringUtils;

import play.modules.morphia.Model.MorphiaQuery;
import redis.clients.jedis.Jedis;

import com.clz619.cache.redis.JedisPoolManager;
import com.clz619.configs.RedisConfigs;
import com.clz619.sinno.mobile.dao.IMobileSegmentDao;
import com.clz619.sinno.mobile.dao.impl.MobileSegmentMongoDao;
import com.clz619.sinno.mobile.logic.IMobileLogic;
import com.clz619.sinno.mobile.models.MobileSegment;

/**
 * 
 * @project sinno
 * @title MobileLogic.java
 * @author lizhong.chen
 * @data 2014年11月23日下午7:02:53
 * @description 手机号逻辑
 * @tag
 * @version V1.0
 */
public class MobileLogic implements IMobileLogic {

    private static IMobileSegmentDao mobileSegmentMongoDao = MobileSegmentMongoDao.getInstance();

    private static final String TAG = "mobile_";

    private static JedisPoolManager jedisPoolManager = JedisPoolManager.getInstance();

    private static Jedis jedis = null;

    private MobileLogic() {
        jedis = jedisPoolManager.getJedis(RedisConfigs.HOST, RedisConfigs.PORT, RedisConfigs.PASSWORD);
        jedis.connect();
    }

    private static class MobileLogicHolder {
        private static IMobileLogic _instance = new MobileLogic();

    }

    public static IMobileLogic getInstance() {
        return MobileLogicHolder._instance;
    }

    @Override
    public String getMobileProp(String mobile) {
        if (StringUtils.isEmpty(mobile) || mobile.length() != 11) {
            return "未知";
        }
        String m = mobile.substring(0, 7);

        String key = TAG + m;

        String mobileProp = jedis.get(key);

        if (StringUtils.isEmpty(mobileProp)) {

            MobileSegment ms = mobileSegmentMongoDao.findByMobilePerfix(m);

            mobileProp = ms.getProvince() + ms.getCity() + "-" + ms.getMobileType();

            jedis.set(key, mobileProp);
        }
        return mobileProp;
    }
}
