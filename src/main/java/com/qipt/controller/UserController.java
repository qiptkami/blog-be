package com.qipt.controller;

import com.qipt.pojo.User;
import com.qipt.response.ResponseData;
import com.qipt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/info")
    public ResponseData userInfo(@RequestParam String username) {
        ResponseData responseData = null;
        User user = userService.selectOne(username);
        if (user != null) {
            responseData = new ResponseData(1, "查询成功", user);
        } else {
            responseData = new ResponseData(0, "未查询到结果", null);
        }
        return responseData;
    }

}
