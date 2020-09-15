package com.shpun.oauth2.service;


import com.shpun.oauth2.model.User;

/**
 * @Description:
 * @Author: sun
 * @Date: 2019/12/26 9:55
 */
public interface UserService {

    User getByUsername(String username);

}
