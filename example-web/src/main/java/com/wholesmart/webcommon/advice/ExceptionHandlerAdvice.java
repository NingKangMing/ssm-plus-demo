package com.wholesmart.webcommon.advice;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.wholesmart.common.exception.ServiceException;
import com.wholesmart.common.web.ResponseUtil;

/**
 * Controller 异常处理器
 * @author kangming.ning
 * @since 2017-12-01
 * */
@ControllerAdvice
public class ExceptionHandlerAdvice {
	
	@ExceptionHandler(value = { Exception.class, RuntimeException.class })
	public void handleErrors(HttpServletRequest request,
			HttpServletResponse response, Exception e) throws Exception {
		System.out.println(request.getRequestURI() + "错误：");
		e.printStackTrace();

		int resultCode = 1020101;
		StringBuffer sf = new StringBuffer("");
		String resultMsg = "接口内部异常";
		String detailMsg = "";

		if (e instanceof MissingServletRequestParameterException
				|| e instanceof BindException) {
			BindException result=(BindException)e;
			List<FieldError> fieldErrors = result.getFieldErrors();
			for (FieldError err : fieldErrors) {
				String field = err.getField();//字段名称
				String message = err.getDefaultMessage();//绑定的错误消息
				sf.append(field+":"+message+";");
			}
			resultCode = 1010101;
			resultMsg=sf.toString();
		} else if (e instanceof ServiceException) {
			ServiceException ex = ((ServiceException) e);

			resultCode = null == ex.getResultCode() ? 0 : ex.getResultCode();
			resultMsg = ex.getMessage();
		} else {
			detailMsg = e.getMessage();
		}

		Map<String, Object> map = Maps.newHashMap();
		map.put("resultCode", resultCode);
		map.put("resultMsg", resultMsg);
		map.put("detailMsg", detailMsg);

		String text = JSON.toJSONString(map);

		ResponseUtil.output(response, text);
	}

}
