package com.maplife.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(scanBasePackages = "com.maplife")
@MapperScan(basePackages = "com.maplife.mapper")
@EnableSwagger2
//@EnableTransactionManagement
@EnableAsync
@EnableCaching
public class MapLifeApplication {
    public static void main(String[] args){
        SpringApplication.run(MapLifeApplication.class, args);
    }
}
