package com.blog.util;
/**
 * @author  大牛哥 
 * @E-mail: 201309512@qq.com 
 * @date 创建时间：2016年12月25日 下午2:35:48
 * @version 1.0
 * @parameter
 * @since
 * @return  */

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * 发送普通邮件
 * @author  大牛哥 
 * @E-mail: 201309512@qq.com 
 * @date 创建时间：2016年12月25日 下午2:25:47
 * @version 1.0
 * @parameter
 * @since
 * @return  */
public class SendEmail {
	/*
	 * 需要一个main.jar包
	 * 发件人的@后一定要和服务器的一致 例如 smtp.126.com
	 *********** 
	 * @126.com smtp.163.com
	 *********** 
	 * @163.com smtp.qq.com **********@qq.com
	 *
	 * 
	 */

	public static final String HOST = "smtp.163.com";
	public static final String PROTOCOL = "smtp";
	public static final int PORT = 25;
	public static final String FROM = "difan_jcw@163.com";// 发件人的email
	public static final String PWD = "10086com";// 发件人密码

	/**
	 * 获取Session
	 * 
	 * @return
	 */
	private static Session getSession() {
		Properties props = new Properties();
		props.put("mail.smtp.host", HOST);// 设置服务器地址
		props.put("mail.store.protocol", PROTOCOL);// 设置协议
		props.put("mail.smtp.port", PORT);// 设置端口
		props.put("mail.smtp.auth", true);

		Authenticator authenticator = new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(FROM, PWD);
			}

		};
		Session session = Session.getDefaultInstance(props, authenticator);

		return session;
	}

	/*
	 * @parameter title 邮件的主题
	 * 
	 * @parameter toEmail 收件人的邮箱
	 * 
	 * @parameter content 发送的内容
	 */
	public static void send(String title, String toEmail, String content) {
		Session session = getSession();
		try {
			System.out.println("--send--" + content);
			// Instantiate a message
			Message msg = new MimeMessage(session);

			// Set message attributes
			msg.setFrom(new InternetAddress(FROM));
			InternetAddress[] address = { new InternetAddress(toEmail) };
			msg.setRecipients(Message.RecipientType.TO, address);
			msg.setSubject(title);
			msg.setSentDate(new Date());
			msg.setContent(content, "text/html;charset=utf-8");
			// Send the message
			Transport.send(msg);
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}
	public static void main(String[] args) {
		String cont = IOUtil.fileReaderStringHandle(Config.PROJECT_PATH+"model/test.html");
		send("激活信息11", "difanjcw@qq.com", "tset");
	}
}