package com.vky;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo
@MapperScan("com.vky.dao")
public class GymnasiumServiceProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(GymnasiumServiceProviderApplication.class, args);
    }

}
