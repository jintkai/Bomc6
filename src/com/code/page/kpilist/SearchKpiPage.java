package com.code.page.kpilist;

import com.code.common.GridTablePage;
import com.code.common.Page;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

/**
 * Created by jinkai on 01/07/2014.
 */
public class SearchKpiPage extends Page {
    @FindBy(how= How.NAME,using="filter.kpiId")
    WebElement kpiId;

    @FindBy(how= How.NAME,using="filter.kpiName")
    WebElement kpiName;

    @FindBy(how= How.ID,using="btn-search")
    WebElement searchBtn;

    public void init()
    {
        kpiId.clear();
        kpiName.clear();
    }

    public GridTablePage searchByID(String searchID)
    {
        init();
        kpiId.sendKeys(searchID);
        searchBtn.click();
        return new GridTablePage();
    }

    public GridTablePage searchByName(String searchName)
    {
        kpiName.sendKeys(searchName);
        searchBtn.click();
        return new GridTablePage();
    }
    public GridTablePage searchMulti(String searchID,String searchName)
    {
        kpiId.sendKeys(searchID);
        kpiName.sendKeys(searchName);
        searchBtn.click();
        return new GridTablePage();
    }
}
