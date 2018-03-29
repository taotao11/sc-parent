package com.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * 服务网关
 *
 */
@EnableZuulProxy//服务网关
@EnableEurekaClient//注册
@EnableFeignClients//远程调用
@SpringBootApplication
public class ScZuulApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScZuulApplication.class, args);
	}
}
