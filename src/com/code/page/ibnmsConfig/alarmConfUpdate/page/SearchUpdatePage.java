package com.code.page.ibnmsConfig.alarmConfUpdate.page;

import com.code.common.GridPage;
import com.code.common.Page;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.Map;

/**
 * Created by jinkai on 2014/7/23.
 */
public class SearchUpdatePage extends Page {
    public SearchUpdatePage(EventFiringWebDriver eventDriver)
    {
        super(eventDriver);
    }
    @FindBy(name="filter.UNIT_ID")
    WebElement UNIT_ID;
    @FindBy(name="filter.KPI_ID")
    WebElement KPI_ID;
    @FindBy(id="ALARM_TYPE")
    WebElement ALARM_TYPE;
    @FindBy(id="WF_STATE")
    WebElement WF_STATE;
    @FindBy(id="btn-search")
    WebElement search;
    public GridPage search(Map<String,String> map)
    {
        tools.sendKeys(UNIT_ID, tools.getMapValue(map, "UNIT_ID_ALAREM"));
        tools.sendKeys(KPI_ID,tools.getMapValue(map,"指标ID_ALARM"));
        tools.selectByVisibleText(ALARM_TYPE, tools.getMapValue(map, "告警类型_ALARM"));
        tools.selectByVisibleText(WF_STATE,tools.getMapValue(map,"工单状态_ALARM"));
        tools.click(search);
        return new GridPage(eventDriver);
    }
}
