package com.blog.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.blog.entity.User;

public interface UserService {
	/**
	 * 用户注册
	 * 
	 * @param name
	 *            用户名
	 * @param pass
	 *            密码
	 * @param phone
	 *            手机号
	 * @param qq
	 * @param email
	 * @param address
	 *            地址
	 */
	public void registerUser(String name, String pass, String phone, String qq, String email, String address);
	/**
	 * 用户登录
	 * @param name 用户名
	 * @param pass 密码
	 * @return
	 */
	public User checkUserPass(String name,String pass);
	/**
	 * 通过id查找用户
	 * @param id
	 * @return
	 */
	public User findUserById(String id);
	/**
	 * 通过昵称查找用户
	 * @param name
	 * @return
	 */
	public User findUserByName(String name);
	/**
	 * 修改用户资料
	 * @param user
	 */
	public void updateUser(String id,String name,String introduct,String pass, String phone, String qq, String email, String address);
	
	
	/**
	 * 查找全部用户
	 * @return
	 */
	public List<Map<String,User>> findAllUser();
	/**
	 * 删除用户（更改状态）
	 * @param id 用户id
	 */
	public void deleteUser(String id);
	/**
	 * 找回密码发送邮件
	 */
	public void repassEmail(String name,String email);
	/**
	 * 找回密码验证
	 */
	public User checkEmailRepass(String sal,String id);
	
}
