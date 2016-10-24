package com.code.bnms.monitorPoints.page;

import com.code.common.Page;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Map;

/**
 * Created by Jin on 2014/9/10.
 */
public class SearchMonitorPointPage extends Page {
    @FindBy(name="point.UNIT_NAME")
    WebElement unitName;
    @FindBy(name="point.KPI_NAME")
    WebElement kpiName;
    @FindBy(name="point.UNIT_ID")
    WebElement unitId;
    @FindBy(name = "point.KPI_ID")
    WebElement kpiId;
    @FindBy(id="btn-search")
    WebElement searchBtn;
    public void search(Map<String,String> map)
    {
        tools.sendKeys(unitName,tools.getMapValue(map,"监控点名称_MONITORPOINT"));
        tools.sendKeys(kpiName,tools.getMapValue(map,"指标名称_MONITORPOINT"));
        tools.sendKeys(unitId,tools.getMapValue(map,"监控点KBP_MONITORPOINT"));
        tools.sendKeys(kpiId,tools.getMapValue(map,"指标ID_MONITORPOINT"));
        tools.click(searchBtn);
    }
}

