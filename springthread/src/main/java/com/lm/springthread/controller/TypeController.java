package com.lm.springthread.controller;

import com.lm.springthread.entity.Type;
import com.lm.springthread.service.TypeService;
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
    void selectType(HttpServletRequest request, @RequestBody Type type) throws Exception {
        LOGGER.debug("selectType的请求参数为：" + type.toString());
        typeService.selectType(type);
    }

    @RequestMapping("deleteType")
    public void deleteType(@RequestBody Type type) throws Exception {
        LOGGER.debug("deleteType的请求参数为：" + type.toString());
        typeService.deleteType(type);
    }

    @RequestMapping("updateType")
    public void updateType(@RequestBody Type type) throws Exception {
        LOGGER.debug("updateType的请求参数为：" + type.toString());
        typeService.updateType(type);
    }

    @RequestMapping("insertType")
    public boolean insertType(@RequestBody Type type) throws Exception {
        LOGGER.debug("insertType的请求参数为：" + type.toString());
        boolean flag = typeService.insertType(type);
        return flag;
    }

}
