package com.code.page.kbplist;

import com.code.common.DriverManager;
import com.code.common.Page;
import com.code.common.TestCase;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

/**
 * Created by jinkai on 2014/6/21.
 */
public class KbpTreePage extends Page {
    @FindBy(how= How.ID,using="fuzzy")
    WebElement fuzzy;
    @FindBy(how=How.XPATH,using="//*[@id=\"asyncTree_12_span\"]")
    WebElement database;
    public void searchByFuzzy()
    {
        fuzzy.clear();
        fuzzy.sendKeys("10-10-33");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        fuzzy.sendKeys(Keys.ARROW_DOWN);
        fuzzy.sendKeys(Keys.ENTER);
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void searchByTree()
    {
        database.click();
    }
}
