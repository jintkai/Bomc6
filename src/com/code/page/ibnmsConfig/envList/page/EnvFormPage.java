package com.code.page.ibnmsConfig.envList.page;

import com.code.common.FormPage;
import com.code.common.GridPage;
import com.code.page.ibnmsConfig.envList.domain.EnvFormDomain;
import com.code.page.ibnmsConfig.envList.domain.EnvSearchDomain;
import com.code.page.ibnmsConfig.reslist.ResListFramePage;
import com.code.page.ibnmsConfig.reslist.domain.ResSearchDomain;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.text.Normalizer;
import java.util.Map;

/**
 * Created by jinkai on 2014/7/9.
 */
public class EnvFormPage  extends FormPage{
    public EnvFormPage(EventFiringWebDriver eventDriver)
    {
        super(eventDriver);
    }
    @FindBy(how= How.ID,using="btn-select-host")
    WebElement selectHostBtn;
    WebElement userName;
    WebElement javaHome;
    WebElement password;
    WebElement ftpPort;
    WebElement protocolPort;
    WebElement form_protocol;

    @Deprecated
    public void inputForm(Map<String,String> map)
    {
        if (!tools.getMapValue(map,"资源名称").isEmpty()){
            tools.click(selectHostBtn);
            ResListFramePage resList=new ResListFramePage(eventDriver);
            String hand=tools.switchToWindowByTitle(resList.title);
            resList.search(map);
            resList.gridTable.selectTr(0);
            resList.resBtn.select();
            tools.switchToWindowByHand(hand);
        }
        tools.sendKeys(userName,tools.getMapValue(map,"用户名"));
        tools.sendKeys(password,tools.getMapValue(map,"密码"));
        tools.sendKeys(javaHome,tools.getMapValue(map,"JDK路径"));
        tools.selectByVisibleText(form_protocol, tools.getMapValue(map, "连接协议"));
        tools.sendKeys(ftpPort,tools.getMapValue(map,"FTP端口"));
        tools.sendKeys(protocolPort,tools.getMapValue(map,"协议端口"));

        tools.click(btnSubmit);
        tools.alertAccept();
    }
    @Deprecated
    public GridPage operateEnv(Map<String,String> map)
    {
        inputForm(map);
        return  new GridPage(eventDriver);
    }

    public GridPage operateEnv(String operation, EnvSearchDomain searchDomain, EnvFormDomain formDomain)
    {
        inputForm(operation,searchDomain,formDomain);
        return  new GridPage(eventDriver);
    }

    public void inputForm(String operation, EnvSearchDomain searchDomain, EnvFormDomain formDomain)
    {
        if (operation.equals("增加")){
            tools.click(selectHostBtn);
            ResListFramePage resList=new ResListFramePage(eventDriver);
            String hand=tools.switchToWindowByTitle(resList.title);
            resList.search(formDomain.getResSearchDomain());
            resList.gridTable.selectTr(0);
            resList.resBtn.select();
            tools.switchToWindowByHand(hand);
        }

        tools.sendKeys(userName,formDomain.getUsername());
        tools.sendKeys(password,formDomain.getPassword());
        tools.sendKeys(javaHome,formDomain.getJdkHome());
        tools.selectByVisibleText(form_protocol, formDomain.getProtocol());
        tools.sendKeys(ftpPort,formDomain.getFtpPort());
        tools.sendKeys(protocolPort,formDomain.getProtocolPort());

        tools.click(btnSubmit);
        tools.alertAccept();
    }
}
