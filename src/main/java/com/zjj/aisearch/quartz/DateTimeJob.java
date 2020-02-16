package com.zjj.aisearch.quartz;

import com.zjj.aisearch.test.TestES;
import org.apache.tika.exception.TikaException;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeJob extends QuartzJobBean {

    @Autowired
    TestES testES;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        //获取JobDetail中关联的数据
        String msg = (String) jobExecutionContext.getJobDetail().getJobDataMap().get("msg");
        System.out.println("current time :" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "---" + msg);
        try {
            testES.test5();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TikaException e) {
            e.printStackTrace();
        }
    }
}