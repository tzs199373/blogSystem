package com.example.common.tool.resolve;

import java.lang.annotation.*;

/**
 * 自定义服务调用注解
 */
@Target(ElementType.METHOD)    
@Retention(RetentionPolicy.RUNTIME)    
@Documented   
@Inherited  
public @interface ServiceType {
	String value();/*标识*/
	String desc() default "无描述信息";/*服务功能描述*/   
}
