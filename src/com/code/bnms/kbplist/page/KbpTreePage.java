package com.code.bnms.kbplist.page;

import com.code.common.Page;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
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
    @FindBy(xpath = "//*/ul[2]/li[1]/a")
    WebElement li;
    public void searchByTree(String treeValue)
    {
        tools.sendKeys(fuzzy,treeValue);

        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        tools.click(li);
        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        /*actions.sendKeys(Keys.ARROW_DOWN);
        //actions.keyDown(Keys.ARROW_DOWN).keyUp(Keys.ARROW_DOWN).perform();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        actions.sendKeys(Keys.ENTER).perform();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
    }
}
