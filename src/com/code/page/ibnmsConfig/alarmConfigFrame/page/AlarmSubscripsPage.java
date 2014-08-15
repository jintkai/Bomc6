package com.code.page.ibnmsConfig.alarmConfigFrame.page;

import com.code.common.Page;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by jinkai on 2014/7/17.
 */
public class AlarmSubscripsPage extends Page {
    @FindBy(id="btn-subscrip-add")
    WebElement addSubscripBtn;
    @FindBy(id="btn-subscrip-edit")
    WebElement editSubscripBtn;
    @FindBy(id="btn-subscrip-delete")
    WebElement delSubscripBtn;

}
