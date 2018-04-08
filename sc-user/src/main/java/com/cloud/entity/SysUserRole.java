package com.cloud.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author taotao123
 * @since 2018-04-06
 */
@TableName("sys_user_role")
public class SysUserRole extends Model<SysUserRole> {

    private static final long serialVersionUID = 1L;

    private Long uid;
    @TableField("role_id")
    private Long roleId;


    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    @Override
    protected Serializable pkVal() {
        return this.uid;
    }

    @Override
    public String toString() {
        return "SysUserRole{" +
        ", uid=" + uid +
        ", roleId=" + roleId +
        "}";
    }
}
