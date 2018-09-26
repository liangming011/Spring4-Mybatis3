package com.lm.springmvc.controller;

import com.lm.springmvc.entity.User;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;

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

    /**几种典型的处理方法签名,以及多种入参类型*/
    /**
     * @RequestParam、@RequestHeader、@CookieValue中的参数详解
     */

    //1: 请求参数按名称匹配的方式绑定到方法入参中。 @RequestParam
    @RequestMapping(path = "/handlel")
    public String handlel(
            //required = false表示此参数可以不传
            @RequestParam(value = "userName",required = false) String userName,
            //required = true表示此参数不可为空,值为空则默认为aaa
            @RequestParam(value = "pwd",required = true,defaultValue = "aaa") String pwd,
            //value = "realName"指的是前端的参数名，string1值得是后端的参数名
            @RequestParam(value = "realName") String string1) {
        return "success";
    }

    //2: 将Cookie值及保文头属性绑定到入参中。@CookieValue、@RequestHeader
    @RequestMapping(path = "/handle2")
    public ModelAndView handle2(
            //可绑定Cookie中的某个值
            @CookieValue("JSESSIONID") String sessionld,
            ////可绑定Header报文头中的某个属性值
            @RequestHeader("Accept-Language") String accpetLanguage) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("success");
        mav.addObject("user", new User());
        return mav;
    }

    //3: 请求参数按名称匹配的方式绑定到User的属性中.
    // @RequestBody, 可有可无，默认转换注入实体中，需要配置其中属性。
    // @ResponseBody，实体默认转换成json返回，具体看情况如String、byte[]等，具体看实体类中配置方式。
    //HttpMessageConverter<T>接口负责这种转换
    @RequestMapping(path = "/handle3")
    public @ResponseBody User handle3(@RequestBody User user) {
        return user;
    }

    //4: 直接桴HTTP请戎对象抟递给处理方法
    @RequestMapping(path="/handle4")
    public String handle4(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        request.getSession();
        request.getCookies();
        request.getHeader("Content-type");
        User user = (User) session.getAttribute("user");
        //……
        return response.encodeRedirectURL("success");
    }

    //5: （不常用）直接桴HTTP请求对象抟递给处理方法
    //WebRequest和NativeWebRequest, 它们也允许作为处理类的入参， 通过这些代理类可访问请求对象的任何信息，
    @RequestMapping(path = "/handle5")
    public String handle5(WebRequest request, NativeWebRequest nativeWebRequest) {
        User user = new User();
        request.getParameter("");
        request.setAttribute("user",user,1);
        nativeWebRequest.getParameter("");
        nativeWebRequest.setAttribute("user",user,1);
        return "success";
    }

    //6: 使用I/O对象作为入参
    @RequestMapping(path = "/handle6")
    public String handle6(OutputStream os) throws IOException {
        Resource res = new ClassPathResource("/image.jpg");//读取文件
        FileCopyUtils.copy(res.getInputStream(),os);//将图片写到输出流中

        return "success";
    }
}