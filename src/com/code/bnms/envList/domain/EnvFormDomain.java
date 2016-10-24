package com.code.bnms.envList.domain;

import com.code.common.JacksonUtil;
import com.code.bnms.reslist.domain.ResSearchDomain;

/**
 * Created by jon on 2016/10/16.
 */
public class EnvFormDomain {
    private String username;
    private String password;
    private String jdkHome;
    private String protocolPort;
    private String ftpPort;
    private String protocol;
    private ResSearchDomain resSearchDomain;

    public ResSearchDomain getResSearchDomain() {
        return resSearchDomain;
    }

    public void setResSearchDomain(ResSearchDomain resSearchDomain) {
        this.resSearchDomain = resSearchDomain;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getJdkHome() {
        return jdkHome;
    }

    public void setJdkHome(String jdkHome) {
        this.jdkHome = jdkHome;
    }

    public String getProtocolPort() {
        return protocolPort;
    }

    public void setProtocolPort(String protocolPort) {
        this.protocolPort = protocolPort;
    }

    public String getFtpPort() {
        return ftpPort;
    }

    public void setFtpPort(String ftpPort) {
        this.ftpPort = ftpPort;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }
    public String toString(){
        return JacksonUtil.toJSon(this);
    }
}
