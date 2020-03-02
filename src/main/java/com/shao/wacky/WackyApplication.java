package com.shao.wacky;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@MapperScan("com.shao.wacky.mapper")
@SpringBootApplication
public class WackyApplication {

    public static void main(String[] args) {
        SpringApplication.run(WackyApplication.class, args);
    }

}
