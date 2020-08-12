package com.ue.filter;


import com.ue.pojo.User;
import com.ue.util.LoginUser;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebFilter(urlPatterns = {"/user/*","/cust/*","/info/*","/share/*"})
public class PermFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        doFilter((HttpServletRequest) request, (HttpServletResponse) response, chain);
    }

    @Override
    public void destroy() {
    }

    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        // /ssm/user/main
        String uri = request.getRequestURI();
        // /ssm
        String contextPath = request.getContextPath();
        // /user/save
        String value = uri.substring(contextPath.length());
        User user = LoginUser.getLoginUser(request);
//        Role role = user.getRoles().get(0);
//        List<Perm> perms = role.getPerms();
//        Set<String> set = new HashSet<>();
//        for (Perm perm : perms) {
//            set.add(perm.getValue());
//        }

//        if (!set.contains(value)) {
//            System.out.println("没有权限：" + value);
//            response.setContentType("text/html;charset=UTF-8");
//            String result = new ObjectMapper().writeValueAsString(Result.error("没有权限"));
//            response.getWriter().write(result);
//            response.getWriter().write("<script>alert('没有权限！')</script>");
//            return;
//        }
/*
        if (!user.hasPerm(value)) {
            response.setContentType("text/html;charset=UTF-8");
            if (value.endsWith("/html")) {
                response.getWriter().write("<h5>没有权限！</h5>");
            } else {
                String result = new ObjectMapper().writeValueAsString(Result.error("没有权限"));
                response.getWriter().write(result);
            }
            return;
        }

        chain.doFilter(request, response);*/
    }
}
