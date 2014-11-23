package com.clz619.sinno.mobile.logic.impl;

import org.apache.commons.lang.StringUtils;

import play.modules.morphia.Model.MorphiaQuery;

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

    private static IMobileLogic _instance = new MobileLogic();

    private MobileLogic() {
    }

    public static IMobileLogic getInstance() {
        return _instance;
    }

    @Override
    public String getMobileProp(String mobile) {
        if (StringUtils.isEmpty(mobile) || mobile.length() != 11) {
            return "未知";
        }
        String m = mobile.substring(0, 7);
        MorphiaQuery mq = MobileSegment.find("mobilePart", m).limit(1);
        if (mq == null) {
            return "未知";
        }
        MobileSegment ms = mq.get();
        if (ms == null) {
            return "未知";
        }

        return ms.getProvince() + "-" + ms.getCity() + "-" + ms.getMobileType();
    }

}
