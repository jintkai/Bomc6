package com.code.page.ibnmsConfig.alarmConfigFrame.page;

import com.code.common.GridPage;
import com.code.common.Page;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Map;

/**
 * Created by jinkai on 2014/7/17.
 */
public class AlarmSearchHeadPage extends Page {
    @FindBy(name="UNIT_ID")
    WebElement UNIT_ID;
    @FindBy(name="KPI_ID")
    WebElement KPI_ID;
    @FindBy(name="KPI_NAME")
    WebElement KPI_NAME;
    @FindBy(name="ALARM_TYPE")
    WebElement ALARM_TYPE;

    public GridPage searchByHead(Map<String,String> map)
    {
        tools.sendKeys(UNIT_ID,tools.getMapValue(map,"UNIT_ID_ALAREM"));
        tools.sendKeys(KPI_ID,tools.getMapValue(map,"指标ID_ALAREM"));
        tools.sendKeys(KPI_NAME,tools.getMapValue(map,"指标名称_ALAREM"));
        tools.sendKeys(ALARM_TYPE,tools.getMapValue(map,"指标类型_ALAREM"));

        actions.sendKeys(Keys.ENTER).perform();
        return new GridPage();
    }
}
