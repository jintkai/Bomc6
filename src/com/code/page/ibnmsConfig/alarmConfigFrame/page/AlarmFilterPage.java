package com.code.page.ibnmsConfig.alarmConfigFrame.page;

import com.code.common.Page;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Map;

/**
 * Created by jinkai on 2014/7/16.
 * 告警过滤策略
 */
public class AlarmFilterPage extends Page {
    @FindBy(className = "bnms-btn-edit")
    WebElement bnmsBtnEdit;
    @FindBy(name="funFormUrl")
    WebElement funFormUrl;
    @FindBy(className="btn-add-fun")
    WebElement btnAddFun;
    public void add(Map<String,String> map)
    {
        inputForm(map);
    }
    public void inputForm(Map<String,String> map)
    {
        if(tools.getMapValue(map,"告警过滤泛函数").isEmpty())
        {
            return;
        }
        tools.click(bnmsBtnEdit);
        tools.selectByVisibleText(funFormUrl,tools.getMapValue(map,"告警过滤泛函数"));
        tools.click(btnAddFun);
        tools.click(bnmsBtnEdit);
    }
}
