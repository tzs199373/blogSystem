package com.example.common.tool.json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.example.common.tool.string.StringUtil;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Iterator;

public class JsonUtils {

    /**
     * 对象返回Json字符串
     * @param obj
     * @param <T>
     * @return
     */
    public static <T> String beanToJson(T obj) {
        if (obj == null) {
            return null;
        }
        try {
            return obj instanceof String ? (String) obj : JSONConfig.getInstance().writeValueAsString(obj);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 对象返回格式化后的Json字符串
     * @param obj
     * @param <T>
     * @return
     */
    public static <T> String beanToJsonPretty(T obj) {
        if (obj == null) {
            return null;
        }
        try {
            return obj instanceof String ? (String) obj : JSONConfig.getInstance().writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Json字符串转成对象
     * @param str
     * @param clazz
     * @param <T>
     */
    public static <T> T jsonToBean(String str, Class<T> clazz) {
        if (StringUtils.isEmpty(str) || clazz == null) {
            return null;
        }
        try {
            return clazz.equals(String.class) ? (T) str : JSONConfig.getInstance().readValue(str, clazz);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 泛型反序列化
     * @param str
     * @param typeReference 对应返回值的类型
     * @param <T>
     * @return
     */
    public static <T> T collectionToBean(String str, TypeReference<T> typeReference) {
        if (StringUtils.isEmpty(str) || typeReference == null) {
            return null;
        }

        try {
            return (T) (typeReference.getType().equals(String.class) ? str : JSONConfig.getInstance().readValue(str, typeReference));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 泛型反序列化
     * @param str
     * @param collectionClass 集合的类型
     * @param elementClasses 集合中元素的类型
     * @param <T>
     * @return
     */
    public static <T> T collectionToBean(String str, Class<?> collectionClass, Class<?>... elementClasses) {
        JavaType javaType = JSONConfig.getInstance().getTypeFactory().constructParametricType(collectionClass, elementClasses);
        try {
            return JSONConfig.getInstance().readValue(str, javaType);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * json转url参数
     * @param url
     * @param json
     * @return
     */
    public static String jsonToUrl(String url,JSONObject json){
        String uri = "";
        try{
            Iterator iter =  json.keys();
            if(json.size() > 0 ){
                StringBuffer sb = new StringBuffer(url+"?");
                while(iter.hasNext())
                {
                    String key =iter.next()+"";
                    String val= StringUtil.getJSONObjectKeyVal(json,key);
                    sb.append(key+"="+URLEncoder.encode(val, "UTF-8")+"&");
                }
                sb.deleteCharAt(sb.length() - 1);
                uri = sb.toString();
            }else{
                uri = url;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return uri;
    }

    /**
     * json转url参数
     * @param url
     * @param jsonStr
     * @return
     */
    public static String jsonToUrl(String url,String jsonStr){
        JSONObject json = JSONObject.fromObject(jsonStr);
        return jsonToUrl(url,json);
    }
}