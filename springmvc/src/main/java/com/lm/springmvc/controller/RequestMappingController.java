package com.lm.springmvc.controller;

import com.lm.springmvc.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/requestMapping/{Pwd}")
public class RequestMappingController {


    /**这三种占位符方式不多说，看看就懂*/
    //1: /user/*/createUser: 匹配/user/aaa/create User/user/bbb/createUser等URL。
    //2: /user/**/createUser: 匹配/user/createUser/user/aaa/bbb/createUser等URL。
    //3: /user/create User??: 匹配/user/createUseraa/user/create U serbb等URL。

    /**
     * 以下三种占位符详细说明
     */
    // 4: /user/{userld} : 匹配 user/123、 user/456等URL。
    // 5: /user/**/{userld}: 匹配 user/aaa/bbb/123、 user/aaa/456等URL。
    // 6: company/{companyId}/user/{ userId} /detail： 匹配 company/123/user/456/detail等URL。
    @RequestMapping("/{userld}")
    public String test4(@PathVariable("userld") String userId, @PathVariable("Pwd") String pwd) {
        return null;
    }

    @RequestMapping("user/**/{userld}")
    public String test5(@PathVariable("userld") String userId) {
        return null;
    }

    @RequestMapping("/company/{userld}/user/{userName}")
    public String test6(@PathVariable("userld") String userid, @PathVariable("userName") String username) {
        return null;
    }

    /**
     * @RequestMapping中的参数详解
     */
    //D "param1": 表示请求须包含名为paraml的请求参数。
    //D "!param1": 表示请求不能包含名为paraml的请求参数。
    //D "param1!=value1": 表示请求包含名为paraml的请求参数，但其值不能为value!。
    //D {"param1=value1 ","param2"}: 表示请求必须包含名为paraml和param2的两个请求参数，且paraml参数的值必须为valuel。

    //使用请求方法及请求参数映射请求
    @RequestMapping(path = "/delete", method = RequestMethod.POST, params = {"userid", "!username"})
    public String delete(String userid) {
        return null;
    }

    //使用报文头映射请求
    //过 @RequestMapping 中的 headers 属性，可以限制客户端发来的请求
    @RequestMapping(path = "/show/{userld}", headers = {"Content-Type=application/json", "Host!=localhost:8080", "Date"})
    public String show(@PathVariable("userld") String userid) {
        return null;
    }

    /**几种典型的处理方法签名*/
    /**
     * @RequestParam、@RequestHeader、@CookieValue中的参数详解
     */

    //1: 请求参数按名称匹配的方式绑定到方法入参中。
    @RequestMapping(path = "/handlel")
    public String handlel(@RequestParam("userName") String userName,
                          @RequestParam("pwd") String pwd,
                          @RequestParam("realName") String realName) {
        return "success";
    }

    //2: 将Cookie值及保文头属性绑定到入参中
    @RequestMapping(path = "/handle2")
    public ModelAndView handle2(@CookieValue("JSESSIONID") String sessionld,
                                @RequestHeader("Accept-Language") String accpetLanguage) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("success");
        mav.addObject("user", new User());
        return mav;
    }

    //3: 请求参数按名称匹配的方式绑定到User的属性中，
    // @RequestBody可有可无，默认转换实体，参数可有可无时，需要配置其中属性。
    // @ResponseBody可有可无，默认转换成json，具体看实体类中配置方式
    @RequestMapping(path = "/handle3")
    public @ResponseBody User handle3(@RequestBody User user) {
        return user;
    }

    //4: 直接桴HTTP请戎对象抟递给处理方法
    @RequestMapping(path="/handle4")
    public String handle4(HttpServletRequest request) {
        request.getSession();
        request.getCookies();
        request.getHeader("Content-type");
        //……
        return "success";
    }

}

