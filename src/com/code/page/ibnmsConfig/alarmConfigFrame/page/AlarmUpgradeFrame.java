package com.code.page.ibnmsConfig.alarmConfigFrame.page;

import com.code.common.Page;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Map;

/**
 * Created by jinkai on 2014/7/16.
 */
public class AlarmUpgradeFrame extends Page {
    @FindBy(id="policy_upLevel")
    WebElement policy_upLevel;
    @FindBy(className = "expression-up-btn")
    WebElement expressionUpBtn;
    @FindBy(name="funFormUrl")
    WebElement funFormUrl;
    @FindBy(className = "btn-add-fun")
    WebElement btnAddFun;
    public void inputForm(Map<String,String> map)
    {
        if (tools.getMapValue(map,"升级泛函数").isEmpty())
            return;
        tools.click(expressionUpBtn);
        tools.selectByVisibleText(policy_upLevel,tools.getMapValue(map,"升级等级"));
        tools.click(btnAddFun);
        tools.click(expressionUpBtn);
    }
    public void add(Map<String,String> map)
    {
        inputForm(map);
    }
}

