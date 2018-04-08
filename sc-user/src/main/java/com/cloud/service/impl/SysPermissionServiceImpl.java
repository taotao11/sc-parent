package com.cloud.service.impl;

import com.cloud.entity.SysPermission;
import com.cloud.mapper.SysPermissionMapper;
import com.cloud.service.SysPermissionService;
import com.cloud.ctl.service.impl.SuperServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author taotao123
 * @since 2018-04-06
 */
@Service
public class SysPermissionServiceImpl extends SuperServiceImpl<SysPermissionMapper, SysPermission> implements SysPermissionService {
    @Autowired
    private SysPermissionMapper sysPermissionMapper;

    /**
     * 通过角色查资源信息
     * @param role
     * @return
     */
    public Set<SysPermission> findPermissionsByRole(String role){
        return sysPermissionMapper.findPermissionsByRole(role);
    }
}
