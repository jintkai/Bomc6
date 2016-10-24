package com.code.bnms.envList.domain;


import com.code.common.JacksonUtil;

public class EnvSearchDomain{
    private String ipAddr;
    private String deviceName;
    public EnvSearchDomain()
    {

    }
    public EnvSearchDomain(String ip,String name){
        this.ipAddr=ip;
        this.deviceName=name;
    }
    public String getIpAddr() {
        return ipAddr;
    }

    public void setIpAddr(String ipAddr) {
        this.ipAddr = ipAddr;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }
    public String toString(){

        return JacksonUtil.toJSon(this);
    }
}
