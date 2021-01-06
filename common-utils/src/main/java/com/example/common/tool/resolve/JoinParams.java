package com.example.common.tool.resolve;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

public class JoinParams implements Serializable {
	//渠道标识
	private String channel = "";
	//服务ID
	private String serviceType;
	//调用服务入参
	private LinkedHashMap params;

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public LinkedHashMap getParams() {
		return params;
	}

	public void setParams(LinkedHashMap params) {
		this.params = params;
	}

	public static JoinParams build(String channel,String serviceType,Map map){
		return new JoinParams(){{
			setServiceType(serviceType);
			setChannel(channel);
			setParams(new LinkedHashMap(){{
				put("params",map);
			}});
		}};
	}
}
