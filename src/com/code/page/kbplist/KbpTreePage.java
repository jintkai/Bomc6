package com.code.page.kbplist;

import com.code.common.GridTablePage;
import com.code.common.Page;
import com.code.common.TestCase;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

/**
 * Created by jinkai on 01/07/2014.
 */
public class KbpTreePage extends Page {
    WebElement fuzzy;
    public void init()
    {
        fuzzy.clear();
    }

    public GridTablePage searchKpiByTree(String kbpClass)
    {
        fuzzy.sendKeys(kbpClass);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        actions.sendKeys(Keys.ARROW_DOWN);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        actions.sendKeys(Keys.ENTER);

        TestCase.eventDriver.switchTo().frame("kbpListFrame");
        return new GridTablePage();
    }
}
