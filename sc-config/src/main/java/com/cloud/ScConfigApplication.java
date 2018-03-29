package com.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 远程配置中心
 *
 */
@EnableEurekaClient//注册
@EnableConfigServer//配置中心
@SpringBootApplication
public class ScConfigApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScConfigApplication.class, args);
	}
}
