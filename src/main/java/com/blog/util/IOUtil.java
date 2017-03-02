package com.blog.util;
/**
 * @author  大牛哥 
 * @E-mail: 201309512@qq.com 
 * @date 创建时间：2016年12月25日 下午4:28:41
 * @version 1.0
 * @parameter
 * @since
 * @return  */

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 将文件读入到一个String，利用FileReader+BufferedReader(提供readLine方法)
 *
 * @param fileName
 * @return String
 */
public class IOUtil {
	public static String fileReaderStringHandle(String fileName) {
		StringBuilder sb = new StringBuilder();
		try {
			BufferedReader in = new BufferedReader(new FileReader(new File(fileName).getAbsoluteFile()));
			try {
				String s;
				while ((s = in.readLine()) != null) {
					sb.append(s);
					sb.append("\n");
				}
			} finally {
				in.close();
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return sb.toString();
	}

	/**
	 * 使用FileInputStream+BufferedInputStream以byte的方式处理文件
	 *
	 * @param fileName
	 * @return byte[]
	 */
	public static byte[] fileReaderByteHandle(String fileName) {
		byte[] data = null;
		try {
			BufferedInputStream bf = new BufferedInputStream(new FileInputStream(fileName));
			try {
				data = new byte[bf.available()];
				bf.read(data);

			} finally {
				bf.close();
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return data == null ? new byte[] {} : data;
	}

	/**
	 * 将指定的text写入到文件名为fileName的文件中
	 *
	 * @param fileName
	 * @param text
	 */
	public static void fileWriterHandle(String fileName, String text) {
		try {
			PrintWriter out = new PrintWriter(new File(fileName).getAbsoluteFile());
			try {
				out.print(text);
			} finally {
				out.close();
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static void main(String[] args) throws IOException {
		System.out.print(fileReaderStringHandle("src/IOUtil.java"));

		for (byte b : fileReaderByteHandle("src/IOUtil.java"))
			System.out.print(b);

		fileWriterHandle("zj.txt", fileReaderStringHandle("src/IOUtil.java"));
	}
}