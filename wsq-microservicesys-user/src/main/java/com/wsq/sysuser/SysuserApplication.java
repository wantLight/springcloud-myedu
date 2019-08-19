package com.wsq.sysuser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author xyzzg
 * @version 1.0
 * @date 2019-8-19 20:50
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class SysuserApplication {

    public static void main(String[] args) {
        SpringApplication.run(SysuserApplication.class, args);
    }
}
