package com.cloud.auth.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "res.auth")
@Data
public class AuthServerConfigDto {
	private String clientId;
	private String clientSecret;
	private String scope;
}
