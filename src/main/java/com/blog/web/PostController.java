package com.blog.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.blog.entity.Post;
import com.blog.entity.Type;
import com.blog.entity.User;
import com.blog.service.PostService;
import com.blog.service.ReplyService;
import com.blog.service.TypeService;
import com.blog.service.exception.PostServiceException;
import com.blog.util.UserUtil;
import com.blog.util.VerifyCodeUtils;

@Controller
@RequestMapping(value = "/post")
public class PostController {
	public String mcode = "1234";
	@Autowired
	public PostService service;
	@Autowired
	public TypeService typeService;
	@Autowired
	public ReplyService replyService;

	@RequestMapping(value = "toaddpost.do")
	public String toAddPost(HttpServletRequest req, HttpServletResponse res) {
		List<Type> types = typeService.findAllType();
		req.setAttribute("types", types);
		req.setAttribute("title", "发帖");
		return "/post/addpost";
	}

	@ResponseBody
	@RequestMapping(value = "addpost.do")
	public JsonResult<Post> addPost(HttpServletRequest req, HttpServletResponse res, String code) {
		mcode = (String) req.getSession().getAttribute("rand");
		if (!UserUtil.isLogin(req)) {
			throw new PostServiceException("用户未登录");
		}
		System.out.println("验证码：" + mcode);
		if (!code.equals(mcode)) {
			throw new PostServiceException("验证码错误");
		}
		User user = UserUtil.sessionGetUser(req);
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		String city = req.getParameter("city");
		String type = req.getParameter("type");
		String uid = user.getId();
		String resources = req.getParameter("resources");
		Post post = service.addPost(title, content, city, type, uid, resources);
		recode(req);
		return new JsonResult<Post>(post);
	}

	@ResponseBody
	@RequestMapping(value = "postlist.do")
	public JsonResult<List<Post>> list(HttpServletRequest req, HttpServletResponse res) {
		List<Post> list = service.getAllPost();
		return new JsonResult<List<Post>>(list);
	}

	@RequestMapping(value = "tolist.do")
	public String tolist(HttpServletRequest req, HttpServletResponse res) {
		req.setAttribute("title", "帖子列表");
		List<Post> list = service.getAllPost();
		req.setAttribute("list", list);
		return "/post/postlist";
	}

	@RequestMapping(value = "postinfo.do")
	public String postinfo(HttpServletRequest req, HttpServletResponse res, String id) {
		Post post = service.findPostById(id);
		req.setAttribute("title", post.getTitle());
		req.setAttribute("info", post);
		return "/post/postinfo";
	}

	@ResponseBody
	@RequestMapping(value = "delete.do")
	public JsonResult deletePost(HttpServletRequest req, HttpServletResponse res, String id) {
		service.deletePostById(id);
		return new JsonResult(0, "删除成功");
	}

	// 刷新验证码
	public void recode(HttpServletRequest req) {
		// 生成随机字串
		String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
		System.out.println(verifyCode);
		// 存入会话session
		HttpSession session = req.getSession(true);
		session.setAttribute("rand", verifyCode.toLowerCase());
	}

}
