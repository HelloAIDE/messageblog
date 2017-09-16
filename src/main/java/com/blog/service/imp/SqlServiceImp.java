package com.blog.service.imp;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.dao.SqlDao;
import com.blog.service.SqlService;
@Service
public class SqlServiceImp implements SqlService {
	@Autowired
	public SqlDao dao;
	
	
	public  List<Object> select(String sql) {
		
		List<Object> list = dao.exeSelect(sql);
		
		
		return list;
	}
}
