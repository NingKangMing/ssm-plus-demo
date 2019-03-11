package com.wholesmart.webcommon.aop;

import java.util.Arrays;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.feilong.core.Validator;
import com.wholesmart.common.web.session.SessionUtil;
import com.wholesmart.entity.LoginLog;

/**
 * Controller 登录日志切面
 * @author kangming.ning
 * @since 2017-09-19
 * */
@Aspect
@Component
public class LoginAspect {
	
   private Logger logger = LoggerFactory.getLogger(getClass());
   
   @Pointcut(value="execution(* com.wholesmart.controller.IndexController.doLogin(..))))")
   private void loginLog() {
   }
   
   @Before("loginLog()")
   public void doBefore(JoinPoint joinPoint) {
       ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
       HttpServletRequest request = attributes.getRequest();
       logger.info("URL : " + request.getRequestURL().toString());
       logger.info("HTTP_METHOD : " + request.getMethod());
       logger.info("IP : " + request.getRemoteAddr());
       logger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
       logger.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));
   }
   
   @After("loginLog()")
   public void doAfter() {
   	String account = (String)SessionUtil.getAttr("account");
   	String msg = (String)SessionUtil.getAttr("msg");
   	 if(Validator.isNotNullOrEmpty(msg)){
   		 this.writeLoginLog("登录失败",msg,account);
        }else{
       	 this.writeLoginLog("登录成功",null,account);
        }
   }
   
   private void writeLoginLog(String status,String msg,String account) {
	   LoginLog loginLog = new LoginLog();
       loginLog.setStatus(status);
       loginLog.setAccountName(account);
       loginLog.setCreateTime(new Date());
       loginLog.setIp(SessionUtil.getUserIP());
       loginLog.setRemark(msg);
       loginLog.insert();
   }

}
