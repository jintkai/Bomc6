package com.code.bnms.reslist.domain;


import com.code.common.JacksonUtil;

/**
 * Created by jon on 2016/10/17.
 */
public class ResSearchDomain {
    private String unitID;
    private String deviceName;
    private String ip;
    private String kbpName=null;
    public ResSearchDomain(){}
    public ResSearchDomain(String unitID,String deviceName,String ip,String kbpName){
        this.unitID=unitID;
        this.kbpName=kbpName;
        this.ip=ip;
        this.deviceName=deviceName;
    }

    public String getUnitID() {
        return unitID;
    }

    public void setUnitID(String unitID) {
        this.unitID = unitID;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getKbpName() {
        return kbpName;
    }

    public void setKbpName(String kbpName) {
        this.kbpName = kbpName;
    }
    public String toString()
    {
        return JacksonUtil.toJSon(this);
    }
}
