package com.lm.springmvc.config;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;

/*@WebFilter(
        filterName = "sessionValidate",
        urlPatterns = {"/deal/*"},
        servletNames = {"springmvc"},
        dispatcherTypes = {DispatcherType.ASYNC,DispatcherType.REQUEST,DispatcherType.ERROR},
        initParams={
                @WebInitParam(name="uri", value="/deal/"),
                @WebInitParam(name="backToUrl", value="/"),
                @WebInitParam(name="debug", value="true"),
                @WebInitParam(name="loginUrl", value="/login")
        }

)*/
public class SessionValidateFilterAnnotation implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //同上SessionValidateFilter
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
