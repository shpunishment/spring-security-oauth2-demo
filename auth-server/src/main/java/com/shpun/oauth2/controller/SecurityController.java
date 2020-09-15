package com.shpun.oauth2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

/**
 * @Description:
 * @Author: sun
 * @Date: 2020/4/28 15:05
 */
@RequestMapping("/security")
@RestController
public class SecurityController {

    @Autowired
    private TokenStore tokenStore;

    /**
     * 获取当前用户信息，供客户端获取
     * @param principal
     * @return
     */
    @GetMapping("/getUserInfo")
    @ResponseBody
    public Principal getUserInfo(Principal principal) {
        return principal;
    }


    /**
     * 获取当前用户信息，测试资源服务器
     * @return
     */
    @GetMapping("/api/getUserInfo")
    @ResponseBody
    public Principal getUserInfo2() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * 撤销token
     * @param request
     */
    @GetMapping("/revokeToken")
    public void revokeToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null) {
            String tokenValue = authHeader.replace("Bearer", "").trim();
            OAuth2AccessToken accessToken = tokenStore.readAccessToken(tokenValue);
            tokenStore.removeAccessToken(accessToken);
        }
    }

}
