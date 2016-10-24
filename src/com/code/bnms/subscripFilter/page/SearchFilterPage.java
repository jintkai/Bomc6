package com.code.bnms.subscripFilter.page;

import com.code.common.GridPage;
import com.code.common.Page;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.Map;

/**
 * Created by Jin on 2014/8/24.
 */
public class SearchFilterPage extends Page {
    public SearchFilterPage(EventFiringWebDriver eventDriver)
    {
        super(eventDriver);
    }
    @FindBy(name="filter.filterName")
    WebElement filterName;
    @FindBy(name="filter.unitId")
    WebElement unitID;
    @FindBy(name="filter.filterDesc")
    WebElement filterDesc;
    @FindBy(name="filter.userName")
    WebElement userName;
    @FindBy(className = "bnms-btn-search")
    WebElement searchBtn;
    public GridPage search(Map<String,String> map)
    {
        tools.sendKeys(filterName,tools.getMapValue(map,"过滤器名称_SUBSCRIPFILTER"));
        tools.sendKeys(unitID,tools.getMapValue(map,"监控实体_SUBSCRIPFILTER"));
        tools.sendKeys(filterDesc,tools.getMapValue(map,"过滤器描述_SUBSCRIPFILTER"));
        tools.sendKeys(userName,tools.getMapValue(map,"创建人_SUBSCRIPFILTER"));
        tools.click(searchBtn);
        return  new GridPage(eventDriver);
    }
}
