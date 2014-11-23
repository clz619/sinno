/**
 * 
 */
package com.clz619.sinno.mobile.dao;

import com.clz619.sinno.mobile.models.MobileSegment;

/**
 * @project sinno
 * @title IMobileSegmentDao.java
 * @author lizhong.chen
 * @data 2014年11月23日下午11:03:17
 * @description TODO
 * @tag
 * @version V1.0
 */
public interface IMobileSegmentDao {

    /**
     * 根据手机前缀查询手机数据
     * 
     * @param mobilePerfix
     * @return
     */
    public MobileSegment findByMobilePerfix(String mobilePerfix);
}
