package com.code.bnms.workstation.domain;

import com.code.common.JacksonUtil;
import com.code.bnms.envList.domain.EnvSearchDomain;


/**
 * Created by jon on 2016/10/16.
 */
public class WKFormDomain {
    private EnvSearchDomain envSearchDomain;
    private String installPath;
    public WKFormDomain(){

    }
    public WKFormDomain(EnvSearchDomain envSearchDomain, String installPath){
        this.envSearchDomain=envSearchDomain;
        this.installPath=installPath;
    }

    public EnvSearchDomain getEnvSearchDomain() {
        return envSearchDomain;
    }

    public void setEnvSearchDomain(EnvSearchDomain envSearchDomain) {
        this.envSearchDomain = envSearchDomain;
    }

    public String getInstallPath() {
        return installPath;
    }

    public void setInstallPath(String installPath) {
        this.installPath = installPath;
    }
    public String toString(){
        return JacksonUtil.toJSon(this);
    }
}
