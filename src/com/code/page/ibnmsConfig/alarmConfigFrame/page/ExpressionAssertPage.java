package com.code.page.ibnmsConfig.alarmConfigFrame.page;

import com.code.common.Page;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by jinkai on 2014/7/17.
 * 表达式验证；
 */
public class ExpressionAssertPage extends Page {
    public String title="表达式验证";
    @FindBy(id="btn-submit")

    WebElement sumbitBtn;
    WebElement unitIdPrefix;
    WebElement kpiId;
    WebElement alarmTime;
    WebElement gatherVal;
    public void submit()
    {
        tools.click(sumbitBtn);
    }
    public void input()
    {

    }
}
