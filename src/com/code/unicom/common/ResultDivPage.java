package com.code.unicom.common;

import com.code.common.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
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
    public void deleteRow(int rowNum){
        tools.sleep(5000);
        List<WebElement> tableTr=resultTable.findElement(By.tagName("tbody")).findElements(By.tagName("tr"));
        WebElement deleteBtn=tableTr.get(rowNum).findElement(By.id("delclick"));
        System.out.println(deleteBtn.getText());
        /*tools.sleep(5000);
        Actions actions = new Actions(tools.getDriver());

        actions.moveToElement(deleteBtn).click().perform();
        tools.sleep(5000);
        clickWebElement(deleteBtn,tools.getDriver());*/
        tools.click(deleteBtn);

        tools.alertAccept();
    }
    void clickWebElement(WebElement weElement, WebDriver wdDriver) {

        // Scroll the browser to the element's Y position
        //((JavascriptExecutor) wdDriver).executeScript(" window.scrollTo("+0+"," + weElement.getLocation().y + ")");
        ((JavascriptExecutor) wdDriver).executeScript(" window.scrollTo("+weElement.getLocation().x+","
                + weElement.getLocation().y + ")");
        // Click the element
        int iAttempts = 0;
        while (iAttempts < 5) {
            try {
                weElement.click();
                break;
            } catch (Exception e) {
            }
            iAttempts++;
        }

    }
}
