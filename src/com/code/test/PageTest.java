package com.code.test;

import com.code.common.TestCase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by jon on 2016/10/21.
 */
public class PageTest extends TestCase {


    //@Parameters("node")
    public PageTest() {
        super("http://172.21.221.141:5555/wd/hub");
    }

    @Test
    public void test(){

        eventDriver.get("http://172.21.10.31:16010/itilManage/getAppealList.do?IACTIVITYID=4015103&timeStamp=1477016773877&PROCESSID=40151");
        tools.sendKeys(tools.findBy(eventDriver, By.id("username")),"admin");
        tools.sendKeys(tools.findBy(eventDriver, By.id("password")),"12345678");
        tools.sendKeys(tools.findBy(eventDriver, By.id("password")),"12345678");
        tools.submit(tools.findBy(eventDriver,By.id("submitBtn")));
        List<WebElement> list=tools.findElements(eventDriver,By.tagName("img"));
        for(int i=0;i<=5;i++){
            tools.click(list.get(3));
            List<WebElement> iframes=tools.findElements(eventDriver,By.id("TB_iframeContent"));
            if(iframes.size()!=0){
                System.out.println("弹出iframe,定位次数:"+i);
                break;
            }else{
                System.out.println("没有弹出iframe,需要再次定位。定位次数:"+i);
            }
            tools.sleep(5000);
        }
        tools.switchToFrame(tools.findBy(eventDriver,By.id("TB_iframeContent")));
        List<WebElement> iframes=tools.findElements(eventDriver,By.xpath("//a[20]"));
        System.out.println("标签总数:"+iframes.size());
        tools.click(iframes.get(0));
        tools.sleep(10000);
    }
}
