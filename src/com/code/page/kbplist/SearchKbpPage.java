package com.code.page.kbplist;

import com.code.common.GridTablePage;
import com.code.common.Page;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

/**
 * Created by jinkai on 01/07/2014.
 */
public class SearchKbpPage extends Page{
    @FindBy(how= How.ID,using="filter.kbpClass")
    WebElement kbpClass;

    @FindBy(how= How.ID,using="filter.kbpCaption")
    WebElement kbpCaption;

    @FindBy(how= How.ID,using="btn-search")
    WebElement searchBtn;

    public void init()
    {
        kbpCaption.clear();
        kbpClass.clear();
    }

    public GridTablePage searchByClass(String searchClass)
    {
        init();
        kbpClass.sendKeys(searchClass);
        searchBtn.click();
        return new GridTablePage();
    }

    public GridTablePage searchByCaption(String searchCaption)
    {
        kbpCaption.sendKeys(searchCaption);
        searchBtn.click();
        return new GridTablePage();
    }
    public GridTablePage searchMulti(String searchClass,String searchCaption)
    {
        kbpClass.sendKeys(searchClass);
        kbpCaption.sendKeys(searchCaption);
        searchBtn.click();
        return new GridTablePage();
    }


}
