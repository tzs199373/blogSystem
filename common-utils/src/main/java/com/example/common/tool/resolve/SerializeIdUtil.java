package com.example.common.tool.resolve;

import com.example.common.tool.date.SysDate;

public class SerializeIdUtil {

    /**
     * 获取日志流水channel+serviceType+yyyymmddHHmmss+6位随机值
     */
    public static String getSerial(String channel,String serviceType)
    {
        String serialNumber = channel+ serviceType+ SysDate.getCurrentTime()+ get6RandomInt() ;
        return serialNumber;
    }

    private static String get6RandomInt(){
        return String.valueOf((int)((Math.random()*9+1)*100000));
    }


}
