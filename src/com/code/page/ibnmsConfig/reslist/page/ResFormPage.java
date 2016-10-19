package com.code.page.ibnmsConfig.reslist.page;

import com.code.common.FormPage;
import com.code.common.GridPage;
import com.code.common.Page;
import com.code.page.ibnmsConfig.reslist.domain.ResFormDomain;
import com.code.page.ibnmsConfig.reslist.domain.ResHostFormDomain;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.Map;

/**
 * Created by jinkai on 2014/7/8.
 */
public class ResFormPage extends FormPage {
    public ResFormPage(EventFiringWebDriver eventDriver)
    {
        super(eventDriver);
    }
    WebElement devTypeKbpClass;
    WebElement deviceName;
    WebElement deviceId;
    WebElement ipAddr;
    WebElement manufactureKbpClass;
    WebElement bzTypeKbpClass;
    WebElement usage;
    WebElement linkman;

    @FindBy(name="res.enable")
    WebElement enable;
    /*
    数据库
     */
    WebElement res_dbUser;
    WebElement res_dbPassword;
    WebElement res_dbUrl;
    WebElement res_dbPort;
    WebElement res_dbSsid;

    public void editRes(Map<String,String> map)
    {
        if(tools.getMapValue(map,"资源类型").equals("数据库"))
        {
            tools.sendKeys(deviceName,tools.getMapValue(map,"资源名称"));
            tools.sendKeys(res_dbUser,tools.getMapValue(map,"用户名"));
            tools.sendKeys(res_dbSsid,tools.getMapValue(map,"SSID"));
            tools.sendKeys(res_dbPort,tools.getMapValue(map,"端口号"));
        }
        else
            tools.sendKeys(deviceName,tools.getMapValue(map,"资源名称"));
        tools.click(btnSubmit);
    }
    @Deprecated
    public GridPage operateRes(Map<String,String> map)
    {
        inputForm(map);
        return  new GridPage(eventDriver);
    }
    @Deprecated
    public void inputForm(Map<String,String> map)
    {
        tools.selectByVisibleText(devTypeKbpClass,tools.getMapValue(map,"资源类型"));
        tools.sendKeys(deviceName,tools.getMapValue(map,"资源名称"));
        tools.sendKeys(deviceId,tools.getMapValue(map,"资源标识"));
        tools.sendKeys(ipAddr,tools.getMapValue(map,"IP地址"));
        tools.selectByVisibleText(manufactureKbpClass,tools.getMapValue(map,"厂家类型"));
        tools.selectByVisibleText(bzTypeKbpClass,tools.getMapValue(map,"业务类型"));
        tools.sendKeys(usage,tools.getMapValue(map,"简明用途"));
        tools.sendKeys(linkman,tools.getMapValue(map,"责任人"));
        tools.selectByVisibleText(enable,tools.getMapValue(map,"是否有效"));
        if (tools.getMapValue(map,"资源类型").equals("数据库")) {
            tools.sendKeys(res_dbUser, tools.getMapValue(map, "用户名"));
            tools.sendKeys(res_dbPassword, tools.getMapValue(map, "密码"));
            tools.sendKeys(res_dbUrl, tools.getMapValue(map, "URL"));
            tools.sendKeys(res_dbSsid,tools.getMapValue(map,"SSID"));
            tools.sendKeys(res_dbPort,tools.getMapValue(map,"端口号"));
        }
        tools.click(btnSubmit);
    }
    public void inputForm(String ResType,ResFormDomain domain){
        tools.selectByVisibleText(devTypeKbpClass,domain.getDevType());
        tools.sendKeys(deviceName,domain.getDevice_name());
        tools.sendKeys(deviceId,domain.getDevice_id());
        tools.sendKeys(ipAddr,domain.getIp_addr());
        tools.selectByVisibleText(manufactureKbpClass,domain.getManufacturer());
        tools.selectByVisibleText(bzTypeKbpClass,domain.getBz_type());
        tools.sendKeys(usage,domain.getUsage());
        tools.sendKeys(linkman,domain.getLinkman());
        tools.selectByVisibleText(enable,domain.getEnable());
        if (domain.getDevType().equals("数据库")) {
            tools.sendKeys(res_dbUser, domain.getDbUser());
            tools.sendKeys(res_dbPassword, domain.getDbPasswd());
            tools.sendKeys(res_dbUrl, domain.getDbUrl());
            tools.sendKeys(res_dbSsid,domain.getSsid());
            tools.sendKeys(res_dbPort,domain.getDbPort());
        }
        tools.click(btnSubmit);
    }

    public GridPage operateRes(String operate, ResFormDomain domain)
    {
        inputForm(operate,domain);
        return  new GridPage(eventDriver);
    }
}
