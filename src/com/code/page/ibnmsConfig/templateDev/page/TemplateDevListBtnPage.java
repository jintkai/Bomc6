package com.code.page.ibnmsConfig.templateDev.page;

import com.code.common.BtnPage;
import com.code.common.Page;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Jin on 2014/8/21.
 */
public class TemplateDevListBtnPage extends BtnPage {
    @FindBy(id = "btn-sync")
    WebElement syncBtn;
    @FindBy(id="btn-set")
    WebElement setBtn;
    public void sync()
    {
        tools.click(syncBtn);
        tools.alertAccept();
    }
    public void devSet(){
        tools.openModelDialog(setBtn);
    }

}
