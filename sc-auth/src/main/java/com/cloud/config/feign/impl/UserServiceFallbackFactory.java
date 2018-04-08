package com.cloud.config.feign.impl;

import com.cloud.config.feign.UserService;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import vo.user.UserVo;

/**
 * 熔断器
 *
 */
@Service
@Slf4j
public class UserServiceFallbackFactory implements FallbackFactory<UserService>{
    @Override
    public UserService create(Throwable throwable) {
        log.info("进入熔断器");
        return username -> {
            UserVo userVo = new UserVo();
            userVo.setId(-1000L);
            return userVo;
        };
    }
}
