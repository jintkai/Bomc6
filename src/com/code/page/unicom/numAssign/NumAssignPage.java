package com.code.page.unicom.numAssign;

import com.code.common.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 16-6-14.
 */
public class NumAssignPage extends Page {
    public NumAssignPage(EventFiringWebDriver eventListener){
        super(eventListener);
    }
    @FindBy(how= How.ID,using = "inValidForm")
    WebElement inValidForm;
    @FindBy(how=How.ID,using = "fileselect")
    WebElement fileselect;
    @FindBy(how=How.ID,using = "begin_num")
    WebElement begin_num;
    @FindBy(how=How.ID,using = "end_num")
    WebElement end_num;
    @FindBy(how=How.ID, using = "desStock_name")
    WebElement desStock_name;
    @FindBy(how=How.ID,using = "busi_type")
    WebElement busi_type;
    @FindBy(how=How.ID,using = "phone_use")
    WebElement phone_use;
    @FindBy(how=How.ID,using = "note")
    WebElement note;
    @FindBy(how=How.CLASS_NAME,using = "form-section-footer")
    WebElement footerForm;
    @FindBy(how=How.ID,using = "group_tree_1_switch")
    WebElement group_tree_1_switch; //树展开按钮
    @FindBy(how=How.ID,using = "group_tree_1_ul")
    WebElement group_tree_1_ul; //展开后的一级菜单

    @FindBy(how=How.XPATH,using = "//tbody/tr[2]/td/div")
    WebElement content;
    @FindBy(how=How.XPATH,using="//tbody/tr[2]/td/div/div/div[2]")
    WebElement dialog;
    @FindBy(how=How.XPATH,using = "//tbody/tr[2]/td/div/div/div[3]/input")
    WebElement dialog_btn;

    public String numAssegn(Map<String,String> map){
        List<WebElement> els = tools.findElements(inValidForm, By.tagName("label"));
        WebElement e1 = null;
        WebElement e2 = null;
        WebElement kind1=null;
        WebElement kind2=null;
        for (WebElement e : els) {
            if(e.getText().equals("手工方式")){
                //e1 = tools.findBy(e,By.tagName("input"));
                e1 = e;
            }
            else if (e.getText().equals("文件方式")){
                e2 = e;
            }
            else if (e.getText().equals("全部")){
                    kind1=e;
                }
            else if(e.getText().equals("普通号码"))
                    {
                      kind2=e;
                    }
        }

        if( tools.getMapValue(map,"操作方式").equals("手工方式"))
        {
            tools.click(e1);
            tools.sendKeys(begin_num,tools.getMapValue(map,"开始号段"));
            tools.sendKeys(end_num,tools.getMapValue(map,"终止号段"));
        }
        else {
            tools.click(e2);
            fileselect.sendKeys(tools.getMapValue(map,"文本路径"));
        }
        if( tools.getMapValue(map,"目的机构").contains("区县")) {
            tools.click(desStock_name);

            tools.click(group_tree_1_switch);

            List<WebElement> treeMenus = tools.findElements(group_tree_1_ul, By.tagName("a"));
            for (int i = 0; i < treeMenus.size(); i++) {
                WebElement e = treeMenus.get(i);
            /*group_tree_1_ul=tools.findBy(tools.getDriver(),By.id("group_tree_1_ul"));
            e=tools.findElements(group_tree_1_ul,By.tagName("a")).get(i);*/
                String s = e.getText();
                if (s.equals(tools.getMapValue(map, "目的机构"))) {
                    //tools.sleep(3000);
                /*group_tree_1_ul=tools.findBy(tools.getDriver(),By.id("group_tree_1_ul"));
                e=tools.findElements(group_tree_1_ul,By.tagName("a")).get(i);*/
                    tools.click(e);
                    break;
                }
            }
        }
        String res=tools.getMapValue(map,"目的机构");
        if( tools.getMapValue(map,"目的机构").contains("市区")){
            tools.click(desStock_name);
            tools.click(tools.findBy(tools.getDriver(),By.id("group_tree_1_a")));
        }
        tools.selectByVisibleText(busi_type,tools.getMapValue(map,"业务分类"));
        tools.selectByVisibleText(phone_use,tools.getMapValue(map,"号码用途"));
        String kind=tools.getMapValue(map,"号码分类");
        if( kind.equals("全部"))
            tools.click(kind1);
        else if( kind.equals("普通号码"))
            tools.click(kind2);

        tools.sendKeys(note,tools.getMapValue(map,"备注"));

        tools.click(tools.findElements(footerForm,By.tagName("a")).get(1));
        return dialog.getText();
    }
    public void clickDialogBtn(){
        tools.click(dialog_btn);
    }
}
