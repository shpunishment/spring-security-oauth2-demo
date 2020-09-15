package com.shpun.oauth2.service.impl;

import com.shpun.oauth2.mapper.UserMapper;
import com.shpun.oauth2.model.User;
import com.shpun.oauth2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author: sun
 * @Date: 2019/12/26 9:55
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getByUsername(String username) {
        return userMapper.getByUsername(username);
    }
}
