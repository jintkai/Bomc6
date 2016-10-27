package com.code.common;

import com.code.bnms.agentList.page.AgFormPage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.lang.reflect.Field;

/**
 * Created by jinkai on 2014/6/21.
 */
public class Page {
    public EventFiringWebDriver eventDriver;
    public Actions actions;//=new Actions(eventDriver);
    public Tools tools;
    public Page(EventFiringWebDriver eventDriver)
    {
        this.eventDriver=eventDriver;
        PageFactory.initElements(eventDriver,this);
        actions=new Actions(eventDriver);
        tools=new Tools(eventDriver);
    }
    public Page()
    {

    }
    public String getPageInfo(){
        StringBuffer info=new StringBuffer();
        Field arr[]=this.getClass().getDeclaredFields();

        for(int i=0;i<arr.length;i++){
            arr[i].setAccessible(true);
            Class c=arr[i].getType();
            StringBuffer attr=new StringBuffer();
            info.append("[变量:"+arr[i].getName());

            PageInfo pageInfo=arr[i].getAnnotation(PageInfo.class);
            if(pageInfo!=null) {
                try {
                    info.append(",说明:" + pageInfo.info());
                    Class<?> cls=Class.forName("org.openqa.selenium.WebElement");
                    try {
                        WebElement e = (WebElement) arr[i].get(this);
                        attr=attr.append(tools.getAttribute(e,"Value")).append(",").append(tools.getAttribute(e,"innerHTML"));

                        info.append(",值:"+attr).append(e.getText());
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
                }


            }
            info.append("]\n");
        }

        System.out.println(info.toString());
        return info.toString();
    }
}

class Persion{
    String a;
    String b;
    AgFormPage page;
    public void test(){
        //打印出所有变量的值
    }
}