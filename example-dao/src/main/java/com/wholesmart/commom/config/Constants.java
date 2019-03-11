package com.wholesmart.commom.config;

/** 
 *  The constant for platform runtime.
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
	 * 上传文件基础虚拟路径
	 */
	public static final String USERFILES_BASE_URL ="upload";
	
	
	/**
	 * 用户名
	 */
	public final static String USERNAME = "userName";
	
	/**
	 * 用户名
	 */
	public final static String LOGINURL = "/login";
	
	/**
	 * 密码名
	 */
	public final static String PASSWORD = "password";
	
	/**
	 * 表数据的正常状态
	 * */
	public final static int STATUSNORMAL=0;
	
	/**
	 * 表数据的删除状态
	 * */
	public final static int STATUSDELETE=1;

	/**
	 * ehcache 全局过期时间默认设置
	 * */
	public static final Integer TIMETOLIVESECONDS =3600;//一小时不访问清除ehcache的缓存
	
	/**
	 * 无雨
	 * */
	public final static int NORAIN=0;
	
	/**
	 * 小雨
	 * */
	public final static int SMALLRAIN=1;
	
	/**
	 * 中雨
	 * */
	public final static int MIDDLERAIN=2;
	
	/**
	 * 大雨
	 * */
	public final static int BIGRAIN=3;
	
	
}
