package com.blog.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.blog.entity.Type;

@Repository
public interface TypeDao {
	public Type findTypeByName(String name);
	public Type findTypeById(String id);
	public List<Type> findAll();
	public void addType(Type type);
	public void update(Type type);
}
