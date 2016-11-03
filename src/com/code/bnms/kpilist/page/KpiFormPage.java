package com.code.bnms.kpilist.page;

import com.code.common.FormPage;
import com.code.common.GridPage;
import com.code.bnms.kpilist.domain.KpiFormDomain;
import com.code.common.PageInfo;
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
    @PageInfo(info = "指标编号")
    @FindBy(id="kpi.kpiId")
    WebElement kpiId;
    @PageInfo(info = "指标名称")
    @FindBy(id="kpi.kpiName")
    WebElement kpiName;
    @PageInfo(info = "KBP编号")
    @FindBy(id="kpi.kbpClass")
    WebElement kbpClass;
    @PageInfo(info = "指标类型")
    @FindBy(id="kpi.kpiType")
    WebElement kpiType;
    @PageInfo(info = "数据类型-字符串")
    @FindBy(id="kpi.kpiStylestring")
    WebElement kpiStylestring;
    @PageInfo(info = "数据类型-数值")
    @FindBy(id="kpi.kpiStylenumber")
    WebElement kpiStylenumber;
    @PageInfo(info = "数据类型-日期时间")
    @FindBy(id="kpi.kpiStyledatetime")
    WebElement kpiStyledatetime;
    @PageInfo(info = "单位")
    @FindBy(id="kpi.kpiMeasure")
    WebElement kpiMeasure;
    @PageInfo(info = "KPI描述")
    @FindBy(id="kpi.kpiDesc")
    WebElement kpiDesc;

    @FindBy(id= "kpi_trendFlag")
    WebElement trendFlag;
    @FindBy(id="kpi_baseLineFlag")
    WebElement baseLineFlag;
    @PageInfo(info = "管理类型")
    @FindBy(id="tr-kpiMngType")
    WebElement kpiMngType;

    @Deprecated
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
    @Deprecated
    public GridPage operateKpi(Map<String,String> map)
    {
        inputForm(map);
        return new GridPage(eventDriver);
    }

    public GridPage operateKpi(String operation, KpiFormDomain domain)
    {
        inputForm(operation,domain);
        return new GridPage(eventDriver);
    }


    public void inputForm(String operation, KpiFormDomain domain)
    {
        if(operation.equals("增加")) {
            tools.selectByVisibleText(kpiMngType.findElement(By.tagName("select")), domain.getKPI_MNGTYPE());
            tools.sleep(3000);

            tools.sendKeys(kpiId, domain.getKPI_ID());
            tools.sendKeys(kpiName, domain.getKPI_NAME());
            if (!(domain.getKBP_CLASS()==null))
                tools.sendKeys(kbpClass, domain.getKBP_CLASS());
            tools.selectByVisibleText(kpiType, domain.getKPI_TYPE());
            tools.sendKeys(kpiMeasure, domain.getKPI_MEASURE());
            tools.sendKeys(kpiDesc, domain.getKPI_DESC());
            String s = domain.getKPI_STYLE();
            if (s.equals("字符串")) {
                tools.click(kpiStylestring);
            }
            if (s.equals("数值")) {
                tools.click(kpiStylenumber);
            }
            if (s.equals("日期时间")) {
                tools.click(kpiStyledatetime);
            }
            s = domain.getTrendFlag();
            if (s.equals("是"))
                tools.click(trendFlag);
            s = domain.getBaseLineFlag();
            if (s.equals("是"))
                tools.click(baseLineFlag);

            tools.click(btnSubmit);
        }
    }
}
