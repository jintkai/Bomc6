package com.code.common;

import com.code.common.GridPage;
import com.code.common.Page;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;

/**
 * Created by jinkai on 06/07/2014.
 */
public class TreePage extends Page {
    public TreePage(EventFiringWebDriver eventDriver)
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
