package com.code.bnms.kpilist.domain;

import com.code.common.JacksonUtil;

/**
 * Created by jon on 16/5/10.
 */
public class KpiFormDomain {
    private String KPI_ID;
    private String KBP_CLASS;
    private String KPI_NAME;
    private String KPI_STYLE;
    private String KPI_MEASURE;
    private String KPI_RANG;
    private String KPI_DESC;
    private String trendFlag;//趋势计算
    private String baseLineFlag;//异动计算
    private String KPI_TYPE;//指标类型
    private String KPI_MNGTYPE;//管理类型

    public String getKPI_MNGTYPE() {
        return KPI_MNGTYPE;
    }

    public void setKPI_MNGTYPE(String KPI_MNGTYPE) {
        this.KPI_MNGTYPE = KPI_MNGTYPE;
    }

    public String getTrendFlag() {
        return trendFlag;
    }

    public void setTrendFlag(String trendFlag) {
        this.trendFlag = trendFlag;
    }

    public String getBaseLineFlag() {
        return baseLineFlag;
    }

    public void setBaseLineFlag(String baseLineFlag) {
        this.baseLineFlag = baseLineFlag;
    }

    public String getKPI_TYPE() {
        return KPI_TYPE;
    }

    public void setKPI_TYPE(String KPI_TYPE) {
        this.KPI_TYPE = KPI_TYPE;
    }

    public String getKPI_ID() {
        return KPI_ID;
    }

    public void setKPI_ID(String KPI_ID) {
        this.KPI_ID = KPI_ID;
    }

    public String getKBP_CLASS() {
        return KBP_CLASS;
    }

    public void setKBP_CLASS(String KBP_CLASS) {
        this.KBP_CLASS = KBP_CLASS;
    }

    public String getKPI_NAME() {
        return KPI_NAME;
    }

    public void setKPI_NAME(String KPI_NAME) {
        this.KPI_NAME = KPI_NAME;
    }

    public String getKPI_STYLE() {
        return KPI_STYLE;
    }

    public void setKPI_STYLE(String KPI_STYLE) {
        this.KPI_STYLE = KPI_STYLE;
    }

    public String getKPI_MEASURE() {
        return KPI_MEASURE;
    }

    public void setKPI_MEASURE(String KPI_MEASURE) {
        this.KPI_MEASURE = KPI_MEASURE;
    }

    public String getKPI_RANG() {
        return KPI_RANG;
    }

    public void setKPI_RANG(String KPI_RANG) {
        this.KPI_RANG = KPI_RANG;
    }

    public String getKPI_DESC() {
        return KPI_DESC;
    }

    public void setKPI_DESC(String KPI_DESC) {
        this.KPI_DESC = KPI_DESC;
    }
    public String toString(){
        return JacksonUtil.toJSon(this);
    }
}
