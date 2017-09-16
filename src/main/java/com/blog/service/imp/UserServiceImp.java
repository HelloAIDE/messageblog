package com.blog.service.imp;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blog.dao.UserDao;
import com.blog.entity.User;
import com.blog.service.UserService;
import com.blog.service.exception.UserServiceException;
import com.blog.util.Config;
import com.blog.util.IOUtil;
import com.blog.util.Md5;
import com.blog.util.ReplaceUtil;
import com.blog.util.SafeUtil;
import com.blog.util.SendEmail;
import com.blog.util.UserUtil;
@Transactional  
@Service("userServiceImp")
public class UserServiceImp implements UserService {
	@Autowired 
	private UserDao dao;
	public void registerUser(String user, String pass, String phone, String qq, String email, String address) {
		System.out.println("注册方法进入");
		if(user == null||user.trim().isEmpty())
		{
			throw new UserServiceException("用户名不能为空");
		}
		String passrex = "[\\w|.|,|?|*|-|+]{3,16}";
		if(!user.matches(passrex))
		{
			throw new UserServiceException("用户名不合法");
		}
		if(pass == null||pass.trim().isEmpty())
		{
			throw new UserServiceException("密码不能为空");
		}
		if(!pass.matches(passrex))
		{
			throw new UserServiceException("密码不合法");
		}
		String mailreg = "^([a-zA-Z0-9_\\.\\-])+\\@(([a-zA-Z0-9\\-])+\\.)+([a-zA-Z0-9]{2,4})+$";
		if(email == null||email.trim().isEmpty())
		{
			throw new UserServiceException("邮箱不能为空");
		}
		if(!email.matches(mailreg))
		{
			throw new UserServiceException("邮箱不合法");
		}
		User users = dao.findUserByName(user);
		if(users!=null)
		{
			throw new UserServiceException("用户名已存在");
		}
		users = new User();
		users.setUser(SafeUtil.strReplaceSensitive(user));
		users.setPass(Md5.saltMD5(pass));
		users.setPhone(SafeUtil.strReplaceSensitive(phone));
		users.setQq_number(SafeUtil.strReplaceSensitive(qq));
		users.setEmail(SafeUtil.strReplaceSensitive(email));
		users.setAddress(SafeUtil.strReplaceSensitive(address));
		users.setName(UserUtil.getRandomName());
		long time = System.currentTimeMillis();
		users.setCreateTime(UserUtil.timeToString(time));
		dao.addUser(users);
		String str = IOUtil.fileReaderStringHandle(Config.PROJECT_PATH+"model/register_email.html");
		String content = ReplaceUtil.replace(str, "");
		Map<String,String> map = new HashMap<String, String>();
		map.put("{username}", user);
		map.put("{password}", pass);
		content =  ReplaceUtil.replace(content,map);
		System.out.println("开始发送邮件");
		SendEmail.send("注册成功-"+Config.TITLE, email, content);
		System.out.println("注册成功");
	}
	public User checkUserPass(String name,String pass){
		if(name==null||name.trim().isEmpty())
		{
			throw new UserServiceException("用户名不能为空");
		}
		if(pass==null||pass.trim().isEmpty())
		{
			throw new UserServiceException("密码不能为空");
		}
		User user = dao.findUserByName(name);
		System.out.println(user);
		if(user==null)
		{
			throw new UserServiceException("用户名或密码不正确");
		}
		if(Md5.saltMD5(pass).equals(user.getPass()))
		{
			long time = System.currentTimeMillis();
			user.setLastLoginTime(UserUtil.timeToString(time));
			dao.updateUser(user);
			return user;
		}
		throw new UserServiceException("用户名或密码不正确");
		
	}
	public User findUserById(String id){
		User user = dao.findUserById(id);
		return user;
	}
	
	public User findUserByName(String name){
		User user = dao.findUserByName(name);
		return user;
	}

