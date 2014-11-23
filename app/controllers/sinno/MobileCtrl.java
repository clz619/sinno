package controllers.sinno;

import org.slf4j.Logger;

import play.Play;

import com.clz619.sinno.configs.LoggerConfigs;
import com.clz619.sinno.mobile.logic.IMobileLogic;
import com.clz619.sinno.mobile.logic.impl.MobileLogic;

import controllers.BaseController;

/**
 * 
 * @project sinno
 * @title MobileCtrl.java
 * @author lizhong.chen
 * @data 2014年11月23日下午7:30:22
 * @description 手机模块 ctrl
 * @tag
 * @version V1.0
 */
public class MobileCtrl extends BaseController {
    private static final Logger LOG = LoggerConfigs.MOBILE_LOG;

    private static IMobileLogic mobileLogic = MobileLogic.getInstance();

    /**
     * @param mobile
     * @return 13738106616:浙江-杭州-移动全球通卡
     */
    public static void searchMobileSegment(String mobile) {
        String mobileProp = mobileLogic.getMobileProp(mobile);

        String retStr = mobile + ":" + mobileProp;

        LOG.info(retStr);

        renderJSON(retStr);
    }
}
