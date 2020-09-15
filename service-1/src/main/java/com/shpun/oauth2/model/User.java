package com.shpun.oauth2.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description:
 * @Author: sun
 * @Date: 2019/12/26 9:20
 */
@Data
public class User implements UserDetails, Serializable {

    private Integer id;

    private String username;

    private String password;

    private List<Role> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    // 是否账户没有过期
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    // 是否账户没有锁定
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    // 是否密码没有过期
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    // 是否账户可用
    public boolean isEnabled() {
        return true;
    }
}
