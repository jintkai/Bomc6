package com.code.page.kbplist;

import com.code.common.GridTablePage;
import com.code.common.Page;
import com.code.common.TestCase;
import com.code.page.kpilist.SearchKpiPage;
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
        /*TestCase.eventDriver.switchTo().defaultContent();
        TestCase.eventDriver.switchTo().frame("kbpListFrame");
        */
        tools.clear(kbpClass);
        tools.clear(kbpCaption);
    }

    public GridTablePage searchByClass(String searchClass)
    {
        tools.sendKeys(kbpClass,searchClass);
        tools.clinck(searchBtn);
        return new GridTablePage();
    }

    public GridTablePage searchByCaption(String searchCaption)
    {
        tools.sendKeys(kbpCaption,searchCaption);
        tools.clinck(searchBtn);
        return new GridTablePage();
    }
    public GridTablePage searchByMulti(String searchClass,String searchCaption)
    {
        tools.sendKeys(kbpCaption,searchCaption);
        tools.sendKeys(kbpClass,searchClass);
        tools.clinck(searchBtn);
        return new GridTablePage();
    }


}
