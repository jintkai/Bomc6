package com.code.page.ibnmsConfig.kpilist.page;

import com.code.common.GridPage;
import com.code.common.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.List;

/**
 * Created by jinkai on 06/07/2014.
 */
public class KpiTreePage extends Page {
    public KpiTreePage(EventFiringWebDriver eventDriver)
    {
        super(eventDriver);
    }
    WebElement fuzzy;

    WebElement asyncTree;

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
        tools.switchToFrame();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void selectTree( String name){
        //WebElement tree_span=asyncTree.findElement(By.id(name));
        List<WebElement> elements=asyncTree.findElements(By.tagName("a"));
        for(int i=0;i<elements.size();i++)
        {
            if (elements.get(i).getAttribute("title").equals(name)){
                tools.click(elements.get(i));
                return;
            }
        }

    }
}
