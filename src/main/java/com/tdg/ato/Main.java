package com.tdg.ato;

import com.tdg.ato.controller.HelloController;
import org.springframework.boot.SpringApplication;

/**
 * Created by DerikZhang on 2017/6/29.
 */
public class Main {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(HelloController.class, args);
//        SpringApplication.run(WorldController.class, args);
    }
}
