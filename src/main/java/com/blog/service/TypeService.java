package com.blog.service;

import java.util.List;

import com.blog.entity.Type;

public interface TypeService {
	/**
	 * 添加分类
	 * @param name
	 * @param image
	 */
	public void addType(String name,String image);
	/**
	 * 更改分类
	 * @param id
	 * @param name
	 * @param image
	 * @param managerId
	 */
	public void updateType(String id,String name,String image,String managerId);
	/**
	 * 获取所有分类
	 */
	public List<Type> findAllType();
	/**
	 * 根据id查找分类
	 */
	public Type findTypeById(String id);
	/**
	 * 根据name查找分类
	 */
	public Type findTypeByName(String name);
}
