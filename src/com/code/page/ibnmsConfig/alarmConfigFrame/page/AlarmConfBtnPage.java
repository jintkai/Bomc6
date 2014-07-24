package com.code.page.ibnmsConfig.alarmConfigFrame.page;

import com.code.common.BtnPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by jinkai on 2014/7/16.
 */
public class AlarmConfBtnPage extends BtnPage {
    @FindBy(id="btn-add-select")
    WebElement btnAddSelect;
    public AlarmConfFormPage addConf(String str)
    {
        tools.moveToElement(this.addBtn);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        tools.selectByVisibleText(btnAddSelect,str);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new AlarmConfFormPage();
    }
}
