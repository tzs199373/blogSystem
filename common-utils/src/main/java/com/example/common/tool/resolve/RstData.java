package com.example.common.tool.resolve;


import com.example.common.tool.json.JSONArray;
import com.example.common.tool.json.JSONObject;

import java.io.Serializable;

/**
 * @Description     返回结果规范
 */
public class RstData implements Serializable
{
    /*服务编码*/
    private String serviceType;

    /*服务调用标识：默认成功;失败： CoreConfig.RESULT_STATUS_SYS_ERROR*/
    private int code = ResultStatus.SUCCESS;

    /*服务调用成功返回结果*/
    private Object data = "";

    /*服务调用信息 默认成功*/
    private String msg = "success";

    /*服务调用序列标识*/
    private String serializeId;

    public RstData() {
    }

    public RstData(Object data)
    {
        this.data=data;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        if(data instanceof JSONObject) {
            return ((JSONObject) data).toMap();
        } else if(data instanceof JSONArray) {
            return ((JSONArray) data).toList();
        } else {
            return data;
        }
    }

    public void setData(Object data) {
        if(data instanceof JSONObject) {
            this.data = ((JSONObject) data).toMap();
        } else if(data instanceof JSONArray) {
            this.data = ((JSONArray) data).toList();
        } else {
            this.data = data;
        }
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getSerializeId() {
        return serializeId;
    }

    public void setSerializeId(String serializeId) {
        this.serializeId = serializeId;
    }


}

