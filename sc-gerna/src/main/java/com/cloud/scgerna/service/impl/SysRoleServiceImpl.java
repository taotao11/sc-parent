package com.cloud.scgerna.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.cloud.scgerna.entity.SysRole;
import com.cloud.scgerna.entity.SysUserRole;
import com.cloud.scgerna.mapper.SysRoleMapper;
import com.cloud.scgerna.service.SysRoleService;
import com.cloud.ctl.service.impl.SuperServiceImpl;
import com.cloud.scgerna.service.SysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author taotao
 * @since 2018-03-29
 */
@Service("sysRoleService")
public class SysRoleServiceImpl extends SuperServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysUserRoleService sysUserRoleService;

    /**
     * 查询用户对应的角色
     * @param id
     * @return
     */
    public List<SysRole> findByUid(long id){
        List<Long> roleIds = new ArrayList<Long>();
        //查询关联表
        List<SysUserRole> list = sysUserRoleService.selectList(new EntityWrapper<SysUserRole>()
                .eq("uid",id));
        //java8 新特性
        list.forEach((SysUserRole sys) -> roleIds.add(sys.getRoleId()));
        List<SysRole> sysRoleList = sysRoleService.selectBatchIds(roleIds);
        return sysRoleList;
    }
}
