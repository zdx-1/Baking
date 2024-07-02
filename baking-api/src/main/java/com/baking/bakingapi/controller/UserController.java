package com.baking.bakingapi.controller;


import com.baking.bakingapi.config.MD5Config;
import com.baking.bakingapi.entity.User;
import com.baking.bakingapi.response.JsonResult;
import com.baking.bakingapi.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 张德鑫
 * @since 2024-07-02
 */
@RestController
@RequestMapping("/v1/users")
public class UserController {
    @Resource
    private UserService userService;
//    注册
    @PostMapping("/reg")
    public JsonResult reg(User user){
        user.setPassword(MD5Config.inputPassToFormPass(user.getPassword()));
        userService.reg(user);
        return JsonResult.ok();
    }
//    登录
    @PostMapping("/login")
    public JsonResult login(User user){
        user.setPassword(MD5Config.inputPassToFormPass(user.getPassword()));
        User user1=userService.login(user);
        return JsonResult.ok(user1);
    }
//    更新
    @PostMapping("/update")
    public JsonResult update( User user) {
        userService.update(user);
        return JsonResult.ok();
    }
//    查询用户管理页面列表
    @GetMapping("/admin")
    public JsonResult listForAdmin(){
        List<User> list = userService.listForAdmin();
        return JsonResult.ok(list);
    }
//    根据id删除用户
@PostMapping("/{id}/delete")
    public JsonResult deleteById(@PathVariable Long id){
        userService.deleteById(id);
        return JsonResult.ok();
    }
}

