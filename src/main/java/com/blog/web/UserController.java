package com.blog.web;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.blog.entity.User;
import com.blog.service.UserService;
import com.blog.service.UserServiceException;
import com.blog.util.Config;
import com.blog.util.FileType;
import com.blog.util.FileTypeJudge;
import com.blog.util.UserUtil;
import com.blog.util.VerifyCodeUtils;

/**
 * 用户操作控制器
 * 
 * @author 健
 *
 */
@SuppressWarnings("all")
@Controller
@RequestMapping("/user")
public class UserController {
	/**
	 * 临时验证码
	 */
	private String mcode = "";

	@Autowired
	private UserService service;

	/**
	 * 跳转到注册界面
	 * 
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping("/toadduser.do")
	public String toadduser(HttpServletRequest req, HttpServletResponse res) {
		req.setAttribute("title", Config.REGISTER);
		return "/user/adduser";
	}

	/**
	 * 跳转到登陆界面
	 * 
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping("/toLogin.do")
	public String toLogin(HttpServletRequest req, HttpServletResponse res) {
		req.setAttribute("title", Config.LOGIN);
		return "/user/login";
	}

	/**
	 * 跳转到找回密码界面
	 * 
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping("/torepass.do")
	public String torepass(HttpServletRequest req, HttpServletResponse res) {
		req.setAttribute("title", Config.REPASS);
		return "/user/repass";
	}

	/**
	 * 注册用户
	 * 
	 * @param req
	 * @param res
	 * @param code
	 * @return
	 */
	@RequestMapping("/adduser.do")
	@ResponseBody
	public Object adduser(HttpServletRequest req, HttpServletResponse res, String code) {
		mcode = (String) req.getSession().getAttribute("rand");
		if (!code.equals(mcode)) {
			return new JsonResult("验证码错误");
		}
		String name = req.getParameter("name");
		String pass = req.getParameter("pass");
		String phone = req.getParameter("phone");
		String qq = req.getParameter("qq");
		String email = req.getParameter("email");
		String address = req.getParameter("address");
		service.registerUser(name, pass, phone, qq, email, address);
		recode(req);
		return new JsonResult<Object>(0, "注册成功");
	}

	/**
	 * 用户登录密码检查
	 * 
	 * @param req
	 * @param res
	 * @param code
	 * @return
	 */

	@RequestMapping(value = "/checkuser.do", method = RequestMethod.POST)
	@ResponseBody
	public Object checkuser(HttpServletRequest req, HttpServletResponse res, String code) {
		mcode = (String) req.getSession().getAttribute("rand");
		if (!code.equals(mcode)) {
			return new JsonResult(1, "验证码错误");
		}
		String name = req.getParameter("name");
		String pass = req.getParameter("pass");
		System.out.println(name + "------" + pass);
		User user = service.checkUserPass(name, pass);
		UserUtil.updateSession(req, user);
		recode(req);
		return new JsonResult<Object>(0, "登陆成功");
	}

	/**
	 * 用户信息修改
	 * 
	 * @param req
	 * @param res
	 * @param code
	 * @return
	 */

	@RequestMapping(value = "/updateUserInfo.do", method = RequestMethod.GET)
	@ResponseBody
	public Object updateUserInfo(HttpServletRequest req, HttpServletResponse res) {
		if (!UserUtil.isLogin(req)) {
			System.out.println("用户未登录");
			throw new UserServiceException("用户未登录");
		}
		String name = req.getParameter("name");
		String introduct = req.getParameter("introduct");
		String pass = req.getParameter("pass");
		String phone = req.getParameter("phone");
		String qq = req.getParameter("qq");
		String email = req.getParameter("email");
		String address = req.getParameter("address");
		String id = ((User) req.getSession().getAttribute("u")).getId();
		System.out.println(id);
		service.updateUser(id, name, introduct, pass, phone, qq, email, address);
		User user = service.findUserById(id);
		UserUtil.updateSession(req, user);
		recode(req);
		return new JsonResult<Object>(0, "修改成功");
	}

	/**
	 * 退出登录
	 * 
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping(value = "/logout.do", method = RequestMethod.GET)
	@ResponseBody
	public Object logout(HttpServletRequest req, HttpServletResponse res) {
		if (!UserUtil.isLogin(req)) {
			System.out.println("用户未登录");
			throw new UserServiceException("用户未登录");
		}
		req.getSession().setAttribute("token", null);
		req.getSession().setAttribute("u", null);
		return new JsonResult<Object>(0, "退出登陆成功");
	}

	/**
	 * 找回密码
	 * 
	 * @param req
	 * @param res
	 * @param code
	 * @return
	 */

	@RequestMapping(value = "/repass.do", method = RequestMethod.GET)
	@ResponseBody
	public JsonResult<Object> repass(HttpServletRequest req, HttpServletResponse res, String code) {
		mcode = (String) req.getSession().getAttribute("rand");
		if (!code.equals(mcode)) {
			return new JsonResult(1, "验证码错误");
		}
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		service.repassEmail(name, email);
		return new JsonResult<Object>(0, "邮件发送成功");
	}

