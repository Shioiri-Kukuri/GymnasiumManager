package com.vky.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.vky.entity.Result;
import com.vky.pojo.User;
import com.vky.service.UserService;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.vky.utils.JwtUtil;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Reference
    private UserService userService;

    @PostMapping("/login.do")
    public Result login(@RequestBody User user) {
        try {
            // 让UserService返回User对象，以便获取登录成功的用户信息
            User loginUser = userService.findByAccountAndPassword(user);
            if (loginUser != null) {
                // 准备返回的数据，包括Token和其他可能的用户信息
                Map<String, Object> resultMap = new HashMap<>();
                resultMap.put("token", JwtUtil.createJWT(UUID.randomUUID().toString(), String.valueOf(loginUser.getAccount()), null));

                // 如果有其他用户信息需要返回，可以继续添加到resultMap中
                resultMap.put("userId", loginUser.getAccount());
                resultMap.put("username", loginUser.getName());
                resultMap.put("roleId", loginUser.getRoleId());

                // 将整个Map作为data返回
                return new Result(true, "登录成功", resultMap);
            } else {
                return new Result(false, "用户名或密码错误");
            }
        } catch (Exception e) {
            return new Result(false, "登录失败：" + e.getMessage());
        }
    }


    @GetMapping("/getUserRole.do")
    public Result getUserRole(@RequestHeader("Authorization") String token) { // 假设JWT Token在Authorization头中传递
        try {
            // 这里简化处理，实际应用中需要安全地验证Token并解析
            String Account = JwtUtil.extractUserIdFromToken(token); // 假定JwtUtil中有这样一个方法来提取JWT中的用户ID
            if (Account != null) {
                // 根据用户ID查询用户角色
                Integer roleId = userService.findRoleIdByAccount(Account);
                if (roleId != null) {
                    return new Result(true, "成功", roleId);
                } else {
                    return new Result(false, "无法获取用户角色");
                }
            } else {
                return new Result(false, "无效的Token");
            }
        } catch (Exception e) {
            return new Result(false, "获取用户角色失败：" + e.getMessage());
        }
    }
}


