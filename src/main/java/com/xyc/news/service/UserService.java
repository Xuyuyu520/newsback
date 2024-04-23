package com.xyc.news.service;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.xyc.news.mapper.UserMapper;
import com.xyc.news.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户服务
 *
 * @author xuYuYu
 * @date 2024-04-23 10:11:29
 */
@Service
@Transactional
public class UserService implements UserDetailsService {

    @Autowired
    UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //查询用户
        User user = userMapper.findOneByUserName(username);
        if (user == null) {
           throw new BadCredentialsException("用户名密码错误错误！"); //抛出异常
        }
        return user;
    }

    public User insertUser(User user) {
        if (user.getRole()==null){
            user.setRole("user");
        }
        userMapper.insertUser(user);
        User fullUser = userMapper.findOneByUserName(user.getUsername());
        return fullUser;
    }
}
