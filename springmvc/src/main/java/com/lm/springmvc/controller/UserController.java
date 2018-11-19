package com.lm.springmvc.controller;

import com.lm.springmvc.entity.User;
import com.lm.springmvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.portlet.ModelAndView;

@Controller                         /*注解指定UserController为处理请求的控制器*/
@RequestMapping("/user")            /*处理来自/user URI的请求*/
public class UserController {

    @Autowired                      /*注入业务层bean*/
    private UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.GET)    /*返回一个ModelAndView类型的逻辑视图名,请求方式*/
    public ModelAndView register(User user) {
        userService.createUser(user);
        ModelAndView mav = new ModelAndView();
        mav.addObject("user", null);
        mav.setViewName("/WEB-INF/view/register");                     /*这个没用，他自行指定/user/register.jsp,不知道为啥*/
        return mav;
    }

    @RequestMapping(value = "/login")    /*返回一个String类型的逻辑视图名*/
    public String login() {
        return "/login";
    }

}
