package com.cloud.service;

import com.cloud.entity.SysRole;
import com.cloud.ctl.service.SuperService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author taotao
 * @since 2018-03-29
 */
public interface SysRoleService extends SuperService<SysRole> {
    public List<SysRole> findByUid(long id);
}
