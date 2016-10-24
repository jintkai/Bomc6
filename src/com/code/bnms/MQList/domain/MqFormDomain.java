package com.code.bnms.MQList.domain;

import com.code.common.JacksonUtil;
import com.code.bnms.envList.domain.EnvSearchDomain;


/**
 * Created by jon on 2016/10/16.
 */
public class MqFormDomain {
    private String mqPort;
    private String jmxPort;
    private String webPort;
    private String installPath;
    private EnvSearchDomain envSearchDomain;
    public MqFormDomain()
    {

    }
    public MqFormDomain(EnvSearchDomain envSearchDomain,String mqPort,String jmxPort,String webPort,String installPath){
        this.envSearchDomain=envSearchDomain;
        this.mqPort=mqPort;
        this.jmxPort=jmxPort;
        this.webPort=webPort;
        this.installPath=installPath;
    }

    public String getMqPort() {
        return mqPort;
    }

    public void setMqPort(String mqPort) {
        this.mqPort = mqPort;
    }

    public String getJmxPort() {
        return jmxPort;
    }

    public void setJmxPort(String jmxPort) {
        this.jmxPort = jmxPort;
    }

    public String getWebPort() {
        return webPort;
    }

    public void setWebPort(String webPort) {
        this.webPort = webPort;
    }

    public String getInstallPath() {
        return installPath;
    }

    public void setInstallPath(String installPath) {
        this.installPath = installPath;
    }

    public EnvSearchDomain getEnvSearchDomain() {
        return envSearchDomain;
    }

    public void setEnvSearchDomain(EnvSearchDomain envSearchDomain) {
        this.envSearchDomain = envSearchDomain;
    }
    public String toString(){
        System.out.println(JacksonUtil.toJSon(this));
        return JacksonUtil.toJSon(this);
    }
}
