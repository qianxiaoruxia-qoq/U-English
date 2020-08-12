package com.ue.filter;

import com.ue.util.LoginUser;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = "/perCenter/*")
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        if (LoginUser.getLoginUser(request) == null) {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        } else {
            filterChain.doFilter(request, response);
        }
        return;
    }

    @Override
    public void destroy() {

    }
}
