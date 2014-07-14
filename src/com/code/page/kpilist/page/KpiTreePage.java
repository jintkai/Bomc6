package com.code.page.kpilist.page;

import com.code.common.GridPage;
import com.code.common.Page;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

/**
 * Created by jinkai on 06/07/2014.
 */
public class KpiTreePage extends Page {
    WebElement fuzzy;
    public void init()
    {
        //tools.clear(fuzzy);
    }

    public GridPage searchByTree(String treeValue)
    {
        tools.switchToFrame();
        tools.switchToFrame(0);
        tools.sendKeys(fuzzy,treeValue);

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
        tools.switchToFrame();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        tools.switchToFrame("kpiListFrame");
        return new GridPage();
    }
}
