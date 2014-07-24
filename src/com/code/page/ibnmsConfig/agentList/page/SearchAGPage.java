package com.code.page.ibnmsConfig.agentList.page;

import com.code.common.GridPage;
import com.code.common.Page;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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
    public GridPage search(Map<String,String> map)
    {
        tools.sendKeys(deviceName,tools.getMapValue(map,"主机名称_AG"));
        tools.sendKeys(ipAddr,tools.getMapValue(map,"IP地址_AG"));
        tools.click(btnSearch);
        return new GridPage();
    }
}
