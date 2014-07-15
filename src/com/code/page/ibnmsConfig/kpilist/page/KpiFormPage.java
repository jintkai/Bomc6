package com.code.page.ibnmsConfig.kpilist.page;

import com.code.common.Data;
import com.code.common.FormPage;
import com.code.common.GridPage;
import com.code.common.Page;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Map;

/**
 * Created by jinkai on 06/07/2014.
 */
public class KpiFormPage extends FormPage {
    @FindBy(id= Data.kpiIdID)
    WebElement kpiId;
    @FindBy(id=Data.kpiNameID)
    WebElement kpiName;
    @FindBy(id=Data.kbpClassID)
    WebElement kbpClass;
    @FindBy(id=Data.kpiTypeID)
    WebElement kpiType;
    @FindBy(id=Data.kpiStylestringID)
    WebElement kpiStylestring;
    @FindBy(id=Data.kpiStylenumberID)
    WebElement kpiStylenumber;
    @FindBy(id=Data.kpiStyledatetimeID)
    WebElement kpiStyledatetime;
    @FindBy(id=Data.kpiMeasureID)
    WebElement kpiMeasure;
    @FindBy(id=Data.kpiDescID)
    WebElement kpiDesc;
    @FindBy(name=Data.trendFlagName)
    WebElement trendFlag;
    @FindBy(name=Data.baseLineFlagName)
    WebElement baseLineFlag;
/*
    public void addKpi(Map<String,String> map)
    {
        if(tools.getMapValue(map,"父节点").equals("0"))
        {
            System.out.println("未选择父节点");
            tools.sendKeys(kpiId,tools.getMapValue(map,"KPI编号"));
            tools.sendKeys(kpiName,tools.getMapValue(map,"KPI名称"));
            tools.sendKeys(kbpClass,tools.getMapValue(map,"KBP编号"));
            tools.selectByVisibleText(kpiType,tools.getMapValue(map,"指标类型"));
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
            tools.sendKeys(kpiMeasure,tools.getMapValue(map,"单位"));
            tools.sendKeys(kpiDesc,tools.getMapValue(map,"指标描述"));
            s = tools.getMapValue(map, "趋势计算");
            if (s.equals("是")) {
                tools.click(trendFlag);
            }
            s = tools.getMapValue(map, "异动计算");
            if (s.equals("是")) {
                tools.click(baseLineFlag);
            }
        }
        else
        {
            System.out.println("选择父节点");
            if(!tools.getAttribute(kbpClass,"value").trim().equals(tools.getMapValue(map,"KBP编号")))
            {
                System.out.println("父节点KBP错误，预期KBP："+tools.getMapValue(map,"KBP编号")+
                        "实际KBP："+tools.getAttribute(kbpClass,"value"));
            }
            if(!tools.getAttribute(kbpClass,"style").trim().equals("display: none;"))
            {
                System.out.println("KBP编号应该只能为只读，但现在能对其进行修改");
            }
            tools.sendKeys(kpiId,tools.getMapValue(map,"KPI编号"));
            tools.sendKeys(kpiName,tools.getMapValue(map,"KPI名称"));
            tools.selectByVisibleText(kpiType,tools.getMapValue(map,"指标类型"));
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
            tools.sendKeys(kpiMeasure,tools.getMapValue(map,"单位"));
            tools.sendKeys(kpiDesc,tools.getMapValue(map,"指标描述"));
            s = tools.getMapValue(map, "趋势计算");
            if (s.equals("是")) {
                tools.click(trendFlag);
            }
            s = tools.getMapValue(map, "异动计算");
            if (s.equals("是")) {
                tools.click(baseLineFlag);
            }
        }
        tools.submit(kpiId);
    }
    public void editKpi(Map<String,String> map)
    {
        tools.sendKeys(kpiName,tools.getMapValue(map,"KPI名称"));
        tools.submit(kpiId);
    }
*/
    public void inputForm(Map<String,String> map)
    {
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
    public GridPage add(Map<String,String> map)
    {
        inputForm(map);
        return new GridPage();
    }

    public GridPage edit(Map<String,String> map) {
        inputForm(map);
        return new GridPage();
    }
}
