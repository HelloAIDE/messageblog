package com.blog.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

import com.blog.entity.User;
import com.blog.service.exception.UserServiceException;

public class UserUtil {
	public  static  String timeToString(long time,String dataformat){
		SimpleDateFormat sdf = new SimpleDateFormat(dataformat);
		String data = sdf.format(time);
		return data;
		
		
	}
	
	public  static  String timeToString(long time){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
		String data = sdf.format(time);
		return data;
		
		
	}
	
	public  static  String getStringTime(){
		long time = System.currentTimeMillis();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
		String data = sdf.format(time);
		return data;
		
		
	}
	
	public static void updateSession(HttpServletRequest req,User user){
		String ua = req.getHeader("user-agent");
		long time = System.currentTimeMillis();
		String uid =user.getId();
		String token = time+"|"+Md5.saltMD5(time+ua+uid);
		System.out.println(time);
		System.out.println(token);
		req.getSession().setAttribute("token",token );
		req.getSession().setAttribute("u",user);
	}
	public static boolean isLogin(HttpServletRequest req) {
		String value = (String) req.getSession().getAttribute("token"); 
		if(value==null)
		{
			throw new UserServiceException("用户未登录");
		}
		System.out.println((User)req.getSession().getAttribute("u"));
		String uid = ((User)req.getSession().getAttribute("u")).getId();
		String[] data = value.split("\\|");
		String time = data[0];
		String md5 = data[1];
		String userAgent = req.getHeader("User-Agent");
		if (md5.equals(Md5.saltMD5(time + userAgent+uid))) {
			return true;
		}
		return false;
	}
	public static User sessionGetUser(HttpServletRequest req){
		User user = (User) req.getSession().getAttribute("u");
		if(user==null)
		{
			throw new UserServiceException("用户未登录");
		}
		return user;
		
	}
	public static String getRandomName(){
		String name = Config.BeforeName+System.currentTimeMillis();
		return name;
	}
	public static boolean openFile(String path){
		File file=new File(path);    
		if(!file.exists())    
		{    
			File file1 =new File(file.getParent());    
			//如果文件夹不存在则创建    
			if  (!file1 .exists()  && !file1 .isDirectory())      
			{   //目录不存在创建目录
			    file1 .mkdirs();    
			} else   
			{  
			    //目录存在不做任何操作
			}  
		    try {    
		        file.createNewFile();    
		        return true;
		    } catch (IOException e) {    
		        // TODO Auto-generated catch block    
		        e.printStackTrace();    
		        return false;
		    }    
		}    
		  return false;
	}
}