	/**
	 * 找回密码返回地址
	 * 
	 * @param req
	 * @param res
	 * @param code
	 * @return
	 */

	@RequestMapping(value = "/repass/callback.do", method = RequestMethod.GET)
	public String callback(HttpServletRequest req, HttpServletResponse res) {
		req.setAttribute("title", "请输入新密码");
		mcode = (String) req.getSession().getAttribute("rand");
		// if (!code.equals(mcode)) {
		// return new JsonResult(1,"验证码错误");
		// }
		String sal = req.getParameter("t");
		String id = req.getParameter("u");
		try {
			User user = service.checkEmailRepass(sal, id);
			req.getSession().setAttribute("uid", user.getId());
		} catch (Exception e) {
			req.setAttribute("errMsg", e.getMessage());
			return "/static/error";
		}
		return "/user/newpass";
	}

	/**
	 * 找回密码
	 * 
	 * @param req
	 * @param res
	 * @param code
	 * @return
	 */

	@RequestMapping(value = "/newpass.do", method = RequestMethod.GET)
	@ResponseBody
	public Object newpass(HttpServletRequest req, HttpServletResponse res, String code) {
		String id = (String) req.getSession().getAttribute("uid");
		String newpass = req.getParameter("newpass");
		service.updateUser(id, null, null, newpass, null, null, null, null);
		;
		req.getSession().setAttribute("uid", null);
		return new JsonResult<Object>(0, "密码修改成功");
	}

	@RequestMapping(value = "/verify.do", method = RequestMethod.GET)
	@ResponseBody
	public void verify(HttpServletRequest req, HttpServletResponse res) {

		// 生成随机字串
		String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
		System.out.println(verifyCode);
		// 存入会话session
		HttpSession session = req.getSession(true);
		session.setAttribute("rand", verifyCode.toLowerCase());
		// 生成图片
		int w = 200, h = 80;
		try {
			VerifyCodeUtils.outputImage(w, h, res.getOutputStream(), verifyCode);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping("/update.do")
	@ResponseBody
	public JsonResult<Object> handleFileUpload(HttpServletRequest request) {
		// 判断是否是文件上传请求
		if (ServletFileUpload.isMultipartContent(request)) {
			// 创建文件上传处理器
			ServletFileUpload upload = new ServletFileUpload();
			// 限制单个上传文件的大小
			upload.setFileSizeMax(1L << 24);
			try {
				// 解析请求
				FileItemIterator iter = upload.getItemIterator(request);
				while (iter.hasNext()) {
					FileItemStream item = iter.next();
					if (!item.isFormField()) {
						//文件名
						String name = "/update/"+item.getName();
						System.out.println(name);
						String type =  item.getContentType();
						
						// 是文件上传对象，获取上传文件的输入流
						InputStream in = item.openStream();
						//inputstream重用
						ByteArrayOutputStream baos = new ByteArrayOutputStream();  
						byte[] buffer = new byte[1024];  
						int len;  
						while ((len = in.read(buffer)) > -1 ) {  
						    baos.write(buffer, 0, len);  
						}  
						baos.flush();                
						  
						InputStream stream1 = new ByteArrayInputStream(baos.toByteArray());  
						  
						//TODO:显示到前台  
						  
						InputStream is = new ByteArrayInputStream(baos.toByteArray());  
						//文件类型判断
						FileType tp = FileTypeJudge.getType(stream1);
						String reg = "PNG|JPG|GIF|BMP";
						System.out.println(tp);
						if(!tp.toString().matches(reg)){
							return new JsonResult<Object>("请选择png,jpg,gif,bmp类型的图片进行上传");
						}
						/* 对上传文件的输入流进行处理，跟本地的文件流处理方式相同 */
						FileOutputStream fos = null;
						// 这里对is进行赋值，略
						// ...
						// 文件输出流fos
						// openFile()为自定义函数，判断文件是否存在等（略）
						String filepath = Config.PROJECT_PATH+name;
						String filelocalhost = Config.BASE_URL+name;
						//创建文件
						UserUtil.openFile(filepath); 

						try {
							// 打开一个已存在文件的输出流
							fos = new FileOutputStream(filepath);
						} catch (FileNotFoundException e) {
							e.printStackTrace();
						}

						// 将输入流is写入文件输出流fos中
						int ch = 0;
						try {
							while ((ch = is.read()) != -1) {
								fos.write(ch);
							}
						} catch (IOException e1) {
							e1.printStackTrace();
						} finally {
							// 关闭输入流等（略）
							fos.close();
							is.close();
						}
						return new JsonResult<Object>(0, filelocalhost);
					}
				}
			} catch (FileUploadException e) {
				System.out.println("上传文件过大");
				return new JsonResult<Object>("上传文件过大");
			} catch (IOException e) {
				System.out.println("文件读取出现问题");
				return new JsonResult<Object>("文件读取出现问题");
			} catch (Exception e) {
				e.printStackTrace();
				
				return new JsonResult<Object>("error错误");
			}
		}
		return new JsonResult<Object>("系统错误");
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
