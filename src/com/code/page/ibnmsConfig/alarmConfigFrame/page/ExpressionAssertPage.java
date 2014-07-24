package com.code.page.ibnmsConfig.alarmConfigFrame.page;

import com.code.common.Page;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by jinkai on 2014/7/17.
 */
public class ExpressionAssertPage extends Page {
    @FindBy(id="btn-submit")
    WebElement sumbitBtn;
    public void submit()
    {
        tools.click(sumbitBtn);
    }
}
