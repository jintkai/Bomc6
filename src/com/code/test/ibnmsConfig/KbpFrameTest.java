package com.code.test.ibnmsConfig;

import com.code.common.Data;
import com.code.common.ExcelDriver;
import com.code.common.GridPage;
import com.code.common.TestCase;
import com.code.page.ibnmsConfig.kbplist.KbpListFramePage;
import jxl.read.biff.BiffException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Iterator;

/**
 * Created by jinkai on 01/07/2014.
 */
public class KbpFrameTest extends TestCase {
    KbpListFramePage kbpFrame;
    GridPage gridTable;
    @Parameters({"node"})
    public KbpFrameTest(String node)
    {

        super(node);
        kbpFrame=new KbpListFramePage(eventDriver);
        gridTable=new GridPage(eventDriver);
        Reporter.log("selenium Grid:"+node);
    }
    @BeforeMethod
    @Parameters({"Action_URL"})
    public void beforeMethod(String actionUrl)
    {
        eventDriver.get(Data.baseUrl + actionUrl);
    }

    @DataProvider(name="kbpList")
    public Iterator dataDriver(Method method) throws IOException, BiffException {
        ExcelDriver excelDriver=new ExcelDriver("KBP管理",method.getName());
        excelHead=excelDriver.getHead(0);
        return excelDriver;
    }
    @Test(dataProvider = "kbpList",priority = 0,description = "查询KBP")
    public void searchKBP(String str[])
    {
        map=tools.changeStringToMap(excelHead,str);
        gridTable=kbpFrame.search(map);
        tools.assertEquals(gridTable.getRowNum(),Integer.parseInt(tools.getMapValue(map,"期望值")),map);
    }
    @Test(dataProvider = "kbpList",priority = 1,description = "按Tree查询KBP")
    public void searchKBPByTree(String str[])
    {
        map=tools.changeStringToMap(excelHead,str);
        gridTable=kbpFrame.searchByTree(map);
        tools.assertEquals(gridTable.getRowNum(),Integer.parseInt(tools.getMapValue(map,"期望值")),map);
    }
    @Test(dataProvider="kbpList",priority = 2,description="KBP增加、删除、修改;")
    public void operateKBP(String str[])
    {
        map=tools.changeStringToMap(excelHead,str);
        String option=tools.getMapValue(map,"操作类型");
        kbpFrame.operateKbp(map);
        gridTable=kbpFrame.search(map);
        tools.assertEquals(gridTable.getRowNum(),Integer.parseInt(tools.getMapValue(map,"期望值")),map);
    }
}
