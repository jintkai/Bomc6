package com.code.page.kbplist;

import com.code.common.Page;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

/**
 * Created by jinkai on 01/07/2014.
 */
public class KbpBtnPage extends Page {

    @FindBy(how= How.ID,using="btn-add")
    WebElement addBtn;
    @FindBy(how= How.ID,using="btn-edit")
    WebElement editBtn;
    @FindBy(how= How.ID,using="btn-del")
    WebElement delBtn;
    public KbpFormPage getAddForm()
    {
        addBtn.click();
        return new KbpFormPage();
    }
}
