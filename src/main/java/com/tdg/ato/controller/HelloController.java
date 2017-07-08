package com.tdg.ato.controller;

import com.tdg.ato.mapper.TestMapper;
import com.tdg.ato.model.Test;
import com.tdg.ato.model.Ticker;
import com.tdg.ato.service.TickerService;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 * Created by DerikZhang on 2017/5/31.
 */

@Controller
@EnableAutoConfiguration
@SpringBootApplication
@EnableScheduling
@MapperScan("com.tdg.ato.mapper")
@ComponentScan(basePackages = {"com.tdg.ato.service","com.tdg.ato.schedule"})
@EntityScan(basePackages = {"com.tdg.ato.model"})
@EnableJpaRepositories(basePackages = {"com.tdg.ato.repository"})
public class HelloController {

    static int count = 0;
    Logger logger = LoggerFactory.getLogger(HelloController.class);

    @Autowired
    private TestMapper testMapper;

    @Autowired
    private TickerService tickerService;

    public HelloController(TestMapper testMapper){
        this.testMapper = testMapper;
    }

    @RequestMapping("/")
    @ResponseBody
    String home() {
        return "Hello World!";
    }

    @RequestMapping("/mybatis")
    @ResponseBody
    String testMybatis() {
        count++;
        logger.info("count:" + count);
        Test test = testMapper.selectById(1);
        System.out.println("JDT-test:" + test.toString());
        return "count:" + count + ",name:" + test.toString();
    }

    @RequestMapping("/jpadata")
    @ResponseBody
    String testJpaData() {
        Ticker ticker  = new Ticker();
        System.out.println(ticker.toString());
        Ticker saveTicker = tickerService.save(ticker);
        return saveTicker.toString();
    }

}