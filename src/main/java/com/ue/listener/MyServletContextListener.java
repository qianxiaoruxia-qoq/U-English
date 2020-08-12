package com.ue.listener;

import com.ue.pojo.Menu;
import com.ue.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.ArrayList;
import java.util.List;

/**
 * ServletContextListener
 * 当servlet容器启动时，将字典缓存，便于后面的使用
 */
public class MyServletContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {
        ServletContext servletContext = event.getServletContext();
        WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(servletContext);

        // 通过ApplicationContext的getBean方法来获取Spring容器中已初始化的bean。
        MenuService menuService = context.getBean(MenuService.class);
        List<Menu> allmenus = menuService.findMenuByGrade();
        event.getServletContext().setAttribute("allmenus", allmenus);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
