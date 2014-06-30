package com.code.page;

import com.code.common.GridTablePage;
import com.code.common.Page;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

/**
 * Created by jinkai on 2014/6/21.
 */
public class SearchBasePage extends Page {
    @FindBy(how= How.NAME,using="filter.unitId")
    WebElement unitID;
    @FindBy(how= How.NAME,using="filter.deviceName")
    WebElement deviceName;
    @FindBy(how= How.NAME,using="filter.bzTypeKbpClass")
    WebElement bzTypeKbpClass;
    @FindBy(how= How.ID,using="btn-search")
    WebElement searchButton;


}
