package com.blog.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.blog.entity.User;

@Repository
public interface UserDao {
	/** 获取全部用户 */
	public List<Map<String, User>> findAllUser();

	/** 根据状态获取用户 */
	public List<Map<String, User>> findUserByState(String state);

	/** 根据ID获取用户 */
	public User findUserById(String id);

	/** 根据Name获取用户 */
	public User findUserByName(String name);

	/** 添加用户 */
	public void addUser(User user);

	/** 修改用户 */
	public void updateUser(User user);
	
}