	public void updateUser(String id,String name,String introduct,String pass, String phone, String qq, String email, String address) {
		String rex = "[\\w|.|,|?|*|-|+]{3,16}";
		User user = dao.findUserById(id);
		if(user==null)
		{
			throw new UserServiceException("用户操作异常");
		}
		
		if(name!=null&&!name.trim().isEmpty())
		{
			if(!name.matches(rex)){
				throw new UserServiceException("用户名不合法");
			}
			user.setName(SafeUtil.strReplaceSensitive(name));
		}
		if(introduct!=null&&!introduct.trim().isEmpty())
		{
			user.setIntroduct(SafeUtil.strReplaceSensitive(introduct));
		}
		if(pass!=null&&!pass.trim().isEmpty())
		{
			user.setPass(Md5.saltMD5(pass));
		} 
		if(phone!=null&&!phone.trim().isEmpty())
		{
			user.setPhone(SafeUtil.strReplaceSensitive(phone));
		}
		if(qq!=null&&!qq.trim().isEmpty())
		{
			user.setQq_number(SafeUtil.strReplaceSensitive(qq));
		}
		if(email!=null&&!email.trim().isEmpty())
		{
			String mailreg = "^([a-zA-Z0-9_\\.\\-])+\\@(([a-zA-Z0-9\\-])+\\.)+([a-zA-Z0-9]{2,4})+$";
			
			if(!email.matches(mailreg))
			{
				throw new UserServiceException("邮箱不合法");
			}
			user.setEmail(SafeUtil.strReplaceSensitive(email));
		}
		if(address!=null&&!address.trim().isEmpty())
		{
			user.setAddress(SafeUtil.strReplaceSensitive(address));
		}
		long time = System.currentTimeMillis();
		user.setLastModifyTime(UserUtil.timeToString(time));
		dao.updateUser(user);
		
	}
	
	public List<Map<String,User>> findAllUser() {
		List<Map<String,User>> list = dao.findAllUser();
		return list;
	}
	public void deleteUser(String id) {
		if(id==null||id.trim().isEmpty())
		{
			throw new UserServiceException("用户不存在");
		}
		User user = dao.findUserById(id);
		if(user==null)
		{
			throw new UserServiceException("用户不存在");
		}
		user.setState("1");
		dao.updateUser(user);
	}
	public void repassEmail(String name,String email) {
		if(name==null||name.trim().isEmpty())
		{
			throw new UserServiceException("用户名不能为空");
		}
		if(email==null||email.trim().isEmpty())
		{
			throw new UserServiceException("邮箱不能为空");
		}
		User user = dao.findUserByName(name);
		if(user==null)
		{
			throw new UserServiceException("用户不存在");
		}
		if(!email.equals(user.getEmail()))
		{
			throw new UserServiceException("用户名或邮箱错误");
		}
		String id = user.getId();
		String pwd = user.getPass();
		String url = getUserURL(id, pwd);
		System.out.println(url);
		String model = IOUtil.fileReaderStringHandle(Config.PROJECT_PATH+"model/email.html");
		model = ReplaceUtil.replace(model, url);
		SendEmail.send("找回密码-"+Config.TITLE, email, model);
	}
	
	private static String getUserURL(String id,String pwd){
		String url = Config.BASE_URL+"repass/callback.html?t=";
		url+=Md5.saltMD5(id+pwd);
		url+="&u="+id;
		return url;
	}
	public User checkEmailRepass(String sal, String id) {
		if(sal==null||sal.trim().isEmpty())
		{
			throw new UserServiceException("该地址已失效");
		}
		if(id==null||id.trim().isEmpty())
		{
			throw new UserServiceException("该地址已失效");
		}
		User user = dao.findUserById(id);
		if(user==null)
		{
			throw new UserServiceException("该地址已失效");
		}
		String pwd = user.getPass();
		if(!sal.equals(Md5.saltMD5(id+pwd))){
			throw new UserServiceException("该地址已失效");
		}
		return user;
	}
}
