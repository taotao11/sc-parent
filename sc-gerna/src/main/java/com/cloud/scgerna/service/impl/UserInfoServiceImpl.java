package com.cloud.scgerna.service.impl;

import com.cloud.scgerna.entity.UserInfo;
import com.cloud.scgerna.mapper.UserInfoMapper;
import com.cloud.scgerna.service.UserInfoService;
import com.cloud.ctl.service.impl.SuperServiceImpl;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author taotao
 * @since 2018-03-29
 */
@Primary
@Service("userInfoService")
public class UserInfoServiceImpl extends SuperServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

}
