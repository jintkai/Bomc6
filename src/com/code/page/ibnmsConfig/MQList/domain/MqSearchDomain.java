package com.code.page.ibnmsConfig.MQList.domain;

/**
 * Created by jon on 2016/10/16.
 */
public class MqSearchDomain {
    public String ip;
    public String device_name;
    private String port;
    private String installPath;
    private String deployStatus;
    private String runStatus;
    public MqSearchDomain(){

    }
    public MqSearchDomain(String ip,String device_name,String port,String installPath,String deployStatus,String runStatus){
        this.ip=ip;
        this.device_name=device_name;
        this.port=port;
        this.installPath=installPath;
        this.deployStatus=deployStatus;
        this.runStatus=runStatus;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getDevice_name() {
        return device_name;
    }

    public void setDevice_name(String device_name) {
        this.device_name = device_name;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getInstallPath() {
        return installPath;
    }

    public void setInstallPath(String installPath) {
        this.installPath = installPath;
    }

    public String getDeployStatus() {
        return deployStatus;
    }

    public void setDeployStatus(String deployStatus) {
        this.deployStatus = deployStatus;
    }

    public String getRunStatus() {
        return runStatus;
    }

    public void setRunStatus(String runStatus) {
        this.runStatus = runStatus;
    }
}
