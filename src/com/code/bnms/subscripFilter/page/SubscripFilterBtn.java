package com.code.bnms.subscripFilter.page;

import com.code.common.BtnPage;
import com.code.common.FormPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.Map;

/**
 * Created by Jin on 2014/8/24.
 */
public class SubscripFilterBtn extends BtnPage {
    public SubscripFilterBtn(EventFiringWebDriver eventDriver)
    {
        super(eventDriver);
    }
    @FindBy(id = "btn-add-filter")
    WebElement filterAddBtn;
    public FormPage add(Map<String,String> map) {
        tools.moveToElement(addBtn);
        tools.selectByVisibleText(filterAddBtn,tools.getMapValue(map,"过滤器类型"));
        return new FormPage(eventDriver);
    }
    public FormPage edit() {
        tools.openModelDialog(editBtn);
        return new FormPage(eventDriver);
    }
}
