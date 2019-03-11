package com.wholesmart.controller;

import java.beans.PropertyEditorSupport;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.wholesmart.common.util.DateUtils;
import com.wholesmart.common.util.StringUtils;


/** 
 * 控制器基类
 * @author kangming.ning 
 * 
 */
public abstract class BaseController{

	protected   Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 初始化数据绑定
	 * 1. 将所有传递进来的String进行HTML编码，防止XSS攻击
	 * 2. 将字段中Date类型转换为String类型
	 */
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		// String类型转换，将所有传递进来的String进行HTML编码，防止XSS攻击
		binder.registerCustomEditor(String.class, new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) {
				setValue(text == null ? null : StringEscapeUtils.escapeHtml4(text.trim()));
			}
			@Override
			public String getAsText() {
				Object value = getValue();
				return value != null ? value.toString() : "";
			}
		});
		// Date 类型转换
		binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) {
				if(!StringUtils.isNullOrBlank(text)) {
					setValue(DateUtils.parseDate(text));
				}
				
			}
		});
	}

	/**
	 * 是否POST请求
	 * */
	protected boolean isPost(HttpServletRequest request){
		return "POST".equals(request.getMethod().toUpperCase());
	}

	/**
	 * 是否ajax请求
	 * */
	protected boolean isAjax(HttpServletRequest request){
		return "XMLHttpRequest".equals(request.getHeader("x-requested-with"));
	}
	
	/**
	 * 获取所有请求参数
	 * @param request
	 * @return
	 */
	protected Map<String, Object> getAllParams(HttpServletRequest request) {
		Map<String,Object> params=new HashMap<String, Object>();
		Enumeration<String> paramNames=request.getParameterNames();
		while(paramNames.hasMoreElements()){
			String name=paramNames.nextElement();
			params.put(name, request.getParameter(name));
		}
		return params;
	}
}
