package com.lm.springmvc.config;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SessionValidateFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //do something for session，like check session timeout……
        HttpServletRequest httprequest = (HttpServletRequest) request;
        HttpServletResponse httpresponse = (HttpServletResponse) response;
        HttpSession session = httprequest.getSession();
        if ((session == null) || (session.getAttribute("user") == null)) {
            String PATH=httprequest.getScheme()+"://"+httprequest.getServerName()+":"+httprequest.getServerPort()+httprequest.getContextPath()+"/";
            httpresponse.sendRedirect(PATH+"login.jsp");
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }

    /**/
}
