package com.cloud.scgerna.service.impl;

import com.cloud.scgerna.entity.SysRolePermission;
import com.cloud.scgerna.mapper.SysRolePermissionMapper;
import com.cloud.scgerna.service.SysRolePermissionService;
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
@Service("sysRolePermissionService")
public class SysRolePermissionServiceImpl extends SuperServiceImpl<SysRolePermissionMapper, SysRolePermission> implements SysRolePermissionService {

}
