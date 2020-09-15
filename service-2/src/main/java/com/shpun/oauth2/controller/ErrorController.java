package com.shpun.oauth2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Description:
 * @Author: sun
 * @Date: 2020/5/3 21:37
 */
@Controller
public class ErrorController {

    @RequestMapping("/401")
    public ModelAndView error401() {
        return new ModelAndView("error/401");
    }

}
