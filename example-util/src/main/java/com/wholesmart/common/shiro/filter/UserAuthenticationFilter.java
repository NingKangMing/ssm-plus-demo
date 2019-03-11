package com.wholesmart.common.shiro.filter;


import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.web.filter.authc.AuthenticationFilter;

/**
 * 用户验证过滤器   通用处理 如有特殊需要请另写一个重新配置
 * @author kangming.ning
 * */
public class UserAuthenticationFilter extends AuthenticationFilter{

	String fallbackUrl="/account/index";
	
	@Override
	protected boolean onAccessDenied(ServletRequest request,
			ServletResponse response) throws Exception {//如果用户未验证将调用
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		//直接跳回登陆页面
		//redirectToLogin(request, response); 
		String contextPath = req.getContextPath();
		String sendUrl=contextPath+fallbackUrl;
		res.getWriter().write(("<script>parent.location.href='"+sendUrl+"'</script>"));
		return false;//false表示Filter处理完毕
	}  

}

