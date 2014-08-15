package com.code.page.C3P.instrFrame.page;

import com.code.common.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by jinkai on 2014/7/23.
 */
public class GroupTreePage extends Page {
    @FindBy(className = "addTree")
    WebElement addTree;
    @FindBy(id="groupTree")
    WebElement groupTree;

    public void addGroup(Map<String,String> map)
    {
        tools.click(addTree);
        int i=0;
        while(i<20){
            actions.sendKeys(Keys.BACK_SPACE);
            i++;
        }
        actions.sendKeys(tools.getMapValue(map,"指令组名称"));
        actions.sendKeys(Keys.ENTER).perform();
    }

    public void editGroup(Map<String,String> map)
    {
        List<WebElement> lists = findGroups();
        WebElement[] elements = new WebElement[lists.size()];
        lists.toArray(elements);
        int index=searchGroup(tools.getMapValue(map,"选择名称"));
        tools.click(elements[index]);
        WebElement span=tools.findBy(elements[index],By.xpath(".//span"));
        WebElement edit=tools.findBy(elements[index], By.className("edit"));
        tools.click(edit);
        //tools.sendKeys(span,tools.getMapValue(map,"指令组名称"));
        int i=0;
        while(i<20)
        {   actions.sendKeys(Keys.BACK_SPACE);
            i++;}
        actions.sendKeys(tools.getMapValue(map,"指令组名称"));
        actions.sendKeys(Keys.ENTER).perform();
    }

    public void deleteGroup(Map<String,String> map)
    {
        List<WebElement> lists = findGroups();
        WebElement[] elements = new WebElement[lists.size()];
        lists.toArray(elements);
        int index=searchGroup(tools.getMapValue(map,"选择名称"));
        tools.click(elements[index]);
        WebElement remove=tools.findBy(elements[index], By.className("remove"));
        tools.click(remove);
        tools.alertAccept();
    }

    public void searchByTree(Map<String,String> map)
    {
        List<WebElement> lists = findGroups();
        WebElement[] elements = new WebElement[lists.size()];
        lists.toArray(elements);
        int i=searchGroup(tools.getMapValue(map,"TREE"));
        tools.click(elements[i]);
    }


    public List<WebElement> findGroups()
    {
       return tools.findElements(groupTree, By.xpath(".//li/a"));
    }
    public void searchGroups() {
        List<WebElement> lists = findGroups();
        WebElement[] elements = new WebElement[lists.size()];
        lists.toArray(elements);
        for (int i = 0; i < lists.size(); i++)
        {
            WebElement span=tools.findBy(elements[i],By.xpath(".//span"));
            System.out.println(span.getText());
        }
    }
    public int searchGroup(String str)
    {
        List<WebElement> lists = findGroups();
        WebElement[] elements = new WebElement[lists.size()];
        lists.toArray(elements);
        for (int i = 0; i < lists.size(); i++)
        {
            WebElement span=tools.findBy(elements[i],By.xpath(".//span"));
            String text=span.getText();
            if (text.equals(str))
                return i;
        }
        System.out.println("无法定位元素");
        return -1;
    }


    public ArrayList<Integer> getListOfGroups(String str)
    {
        ArrayList<Integer> list = new ArrayList();
        List<WebElement> lists=findGroups();
        WebElement[] elements = new WebElement[lists.size()];
        lists.toArray(elements);
        for (int i=0;i<lists.size();i++)
        {
            elements[i].getAttribute("ID");
            WebElement span=tools.findBy(elements[i],By.xpath(".//span"));
            if( span.getText().equals(str))
                list.add(i+1);
        }
        return list;
    }
 }
