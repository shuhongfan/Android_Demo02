package com.shf.web.servlet;


import javax.servlet.*;
import java.io.IOException;
import java.util.Random;
import java.util.logging.LogRecord;

public class SleepFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            System.out.println("request server..");
            Thread.sleep(new Random().nextInt(15)*100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }
}
