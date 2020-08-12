package com.ue.util;


import com.ue.pojo.User;

import javax.servlet.http.HttpServletRequest;


public class LoginUser {

    private static final String LOGIN_USER = "loginUser";

    public static void setLoginUser(HttpServletRequest request, User user) {
        request.getSession().setAttribute(LOGIN_USER, user);
    }

    public static User getLoginUser(HttpServletRequest request) {
        return (User) request.getSession().getAttribute(LOGIN_USER);
    }

    public static void loginOut(HttpServletRequest request) {
        request.getSession().setAttribute(LOGIN_USER, null);
    }

}
