package com.cloud.config.entity;

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

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private List<String> roleList;

    /**
     * 放置密码 用户名
     * @param tUser
     */
    public UserInfo(UserVo tUser) {
        this.username = tUser.getName().trim();
        this.password = tUser.getPassword().trim();
        roleList = tUser.getRoleList();
    }

    /**
     * 授权
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        //java8循环写法
        roleList.forEach(s -> grantedAuthorityList.add(new SimpleGrantedAuthority(s)));
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
