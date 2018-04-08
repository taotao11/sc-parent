package com.cloud.feign;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *
 * 角色信息提供者
 */
@RestController
@RequestMapping("roleFeign")
public class SysRole {
    //查询用户角色信息
    @RequestMapping("role/{roles}")
    public List<SysRole> getRoleByNames(@PathVariable("roles") String roles){
        //split(";") 字符串按照";"截取分数组
        String[] roleArr = roles.split(";");
        for (String role: roleArr){
            //todo
        }
        return null;
    }

}
