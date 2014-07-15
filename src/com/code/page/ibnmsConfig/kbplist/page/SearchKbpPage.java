package com.code.page.ibnmsConfig.kbplist.page;

import com.code.common.GridPage;
import com.code.common.Page;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.Map;

/**
 * Created by jinkai on 01/07/2014.
 */
public class SearchKbpPage extends Page {
    @FindBy(how = How.ID, using = "filter.kbpClass")
    WebElement kbpClass;

    @FindBy(how = How.ID, using = "filter.kbpCaption")
    WebElement kbpCaption;

    @FindBy(how = How.ID, using = "btn-search")
    WebElement searchBtn;

    public void init() {
        /*TestCase.eventDriver.switchTo().defaultContent();
        TestCase.eventDriver.switchTo().frame("kbpListFrame");
        */
        tools.clear(kbpClass);
        tools.clear(kbpCaption);
    }

    public GridPage searchByClass(String searchClass) {
        tools.sendKeys(kbpClass, searchClass);
        tools.click(searchBtn);
        return new GridPage();
    }

    public GridPage searchByCaption(String searchCaption) {
        tools.clear(kbpClass);
        tools.sendKeys(kbpCaption, searchCaption);
        tools.click(searchBtn);
        return new GridPage();
    }

    public GridPage searchByMulti(String searchClass, String searchCaption) {
        tools.sendKeys(kbpCaption, searchCaption);
        tools.sendKeys(kbpClass, searchClass);
        tools.click(searchBtn);
        return new GridPage();
    }

    public void searchKbp(Map<String, String> map) {
        tools.sendKeys(kbpCaption, tools.getMapValue(map, "KBP名称"));
        tools.sendKeys(kbpClass, tools.getMapValue(map, "KBP编号"));
        tools.click(searchBtn);
    }

    public GridPage search(Map<String, String> map)
    {
        tools.sendKeys(kbpCaption,tools.getMapValue(map,"KBP名称"));
        tools.sendKeys(kbpClass,tools.getMapValue(map,"KBP编号"));
        tools.click(searchBtn);
        return new GridPage();
    }
}
