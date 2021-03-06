package com.lm.springaop.controller;

import com.lm.springaop.entity.Type;
import com.lm.springaop.service.TypeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("type")
public class TypeController {

    @Autowired
    private TypeService typeService;

    //log4g2的日志使用方式一
    private static final Logger LOGGER = LogManager.getLogger(TypeController.class);

    @RequestMapping("selectType")
    public @ResponseBody
    Type selectType(HttpServletRequest request, @RequestBody Type type) throws Exception {
        LOGGER.debug("selectType的请求参数为：" + type.toString());
        Type type1 = typeService.selectType(type);
        return type1;
    }

    @RequestMapping("deleteType")
    public boolean deleteType(@RequestBody Type type) throws Exception {
        LOGGER.debug("deleteType的请求参数为：" + type.toString());
        boolean flag = typeService.deleteType(type);
        return flag;
    }

    @RequestMapping("updateType")
    public boolean updateType(@RequestBody Type type) throws Exception {
        LOGGER.debug("updateType的请求参数为：" + type.toString());
        boolean flag = typeService.updateType(type);
        return flag;
    }

    @RequestMapping("insertType")
    public boolean insertType(@RequestBody Type type) throws Exception {
        LOGGER.debug("insertType的请求参数为：" + type.toString());
        boolean flag = typeService.insertType(type);
        return flag;
    }

}
