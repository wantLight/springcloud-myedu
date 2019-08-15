package com.wsq.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author xyzzg
 * @version 1.0
 * @date 2019-8-15 19:46
 */
@SpringBootApplication
@ComponentScan(basePackages={"com.wsq.edu","com.wsq.common"})
public class EduApplication {

    public static void main(String[] args) {
        SpringApplication.run(EduApplication.class, args);
    }
}
