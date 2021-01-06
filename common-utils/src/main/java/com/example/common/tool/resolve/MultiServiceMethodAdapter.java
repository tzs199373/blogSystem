package com.example.common.tool.resolve;

import com.example.common.tool.json.JSONArray;
import com.example.common.tool.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 功能：服务调用分发器
 */
public class MultiServiceMethodAdapter {
    private  static final Logger logger = LoggerFactory.getLogger(MultiServiceMethodAdapter.class);

    public static RstData invoke(JoinParams joinParam, BeanFactory beanFactory) {
        RstData rstData = null;
        String serviceType = joinParam.getServiceType();
        String serializeId = SerializeIdUtil.getSerial(joinParam.getChannel(),serviceType);
        try {
            List<String> list = (List<String>) joinParam.getParams().values().stream().map(obj -> {
                if (obj instanceof Map) {
                    return JSONObject.fromObject(obj).toString();
                } else if (obj instanceof List) {
                    return JSONArray.fromObject(obj).toString();
                } else {
                    return obj.toString();
                }
            }).collect(Collectors.toList());
            if (serviceType == null && serviceType.length() < 7) {
                throw new Exception("MultiServiceMethodAdapter: ServiceType is format error ：" + serviceType);
            }
            String serviceName = serviceType.substring(0, 6);
            boolean containBean = beanFactory.containsBean(serviceName);
            if (!containBean) {
                throw new Exception("MultiServiceMethodAdapter: Service does not exist ：" + serviceType);
            }
            Object delegate = beanFactory.getBean(serviceName);
            Method method = ServiceBeanMethodResolver.getHandlerMethod(delegate, serviceType);
            if (method == null) {
                throw new Exception("MultiServiceMethodAdapter: Service without this method ：" + serviceType);
            }
            Class<?> c = method.getDeclaringClass();
            String className = c.getName();
            ServiceType type = ServiceBeanMethodResolver.getServiceType(serviceType);
            String busId = type.value();
            String desc = type.desc();
            String args = JSONArray.fromObject(list).toString();
            logger.info(className + "  " + busId + " " + desc);
            Object[] objs = ServiceMsgConverter.argsTypeConvertor(method, list.toArray());
            rstData = (RstData) method.invoke(delegate, objs);
        } catch (Exception e) {
            String errorStr = "MultiServiceMethodAdapter: Service call failure：" + serviceType + " " + handleException(e);
            logger.error(errorStr);
            if(rstData ==null){
                rstData = new RstData();
            }
            rstData.setCode(ResultStatus.ERROR);
            rstData.setMsg(errorStr);
        }finally {
            rstData.setSerializeId(serializeId);
            rstData.setServiceType(serviceType);
        }
        return rstData;
    }

    private static String handleException(Exception e) {
        String msg = null;
        if (e instanceof InvocationTargetException) {
            Throwable targetEx = ((InvocationTargetException) e).getTargetException();
            if (targetEx != null) {
                msg = targetEx.getMessage();
            }
        } else {
            msg = e.getMessage();
        }
        return msg;
    }
}
