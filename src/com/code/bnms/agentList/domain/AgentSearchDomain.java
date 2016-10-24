package com.code.bnms.agentList.domain;

import com.code.common.JacksonUtil;

/**
 * Created by jon on 2016/10/16.
 */
public class AgentSearchDomain {
    private String device_name;
    private String ip;
    public AgentSearchDomain(){

    }
    public AgentSearchDomain(String device_name,String ip){
        this.device_name=device_name;
        this.ip=ip;
    }

    public String getDevice_name() {
        return device_name;
    }

    public void setDevice_name(String device_name) {
        this.device_name = device_name;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
    public String toString(){
        return JacksonUtil.toJSon(this);
    }
}
