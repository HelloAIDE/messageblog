package com.blog.web;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.blog.dao.UserDao;
import com.blog.entity.User;
import com.blog.service.UserService;
import com.blog.util.Config;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private UserService service;

	@RequestMapping("/toadduser.do")
	public String toadduser(HttpServletRequest req, HttpServletResponse res) {
		req.setAttribute("title", "添加用户");
		return "/admin/adduser";
	}

	@RequestMapping("/adduser.do")
	@ResponseBody
	public Object adduser(HttpServletRequest req, HttpServletResponse res, String code) {
		if (!code.equals("1234")) {
			return new JsonResult("验证码错误");
		}
		String name = req.getParameter("name");
		System.out.println(name);
		String pass = req.getParameter("pass");
		String phone = req.getParameter("phone");
		String qq = req.getParameter("qq");
		String email = req.getParameter("email");
		String address = req.getParameter("address");
		service.registerUser(name, pass, phone, qq, email, address);
		return new JsonResult<Object>("成功");
	}
	
	
}
