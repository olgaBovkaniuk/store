package com.gmail.olgabovkaniuk.app.servlets.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;

public class LogFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("LogFilter init!");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        System.out.println("#INFO " + new Date() + " URL = " + req.getRequestURL() + (req.getQueryString() == null ? "" : "?" + req.getQueryString()));
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        System.out.println("LogFilter destroy.");
    }
}
