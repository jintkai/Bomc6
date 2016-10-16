package com.code.page.ibnmsConfig.agentList.page;

import com.code.common.GridPage;
import com.code.common.Page;
import com.code.page.ibnmsConfig.agentList.domain.AgentSearchDomain;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.Map;

/**
 * Created by jinkai on 2014/7/15.
 */
public class SearchAGPage extends Page {
    @FindBy(name="filter.deviceName")
    WebElement deviceName;
    @FindBy(name="filter.ipAddr")
    WebElement ipAddr;
    @FindBy(id="btn-search")
    WebElement btnSearch;
    public SearchAGPage(EventFiringWebDriver eventDriver){
        super(eventDriver);
    }
    public GridPage search(Map<String,String> map)
    {
        tools.sendKeys(deviceName,tools.getMapValue(map,"主机名称_AG"));
        tools.sendKeys(ipAddr,tools.getMapValue(map,"IP地址_AG"));
        tools.click(btnSearch);
        return new GridPage(eventDriver);
    }
    public GridPage search(AgentSearchDomain agentSearchDomain)
    {
        tools.sendKeys(deviceName,agentSearchDomain.getDevice_name());
        tools.sendKeys(ipAddr,agentSearchDomain.getIp());
        tools.click(btnSearch);
        return new GridPage(eventDriver);
    }
}
