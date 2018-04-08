package com.cloud.service;

import com.cloud.entity.SysRole;
import com.cloud.ctl.service.SuperService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author taotao123
 * @since 2018-04-06
 */
public interface SysRoleService extends SuperService<SysRole> {
    /**
     * 通过用户id 查询 角色信息
     * @param uid
     * @return
     */
    public List<String> findRolesByUid(long uid);

}
