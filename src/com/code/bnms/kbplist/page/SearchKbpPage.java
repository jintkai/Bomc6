package com.code.bnms.kbplist.page;

import com.code.common.GridPage;
import com.code.common.Page;
import com.code.bnms.kbplist.domain.KbpSearchDomain;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.Map;

/**
 * Created by jinkai on 01/07/2014.
 * Kbp的查询功能
 */
public class SearchKbpPage extends Page {
    @FindBy(how = How.ID, using = "filter.kbpClass")
    WebElement kbpClass;
    @FindBy(how = How.ID, using = "filter.kbpCaption")
    WebElement kbpCaption;
    @FindBy(how = How.ID, using = "btn-search")
    WebElement searchBtn;
    public SearchKbpPage(EventFiringWebDriver eventDriver)
    {
        super(eventDriver);
    }
    @Deprecated
    public GridPage search(Map<String, String> map)
    {
        tools.sendKeys(kbpCaption, tools.getMapValue(map, "KBP名称_KBP"));
        tools.sendKeys(kbpClass, tools.getMapValue(map, "KBP编号_KBP"));
        tools.click(searchBtn);
        return new GridPage(eventDriver);
    }
    public GridPage search(KbpSearchDomain domain)
    {
        tools.sendKeys(kbpCaption, domain.getKbp_Caption());
        tools.sendKeys(kbpClass,domain.getKbp_class());
        tools.click(searchBtn);
        return new GridPage(eventDriver);
    }
}
