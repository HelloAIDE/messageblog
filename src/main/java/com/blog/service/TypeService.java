package com.blog.service;

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
	 * 根据id查找分类
	 */
	public Type findTypeById(String id);
	/**
	 * 根据name查找分类
	 */
	public Type findTypeByName(String name);
}
