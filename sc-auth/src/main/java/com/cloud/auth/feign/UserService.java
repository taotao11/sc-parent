package com.cloud.auth.feign;

import vo.user.UserVo;

public interface UserService  {

    //查询用户
    public UserVo getByUserName(String name);
}
