package com.ue.controller;

import com.ue.service.MenuService;
import com.ue.util.MenuUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MainController {

    @Autowired
    private MenuService menuService;

    @RequestMapping(path = {"/","index"})
    public String index(HttpServletRequest request){
        MenuUtil.getMainMenus(request);
        return "index";
    }

    @RequestMapping(path = {"/404"})
    public String error404(HttpServletRequest request){
        MenuUtil.getMainMenus(request);
        return "404";
    }
}
