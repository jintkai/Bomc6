package com.code.page.kbplist;

import com.code.common.GridTablePage;
import com.code.common.Page;
import com.code.common.TestCase;
import org.openqa.selenium.JavascriptExecutor;
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
        TestCase.eventDriver.switchTo().defaultContent();
        TestCase.eventDriver.switchTo().frame("kbpTree");
        init();
        fuzzy.sendKeys(kbpClass);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        actions.sendKeys(Keys.ARROW_DOWN).perform();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        actions.sendKeys(Keys.ENTER).perform();
        TestCase.eventDriver.switchTo().defaultContent();

        String js="return document.getElementById(\"kbpListFrame\").contentWindow.document.readyState";
        String str= (String) ((JavascriptExecutor)TestCase.driver).executeScript(js);
        while(!((JavascriptExecutor)TestCase.driver).executeScript(js).equals("complete"))
        {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        TestCase.eventDriver.switchTo().frame("kbpListFrame");
        return new GridTablePage();
    }
}