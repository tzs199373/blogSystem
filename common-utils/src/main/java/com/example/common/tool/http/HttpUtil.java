package com.example.common.tool.http;


import com.example.common.tool.json.JSONObject;
import com.example.common.tool.string.StringUtil;
import com.example.common.tool.validate.ObjectCensor;

import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;

/**
 * @author tzs
 * @version 1.0
 * @Description HttpClientPoolUtil的二次封装
 * @since 2019/10/18 16:08
 */
public class HttpUtil {
    /**
     * @param url
     * @param param		JSON字符串,将转为键值对形式追加到url后面
     * @param body		JSON字符串
     * @param headMap	消息头，内含"Content-Type"="application/x-www-form-urlencoded"
     * @return
     * @throws Exception
     */
    public static String postForm(String url, String param,String body, Map<String, String> headMap) throws Exception{
        headMap.put("Content-Type","application/x-www-form-urlencoded");

        // 构建请求参数
        StringBuffer sb = new StringBuffer();
        if (!ObjectCensor.checkObjectIsNull(body)) {
            JSONObject joinParam = JSONObject.fromObject(body);
            Iterator iter = joinParam.keys();
            while (iter.hasNext())
            {
                String keyT =iter.next()+"";
                String key  = StringUtil.getRstFieldName_ByVoFldName(keyT).toLowerCase();
                String val= StringUtil.getJSONObjectKeyVal(joinParam,keyT);
                sb.append(key);
                sb.append("=");
                sb.append(URLEncoder.encode(val, "UTF-8"));
                sb.append("&");
            }
            sb.replace(0, sb.length(), sb.substring(0, sb.length() - 1));
        }
        return HttpClientPoolUtil.post(appendParamToURL(url,param), sb.toString(),headMap);
    }

    /**
     * @param url
     * @param param		JSON字符串,将转为键值对形式追加到url后面
     * @param body		JSON字符串
     * @param headMap	消息头，内含"Content-Type"="application/json"
     * @return
     * @throws Exception
     */
    public static String postJSON(String url, String param,String body,Map<String, String> headMap) throws Exception{
        headMap.put("Content-Type","application/json");
        return HttpClientPoolUtil.post(appendParamToURL(url,param), body,headMap);
    }

    /**
     * @param url
     * @param param		JSON字符串,将转为键值对形式追加到url后面
     * @param body		JSON字符串
     * @param headMap	消息头，内含"Content-Type"="application/x-www-form-urlencoded"
     * @return
     * @throws Exception
     */
    public static String putForm(String url, String param,String body,Map<String, String> headMap) throws Exception{
        headMap.put("Content-Type","application/x-www-form-urlencoded");

        // 构建请求参数
        StringBuffer sb = new StringBuffer();
        if (!ObjectCensor.checkObjectIsNull(body)) {
            JSONObject joinParam = JSONObject.fromObject(body);
            Iterator iter = joinParam.keys();
            while (iter.hasNext())
            {
                String keyT =iter.next()+"";
                String key  = StringUtil.getRstFieldName_ByVoFldName(keyT).toLowerCase();
                String val= StringUtil.getJSONObjectKeyVal(joinParam,keyT);
                sb.append(key);
                sb.append("=");
                sb.append(URLEncoder.encode(val, "UTF-8"));
                sb.append("&");
            }
            sb.replace(0, sb.length(), sb.substring(0, sb.length() - 1));
        }
        return HttpClientPoolUtil.put(appendParamToURL(url,param), sb.toString(),headMap);
    }

    /**
     * @param url
     * @param param		JSON字符串,将转为键值对形式追加到url后面
     * @param body		JSON字符串
     * @param headMap	消息头，内含"Content-Type"="application/json"
     * @return
     * @throws Exception
     */
    public static String putJSON(String url, String param,String body,Map<String, String> headMap) throws Exception{
        headMap.put("Content-Type","application/json");
        return HttpClientPoolUtil.put(appendParamToURL(url,param),body,headMap);
    }

    /**
     * @param url
     * @param param		JSON字符串,将转为键值对形式追加到url后面
     * @return
     * @throws Exception
     */
    public static String delete(String url,String param,Map<String, String> headMap) throws Exception{
        return HttpClientPoolUtil.delete(appendParamToURL(url,param),headMap);
    }

    /**
     * @param url
     * @param param		JSON字符串,将转为键值对形式追加到url后面
     * @return
     * @throws Exception
     */
    public static String get(String url, String param,Map<String, String> headMap) throws Exception{
        return HttpClientPoolUtil.get(appendParamToURL(url,param),headMap);
    }

    private static String appendParamToURL(String url,String param) throws Exception{
        if(ObjectCensor.isStrRegular(param)){
            JSONObject json  = JSONObject.fromObject(param);
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
                url = sb.toString();
            }
        }
        return url;
    }
}
