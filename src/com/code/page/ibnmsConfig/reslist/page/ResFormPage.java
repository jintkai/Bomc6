package com.code.page.ibnmsConfig.reslist.page;

import com.code.common.FormPage;
import com.code.common.GridPage;
import com.code.common.Page;
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


    public void editRes(Map<String,String> map)
    {
        if(tools.getMapValue(map,"资源类型").equals("数据库"))
        {
            tools.sendKeys(deviceName,tools.getMapValue(map,"资源名称"));
            tools.sendKeys(res_dbUser,tools.getMapValue(map,"用户名"));
        }
        else
            tools.sendKeys(deviceName,tools.getMapValue(map,"资源名称"));
        tools.click(btnSubmit);
    }

    public GridPage operateRes(Map<String,String> map)
    {
        inputForm(map);
        return  new GridPage(eventDriver);
    }
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
        }
        tools.click(btnSubmit);
    }
}
