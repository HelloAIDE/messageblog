package com.blog.util;

public class SafeUtil {
	public static String strReplaceSensitive(String str){
		String str1 ;
		str=str.replaceAll("<script>", "&lt;script&gt;");
		str=str.replaceAll("</script>", "&lt;/script&gt;");
		str=str.replaceAll("<script", "&lt;script");
		str=str.replaceAll("script>", "script&gt;");
		str=str.replaceAll("</script", "&lt;/script");
		return str;
	}
}
