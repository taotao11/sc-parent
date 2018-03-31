package com.cloud.service.impl;

import com.cloud.entity.UserInfo;
import com.cloud.mapper.UserInfoMapper;
import com.cloud.service.UserInfoService;
import com.cloud.ctl.service.impl.SuperServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author taotao
 * @since 2018-03-29
 */
@Service("userInfoService")
public class UserInfoServiceImpl extends SuperServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

}
