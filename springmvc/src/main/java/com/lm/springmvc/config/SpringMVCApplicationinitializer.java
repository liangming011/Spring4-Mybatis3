package com.lm.springmvc.config;

import org.springframework.core.annotation.Order;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.*;
import java.util.EnumSet;

/**
 * DispatcherServlet的编码定义方式，加载默认组件
 *
 * 简言之， 当DispatcherServlet初始化后，就会自动扫描上下文的Bean, 根据名称或类型匹配的机制
 * 查找自定义的组件，找不到时则使用DispatcherServlet.properties定义的默认组件。
 *
 * 继承结构
 * WebApplicationInitializer > AbstractContextLoaderInitializer > AbstractDispatcherServletInitializer > AbstractAnnotationConfigDispatcherServletInitializer
 * */
@Order
public class SpringMVCApplicationinitializer implements WebApplicationInitializer {

    private static final String SERVLET_NAME = "springmvc";

    private static final long MAX_FILE_UPLOAD_SIZE = 1024 * 1024 * 5; // 5 Mb

    private static final int FILE_SIZE_THRESHOLD = 1024 * 1024; // After 1Mb

    private static final long MAX_REQUEST_SIZE = -1L; // No request size limit

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {

        // 注册springmvc的servlet
        this.addServlet(servletContext);

        // 注册监听器
        this.addListener(servletContext);

        // 注册过滤器
        this.addFilter(servletContext);

        //配置session过期时间120分钟(报错，设置session三种方式：1：web.xml,2:java代码硬编码，3：tomcat的conf/server.xml中)
        //servletContext.setSessionTimeout(120);
    }

    private void addServlet(ServletContext servletContext) {

        // 注册springmvc 的 servlet
        XmlWebApplicationContext appContext = new XmlWebApplicationContext();
        appContext.setConfigLocation("classpath*:/webApplicationContext.xml");

        ServletRegistration.Dynamic servlet = servletContext.addServlet(SERVLET_NAME,new DispatcherServlet(appContext));
        servlet.setLoadOnStartup(1);

        // 添加springmvc 允许访问的Controller后缀 全部通过请用 “/”
        servlet.addMapping("*.html");

        //上传、下载文件操作限制
        //servlet.setMultipartConfig(new MultipartConfigElement(null, MAX_FILE_UPLOAD_SIZE, MAX_REQUEST_SIZE, FILE_SIZE_THRESHOLD));

    }


    private void addListener(ServletContext servletContext) {

        servletContext.setInitParameter("contextConfigLocation","classpath*:/applicationContext.xml");
        servletContext.addListener(ContextLoaderListener.class);
    }

    private void addFilter(ServletContext servletContext) {

        //第一种过滤器 过滤字符集 (使用编码的定义形式)
        FilterRegistration.Dynamic filter = servletContext.addFilter("characterEncodingFilter",new CharacterEncodingFilter());
        filter.setInitParameter("encoding","UTF-8");
        filter.setInitParameter("forceEncoding","true");
        //  -- 第一个参数为null，则是缺省的REQUEST模式
        //  -- 第二个参数false表明在代码设置的filter先于web.xml中设置的filter，true则是先web.xml定义的filter后代码设置的。
        filter.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST, DispatcherType.ASYNC),false,"/*");
        filter.addMappingForServletNames(EnumSet.of(DispatcherType.REQUEST, DispatcherType.ASYNC),false,"springmvc");


        //第二种过滤器 过滤session请求（使用编码的自定义类形式）
        //servletContext.addFilter("sessionValidate",SessionValidateFilter.class);
        //…………

        //第三个过滤器 过滤session请求（使用注解的自定义类形式，spring容器自行加载，不需要配置）
        //servletContext.addFilter("sessionValidate",SessionValidateFilterAnnotation.class);
        //…………
    }

}
