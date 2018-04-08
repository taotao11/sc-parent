package com.cloud.service.impl;

import com.cloud.entity.UserInfo;
import com.cloud.mapper.UserInfoMapper;
import com.cloud.service.UserInfoService;
import com.cloud.ctl.service.impl.SuperServiceImpl;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author taotao123
 * @since 2018-04-06
 */
//spring 一个接口别多个实现类实现时 添加 Primary 注解
@Primary
@Service
public class UserInfoServiceImpl extends SuperServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

}
