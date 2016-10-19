package com.code.page.ibnmsConfig.kpilist.domain;

import com.code.common.JacksonUtil;

/**
 * Created by jon on 2016/10/17.
 */
public class KpiSearchDomain {
    private String kpiID;
    private String kpiName;
    public KpiSearchDomain(){

    }
    public KpiSearchDomain(String id,String name){
        kpiID=id;
        kpiName=name;
    }
    public String toString(){
        return JacksonUtil.toJSon(this);
    }

    public String getKpiID() {
        return kpiID;
    }

    public void setKpiID(String kpiID) {
        this.kpiID = kpiID;
    }

    public String getKpiName() {
        return kpiName;
    }

    public void setKpiName(String kpiName) {
        this.kpiName = kpiName;
    }
}
