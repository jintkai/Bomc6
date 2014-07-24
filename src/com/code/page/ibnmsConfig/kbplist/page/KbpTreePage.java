package com.code.page.ibnmsConfig.kbplist.page;

import com.code.common.GridPage;
import com.code.common.Page;
import com.code.common.TreePage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

/**
 * Created by jinkai on 01/07/2014.
 */
public class KbpTreePage extends Page {

    WebElement fuzzy;
    public void init()
    {
        tools.clear(fuzzy);
    }

    public GridPage searchKpiByTree(String treeValue)
    {
        init();
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
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        tools.switchToFrame("kbpListFrame");
        return new GridPage();
    }
}
