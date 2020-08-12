package com.ue.service.impl;

import com.ue.dao.UserMapper;
import com.ue.pojo.User;
import com.ue.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findUserByUsername(String username) {
        //select * from user where username=?
        Condition condition = new Condition(User.class);
        Example.Criteria criteria = condition.createCriteria();
        criteria.andEqualTo("username", username);
        List<User> users = userMapper.selectByExample(condition);

        return users.size() == 1 ? users.get(0) : null;
    }

    @Override
    public User queryUserById(Integer userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    public void saveUser(User user) {
        userMapper.insert(user);
        String nickName = "ue_" + (int)((Math.random()*9+1)*1000);
        System.out.println("nickName>>>>" + nickName);
        user.setNickname(nickName);
        userMapper.updateByPrimaryKey(user);
    }

    @Override
    public void editUser(User user) {
        userMapper.updateByPrimaryKey(user);
    }
}
