package com.example.common.tool.resolve;

import java.lang.annotation.*;

/**
 * �Զ���������ע��
 */
@Target(ElementType.METHOD)    
@Retention(RetentionPolicy.RUNTIME)    
@Documented   
@Inherited  
public @interface ServiceType {
	String value();/*��ʶ*/
	String desc() default "��������Ϣ";/*����������*/   
}
