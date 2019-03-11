package com.wholesmart.webcommon.aop;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.feilong.core.Validator;
import com.wholesmart.common.annotation.ApiLog;
import com.wholesmart.common.shiro.entity.ShiroUser;
import com.wholesmart.common.web.session.SessionUtil;
import com.wholesmart.entity.Log;


/**
 * Controller 操作日志切面
 * @author kangming.ning
 * @since 2017-09-19
 * */
@Aspect
@Component
public class WebLogAspect {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Pointcut("execution (* com.wholesmart.controller..*.*(..))&&@annotation(com.wholesmart.common.annotation.ApiLog)")  
    public  void controllerAspect() { 
   	 
    }
	
	@SuppressWarnings("rawtypes")
	@Around(value="controllerAspect()")
	public Object doWebLog(ProceedingJoinPoint joinPoint) throws Throwable {
		Object returnVal;
		String targetName = joinPoint.getTarget().getClass().getName(); 
		String methodName = joinPoint.getSignature().getName();
		Long logId=0L;
		Object[] arguments = joinPoint.getArgs();  
		if (methodName.equals("login") || methodName.equals("doLogin")) {
			return joinPoint.proceed();
		}
		try {
			ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
					.getRequestAttributes();
			HttpServletRequest request = attributes.getRequest();
			String methodToUse = joinPoint.getSignature().getDeclaringTypeName() + "."
					+ joinPoint.getSignature().getName();
			Class targetClass = Class.forName(targetName);
			Method[] methods = targetClass.getMethods();
			for (Method method : methods) {
				if (method.getName().equals(methodName)) {
					Class[] clazzs = method.getParameterTypes();
					if (clazzs.length == arguments.length) {//方法签名和参数列表都相同 确定是此方法
						Annotation[] methodAnnotations = method.getDeclaredAnnotations();
						if(methodAnnotations.length==0) {
							logger.debug("no Annotations Found");
							break;//直接退出循环
						}
						if(method.isAnnotationPresent(ApiLog.class)) {//有此注解
							ApiLog an = method.getAnnotation(ApiLog.class);
							String opMsg = an.value();//操作日志信息
							String opType = an.operationType();//操作类型
							String opModel = an.module();//操作模块
							Log log=new Log();
							log.setOperaRecord(opMsg);
							log.setOperaStlye(opType);
							log.setOperaModel(opModel);
							Long id = insertLog(request, log);
							logId=id;
						}
					}
				}
			}
			logger.info("URL : " + request.getRequestURL().toString());
			logger.info("HTTP_METHOD : " + request.getMethod());
			logger.info("IP : " + request.getRemoteAddr());
			logger.info("CLASS_METHOD : " + methodToUse);
			logger.info("ARGS : " + Arrays.toString(arguments));
			returnVal = joinPoint.proceed();
			updateWebLog(logId, "操作成功");
		} catch (Exception e) {
			updateWebLog(logId,e.getMessage());
			throw new RuntimeException(e);
		}
		return returnVal;
	}
	
	private Long insertLog(HttpServletRequest request,Log log) {
		ShiroUser shiroUser = SessionUtil.getloginUser();
		if(Validator.isNotNullOrEmpty(shiroUser)){
			log.setUserId(Long.valueOf(shiroUser.getId()));
			log.setRelname(shiroUser.getRealName());
			log.setUsername(shiroUser.getAccount());
		}
		log.setOperaTime(new Date());
		log.setUserIp(request.getRemoteAddr());
		log.insert();
		return log.getId();
	}
	

	private void updateWebLog(Long id, String remark) {
		Log log = new Log();
		log.setId(id);
		log.setOperaResult(remark);
		log.updateById();
	}


}
