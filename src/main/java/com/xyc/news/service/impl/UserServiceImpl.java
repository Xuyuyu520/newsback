// package com.project.news.service.impl;/*
//  * To change this license header, choose License Headers in Project Properties.
//  * To change this template file, choose Tools | Templates
//  * and open the template in the editor.
//  */
//
// import com.project.news.mapper.UserMapper;
// import com.project.news.pojo.User;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.authentication.BadCredentialsException;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;
// import org.springframework.stereotype.Service;
// import org.springframework.transaction.annotation.Transactional;
//
// /**
//  * 
//  */
//
// @Service
// @Transactional
// public class UserServiceImpl implements UserDetailsService {
//
// 	@Autowired
// 	UserMapper userMapper;
//
// 	@Override
// 	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
// 		// 查询用户
// 		User user = userMapper.findOneByUserName(username);
// 		if (user == null) {
// 			throw new BadCredentialsException("用户名密码错误错误！"); // 抛出异常
// 		}
// 		return user;
// 	}
//
// 	public User insertUser(User user) {
// 		// 设置默认角色
// 		if (user.getRole() == null) {
// 			user.setRole("user");
// 		}
// 		userMapper.insertUser(user);
// 		User fullUser = userMapper.findOneByUserName(user.getUsername());
// 		return fullUser;
// 	}
// }
