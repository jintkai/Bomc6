package com.code.page.ibnmsConfig.kbplist.page;

import com.code.common.Data;
import com.code.common.Page;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import com.code.common.BtnPage;
/**
 * Created by jinkai on 01/07/2014.
 * 操作按钮的控件，继承BtnPage，若有特殊的按钮，则可在此类中声明
 */
public class KbpBtnPage extends BtnPage {
    @FindBy(id = "btn-cite")
    public WebElement citeBtn;
    public void cite()
    {
        tools.click(citeBtn);
    }
}
