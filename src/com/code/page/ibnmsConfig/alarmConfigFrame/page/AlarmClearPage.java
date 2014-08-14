package com.code.page.ibnmsConfig.alarmConfigFrame.page;

import com.code.common.Page;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Map;

/**
 * Created by jinkai on 2014/7/17.
 * 告警清除表达式
 */
public class AlarmClearPage extends Page {
    @FindBy(name="clearWay")
    WebElement clearWay;

    @FindBy(className = "expression-clear-btn")
    WebElement expressionUpBtn;
    @FindBy(name="funFormUrl")
    WebElement funFormUrl;
    @FindBy(className = "btn-add-fun")
    WebElement btnAddFun;

    //月份内,泛函数：采集时间在某月内
    @FindBy(name="isInMonth_month")
    WebElement isInMonth_month;
    //指定指标的值大小比较
    @FindBy(name="compareKpiValueByUK_unitId")
    WebElement compareKpiValueByUK_unitId;
    @FindBy(name="compareKpiValueByUK_kpiId")
    WebElement compareKpiValueByUK_kpiId;
    @FindBy(name="compareKpiValueByUK_compare_type")
    WebElement compareKpiValueByUK_compare_type;
    @FindBy(name="compareKpiValueByUK_compare_value")
    WebElement compareKpiValueByUK_compare_value;

    //指标值最近N个周期大于等于某个值
    @FindBy(name="overByTimes_m")
    WebElement overByTimes_m;
    @FindBy(name="overByTimes_n")
    WebElement overByTimes_n;
    @FindBy(name="overByTimes_threshold")
    WebElement overByTimes_threshold;

    public void add(Map<String,String> map)
    {
        inputForm(map);
    }

    public void inputForm(Map<String,String> map)
    {
        tools.selectByVisibleText(clearWay,tools.getMapValue(map,"清除方式"));

        if (tools.getMapValue(map,"泛函数").isEmpty())
            return;
        tools.click(expressionUpBtn);
        tools.selectByVisibleText(funFormUrl,tools.getMapValue(map,"泛函数"));

        tools.click(btnAddFun);

        tools.sendKeys(isInMonth_month,tools.getMapValue(map,"isInMonth_month"));

        tools.sendKeys(compareKpiValueByUK_unitId,tools.getMapValue(map,"compareKpiValueByUK_unitId"));
        tools.sendKeys(compareKpiValueByUK_kpiId,tools.getMapValue(map,"compareKpiValueByUK_kpiId"));
        tools.selectByVisibleText(compareKpiValueByUK_compare_type,tools.getMapValue(map,"compareKpiValueByUK_compare_type"));
        tools.sendKeys(compareKpiValueByUK_compare_value,tools.getMapValue(map,"compareKpiValueByUK_compare_value"));

        tools.sendKeys(overByTimes_m,tools.getMapValue(map,"overByTimes_m"));
        tools.sendKeys(overByTimes_n,tools.getMapValue(map,"overByTimes_n"));
        tools.sendKeys(overByTimes_threshold,tools.getMapValue(map,"overByTimes_threshold"));

        tools.click(expressionUpBtn);
    }
    public void operate(Map<String,String>map){inputForm(map);}
}
