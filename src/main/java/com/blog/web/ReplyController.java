package com.blog.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.blog.entity.Reply;
import com.blog.entity.User;
import com.blog.service.ReplyService;

@Controller
public class ReplyController {
	@Autowired
	public ReplyService service;
	
	@RequestMapping("addreply.do")
	@ResponseBody
	public JsonResult addreply(HttpServletRequest req ,HttpServletResponse res){
		String content = req.getParameter("content");
		User u = (User) req.getSession().getAttribute("u");
		if(u==null)
		{
			return new JsonResult("请先登录");
		}
		String name = u.getName();
		String uId = u.getId();
		String postId = req.getParameter("postid");
		String parentId = req.getParameter("parentid");
		service.addReply(content, postId, parentId, name,uId);
		return new JsonResult(0,"回复成功");
	}
	@ResponseBody
	@RequestMapping(value="getParentAllReply.do")
	public JsonResult getParentAllReply(HttpServletRequest req ,HttpServletResponse res,String parentid){
		List<Reply> list = service.findAllParent(parentid);
			return new JsonResult(list);
	}
	@ResponseBody
	@RequestMapping(value="getReply.do")
	public JsonResult getReply(HttpServletRequest req ,HttpServletResponse res,String postid){
		List<Reply> list = service.findReplyPostId(postid);
		System.out.println(list);
			return new JsonResult(list);
	}
}
