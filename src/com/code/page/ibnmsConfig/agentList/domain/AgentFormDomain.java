package com.code.page.ibnmsConfig.agentList.domain;

import com.code.common.JacksonUtil;
import com.code.page.ibnmsConfig.envList.domain.EnvSearchDomain;


/**
 * Created by jon on 2016/10/16.
 */
public class AgentFormDomain {
    private String agentName;
    private String installPath;
    private String dbPort;
    private String jmxPort;
    private String lang;
    private EnvSearchDomain envSearchDomain;
    public AgentFormDomain(){

    }
    public AgentFormDomain(EnvSearchDomain envSearchDomain, String agentName, String installPath, String dbPort,String jmxPort,String lang){
        this.envSearchDomain=envSearchDomain;
        this.agentName=agentName;
        this.installPath=installPath;
        this.dbPort=dbPort;
        this.jmxPort=jmxPort;
        this.lang=lang;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getInstallPath() {
        return installPath;
    }

    public void setInstallPath(String installPath) {
        this.installPath = installPath;
    }

    public String getDbPort() {
        return dbPort;
    }

    public void setDbPort(String dbPort) {
        this.dbPort = dbPort;
    }

    public String getJmxPort() {
        return jmxPort;
    }

    public void setJmxPort(String jmxPort) {
        this.jmxPort = jmxPort;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public EnvSearchDomain getEnvSearchDomain() {
        return envSearchDomain;
    }

    public void setEnvSearchDomain(EnvSearchDomain envSearchDomain) {
        this.envSearchDomain = envSearchDomain;
    }
    public String toString(){
        return JacksonUtil.toJSon(this);
    }
}
