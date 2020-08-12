package com.ue.service.impl;

import com.ue.dao.MenuMapper;
import com.ue.pojo.Menu;
import com.ue.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("menuService")
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Menu> findMenuByGrade() {
        List<Menu> menus = menuMapper.selectAll();
        return menus;
    }
}
