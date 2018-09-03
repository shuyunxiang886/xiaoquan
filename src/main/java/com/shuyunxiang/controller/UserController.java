package com.shuyunxiang.controller;

import com.shuyunxiang.pojo.Result;
import com.shuyunxiang.pojo.User;
import com.shuyunxiang.pojo.UserAdmin;
import com.shuyunxiang.servie.LoginUserService;
import com.shuyunxiang.servie.RegisetUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @Autowired
    private RegisetUserService userService;

    @Autowired
    private LoginUserService loginUserService;

    @RequestMapping("judgeUserName")
    @ResponseBody
    public Result judgeUserName(String username) {

        Result result = userService.getResult(username);

        return result;
    }

    @RequestMapping("registerUser")
    @ResponseBody
    public Result registerUser(UserAdmin userAdmin) {

        Result result = userService.insertUser(userAdmin);

        return result;
    }

    @RequestMapping("loginUser")
    @ResponseBody
    public Result loginUser(User user) {

        Result result = loginUserService.loginUser(user);

        return result;
    }
}
