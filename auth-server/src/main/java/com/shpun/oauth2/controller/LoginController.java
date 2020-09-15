package com.shpun.oauth2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Description:
 * @Author: sun
 * @Date: 2020/4/9 16:17
 */
@Controller
public class LoginController {

    @RequestMapping("/loginPage")
    public ModelAndView loginPage() {
        return new ModelAndView("loginPage");
    }

}
