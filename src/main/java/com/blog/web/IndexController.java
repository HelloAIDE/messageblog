package com.blog.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.blog.entity.User;
import com.blog.service.UserService;
import com.blog.util.Config;

@Controller
public class IndexController {
	@Autowired
	private UserService service ;
	@RequestMapping("/index.do")
	public String index(HttpServletRequest req,HttpServletResponse res){
		User u = (User) req.getSession().getAttribute("u");
		req.setAttribute("title", Config.INDEX);
		if(u!=null)
		{
			req.setAttribute("uname", u.getUser());
		}
		
		return "WEB-INF/index";
	}
}
