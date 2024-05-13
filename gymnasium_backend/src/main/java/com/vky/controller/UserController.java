package com.vky.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.vky.entity.Result;
import com.vky.pojo.User;
import com.vky.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Reference
    private UserService userService;

    @PostMapping("/login.do")
    public Result login(@RequestBody User user) {
        try {
            userService.findByAccountAndPassword(user);
            if (user != null) {
                // 登录成功，可以考虑生成JWT Token等安全措施
                return new Result(true, "登录成功");
            } else {
                return new Result(false, "用户名或密码错误");
            }
        } catch (Exception e) {
            return new Result(false, "登录失败：" + e.getMessage());
        }
    }
}
    /*public Result login(@RequestParam("account") Integer account, @RequestParam("password") String password) {
        try {
            User user = userService.findByAccountAndPassword(account,password);
            if (user != null) {
                // 登录成功，可以考虑生成JWT Token等安全措施
                return new Result(true, "登录成功");
            } else {
                return new Result(false, "用户名或密码错误");
            }
        } catch (Exception e) {
            return new Result(false, "登录失败：" + e.getMessage());
        }
    }*/

