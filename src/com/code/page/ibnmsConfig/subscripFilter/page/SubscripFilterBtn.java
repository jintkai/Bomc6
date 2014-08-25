package com.code.page.ibnmsConfig.subscripFilter.page;

import com.code.common.BtnPage;
import com.code.common.FormPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Map;

/**
 * Created by Jin on 2014/8/24.
 */
public class SubscripFilterBtn extends BtnPage {
    @FindBy(id = "btn-add-filter")
    WebElement filterAddBtn;
    public FormPage add(Map<String,String> map) {
        tools.moveToElement(addBtn);
        tools.selectByVisibleText(filterAddBtn,tools.getMapValue(map,"过滤器类型"));
        return new FormPage();
    }
}
