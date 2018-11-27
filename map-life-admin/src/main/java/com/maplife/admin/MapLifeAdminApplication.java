package com.maplife.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @auther mosesc
 * @date 11/27/18.
 */
@SpringBootApplication
@MapperScan(basePackages = "com.maplife.mapper")
@EnableSwagger2
public class MapLifeAdminApplication {
    public static void main(String[] args){
        SpringApplication.run(MapLifeAdminApplication.class, args);
    }

}
