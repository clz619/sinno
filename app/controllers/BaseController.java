package controllers;

import org.slf4j.Logger;

import com.clz619.configs.LoggerConfigs;

import play.mvc.Before;
import play.mvc.Controller;

/**
 * 
 * @project sinno
 * @title BaseController.java
 * @author lizhong.chen
 * @data 2014年11月23日下午7:21:22
 * @description 基础base ctrl
 * @tag
 * @version V1.0
 */
public class BaseController extends Controller {
    private static final Logger LOG = LoggerConfigs.BASE_CTRL_LOG;

    @Before
    public static void log() {
        LOG.info("remoteAddr:{},sessionId:{},request:{}", new Object[] { request.remoteAddress, session.getId(),
                request.url });
    }
    
    
}
