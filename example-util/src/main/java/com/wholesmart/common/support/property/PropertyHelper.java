package com.wholesmart.common.support.property;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.wholesmart.common.util.PropertiesLoader;
import com.wholesmart.common.util.StringUtils;

/** 
 *  读取属性文件的键值工具类。默认读取config.properties文件。需要读取更多属性文件查看{@link com.wholesmart.common.util.PropertiesLoader} 
 * @author kangming.ning [ningkangming@126.com]
 * @since 2017-09-14
 */
public class PropertyHelper {
	
	/**
	 * 属性文件加载对象
	 */
	private static PropertiesLoader loader = new PropertiesLoader("config.properties");
	
	/**
	 * 保存全局属性值
	 */
	private static Map<String, String> map =new ConcurrentHashMap<>();
	
	/**
	 * 获取配置
	 */
	public static String getConfig(String key) {
		String value = map.get(key);
		if (value == null){
			value = loader.getProperty(key);
			map.put(key, value != null ? value : StringUtils.EMPTY);
		}
		return value;
	}

}
