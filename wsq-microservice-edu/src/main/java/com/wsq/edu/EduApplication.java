package com.wsq.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * 只适用于Eureka作为注册中心
 *
 * @author xyzzg
 * @version 1.0
 * @date 2019-8-15 19:46
 */
@SpringBootApplication
@ComponentScan(basePackages={"com.wsq.edu","com.wsq.common"})
@EnableEurekaClient
public class EduApplication {

    public static void main(String[] args) {
        SpringApplication.run(EduApplication.class, args);
    }
}
