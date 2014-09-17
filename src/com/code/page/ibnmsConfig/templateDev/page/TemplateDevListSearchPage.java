package com.code.page.ibnmsConfig.templateDev.page;

import com.code.common.GridPage;
import com.code.common.Page;
import com.code.page.ibnmsConfig.templateDev.TemplateDevListPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.Map;

/**
 * Created by Jin on 2014/8/21.
 */
public class TemplateDevListSearchPage extends Page{
    public TemplateDevListSearchPage(EventFiringWebDriver eventDriver)
    {
        super(eventDriver);
    }
    @FindBy(name="filter.unitId")
    WebElement unitId;
    @FindBy(name = "filter.deviceName")
    WebElement deviceName;
    @FindBy(name = "filter.ipAddr")
    WebElement ipAddr;
    @FindBy(name = "filter.templateId")
    WebElement templateId;
    @FindBy(id = "btn-search")
    WebElement searchBtn;
    public GridPage search(Map<String,String> map)
    {
        tools.sendKeys(unitId,tools.getMapValue(map,"资源UNIT_ID_TEMPLATEDEV"));
        tools.sendKeys(deviceName,tools.getMapValue(map,"资源名称_TEMPLATEDEV"));
        tools.sendKeys(ipAddr,tools.getMapValue(map,"IP_TEMPLATEDEV"));
        tools.selectByVisibleText(templateId,tools.getMapValue(map,"模板_TEMPLATEDEV"));
        tools.click(searchBtn);
        return new GridPage(eventDriver);
    }
}
