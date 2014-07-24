package com.code.page.ibnmsConfig.alarmConfigFrame.page;

import com.code.common.Page;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Map;

/**
 * Created by jinkai on 2014/7/16.
 */
public class AlarmPolicyPage extends Page {
    //告警描述策略
    @FindBy(className = "expression-desc-btn")
    WebElement expressionDescBtn;
    @FindBy(name = "funFormUrl")
    WebElement funFormUrl;
    @FindBy(className="btn-add-fun")
    WebElement btnAddFun;
    //告警等级表达式
    @FindBy(className="expression-level-btn")
    WebElement expressionLevelBtn;
    @FindBy(className = "conditionType")
    WebElement conditionType;
    @FindBy(name="number")
    WebElement number;
    @FindBy(xpath = "//table[2]//button[@class=\"btn-add-fun\"]")
    WebElement btnAddFun2;
    public void add(Map<String,String> map)
    {
        inputForm(map);
    }
    public void inputForm(Map<String,String> map)
    {
        if (!tools.getMapValue(map,"告警描述泛函数").isEmpty()) {
            tools.click(expressionDescBtn);
            tools.selectByVisibleText(funFormUrl, tools.getMapValue(map, "告警描述泛函数"));
            tools.click(btnAddFun);
            tools.click(expressionDescBtn);
        }
        if(!tools.getMapValue(map,"严重告警表达式").isEmpty()) {
            tools.click(expressionLevelBtn);
            tools.selectByVisibleText(conditionType, tools.getMapValue(map, "严重告警表达式"));
            tools.sendKeys(number, tools.getMapValue(map, "严重告警表达式条件"));
            tools.click(btnAddFun2);
            tools.click(expressionLevelBtn);
        }
    }
}
