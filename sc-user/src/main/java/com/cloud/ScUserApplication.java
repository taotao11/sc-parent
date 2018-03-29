package com.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 用户模块
 */
@EnableEurekaClient//注册
@SpringBootApplication
public class ScUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScUserApplication.class, args);
	}
}
