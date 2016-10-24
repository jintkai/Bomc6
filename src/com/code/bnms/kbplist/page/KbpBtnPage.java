package com.code.bnms.kbplist.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.code.common.BtnPage;
import org.openqa.selenium.support.events.EventFiringWebDriver;

/**
 * Created by jinkai on 01/07/2014.
 * 操作按钮的控件，继承BtnPage，若有特殊的按钮，则可在此类中声明
 */
public class KbpBtnPage extends BtnPage {
    public KbpBtnPage(EventFiringWebDriver eventDriver)
    {
        super(eventDriver);
    }
    @FindBy(id = "btn-cite")
    public WebElement citeBtn;
    public void cite()
    {
        tools.click(citeBtn);
    }
}
