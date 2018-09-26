package com.lm.springmvc.controller;

import com.lm.springmvc.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.Map;

@Controller
@SessionAttributes("user")
public class ModelDataController {

    /**
     * 1：ModelAndView忽略
     * 2：@ModelAttribute
     * 3：Map和Model
     * 4：@SessionAttributes ：如果希望在多个请求之间共用某个模型属性数据，则可以在控制器类中标注一个 @SessionAttributes,
     *                        Spring MVC会将模型中对应的属性（通过名称如user）暂存到HttpSession中。
     * */


    //2：@ModelAttribute：将入参加入模型
    @RequestMapping(path = "/handle1")
    public String handle1 (@ModelAttribute("user") User user) {
        user.setName("asafsd");
        return "/user/createSuccess";
    }
    //2：@ModelAttribute:将返回值加入模型
    @RequestMapping(path = "/handle2")
    @ModelAttribute("user")
    public User handle2 () {
        User user = new User();
        user.setName("asafsd");
        return user;
    }

    //3：Map和Model:一般是使用ModelMap或者ExtendedModelMap
    /**
     * SpringMVC在内部使用 一个org.springframework.ui .Model接口存储模型数据，它的功能类似千java.util.Map, 但它比Map易用。
     * org.springframework.ui.ModelMap实现了Map接口，
     * org.springframework.ui.ExtendedModelMap扩展千ModelMap的同时实现了Model接口。
     * */
    @RequestMapping(path = "/handle3")
    public String handle3 (Map map, Model model, ModelMap modelMap, ExtendedModelMap extendedModelMap) {
        modelMap.addAttribute("testAttr","valuel");
        User user= (User)modelMap.get("user");
        user.setName("tom");
        return "/user/createSuccess";
    }


}
