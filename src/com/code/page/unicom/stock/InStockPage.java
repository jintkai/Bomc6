package com.code.page.unicom.stock;

import com.code.common.Page;
import com.code.page.ibnmsConfig.pmalarmList.page.PaBtnPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 16-6-14.
 */
public class InStockPage extends Page{
    public InStockPage(EventFiringWebDriver eventFiringWebDriver){
        super(eventFiringWebDriver);
    }
    @FindBy(how=How.ID,using = "fileselect")
    WebElement fileselect;
    @FindBy(how=How.ID,using = "inFile")
    WebElement inFile;
    @FindBy(how=How.ID,using = "inBegin_num")
    WebElement inBegin_num;
    @FindBy(how=How.ID,using = "inEnd_num")
    WebElement inEnd_num;
    @FindBy(how=How.ID,using = "inBusi_type")
    WebElement inBusi_type;
    @FindBy(how=How.ID,using = "inPhone_use")
    WebElement inPhone_use;
    @FindBy(how=How.ID,using = "inStock_id")
    WebElement inStock_id;
    @FindBy(how=How.ID,using = "inNote")
    WebElement inNote;
    @FindBy(how=How.XPATH,using = "//*[@id=\"pageBody\"]/div/div/div/div[2]/div/a")
    WebElement btn;
    @FindBy(how=How.ID,using = "inValidForm")
    WebElement inValidForm;
    @FindBy(how=How.XPATH,using = "//tbody/tr[2]/td/div")
    WebElement content;
    @FindBy(how=How.XPATH,using="//tbody/tr[2]/td/div/div/div[2]")
    WebElement dialog;
    @FindBy(how=How.XPATH,using = "//tbody/tr[2]/td/div/div/div[3]/input")
    WebElement dialog_btn;
    public String inStock(Map<String,String> map){
        List<WebElement> els = tools.findElements(inValidForm,By.tagName("label"));
        WebElement e1 = null;
        WebElement e2 = null;
        WebElement opKind1=null;
        WebElement opKind2 = null;
        for (WebElement e : els) {
            String a=e.getText();
            System.out.println(e.getText());
            if(e.getText().equals("起止号码入库")){
                //e1 = tools.findBy(e,By.tagName("input"));
                e1 = e;
            }
            else if (e.getText().equals("号段入库")){
                    e2 = e;
                }
            else if( e.getText().equals("手工方式")){
                    opKind1 = e;
            }
            else if ( e.getText().equals("文件方式")){
                opKind2 = e;
            }
        }
        if( tools.getMapValue(map,"操作方式").equals("手工方式")){
            tools.click(opKind1);
            if( tools.getMapValue(map,"操作类型").equals("起止号段入库"))
            {
                tools.click(e1);
                tools.sendKeys(inBegin_num,tools.getMapValue(map,"开始号段"));
                tools.sendKeys(inEnd_num,tools.getMapValue(map,"终止号段"));
            }
            else
                tools.click(e2);
        }
        else {
            tools.click(opKind2);
            //String js="$('#inFile').removeAttr('type')";
            //tools.execJS(js);
            fileselect.sendKeys(tools.getMapValue(map,"文本路径"));
            //tools.sendKeys(fileselect,tools.getMapValue(map,"文本路径"));
            //tools.click(fileselect);
            //tools.click(tools.findBy(tools.getDriver(),By.id("fileselect")));
        }


        tools.selectByVisibleText(inBusi_type,tools.getMapValue(map,"业务分类"));
        tools.selectByVisibleText(inPhone_use,tools.getMapValue(map,"号码用途"));
        tools.selectByVisibleText(inStock_id,tools.getMapValue(map,"入库仓库"));
        tools.sendKeys(inNote,tools.getMapValue(map,"备注"));
        tools.click(btn);
        tools.sleep(20000);
        return dialog.getText();
    }

    public void clickDialogBtn(){
        tools.click(dialog_btn);
    }

}
