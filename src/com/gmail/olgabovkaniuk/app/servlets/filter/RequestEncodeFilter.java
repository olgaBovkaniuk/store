package com.gmail.olgabovkaniuk.app.servlets.filter;

import javax.servlet.*;
import java.io.IOException;

public class RequestEncodeFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) {
        System.out.println("Request response encoder Filter object has been created.");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        chain.doFilter(request, response);
        response.setContentType("text/html; charset=UTF-8");
    }

    @Override
    public void destroy() {

    }
}
