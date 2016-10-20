package com.code.page.ibnmsConfig.appServer.domain;

import com.code.common.JacksonUtil;
import com.code.page.ibnmsConfig.envList.domain.EnvSearchDomain;

/**
 * Created by jon on 2016/10/20.
 */
public class AppFormDomain {
    private EnvSearchDomain envSearchDomain;
    private String appName;
    private String processKey;
    private String instatllPath;

    public EnvSearchDomain getEnvSearchDomain() {
        return envSearchDomain;
    }

    public void setEnvSearchDomain(EnvSearchDomain envSearchDomain) {
        this.envSearchDomain = envSearchDomain;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getProcessKey() {
        return processKey;
    }

    public void setProcessKey(String processKey) {
        this.processKey = processKey;
    }

    public String getInstatllPath() {
        return instatllPath;
    }

    public void setInstatllPath(String instatllPath) {
        this.instatllPath = instatllPath;
    }
    public String toString(){
        return JacksonUtil.toJSon(this);
    }
}
