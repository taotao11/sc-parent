package com.cloud.service.impl;

import com.cloud.entity.SysRole;
import com.cloud.mapper.SysRoleMapper;
import com.cloud.service.SysRoleService;
import com.cloud.ctl.service.impl.SuperServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author taotao123
 * @since 2018-04-06
 */
@Service
public class SysRoleServiceImpl extends SuperServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {
    @Autowired
    private SysRoleMapper sysRoleMapper;
    /**
     * 查询角色信息
     * @param uid
     * @return
     */
    @Override
    public List<String> findRolesByUid(long uid) {
        System.out.println(sysRoleMapper.findRolesByUid(1));
        List<String> roles = sysRoleMapper.findRolesByUid(1);

        return (roles.size() == 0) ? null : roles;
    }
}
