package com.code.page.ibnmsConfig.kbplist.page;

import com.code.common.GridPage;
import com.code.common.Page;
import com.code.common.TreePage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;

/**
 * Created by jinkai on 01/07/2014.
 * Kbp查询树
 */
public class KbpTreePage extends Page {

    public KbpTreePage(EventFiringWebDriver eventDriver)
    {
        super(eventDriver);
    }
    WebElement fuzzy;
    public void searchByTree(String treeValue)
    {
        tools.sendKeys(fuzzy,treeValue);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        actions.sendKeys(Keys.ARROW_DOWN).perform();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        actions.sendKeys(Keys.ENTER).perform();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
