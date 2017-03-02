package com.blog.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
@Repository
public interface SqlDao {
	public List<Object> exeSelect(String sql);
} 
