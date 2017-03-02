package com.blog.web;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.blog.service.SqlService;
import com.blog.util.Config;
import com.blog.util.DatabaseBackup;

@Controller
@RequestMapping("/admin/system")
public class SystemController {
	@Autowired
	public SqlService service;
	@RequestMapping("execute.do")
	@ResponseBody
	public JsonResult< List<Object>> executeSelect(String sql){
		 List<Object> list = service.select(sql);
		 System.out.println(list);
		return new JsonResult< List<Object>>(list);
	}
	
	@RequestMapping("backupDb.do")
	@ResponseBody
	public JsonResult<List<Object>> backupDb(){
		 DatabaseBackup.back();
		return new JsonResult<List<Object>>(0,"备份成功");
	}
	@RequestMapping("restoreDb.do")
	@ResponseBody
	public JsonResult<List<Object>> restoreDb(String path){
		DatabaseBackup.restore(Config.BACKUP_PATH+path);
		return new JsonResult<List<Object>>(0,"还原成功");
	}
	@RequestMapping("backuplist.do")
	@ResponseBody
	public JsonResult<List<String>> getBackList(){
		List<String> list =  DatabaseBackup.getAllBack();
		return new JsonResult<List<String>>(list);
	}
	
	
	
	
}
