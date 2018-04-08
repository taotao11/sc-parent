package com.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.ComponentScan;

/**
 * 服务网关
 *
 */
@EnableZuulProxy//服务网关
@EnableEurekaClient//注册
@EnableFeignClients//远程调用
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ComponentScan(basePackages = {"com.cloud","config"})
public class ScZuulApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScZuulApplication.class, args);
	}
}
