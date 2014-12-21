/**
 * 
 */
package com.clz619.plugin.test;

import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;

import com.clz619.configs.LoggerConfigs;

import play.jobs.Job;
import play.jobs.JobsPlugin;

/**
 * @project sinno
 * @title JobTest.java
 * @author lizhong.chen
 * @data 2014年11月30日下午2:23:43
 * @description TODO
 * @tag
 * @version V1.0
 */
public class JobTest {

    private static final Logger LOG = LoggerConfigs.JOB_LOG;

    public static void jobPlugin() {
        ScheduledThreadPoolExecutor executor = JobsPlugin.executor;
        List<Job> scheduledJobs = JobsPlugin.scheduledJobs;

        if (CollectionUtils.isNotEmpty(scheduledJobs)) {
            for (int i = scheduledJobs.size() - 1; i >= 0; i--) {
                Job job = scheduledJobs.get(i);
                String clazzName = job.getClass().getCanonicalName();
                LOG.info("class:{},invocation:{}，queue size:{}", new Object[] { clazzName, job.invocationType,
                        executor.getQueue().size() });
                if ("com.clz619.job.HelloWorldJob".equals(clazzName)) {
                    boolean flag = executor.remove(job);
                    scheduledJobs.remove(job);
                    LOG.info("executor remove:{}", executor.getTaskCount());
                }

                executor.shutdown();
            }
        }
        LOG.info("executor count:{}", executor.getTaskCount());
    }
}
