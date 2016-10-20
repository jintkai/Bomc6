package com.code.page.ibnmsConfig.appServer.domain;

/**
 * Created by jon on 2016/10/20.
 */
public class AppSearchDomain {
    private String appModle;
    private String runStatus;
    private String deployStatus;

    public String getAppModle() {
        return appModle;
    }

    public void setAppModle(String appModle) {
        this.appModle = appModle;
    }

    public String getRunStatus() {
        return runStatus;
    }

    public void setRunStatus(String runStatus) {
        this.runStatus = runStatus;
    }

    public String getDeployStatus() {
        return deployStatus;
    }

    public void setDeployStatus(String deployStatus) {
        this.deployStatus = deployStatus;
    }
}
