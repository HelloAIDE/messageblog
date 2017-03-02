package com.blog.util;

import java.util.Iterator;
import java.util.Map;

/**
 * @author 大牛哥
 * @E-mail: 201309512@qq.com
 * @date 创建时间：2016年12月25日 下午4:30:39
 * @version 1.0
 * @parameter
 * @since
 * @return
 */

public class ReplaceUtil {
	public static String replace(String str, String url) {
		str = str.replace("{data}", UserUtil.getStringTime());
		str = str.replace("{repassurl}", url);
		str = str.replace("{url}", Config.BASE_URL);
		str = str.replace("{title}", Config.TITLE);
		return str;
	}

	public static String replace(String str, Map<String, String> map) {
		Iterator it = map.entrySet().iterator();
		while (it.hasNext()) {
			java.util.Map.Entry entry = (java.util.Map.Entry) it.next();
			String key = (String) entry.getKey();
			String value = (String) entry.getValue();
			str = str.replace(key, value);
		}
		return str;
	}
}
