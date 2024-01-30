package com.getjavajob.training.bezmenovp.socialnetwork.web;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter("/*")
public class UTF8Filter implements Filter {

    private String encoding;

    @Override
    public void init(FilterConfig config) {
        this.encoding = config.getInitParameter("encoding");
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        if (encoding != null) {
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
        }
        chain.doFilter(request, response);
    }

}
