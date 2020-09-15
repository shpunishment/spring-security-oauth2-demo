package com.shpun.oauth2.service;


import com.shpun.oauth2.model.Role;

import java.util.List;

/**
 * @Description:
 * @Author: sun
 * @Date: 2019/12/26 9:55
 */
public interface RoleService {

    List<Role> getByUserId(Integer userId);

}
