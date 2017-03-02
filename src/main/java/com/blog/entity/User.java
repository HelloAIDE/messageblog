package com.blog.entity;

import java.io.Serializable;

/**
 * 用户实体类
 * 
 * @author 健
 *
 */
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String introduct;
	private String user;
	private String pass;
	private String name;
	private String avatar;
	private String qq_number;
	private String phone;
	private String email;
	private String address;
	private String state;
	private String createTime;
	private String lastLoginTime;
	private String lastModifyTime;

	public User(String id, String introduct, String user, String pass, String name, String avatar, String qq_number,
			String phone, String email, String address, String state, String createTime, String lastLoginTime,
			String lastModifyTime) {
		super();
		this.id = id;
		this.introduct = introduct;
		this.user = user;
		this.pass = pass;
		this.name = name;
		this.avatar = avatar;
		this.qq_number = qq_number;
		this.phone = phone;
		this.email = email;
		this.address = address;
		this.state = state;
		this.createTime = createTime;
		this.lastLoginTime = lastLoginTime;
		this.lastModifyTime = lastModifyTime;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getLastModifyTime() {
		return lastModifyTime;
	}

	public void setLastModifyTime(String lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIntroduct() {
		return introduct;
	}

	public void setIntroduct(String introduct) {
		this.introduct = introduct;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getQq_number() {
		return qq_number;
	}

	public void setQq_number(String qq_number) {
		this.qq_number = qq_number;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", introduct=" + introduct + ", user=" + user + ", pass=" + pass + ", name=" + name
				+ ", avatar=" + avatar + ", qq_number=" + qq_number + ", phone=" + phone + ", email=" + email
				+ ", address=" + address + ", state=" + state + ", createTime=" + createTime + ", lastLoginTime="
				+ lastLoginTime + ", lastModifyTime=" + lastModifyTime + "]";
	}

	public User() {
		// TODO Auto-generated constructor stub
	}
}
