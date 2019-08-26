package com.wsq.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Eureka服务器两两互相注册，其它微服务注册到集群中所有的服务器上
 *
 *
 * @author xyzzg
 * @version 1.0
 * @date 2019-8-26 20:27
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplication.class);
    }
}