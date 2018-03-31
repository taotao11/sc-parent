package com.cloud.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * 认证中心
 *
 */
@SpringBootApplication
@EnableEurekaClient
@EnableResourceServer
@EnableFeignClients
@ComponentScan(basePackages = {"com.cloud.auth","config"})
public class ScAuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(ScAuthApplication.class, args);
    }
}
