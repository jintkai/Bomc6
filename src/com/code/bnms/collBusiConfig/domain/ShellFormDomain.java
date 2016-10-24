package com.code.bnms.collBusiConfig.domain;

import com.code.common.JacksonUtil;
import com.code.bnms.kbplist.domain.KbpSearchDomain;
import com.code.bnms.kpilist.domain.KpiSearchDomain;

/**
 * Created by jon on 2016/10/20.
 */
public class ShellFormDomain {
    private String serviceName;
    private String serviceDesc;
    private String shellName;
    private String shellType;
    private String cronsName;
    private String overTimeId;
    private String shell;
    private KbpSearchDomain kbpSearchDomain;
    private KpiSearchDomain[] kpiSearchDomains;



    public String getShell() {
        return shell;
    }

    public void setShell(String shell) {
        this.shell = shell;
    }

    public KpiSearchDomain[] getKpiSearchDomains() {
        return kpiSearchDomains;
    }

    public void setKpiSearchDomains(KpiSearchDomain[] kpiSearchDomains) {
        this.kpiSearchDomains = kpiSearchDomains;
    }

    public String getOverTimeId() {
        return overTimeId;
    }

    public void setOverTimeId(String overTimeId) {
        this.overTimeId = overTimeId;
    }

    public KbpSearchDomain getKbpSearchDomain() {
        return kbpSearchDomain;
    }

    public void setKbpSearchDomain(KbpSearchDomain kbpSearchDomain) {
        this.kbpSearchDomain = kbpSearchDomain;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceDesc() {
        return serviceDesc;
    }

    public void setServiceDesc(String serviceDesc) {
        this.serviceDesc = serviceDesc;
    }

    public String getShellName() {
        return shellName;
    }

    public void setShellName(String shellName) {
        this.shellName = shellName;
    }

    public String getShellType() {
        return shellType;
    }

    public void setShellType(String shellType) {
        this.shellType = shellType;
    }

    public String getCronsName() {
        return cronsName;
    }

    public void setCronsName(String cronsName) {
        this.cronsName = cronsName;
    }
    public String toString(){
        return JacksonUtil.toJSon(this);
    }
}
