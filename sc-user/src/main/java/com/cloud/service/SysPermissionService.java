package com.cloud.service;

import com.cloud.entity.SysPermission;
import com.cloud.ctl.service.SuperService;

import java.util.Set;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author taotao123
 * @since 2018-04-06
 */
public interface SysPermissionService extends SuperService<SysPermission> {
    /**
     * 通过角色查资源信息
     * @param role
     * @return
     */
    public Set<SysPermission> findPermissionsByRole(String role);

}
