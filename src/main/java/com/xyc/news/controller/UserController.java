package com.xyc.news.controller;

import com.xyc.news.common.WrapMapper;
import com.xyc.news.common.Wrapper;
import com.xyc.news.pojo.User;
import com.xyc.news.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户控制器* @author xuYuYu
 * @date 2024-04-23 10:07:43
 */
@RestController
@CrossOrigin
public class UserController {
    @Autowired
    UserService userService;
    @PostMapping("/register")
    public Wrapper<User> insertUser(@RequestBody User user){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        User fullUser = userService.insertUser(user);
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE,Wrapper.SUCCESS_MESSAGE,fullUser);
    }
}
