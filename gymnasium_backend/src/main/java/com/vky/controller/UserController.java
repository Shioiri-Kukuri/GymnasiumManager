package com.vky.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.vky.entity.PageResult;
import com.vky.entity.QueryPageBean;
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

    @PostMapping("/logout.do")
    public Result logout(@RequestHeader("Authorization") String token) {
        try {
            // 在尝试使令牌失效之前，先验证其有效性。
            // 这一步确保我们在继续操作前使用的是一个有效的令牌。
            if (JwtUtil.validateToken(token)) {
                // 假设JwtUtil中有一个方法来处理令牌失效的逻辑，
                // 这可能涉及到将令牌添加到黑名单中或管理其有效状态。
                // 注意：由于JWT无状态的特性，实际的JWT失效处理比较复杂；
                // 通常，你需要在服务器端维护一个令牌黑名单。
                boolean isLogoutSuccess = JwtUtil.invalidateToken(token);

                if (isLogoutSuccess) {
                    // 通知客户端登出成功。
                    return new Result(true, "登出成功");
                } else {
                    // 如果令牌未能失效，则返回相应的错误信息。
                    return new Result(false, "登出失败，无法使令牌失效");
                }
            } else {
                // 如果令牌已失效，告知客户端。
                return new Result(false, "无效的令牌，您可能已经登出");
            }
        } catch (Exception e) {
            // 捕获与令牌验证或失效相关的所有异常。
            return new Result(false, "登出时发生错误：" + e.getMessage());
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

    @RequestMapping("/adduser.do")
    public Result adduser(@RequestBody User user){
        try{
            user.setRoleId(1);
            userService.add(user);
        }catch(Exception e){
            return new Result(false,"添加失败");
        }
        return new Result(true,"添加成功");
    }

    @RequestMapping("/addmentadmin.do")
    public Result addmentadmin(@RequestBody User user){
        try{
            userService.add(user);
        }catch(Exception e){
            return new Result(false,"添加失败");
        }
        return new Result(true,"添加成功");
    }

    @RequestMapping("/edit.do")
    public Result edit(@RequestBody User user){
        try{
            userService.edit(user);
        }catch(Exception e){
            return new Result(false,"修改失败");
        }
        return new Result(true,"修改成功");
    }


    @RequestMapping("/findByAccount.do")
    public Result findByAccount(Integer account){
        try{
            User user = userService.findByAccount(account);
            return new Result(true,"查询成功",user);
        }catch(Exception e){
            return new Result(false,"查询失败");
        }
    }

    @RequestMapping("/findPage.do")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
        PageResult pageResult = userService.pageQuery(
                queryPageBean.getCurrentPage(),
                queryPageBean.getPageSize(),
                queryPageBean.getQueryString()
        );
        return pageResult ;
    }

    @RequestMapping("/delete.do")
    public Result delete(Integer account){
        try{
            userService.delete(account);
        }catch(Exception e){
            return new Result(false,"删除失败");
        }
        return new Result(true,"删除成功");
    }

    @RequestMapping("/info.do")
    public Result info(@RequestHeader("Authorization") String token) {
        try {
            // 在尝试使令牌失效之前，先验证其有效性。
            // 这一步确保我们在继续操作前使用的是一个有效的令牌。
            if (JwtUtil.validateToken(token)) {
                // 假设JwtUtil中有一个方法来处理令牌失效的逻辑，
                // 这可能涉及到将令牌添加到黑名单中或管理其有效状态。
                // 注意：由于JWT无状态的特性，实际的JWT失效处理比较复杂；
                // 通常，你需要在服务器端维护一个令牌黑名单。
                boolean isLogoutSuccess = JwtUtil.invalidateToken(token);
                if (isLogoutSuccess) {
                    // 通知客户端登出成功。
                    return new Result(true, "登出成功");
                } else {
                    // 如果令牌未能失效，则返回相应的错误信息。
                    return new Result(false, "登出失败，无法使令牌失效");
                }

            }
        }catch (Exception e){
            return new Result(false,"获取信息失败");
        }
        return new Result(true,"获取信息成功");
    }
}


