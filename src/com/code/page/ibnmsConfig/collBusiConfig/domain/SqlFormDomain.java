package com.code.page.ibnmsConfig.collBusiConfig.domain;

import com.code.page.ibnmsConfig.kbplist.domain.KbpSearchDomain;
import com.code.page.ibnmsConfig.kpilist.domain.KpiSearchDomain;

/**
 * Created by jon on 2016/10/22.
 */
public class SqlFormDomain {
    private String serviceName;
    private String serviceDesc;

    private String cronsName;
    private String sql;
    private KbpSearchDomain kbpSearchDomain;
    private String[] entirys;

    private KpiSearchDomain[] kpiSearchDomains;

    public KpiSearchDomain[] getKpiSearchDomains() {
        return kpiSearchDomains;
    }

    public void setKpiSearchDomains(KpiSearchDomain[] kpiSearchDomains) {
        this.kpiSearchDomains = kpiSearchDomains;
    }

    public String[] getEntirys() {
        return entirys;
    }

    public void setEntirys(String[] entirys) {
        this.entirys = entirys;
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



    public String getCronsName() {
        return cronsName;
    }

    public void setCronsName(String cronsName) {
        this.cronsName = cronsName;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public KbpSearchDomain getKbpSearchDomain() {
        return kbpSearchDomain;
    }

    public void setKbpSearchDomain(KbpSearchDomain kbpSearchDomain) {
        this.kbpSearchDomain = kbpSearchDomain;
    }
}
