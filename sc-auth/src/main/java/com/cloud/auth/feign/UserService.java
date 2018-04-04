package com.cloud.auth.feign;

import com.cloud.auth.feign.impl.UserServiceFallbackFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import vo.user.UserVo;

/**
 * feign
 * fallbackFactory 熔断机制
 * 远程调用
 */
@FeignClient(value = "sc-gerna",fallbackFactory = UserServiceFallbackFactory.class)
public interface UserService  {

    //查询用户
    @RequestMapping(value = "/userFeign/user/{name}",method = RequestMethod.POST)
    public UserVo getByUserName(@PathVariable("name") String name);
}
