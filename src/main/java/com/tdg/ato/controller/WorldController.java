package com.tdg.ato.controller;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by DerikZhang on 2017/6/29.
 */

@Controller
@EnableAutoConfiguration
@EnableScheduling
@SpringBootApplication
@MapperScan("com.tdg.ato.mapper")
public class WorldController {

    @RequestMapping("/sec")
    @ResponseBody
    String home() {
        return "Hello Sec!";
    }
}
