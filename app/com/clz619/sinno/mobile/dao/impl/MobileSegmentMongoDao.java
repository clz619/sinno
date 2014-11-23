/**
 * 
 */
package com.clz619.sinno.mobile.dao.impl;

import play.modules.morphia.Model.MorphiaQuery;

import com.clz619.sinno.mobile.dao.IMobileSegmentDao;
import com.clz619.sinno.mobile.models.MobileSegment;

/**
 * @project sinno
 * @title MobileSegmentDao.java
 * @author lizhong.chen
 * @data 2014年11月23日下午11:05:07
 * @description 电话属性 mongo查询器
 * @tag
 * @version V1.0
 */
public class MobileSegmentMongoDao implements IMobileSegmentDao {
    private MobileSegmentMongoDao() {
    }

    private static class MobileSegmentMongoDaoHolder {
        private static IMobileSegmentDao _instance = new MobileSegmentMongoDao();
    }

    public static IMobileSegmentDao getInstance() {
        return MobileSegmentMongoDaoHolder._instance;
    }

    @Override
    public MobileSegment findByMobilePerfix(String mobilePerfix) {
        MorphiaQuery mq = MobileSegment.find("mobilePart", mobilePerfix).limit(1);
        if (mq == null) {
            return null;
        }
        return mq.get();
    }

}
