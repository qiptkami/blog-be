package com.qipt.controller.admin;

import com.qipt.pojo.User;
import com.qipt.response.ResponseData;
import com.qipt.service.UserService;
import com.qipt.util.JWTUtils;
import com.qipt.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/*
重定向代表之前的请求已结束，给客户端一个新的url，让客户端重新请求去获取资源，这个url可以是站外的，效率相对于转发要低，之前的request域已经失效，可以通过session来获得一些参数。
而转发则和它大致相反：还是同一个request请求，浏览器地址栏不发生变化，只能访问站内资源，更快
 */
@RestController
@RequestMapping("/admin")
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseData login(@RequestBody User u) {
        Map<String, Object> map = new HashMap<>();
        String message = "";
        int status = 0;
        ResponseData responseData = null;
        User user = userService.selectOne(u.getUsername());
        if (user == null) {
            message = "用户不存在";
        } else if (!user.getPassword().equals(MD5Utils.string2MD5(u.getPassword()))) {
            message = "密码错误";
            user = null;
        } else {
            //验证通过
            //生成token
            String token = JWTUtils.createToken(user);
            user.setPassword(null);
            message = "登录成功";
            status = 1;
            map.put("token", token);
            try {
                JWTUtils.verifyToken(token);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        map.put("userInfo", user);
        responseData = new ResponseData(status, message, map);
        return responseData;
    }

}
