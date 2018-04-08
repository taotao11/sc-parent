package com.cloud.config.impl;


import com.cloud.config.entity.UserInfo;
import com.cloud.config.feign.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import vo.user.UserVo;

import java.io.Serializable;

/**
 * 实现security的UserDetailsService，实现自己的验证逻辑
 * @author Administrator
 *
 */
@Service("userDetailsService")
@Slf4j
public class UserDetailServiceImpl implements UserDetailsService, Serializable{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
    private UserService userService;

    @Override
    public UserInfo loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("Res:{}" + username);
        UserVo tUser = userService.getByUserName(username);
        return new UserInfo(tUser);
    }







}
