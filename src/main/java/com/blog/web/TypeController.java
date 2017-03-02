package com.blog.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.blog.service.TypeService;
@RequestMapping("/admin")
@Controller
public class TypeController {

	@Autowired
	public TypeService service;
	@RequestMapping("toaddtype.do")
	public String toAddType(HttpServletRequest req ,HttpServletResponse res){
		req.setAttribute("title", "添加分类");
		return "/type/addtype";
	}
	@RequestMapping("addtype.do")
	@ResponseBody
	public JsonResult AddType(HttpServletRequest req ,HttpServletResponse res,String name,String image){
		service.addType(name, image);
		return new JsonResult(0,"成功");
	}
	
	@RequestMapping("toupdate.do")
	public String toUpdate(HttpServletRequest req ,HttpServletResponse res,String id){
		req.setAttribute("title", "修改分类");
		req.setAttribute("type", service.findTypeById(id));
		return "/type/update";
	}
	
	@RequestMapping("update.do")
	@ResponseBody
	public JsonResult update(HttpServletRequest req ,HttpServletResponse res,String id,String name,String image,String managerId){
		service.updateType(id, name, image, managerId);
		return new JsonResult(0,"成功");
	}
}
