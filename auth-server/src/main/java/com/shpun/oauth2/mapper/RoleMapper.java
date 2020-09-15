package com.shpun.oauth2.mapper;

import com.shpun.oauth2.model.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description:
 * @Author: sun
 * @Date: 2019/12/26 9:45
 */
public interface RoleMapper {

    List<Role> getByUserId(@Param("userId") Integer userId);

}
