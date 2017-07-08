package com.tdg.ato.schedule;

import com.tdg.ato.service.OkcoinApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by DerikZhang on 2017/6/29.
 */
@Component
public class ScheduledTasks {

    public final static long ONE_Minute =  60 * 1000;
    public final static SimpleDateFormat dateFormat= new SimpleDateFormat("yyyyMMddHHmmss");
    Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);
    @Autowired
    private OkcoinApiService okcoinApiService;

//    @Scheduled(fixedDelay=ONE_Minute)
//    public void fixedDelayJob(){
//        logger.info(dateFormat.format(new Date())+" >>fixedDelay执行....");
//    }
//
//    @Scheduled(fixedRate=ONE_Minute)
//    public void fixedRateJob(){
//        logger.info(dateFormat.format(new Date())+" >>fixedRate执行....");
//    }

    @Scheduled(cron="* 0/5 * * * ?")
    public void cronJob(){
        logger.info(dateFormat.format(new Date())+">>LTC:" + okcoinApiService.getTickerInfo());

    }
}
