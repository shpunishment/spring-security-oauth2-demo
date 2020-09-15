package com.shpun.oauth2.config;

import com.shpun.oauth2.model.User;
import com.shpun.oauth2.service.RoleService;
import com.shpun.oauth2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @Description: 实现 UserDetailsService ，该接口定义根据用户名获取用户所有信息，包括用户和权限
 * @Author: sun
 * @Date: 2019/12/26 9:59
 */
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getByUsername(username);
        user.setAuthorities(roleService.getByUserId(user.getId()));

        return user;
    }
}
