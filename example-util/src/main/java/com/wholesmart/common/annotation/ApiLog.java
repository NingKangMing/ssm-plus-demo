package com.wholesmart.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 日志注解类
 * @author kangming.ning
 * @since 2017-09-19
 * */
@Target(ElementType.METHOD)  
@Retention(RetentionPolicy.RUNTIME)  
@Documented 
public @interface ApiLog {
	
	 /**
     * <p>
     * 日志信息、操作信息
     * </p>
     */
    String value() default "";
    
    /**
     * <p>
     * 日志操作类型
     * </p>
     */
    String operationType() default "";
    
    /**
     * <p>
     * 日志操作模块
     * </p>
     */
    String module() default "";
    

}
