package com.wholesmart.commom.config;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.wholesmart.common.util.PropertiesLoader;
import com.wholesmart.common.util.StringUtils;
 
/** 
 *  The constant for hurtplatform runtime.
 * @author kangming.ning [ningkangming@126.com]
 * 
 */
public class Constants {
	
	private Constants(){}
	
	/**
	 * 当前对象实例
	 */
	private static Constants constant=new Constants();
	
	
    /**
     * 系统版本
     * */
	public static final String SYS_VERSION = "V1.0.001";
	
	
	/**
	 * 获取当前对象实例
	 */
	public static Constants getInstance() {
		return constant;
	}
	
	/**
	 * 保存全局属性值
	 */
	private static Map<String, String> map =new ConcurrentHashMap<>();
	
	
	/**
	 * 属性文件加载对象
	 */
	private static PropertiesLoader loader = new PropertiesLoader("config.properties");
	
	/**
	 * 上传文件基础虚拟路径
	 */
	public static final String USERFILES_BASE_URL ="upload";
	
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
	
	/**
	 * user-agent为mobile
	 */
	public final static String MOBILE = "mobile";
	
	/**
	 * 用户名
	 */
	public final static String USERNAME = "userName";
	
	/**
	 * 用户名
	 */
	public final static String LOGINURL = "/account/login";
	
	/**
	 * 密码名
	 */
	public final static String PASSWORD = "password";
	
	/**
	 * 移动用户
	 */
	public final static String MOBILEUSER = "mobileuser";
	
	/**
	 * 注册失败
	 */
	public final static String SHIRO_REGISTER_CODE_NAME = "shiroRegisterFailure";
	

	/**
	 * ehcache 全局过期时间默认设置
	 * */
	public static final Integer TIMETOLIVESECONDS =3600;//一小时不访问清除ehcache的缓存
	
	
	
}
