package com.cloud.scgerna.feign;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.cloud.scgerna.entity.SysRole;
import com.cloud.scgerna.entity.UserInfo;
import com.cloud.scgerna.service.SysRoleService;
import com.cloud.scgerna.service.UserInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import vo.user.UserVo;

import java.util.ArrayList;
import java.util.List;


/**
 * 用户信息提供者
 */
@RestController
@RequestMapping("/userFeign")
public class UserInfoFeign {
    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private SysRoleService sysRoleService;
    /**
     * 查询用户
     *
     * @return
     */
    @RequestMapping(value = "/user/{name}",method = RequestMethod.POST)
    public UserVo getByUserName(@PathVariable("name") String name){
        System.out.println(name);
        List<UserInfo> infoList = userInfoService.selectList(new EntityWrapper<UserInfo>());
        System.out.println(infoList);
        //查询用户
        UserInfo info = userInfoService.selectList(new EntityWrapper<UserInfo>()
                .eq("name",name)).get(0);
        UserVo rUserVo = new UserVo();
        //对象复制
        BeanUtils.copyProperties(info,rUserVo);
        //查询角色
       List<SysRole> sysRoleList = sysRoleService.findByUid(info.getUid());
       List<String> roles = new ArrayList<>();

       sysRoleList.forEach(role -> {if (role.getRole() != null && !role.getRole().equals("")) roles.add(role.getRole()); });
       rUserVo.setRoleList(roles);
      return rUserVo;
    }
    public static void main(String[] args){
        new UserInfoFeign().userInfoService.selectList(new EntityWrapper<>());
    }
}
