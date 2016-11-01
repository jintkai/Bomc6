package com.code.test.unicom;

import com.code.common.Data;
import com.code.common.TestCase2;
import com.code.test.unicom.main.MainPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * Created by Administrator on 16-5-31.
 */
public class MainMenuTest extends TestCase2 {
    MainPage mainPage=new MainPage(eventDriver);
    @Parameters({"node"})
    public MainMenuTest(String node) {
        super(node);
    }
//    @BeforeMethod
//    @Parameters({"Base_URL"})
//    public void beforeMethod(String base_url)
//    {
//        eventDriver.get(Data.baseUrl);
//        beforeClass(base_url);
//    }

    @Test
    public void selectMenu(){
        tools.sleep(10000);
        mainPage.menuPage.selectMenu("号码属性维护","靓号规则维护");
        tools.sleep(5000);
        //mainPage.leftBodyPage.add();
        //tools.sleep(10000);
    }
}
