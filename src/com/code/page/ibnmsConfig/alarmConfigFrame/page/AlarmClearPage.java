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
    WebElement expressionClearBtn;
    WebElement funFormUrl;
    @FindBy(className="btn-add-fun")
    WebElement btnAddFun;
    public void add(Map<String,String> map)
    {
        inputForm(map);
    }
    public void inputForm(Map<String,String> map)
    {
        tools.selectByVisibleText(clearWay,tools.getMapValue(map,"告警清除方式"));
        if(tools.getMapValue(map,"告警清除方式").equals("表达式清除"))
        {
            tools.click(expressionClearBtn);
            tools.selectByVisibleText(funFormUrl,"是否为最新告警");
            tools.click(btnAddFun);
            tools.click(expressionClearBtn);
        }
    }
}
