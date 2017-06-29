package com.tdg.ato.controller;

import com.tdg.ato.mapper.TestMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 * Created by DerikZhang on 2017/5/31.
 */

@Controller
@EnableAutoConfiguration
@EnableScheduling
@SpringBootApplication
@MapperScan("com.tdg.ato.mapper")
public class HelloController {

    static int count = 0;
    Logger logger = LoggerFactory.getLogger(HelloController.class);

    @Autowired
    private TestMapper testMapper;

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
        return "count:" + count + ",name:" + testMapper.selectById(1);
    }

}