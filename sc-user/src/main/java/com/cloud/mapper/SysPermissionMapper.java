package com.cloud.mapper;

import com.cloud.entity.SysPermission;
import com.cloud.ctl.mapper.SuperMapper;

import java.util.List;
import java.util.Set;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author taotao123
 * @since 2018-04-06
 */
public interface SysPermissionMapper extends SuperMapper<SysPermission> {
    public Set<SysPermission> findPermissionsByRole(String role);
}
