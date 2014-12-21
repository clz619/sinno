/**
 * 
 */
package com.clz619.job;

import org.slf4j.Logger;

import com.clz619.configs.LoggerConfigs;
import com.clz619.plugin.test.JobTest;

import play.jobs.Every;
import play.jobs.Job;
import play.jobs.OnApplicationStart;

/**
 * @project sinno
 * @title HelloWorldJob.java
 * @author lizhong.chen
 * @data 2014年11月30日下午1:17:42
 * @description TODO
 * @tag
 * @version V1.0
 */
// @OnApplicationStart(async = true)
@Every("5s")
public class HelloWorldDoneJob extends Job {

    private static final Logger LOG = LoggerConfigs.JOB_LOG;

    private static int i = 10000;

    @Override
    public void doJob() throws Exception {
        LOG.info("done i={}", i++);

        // JobTest.jobPlugin();
    }
}
