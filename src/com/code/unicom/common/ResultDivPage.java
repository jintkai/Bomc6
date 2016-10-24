package com.code.unicom.common;

import com.code.common.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.List;

/**
 * Created by Administrator on 16-6-1.
 */
public class ResultDivPage extends Page {
    public ResultDivPage(EventFiringWebDriver webDriver){super(webDriver);}
    @FindBy(how= How.TAG_NAME,using = "table")
    WebElement resultTable;
    public void getTabelTh(){
        List<WebElement> tableThs=resultTable.findElements(By.tagName("th"));
        for(WebElement tableTh : tableThs){
            System.out.println(tableTh.getText());
        }
    }
    public int getCount(){
        try {
            List<WebElement> tableTr=resultTable.findElement(By.tagName("tbody")).findElements(By.tagName("tr"));
            return tableTr.size();
        }
        catch (Exception e)
        {
            return 0;
        }
    }
}
