package com.code.page.ibnmsConfig.kpilist.page;

import com.code.common.Data;
import com.code.common.FormPage;
import com.code.common.GridPage;
import com.code.common.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.Map;

/**
 * Created by jinkai on 06/07/2014.
 */
public class KpiFormPage extends FormPage {
    public KpiFormPage(EventFiringWebDriver eventDriver)
    {
        super(eventDriver);
    }
    @FindBy(id="kpi.kpiId")
    WebElement kpiId;
    @FindBy(id="kpi.kpiName")
    WebElement kpiName;
    @FindBy(id="kpi.kbpClass")
    WebElement kbpClass;
    @FindBy(id="kpi.kpiType")
    WebElement kpiType;
    @FindBy(id="kpi.kpiStylestring")
    WebElement kpiStylestring;
    @FindBy(id="kpi.kpiStylenumber")
    WebElement kpiStylenumber;
    @FindBy(id="kpi.kpiMeasure")
    WebElement kpiStyledatetime;
    @FindBy(id="kpi.kpiMeasure")
    WebElement kpiMeasure;
    @FindBy(id="kpi.kpiDesc")
    WebElement kpiDesc;
    @FindBy(id= "kpi_trendFlag")
    WebElement trendFlag;
    @FindBy(id="kpi_baseLineFlag")
    WebElement baseLineFlag;
    @FindBy(id="tr-kpiMngType")
    WebElement kpiMngType;

    public void inputForm(Map<String,String> map)
    {

        tools.selectByVisibleText(kpiMngType.findElement(By.tagName("select")),tools.getMapValue(map,"管理类型"));
        tools.sleep(3000);

        tools.sendKeys(kpiId,tools.getMapValue(map,"编号"));
        tools.sendKeys(kpiName,tools.getMapValue(map,"名称"));
        if (!tools.getMapValue(map,"KBP编号").isEmpty())
        tools.sendKeys(kbpClass,tools.getMapValue(map,"KBP编号"));
        tools.selectByVisibleText(kpiType,tools.getMapValue(map,"指标类型"));
        tools.sendKeys(kpiMeasure,tools.getMapValue(map,"单位"));
        tools.sendKeys(kpiDesc,tools.getMapValue(map,"指标描述"));
        String s = tools.getMapValue(map, "数据类型");
        if (s.equals("字符串")) {
            tools.click(kpiStylestring);
        }
        if (s.equals("数值"))
        {
            tools.click(kpiStylenumber);
        }
        if (s.equals("日期时间"))
        {
            tools.click(kpiStyledatetime);
        }
        s = tools.getMapValue(map, "趋势计算");
        if (s.equals("是"))
            tools.click(trendFlag);
        s = tools.getMapValue(map, "异动计算");
        if (s.equals("是"))
            tools.click(baseLineFlag);

        tools.click(btnSubmit);
    }
    public GridPage operateKpi(Map<String,String> map)
    {
        inputForm(map);
        return new GridPage(eventDriver);
    }

}
