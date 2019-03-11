package com.wholesmart.common.web.session;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.wholesmart.common.shiro.entity.ShiroUser;


/**
 * 
 * Shiro的session工具
 * */
public class SessionUtil {
	
	/**
	 * 获取登录用户的userId
	 * @return
	 */
	public static String getloginUserId()
	{
		Subject subject = SecurityUtils.getSubject();
		ShiroUser sessionUser = (ShiroUser)subject.getSession().getAttribute("userSession");
		return sessionUser.getId();
	}
	
	/**
	 * 获取登录用户的userId
	 * @return
	 */
	public static String getloginUserAccountName()
	{
		Subject subject = SecurityUtils.getSubject();
		ShiroUser sessionUser = (ShiroUser)subject.getSession().getAttribute("userSession");
		return sessionUser.getAccount();
	}
	
	/**
	 * 获取登录用户
	 * @return
	 */
	public static ShiroUser getloginUser()
	{
		Subject subject = SecurityUtils.getSubject();
		return (ShiroUser)subject.getSession().getAttribute("userSession");
	}
	
	
	/**
	 * 返回用户的IP地址
	 * @param request
	 * @return
	 */
	public static String getUserIP() {
		Subject subject = SecurityUtils.getSubject();
		return subject.getSession().getHost();
	}
	
	
	public static Object getAttr(Object key) {
		Subject subject = SecurityUtils.getSubject();
		return subject.getSession().getAttribute(key);
	}

}
