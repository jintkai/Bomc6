package com.code.bnms.kpilist.page;

import com.code.common.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.ArrayList;
import java.util.Collections;
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

    WebElement e=asyncTree;

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


        /**
         * 此处存在问题,以后修改
         */
        List<WebElement> elements=asyncTree.findElements(By.tagName("a"));
        for(int i=0;i<elements.size();i++)
        {
            if (elements.get(i).getAttribute("title").equals(name)){
                tools.click(elements.get(i));
                return;
            }
        }

    }
    public void exppandTree(String ... strings){
        List <String> list=new ArrayList<String>();
        Collections.addAll(list,strings);
        for(int j=0;j<strings.length;j++) {


            List<WebElement> elements1 = e.findElements(By.tagName("li"));
            for (int i = 0; i < elements1.size(); i++) {
                String s=elements1.get(i).getText();
                System.out.println("test"+strings[j]);
                if (s.contains(strings[j]))
                {
                    WebElement button=elements1.get(i).findElement(By.tagName("button"));
                    System.out.println(button.getAttribute("id"));
                    tools.click(button);
                    list.remove(0);
                    if (list.size()==0)
                        return;
                    strings= list.toArray(new String[list.size()]);
                    exppandTree(strings);
                }
                e=e.findElement(By.tagName("ul"));
            }
        }

    }

}
