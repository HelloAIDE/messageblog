package com.blog.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * MySQL数据库的备份与恢复  缺陷：可能会被杀毒软件拦截
 * 
 * @author 大牛哥
 * @version 1.0
 */
public class DatabaseBackup {
	/** MySQL安装目录的Bin目录的绝对路径 */
	private String mysqlBinPath;
	/** 访问MySQL数据库的用户名 */
	private String username;
	/** 访问MySQL数据库的密码 */
	private String password;

	public String getMysqlBinPath() {
		return mysqlBinPath;
	}

	public void setMysqlBinPath(String mysqlBinPath) {
		this.mysqlBinPath = mysqlBinPath;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public DatabaseBackup(String mysqlBinPath, String username, String password) {
		if (!mysqlBinPath.endsWith(File.separator)) {
			mysqlBinPath = mysqlBinPath + File.separator;
		}
		this.mysqlBinPath = mysqlBinPath;
		this.username = username;
		this.password = password;
	}

	/**
	 * 备份数据库
	 * 
	 * @param output
	 *            输出流
	 * @param dbname
	 *            要备份的数据库名
	 */
	public void backup(OutputStream output, String dbname) {
		String command = "cmd /c " + mysqlBinPath + "mysqldump -u" + username + " -p" + password
				+ " --set-charset=utf8 " + dbname;
		PrintWriter p = null;
		BufferedReader reader = null;
		try {
			p = new PrintWriter(new OutputStreamWriter(output, "utf8"));
			Process process = Runtime.getRuntime().exec(command);
			InputStreamReader inputStreamReader = new InputStreamReader(process.getInputStream(), "utf8");
			reader = new BufferedReader(inputStreamReader);
			String line = null;
			while ((line = reader.readLine()) != null) {
				p.println(line);
			}
			p.flush();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
				if (p != null) {
					p.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 备份数据库，如果指定路径的文件不存在会自动生成
	 * 
	 * @param dest
	 *            备份文件的路径
	 * @param dbname
	 *            要备份的数据库
	 */
	public boolean backup(String dest, String dbname) {
		try {
			OutputStream out = new FileOutputStream(dest);
			backup(out, dbname);
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 恢复数据库
	 * 
	 * @param input
	 *            输入流
	 * @param dbname
	 *            数据库名
	 */
	public boolean restore(InputStream input, String dbname) {
		String command = "cmd /c " + mysqlBinPath + "mysql -u" + username + " -p" + password + " " + dbname;
		try {
			Process process = Runtime.getRuntime().exec(command);
			OutputStream out = process.getOutputStream();
			String line = null;
			String outStr = null;
			StringBuffer sb = new StringBuffer("");
			BufferedReader br = new BufferedReader(new InputStreamReader(input, "utf8"));
			while ((line = br.readLine()) != null) {
				sb.append(line + "/r/n");
			}
			outStr = sb.toString();
			OutputStreamWriter writer = new OutputStreamWriter(out, "utf8");
			writer.write(outStr);
			writer.flush();
			out.close();
			br.close();
			writer.close();
			return true;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 恢复数据库
	 * 
	 * @param dest
	 *            备份文件的路径
	 * @param dbname
	 *            数据库名
	 * @return 
	 */
	public boolean restore(String dest, String dbname) {
		try {
			InputStream input = new FileInputStream(dest);
			restore(input, dbname);
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static void back() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-mybatis.xml");
		org.apache.commons.dbcp.BasicDataSource ds = (org.apache.commons.dbcp.BasicDataSource) ctx
				.getBean("dataSource");
		String userName = ds.getUsername();
		String pwd = ds.getPassword();
		String binPath =  Config.MYSQL_BIN_PATH;
		String dbName = "blog";
		String dbPath = Config.BACKUP_PATH+UserUtil.timeToString(System.currentTimeMillis(), "yyyyMMddHHmmss")+".sql";
		DatabaseBackup bak = new DatabaseBackup(binPath, userName, pwd);
		System.out.println("正在备份数据库" + dbName + "到：" + dbPath);
		if (bak.backup(dbPath, dbName)) {
			System.out.println("备份成功");
		}else{
			try {
				throw new Exception("备份失败");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// bak.restore("c:/ttt.sql", "blog");
	}

	public static void restore(String dbPath) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-mybatis.xml");
		org.apache.commons.dbcp.BasicDataSource ds = (org.apache.commons.dbcp.BasicDataSource) ctx
				.getBean("dataSource");
		String userName = ds.getUsername();
		String pwd = ds.getPassword();
		String binPath = Config.MYSQL_BIN_PATH;
		String dbName = "blog";
		DatabaseBackup bak = new DatabaseBackup(binPath, userName, pwd);
		System.out.println("正在从"+dbPath+"还原数据库" + dbName );
		if (bak.restore(dbPath, dbName)) {
			System.out.println("还原成功");
		}else{
			try {
				throw new Exception("备份失败");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public static List<String> getAllBack(){
		File file = new File(Config.BACKUP_PATH);
		File[] files = file.listFiles(new FileFilter() {
			
			public boolean accept(File pathname) {
				if(pathname.getName().endsWith(".sql")){
					return true;
				}
				return false;
			}
		});
		List<String> list =new ArrayList<String>();
		for (File file1 : files) {
			list.add(file1.getName());
		}
		return list;
	}
	public static void main(String[] args) {
//		back();
		for (String file : getAllBack()) {
			System.out.println(file);
		}
//		restore();
	}
}