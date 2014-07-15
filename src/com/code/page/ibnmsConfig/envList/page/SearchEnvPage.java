package com.code.page.ibnmsConfig.envList.page;

import com.code.common.GridPage;
import com.code.common.Page;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.Map;

/**
 * Created by jinkai on 2014/7/9.
 */
public class SearchEnvPage extends Page {
    @FindBy(how= How.NAME,using = "filter.ipAddr")
    WebElement ipAddr;
    @FindBy(how=How.NAME,using="filter.deviceName")
    WebElement deviceName;
    @FindBy(how=How.ID,using="btn-search")
    WebElement btnSearch;
    public void searchEnv(Map<String,String> map)
    {
        tools.sendKeys(ipAddr,tools.getMapValue(map,"IP地址"));
        tools.sendKeys(deviceName,tools.getMapValue(map,"主机名称"));
        tools.click(btnSearch);
    }
    public void searchByName(String name)
    {
        tools.sendKeys(deviceName,name);
        tools.click(btnSearch);
    }
    public GridPage search(Map<String,String> map)
    {
        tools.sendKeys(ipAddr,tools.getMapValue(map,"IP地址"));
        tools.sendKeys(deviceName,tools.getMapValue(map,"主机名称"));
        tools.click(btnSearch);
        return new GridPage();
    }
}
