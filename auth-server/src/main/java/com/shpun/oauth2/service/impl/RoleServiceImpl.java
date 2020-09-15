package com.shpun.oauth2.service.impl;

import com.shpun.oauth2.mapper.RoleMapper;
import com.shpun.oauth2.model.Role;
import com.shpun.oauth2.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @Author: sun
 * @Date: 2019/12/26 9:56
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> getByUserId(Integer userId) {
        return roleMapper.getByUserId(userId);
    }
}
