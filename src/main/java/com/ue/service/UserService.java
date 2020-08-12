package com.ue.service;

import com.ue.pojo.User;

public interface UserService {
    User findUserByUsername(String username);

    User queryUserById(Integer userId);

    void saveUser(User user);

    void editUser(User user);

}
