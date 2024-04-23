package com.xyc.news.mapper;

import com.xyc.news.pojo.User;

public interface UserMapper {

	/**
	 * 根据id查询
	 * @param username
	 * @return
	 */
	public User findOneByUserName(String username) ;

    void insertUser(User user);
}
