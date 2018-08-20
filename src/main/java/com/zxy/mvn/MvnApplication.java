package com.zxy.mvn;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.zxy.mvn.dataobject.mapper")
public class MvnApplication {

    public static void main(String[] args) {
        SpringApplication.run(MvnApplication.class, args);
    }
}
