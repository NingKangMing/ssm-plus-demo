package com.wholesmart.interceptor;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.NamedThreadLocal;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.wholesmart.common.util.DateUtils;

/**
 * 请求信息日志打印，打印JVM内存相关信息，方便观察当前应用在JVM中的内存使用情况
 * 
 * @author kangming.ning
 * 
 * @version 2017-03-08
 * 
 */
public class JvmLogInterceptor implements HandlerInterceptor{

	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	private boolean debug = true;//是否打印jvm内存消息

	private static final ThreadLocal<Long> TIME_THREAD_LOCAL=new NamedThreadLocal<Long>("ThreadLocalStartTime");

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		if (debug){
			long beginTime = System.currentTimeMillis();//1、开始时间  
			TIME_THREAD_LOCAL.set(beginTime);//线程绑定变量（该数据只有当前请求的线程可见）  
			logger.info("请求开始: {}  URI: {}", new SimpleDateFormat("hh:mm:ss.SSS").format(beginTime), request.getRequestURI());
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		if (modelAndView != null&&debug){
			logger.info("ViewName: " + modelAndView.getViewName());
		}
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
					throws Exception {
		// 打印JVM信息。
		if (debug){
			long beginTime = TIME_THREAD_LOCAL.get();//得到线程绑定的局部变量（开始时间）  
			long endTime = System.currentTimeMillis(); 	//2、结束时间  
			logger.info("请求结束：{}  耗时：{}  URI: {}  最大内存: {}m  已分配内存: {}m  已分配内存中的剩余空间: {}m  最大可用内存: {}m",
					new SimpleDateFormat("hh:mm:ss.SSS").format(endTime), DateUtils.formatDateTime(endTime - beginTime),
					request.getRequestURI(), Runtime.getRuntime().maxMemory()/1024/1024, Runtime.getRuntime().totalMemory()/1024/1024, Runtime.getRuntime().freeMemory()/1024/1024, 
					(Runtime.getRuntime().maxMemory()-Runtime.getRuntime().totalMemory()+Runtime.getRuntime().freeMemory())/1024/1024); 
			TIME_THREAD_LOCAL.remove();//把绑定的变量remove
		}
	}


	public void setDebug(boolean debug) {
		this.debug = debug;
		if (!debug) {
			logger.info("Disable Print JVM Massage!");
		}
	}

}
