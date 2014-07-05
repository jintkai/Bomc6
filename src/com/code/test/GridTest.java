package com.code.test;

import com.code.common.GridTablePage2;
import com.code.common.GridTools;
import com.code.common.TestCase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * Created by jinkai on 04/07/2014.
 */
public class GridTest extends TestCase{
    GridTools grid=new GridTools();
    @Test
    public void test()
    {
        //System.out.println(grid.getTdOfTr(4, "KBP名称"));
        grid.selectTr(0);
    }

    @BeforeMethod
    @Parameters({"Base_URL","Action_URL"})
    public void beforeMethod(String baseUrl,String actionUrl)
    {
        TestCase.eventDriver.get("http://172.21.0.31:8084/ibnms/config/kbpList.action?kbp_class=");
    }

}
