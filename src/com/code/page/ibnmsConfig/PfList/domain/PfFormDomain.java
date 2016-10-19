package com.code.page.ibnmsConfig.PfList.domain;

import com.code.common.JacksonUtil;
import com.code.page.ibnmsConfig.envList.domain.EnvSearchDomain;

/**
 * Created by jon on 2016/10/19.
 */
public class PfFormDomain {
    private String installPath;
    private String lang;
    private EnvSearchDomain enSearchDomain;

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public EnvSearchDomain getEnSearchDomain() {
        return enSearchDomain;
    }

    public void setEnSearchDomain(EnvSearchDomain enSearchDomain) {
        this.enSearchDomain = enSearchDomain;
    }

    public String getInstallPath() {
        return installPath;
    }

    public void setInstallPath(String installPath) {
        this.installPath = installPath;
    }
    public String toString()
    {
        return JacksonUtil.toJSon(this);
    }
}
