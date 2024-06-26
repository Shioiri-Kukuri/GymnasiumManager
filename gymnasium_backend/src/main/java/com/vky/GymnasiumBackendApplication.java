package com.vky;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


@EnableDubbo
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class GymnasiumBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(GymnasiumBackendApplication.class, args);
    }

}
