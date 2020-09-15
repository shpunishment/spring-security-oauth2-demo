package com.shpun.oauth2.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

/**
 * @Description:
 * @Author: sun
 * @Date: 2020/4/26 18:13
 */
@Controller
public class Service1Controller {

    @RequestMapping(path = {"/", "/home"})
    public ModelAndView home() {
        return new ModelAndView("home");
    }

    // 需要开启方法级别保护
    @PreAuthorize("hasRole('USER')")
    @RequestMapping("/user")
    public ModelAndView user() {
        return new ModelAndView("user");
    }

    // 需要开启方法级别保护
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping("/admin")
    public ModelAndView admin() {
        return new ModelAndView("admin");
    }

    /**
     * /api 受资源服务器保护，验证token，请求时需要带上
     */
    @GetMapping("/api/getUserInfo")
    @ResponseBody
    public Principal getUserInfo() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * /api2 受Spring Security保护，验证cookie和session，需要先通过登录
     */
    @GetMapping("/api2/getUserInfo")
    @ResponseBody
    public Principal getUserInfo2() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
