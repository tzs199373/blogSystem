package com.example.common.tool.resolve;


import com.example.common.tool.json.JSONArray;
import com.example.common.tool.json.JSONObject;

import java.io.Serializable;

/**
 * @Description     ���ؽ���淶
 */
public class RstData implements Serializable
{
    /*�������*/
    private String serviceType;

    /*������ñ�ʶ��Ĭ�ϳɹ�;ʧ�ܣ� CoreConfig.RESULT_STATUS_SYS_ERROR*/
    private int code = ResultStatus.SUCCESS;

    /*������óɹ����ؽ��*/
    private Object data = "";

    /*���������Ϣ Ĭ�ϳɹ�*/
    private String msg = "success";

    /*����������б�ʶ*/
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

