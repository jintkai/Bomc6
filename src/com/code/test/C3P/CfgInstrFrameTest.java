package com.code.test.C3P;

import com.code.common.ExcelDriver;
import com.code.common.GridPage;
import com.code.common.TestCase;
import com.code.C3P.instrFrame.CfgInstrFrame;
import jxl.read.biff.BiffException;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;

/**
 * Created by jinkai on 2014/7/23.
 */
public class CfgInstrFrameTest extends TestCase {
    public CfgInstrFrame instrFrame=new CfgInstrFrame();
    @Parameters({"node"})
    public CfgInstrFrameTest(String node)
    {
        super(node);
        instrFrame=new CfgInstrFrame();

    }
    @BeforeMethod
    @Parameters({"Base_URL","Action_URL"})
    public void beforeMethod(String baseUrl,String actionUrl)
    {
        eventDriver.get(baseUrl + actionUrl);
    }

    @DataProvider(name="instrFrame")
    public Iterator dataDriver(Method method) throws IOException, BiffException {
        ExcelDriver excelDriver=new ExcelDriver("CFGINSTR_NEW",method.getName());
        excelHead=excelDriver.getHead(0);
        return excelDriver;
    }
    @Test(dataProvider = "instrFrame")
    public void addGroup(String[] str)
    {
        map=tools.changeStringToMap(excelHead,str);
        String option=tools.getMapValue(map,"操作类型");
        if (option.equals("增加"))
            instrFrame.addGroup(map);
        if (option.equals("修改"))
            instrFrame.editGroup(map);
        if (option.equals("删除"))
            instrFrame.deleteGroup(map);
        eventDriver.navigate().refresh();
        tools.assertEquals(instrFrame.getListOfGroups(tools.getMapValue(map,"TREE")).size(),tools.getMapValue(map,"期望值"),map);
    }
    @Test(dataProvider = "instrFrame")
    public void searchByTree(String[] str)
    {
        map=tools.changeStringToMap(excelHead,str);
        GridPage gridTable=instrFrame.searchByTree(map);
        tools.assertEquals(gridTable.getRowNum(),tools.getMapValue(map,"期望值"),map);
    }
    @Test
    public void getAttribute()
    {
        List<WebElement> lists=instrFrame.findGroups();
        WebElement[] elements=new WebElement[lists.size()];
        lists.toArray(elements);
        tools.assertEquals(elements[instrFrame.searchGroup("默认指令组")].getAttribute("class"),
                "level0 curSelectedNode","默认组错误");
    }
}
