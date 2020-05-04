package com.tower.reback;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.tower.reback.dao")
public class RebackApplication {

    public static void main(String[] args) {
        SpringApplication.run(RebackApplication.class, args);
    }

}
