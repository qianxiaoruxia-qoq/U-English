package com.ue.util;

import com.ue.pojo.Menu;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class MenuUtil {
    public static void getMainMenus(HttpServletRequest request) {
        List<Menu> allmenus = (List<Menu>) request.getServletContext().getAttribute("allmenus");

        List<Menu> menus = new ArrayList<>();
        List<Menu> secondMenus = new ArrayList<>();
        List<Menu> picMenus = new ArrayList<>();

        for (Menu menu : allmenus) {
            if (menu.getPId() == 0 && menu.getGrade() == 1) {
                menus.add(menu);
            } else if (menu.getPId() == 0 && menu.getGrade() == 2) {
                secondMenus.add(menu);
            } else if (menu.getPId() == 0 && menu.getGrade() == 3) {
                picMenus.add(menu);
            }
        }

        for (Menu menu : menus) {
            List<Menu> subMenus = new ArrayList<>();
            for (Menu subMenu : allmenus) {
                if (subMenu.getPId() == menu.getId() && subMenu.getGrade() == 1) {
                    subMenus.add(subMenu);
                }
            }
            menu.setSubMenus(subMenus);
        }
        request.setAttribute("menus", menus);
        request.setAttribute("secondMenus", secondMenus);
        request.setAttribute("picMenus", picMenus);
    }

    public static void getTopMenus(HttpServletRequest request) {
        List<Menu> allmenus = (List<Menu>) request.getServletContext().getAttribute("allmenus");

        List<Menu> menus = new ArrayList<>();
        List<Menu> secondMenus = new ArrayList<>();

        for (Menu menu : allmenus) {
            if (menu.getPId() == 0 && menu.getGrade() == 1) {
                menus.add(menu);
            } else if (menu.getPId() == 0 && menu.getGrade() == 2) {
                secondMenus.add(menu);
            }
        }

        for (Menu menu : menus) {
            List<Menu> subMenus = new ArrayList<>();
            for (Menu subMenu : allmenus) {
                if (subMenu.getPId() == menu.getId() && subMenu.getGrade() == 1) {
                    subMenus.add(subMenu);
                }
            }
            menu.setSubMenus(subMenus);
        }
        request.setAttribute("menus", menus);
        request.setAttribute("secondMenus", secondMenus);
    }
}
