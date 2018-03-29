package com.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 注册中心
 *
 */
//注册中心
@EnableEurekaServer
@SpringBootApplication
public class ScEurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScEurekaApplication.class, args);
	}
}
