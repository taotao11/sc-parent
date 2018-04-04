package com.cloud.scgerna.service.impl;

import com.cloud.scgerna.entity.SysUserRole;
import com.cloud.scgerna.mapper.SysUserRoleMapper;
import com.cloud.scgerna.service.SysUserRoleService;
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
@Service("sysUserRoleService")
public class SysUserRoleServiceImpl extends SuperServiceImpl<SysUserRoleMapper, SysUserRole> implements SysUserRoleService {

}
