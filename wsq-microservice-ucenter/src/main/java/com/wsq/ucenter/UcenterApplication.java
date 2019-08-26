package com.wsq.ucenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author xyzzg
 * @version 1.0
 * @date 2019-8-26 21:05
 */
@SpringBootApplication
@ComponentScan(basePackages={"com.wsq.ucenter","com.wsq.common"})
@EnableEurekaClient
public class UcenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(UcenterApplication.class, args);
    }
}
