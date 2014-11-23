package controllers;

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

    @Before
    public static void log() {

    }
}
