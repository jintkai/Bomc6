package com.code.page.ibnmsConfig.envList.page;

import com.code.common.FormPage;
import com.code.common.GridPage;
import com.code.page.ibnmsConfig.reslist.ResListFramePage;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.text.Normalizer;
import java.util.Map;

/**
 * Created by jinkai on 2014/7/9.
 */
public class EnvFormPage  extends FormPage{
    @FindBy(how= How.ID,using="btn-select-host")
    WebElement selectHostBtn;
    WebElement userName;
    WebElement javaHome;
    WebElement password;
    WebElement ftpPort;
    WebElement protocolPort;
    WebElement form_protocol;

    public void addEnv(Map<String,String> map)
    {
        tools.sendKeys(userName,tools.getMapValue(map,"用户名"));
        tools.sendKeys(password,tools.getMapValue(map,"密码"));
        tools.sendKeys(javaHome,tools.getMapValue(map,"JDK路径"));
        tools.selectByVisibleText(form_protocol, tools.getMapValue(map, "连接协议"));
        tools.sendKeys(ftpPort,tools.getMapValue(map,"FTP端口"));
        tools.sendKeys(protocolPort,tools.getMapValue(map,"协议端口"));
        tools.click(selectHostBtn);
        String hand=tools.swithToWindowByTitle("资源列表");
        ResListFramePage resList=new ResListFramePage();
        //resList.searchResByName(tools.getMapValue(map,"主机名称"));
        //resList.search(map);
        //resList.searchRes(map);
        resList.gridTable.selectTr(0);
        resList.resBtn.select();
        tools.switchToWindowByHand(hand);
        tools.click(btnSubmit);
        tools.alertAccept();
    }
    public void inputForm(Map<String,String> map)
    {
        tools.sendKeys(userName,tools.getMapValue(map,"用户名"));
        tools.sendKeys(password,tools.getMapValue(map,"密码"));
        tools.sendKeys(javaHome,tools.getMapValue(map,"JDK路径"));
        tools.selectByVisibleText(form_protocol, tools.getMapValue(map, "连接协议"));
        tools.sendKeys(ftpPort,tools.getMapValue(map,"FTP端口"));
        tools.sendKeys(protocolPort,tools.getMapValue(map,"协议端口"));
        if (!tools.getMapValue(map,"资源名称").isEmpty()){
        tools.click(selectHostBtn);
        String hand=tools.swithToWindowByTitle("资源列表");
        ResListFramePage resList=new ResListFramePage();
        resList.search(map);
        resList.gridTable.selectAllTr();
        resList.resBtn.select();
        tools.switchToWindowByHand(hand);}
        tools.click(btnSubmit);
        tools.alertAccept();
    }
    public GridPage add(Map<String,String> map)
    {
        inputForm(map);
        return  new GridPage();
    }
}
