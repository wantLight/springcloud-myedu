package com.wsq.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 提供服务端和客户端支持
 * 集中管理各环境的配置文件
 * 配置文件修改之后，可以快速的生效
 * 可以进行版本管理
 * 支持大的并发查询
 * @author xyzzg
 * @version 1.0
 * @date 2019-8-29 14:38
 */
@SpringBootApplication
@EnableConfigServer//作为配置中心服务器
@EnableEurekaClient//注册到Eureka
public class ConfigServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConfigServerApplication.class);
    }
}
