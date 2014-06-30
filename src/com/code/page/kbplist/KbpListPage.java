package com.code.page.kbplist;

import com.code.common.Page;
import com.code.common.GridTablePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

/**
 * Created by jinkai on 2014/6/22.
 */
public class KbpListPage extends Page {

    @FindBy(how= How.ID,using="filter.kbpClass")
    WebElement kbpClass;

    @FindBy(how= How.ID,using="filter.kbpCaption")
    WebElement kbpCaption;

    @FindBy(how= How.ID,using="btn-search")
    WebElement searchBtn;

    @FindBy(id="btn-add")
    WebElement addBtn;


    GridTablePage gridTable=new GridTablePage();

    KbpFromPage kbpFrom=new KbpFromPage();

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
        return gridTable=new GridTablePage();
    }

    public GridTablePage searchByCaption(String searchCaption)
    {
        kbpCaption.sendKeys(searchCaption);
        searchBtn.click();
        return gridTable=new GridTablePage();
    }
    public GridTablePage searchMulti(String searchClass,String searchCaption)
    {
        kbpClass.sendKeys(searchClass);
        kbpCaption.sendKeys(searchCaption);
        searchBtn.click();
        return gridTable=new GridTablePage();
    }
    public boolean  addKBP(String[] obj)
    {
        addBtn.click();
        kbpFrom.addKBP(obj);
        System.out.println(obj[7]+"实际查询数据："+searchByCaption(obj[7]).getRowNum());
        return searchByClass(obj[7]).getRowNum()==1;
    }
}
