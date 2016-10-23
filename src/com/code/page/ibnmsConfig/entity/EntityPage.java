package com.code.page.ibnmsConfig.entity;

import com.code.common.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.List;

/**
 * Created by jon on 2016/10/22.
 */
public class EntityPage extends Page{
    public EntityPage(EventFiringWebDriver driver){
        super(driver);
    }
    @FindBy(id="entityQueryTree")
    WebElement entityQueryTree;
    public String title="监控指标列表";
    @FindBy(id = "btn-cite")
    WebElement citeBtn;
    public Page selectTree(String ... treeNames){

        int l=treeNames.length;
        WebElement e;
        for (int i=0;i<treeNames.length;i++){
            e = tools.findBy(eventDriver, By.xpath("//a[@title='" + treeNames[i] + "']"));
            if(i!=0) {

                if(i!=(l-1)) {

                    e=tools.findBy(e, By.xpath("./.."));
                    System.out.println("tagName"+e.getTagName()+"id="+e.getAttribute("id"));
                    //e=tools.findBy(e, By.xpath(".//li"));
                    WebElement e2 = tools.findBy(e, By.xpath("./button"));
                    tools.click(e2);
                }else{
                    tools.click(e);
                }
            }
        }

        return this;
    }

    public Page selectEntity(String ... entitys){
        tools.switchToFrame(entityQueryTree);
        selectTree(entitys);
        tools.switchToDefaultFrame();
        tools.switchToFrame("entityQueryFrame");
        tools.click(citeBtn);
        return this;
    }

}
