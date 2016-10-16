package com.code.page.ibnmsConfig.envList.page;

import com.code.common.GridPage;
import com.code.common.Page;
import com.code.page.ibnmsConfig.envList.domain.EnvSearchDomain;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.Map;

/**
 * Created by jinkai on 2014/7/9.
 */
public class SearchEnvPage extends Page {
    public SearchEnvPage(EventFiringWebDriver eventDriver)
    {
        super(eventDriver);
    }
    @FindBy(how= How.NAME,using = "filter.ipAddr")
    WebElement ipAddr;
    @FindBy(how=How.NAME,using="filter.deviceName")
    WebElement deviceName;
    @FindBy(how=How.ID,using="btn-search")
    WebElement btnSearch;

    public GridPage search(Map<String,String> map)
    {
        tools.sendKeys(ipAddr,tools.getMapValue(map,"IP地址_ENV"));
        tools.sendKeys(deviceName,tools.getMapValue(map,"主机名称_ENV"));
        tools.click(btnSearch);
        return new GridPage(eventDriver);
    }
    public GridPage search(EnvSearchDomain envSearchDomain){
        tools.sendKeys(ipAddr,envSearchDomain.getIpAddr());
        tools.sendKeys(deviceName,envSearchDomain.getDeviceName());
        tools.click(btnSearch);
        return new GridPage(eventDriver);
    }
}
