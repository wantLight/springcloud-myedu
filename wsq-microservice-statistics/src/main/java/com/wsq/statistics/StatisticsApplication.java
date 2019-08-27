package com.wsq.statistics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author xyzzg
 * @version 1.0
 * @date 2019-8-27 21:41
 */
@SpringBootApplication
@ComponentScan(basePackages={"com.wsq.statistics","com.wsq.common"})
@EnableEurekaClient
public class StatisticsApplication {
    public static void main(String[] args) {
        SpringApplication.run(StatisticsApplication.class, args);
    }
}
