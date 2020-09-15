package com.shpun.oauth2.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

/**
 * @Description:
 * @Author: sun
 * @Date: 2019/12/26 9:23
 */
@Data
public class Role implements GrantedAuthority {

    private Integer id;

    // authority 需要加上前缀 ROLE_
    private String name;

    @Override
    public String getAuthority() {
        return name;
    }
}
