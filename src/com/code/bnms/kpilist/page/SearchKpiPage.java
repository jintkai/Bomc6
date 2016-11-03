package com.code.bnms.kpilist.page;

import com.code.common.GridPage;
import com.code.common.Page;
import com.code.bnms.kpilist.domain.KpiSearchDomain;
import com.code.common.PageInfo;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.Map;

/**
 * Created by jinkai on 06/07/2014.
 */
public class SearchKpiPage extends Page {
    public SearchKpiPage(EventFiringWebDriver eventDriver)
    {
        super(eventDriver);
    }
    @PageInfo(info = "KPI编号")
    @FindBy(name = "filter.kpiId")
    WebElement kpiId;
    @PageInfo(info = "KPI名称")
    @FindBy(name = "filter.kpiName")
    WebElement kpiName;
    @PageInfo(info = "查询")
    @FindBy(id = "btn-search")
    WebElement btnSearch;
    @Deprecated
    public GridPage search(Map<String,String> map)
    {
        tools.sendKeys(kpiId,tools.getMapValue(map,"指标编号_KPI"));
        tools.sendKeys(kpiName,tools.getMapValue(map,"指标名称_KPI"));
        tools.click(btnSearch);
        return new GridPage(eventDriver);
    }
    public GridPage search(KpiSearchDomain kpiSearchDomain){
        tools.sendKeys(kpiId,kpiSearchDomain.getKpiID());
        tools.sendKeys(kpiName,kpiSearchDomain.getKpiName());
        tools.click(btnSearch);
        return new GridPage(eventDriver);
    }

}
