package com.cloud.auth.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import vo.user.UserVo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 接收密码 姓名 角色
 *
 */
@Data
public class UserInfo implements UserDetails,Serializable {
    private String username;
    private String password;
    private List<String> roleList;

    public UserInfo(UserVo tUser) {
        this.username = tUser.getAccount().trim();
        this.password = tUser.getPassword().trim();
        roleList = tUser.getRoleList();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        //java8循环写法
        roleList.forEach(role ->grantedAuthorityList.add(new SimpleGrantedAuthority(role)));
        return grantedAuthorityList;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
