package com.ue.controller;

import com.ue.pojo.User;
import com.ue.util.LoginUser;
import com.ue.util.MenuUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("menu")
public class MenuController {

    @RequestMapping(path = {"article/{page}", "index/{page}", "listen/{page}"})
    public String toPage(HttpServletRequest request, HttpServletResponse response, @PathVariable String page, Integer sort, String name) throws IOException {
        User user = LoginUser.getLoginUser(request);
        request.setAttribute("user", user);
        MenuUtil.getTopMenus(request);
        if ("index".equals(page)) {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        }
        System.out.println(">>>>" + (sort + 1));

        request.setAttribute("sort", sort);

        if (name != null || !"".equals(name)) {
            request.setAttribute("menuName", name);
        }
        return "mainView";
    }

    @RequestMapping("word/{page}")
    public String wordLearn(HttpServletRequest request, @PathVariable String page, String name) {
        User user = LoginUser.getLoginUser(request);
        request.setAttribute("user", user);
        MenuUtil.getTopMenus(request);
        if (name != null || !"".equals(name)) {
            request.setAttribute("menuName", name);
        }
        if ("newword".equals(page)) {
            return "newWordMain";
        }
        return "wordLearnView";
    }

    @RequestMapping("class/{page}")
    public String classList(HttpServletRequest request, @PathVariable String page, String name, Integer sort) {
        User user = LoginUser.getLoginUser(request);
        request.setAttribute("user", user);
        MenuUtil.getTopMenus(request);
        request.setAttribute("sort", sort);
        if (name != null || !"".equals(name)) {
            request.setAttribute("menuName", name);
        }
        return "courseMain";
    }
}
