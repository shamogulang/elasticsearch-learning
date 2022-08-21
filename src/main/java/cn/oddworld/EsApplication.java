package cn.oddworld;

import cn.oddworld.test.anno.MyFeignScanner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Copyright 2021 ChinaMobile Info. Tech Ltd. All rights reserved.
 *
 * @Author: jeffchan
 * @DATE: 2021/11/22 21:34
 **/
@SpringBootApplication
@MyFeignScanner("cn.oddworld")
public class  EsApplication {

    public static void main(String[] args) {
        SpringApplication.run( EsApplication.class, args);
    }
}
