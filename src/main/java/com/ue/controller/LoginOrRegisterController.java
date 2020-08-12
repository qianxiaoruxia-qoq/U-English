package com.ue.controller;

import com.ue.pojo.User;
import com.ue.service.UserService;
import com.ue.util.LoginUser;
import com.ue.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("lor")
public class LoginOrRegisterController {

    @Autowired
    private UserService userService;

    @RequestMapping("login")
    @ResponseBody
    public Result login(HttpServletRequest request, String username, String pass) {
        if (username == null || "".equals(username.trim())) {
            return Result.error("请输入用户名");
        }
        if (pass == null || "".equals(pass.trim())) {
            return Result.error("请输入密码");
        }

        User user = userService.findUserByUsername(username);

        pass = DigestUtils.md5DigestAsHex(pass.getBytes());

        if (null == user || !pass.equals(user.getPassword())) {
            return Result.error("用户名或密码错误！");
        }

        LoginUser.setLoginUser(request, user);

        return Result.ok();
    }

    @RequestMapping("register")
    @ResponseBody
    public Result register(HttpServletRequest request, String regname, String regpass, String reregpass) {
        if (regname == null || "".equals(regname.trim())) {
            return Result.error("请输入用户名");
        }
        if (regpass == null || "".equals(regpass.trim())) {
            return Result.error("请输入密码");
        }
        if (reregpass == null || "".equals(reregpass.trim())) {
            return Result.error("请输入确认密码");
        }
        if (!regpass.equals(reregpass)) {
            return Result.error("两次输入的密码不相同");
        }

        User user = userService.findUserByUsername(regname);

        if (null != user) {
            return Result.error("用户已存在！");
        }

        regpass = DigestUtils.md5DigestAsHex(regpass.getBytes());

        User user1 = new User();
        user1.setUsername(regname);
        user1.setPassword(regpass);
        user1.setAvatar("/libs/img/1cf3b3b4-f6a3-46e5-8a77-4b8c3fef153b.jpg");

        userService.saveUser(user1);


        LoginUser.setLoginUser(request, user1);

        return Result.ok();
    }
}
