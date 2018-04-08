package com.cloud.config.config;

import constant.CommonConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * 认证符服务器的逻辑实现
 * 
 * @author Administrator
 *
 */
//@Configuration
//@EnableAuthorizationServer
public class AuthServerConfiguration extends AuthorizationServerConfigurerAdapter {
	// dto
	@Autowired
	private AuthServerConfigDto authServerConfigDto;
	// 认证管理器
	@Autowired
	private AuthenticationManager authenticationManager;
	// redis连接工厂
	@Autowired
	private RedisConnectionFactory redisConnectionFactory;
	//sercurity认证所实现的service
	@Autowired
	private UserDetailsService userDetailsService;

	/**
	 * 配置认证服务器的ClientId和ClientSecret以及scope
	 * @param clients
	 * @throws Exception
	 */
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		System.out.println(authServerConfigDto.getClientId());
		clients
				.inMemory()
				.withClient(authServerConfigDto.getClientId())
				.secret(authServerConfigDto.getClientSecret())
				.authorizedGrantTypes("refresh_token", "password")//用户密码生成策略
				.scopes(authServerConfigDto.getScope());
	}

	/**
	 * 认证服务器终端配置，设置token的存储模式(redis),token生成的策略jwt
	 * 配置认证管理器，认证的userDetailsService
	 * @param //endpoints
	 * @throws Exception
	 */
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints
				.tokenStore(new RedisTokenStore(redisConnectionFactory))
				.accessTokenConverter(jwtAccessTokenConverter())
				.authenticationManager(authenticationManager)
				.userDetailsService(userDetailsService);
	}

	/**
	 * jwt
	 * @return
	 */
	@Bean
	public JwtAccessTokenConverter jwtAccessTokenConverter() {
		JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
		jwtAccessTokenConverter.setSigningKey(CommonConstant.JWT_SIGN_KEY);
		return jwtAccessTokenConverter;
	}

	/**
	 * 密码验证
	 * @return
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
		//允许表单认证
		oauthServer.allowFormAuthenticationForClients();
	}

}
