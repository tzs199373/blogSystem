package com.example.common.tool.resolve;

import org.springframework.core.annotation.AnnotationUtils;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class ServiceBeanMethodResolver
{
	private final static Map<String, Method> HANDLER_METHOD_MAP = new HashMap<String, Method>();
	
	private final static Map<String, ServiceType> SERVICE_MAP = new HashMap<String, ServiceType>();

	public static void registerHandlerMethods(Object delegate)
	{
		Class<?> delegateClass = delegate.getClass();
		Method[] methods = delegateClass.getDeclaredMethods();
		for (Method method : methods)
		{
			ServiceType annotation = AnnotationUtils.findAnnotation(method, ServiceType.class);
        	if(annotation!=null)
        	{
        		String serviceType=annotation.value();
				HANDLER_METHOD_MAP.put(serviceType, method);
				SERVICE_MAP.put(serviceType, annotation);
        	}
		}
	}

	public  static ServiceType  getServiceType(String serviceType)
	{
		return SERVICE_MAP.get(serviceType);
	}
	
	public static Method getHandlerMethod(Object delegate,String serviceType) 
	{
		Method method = null;
		if(serviceType!=null && !"".equals(serviceType))
		{
			method = HANDLER_METHOD_MAP.get(serviceType);
		}
		if(method==null)
		{
			registerHandlerMethods(delegate);
			method = HANDLER_METHOD_MAP.get(serviceType);
		}
		return method;
	}
}
