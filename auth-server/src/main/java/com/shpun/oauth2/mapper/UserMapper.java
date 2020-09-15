package com.shpun.oauth2.mapper;

import com.shpun.oauth2.model.User;
import org.apache.ibatis.annotations.Param;

/**
 * @Description:
 * @Author: sun
 * @Date: 2019/12/26 9:45
 */
public interface UserMapper {

    User getByUsername(@Param("username") String username);

}
