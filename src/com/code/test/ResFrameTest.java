package com.code.test;

import com.code.common.ExcelDriver;
import com.code.common.GridPage;
import com.code.common.TestCase;
import com.code.page.reslist.ResListFramePage;
import jxl.read.biff.BiffException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Iterator;

/**
 * Created by jinkai on 07/07/2014.
 */
public class ResFrameTest extends TestCase {
    ResListFramePage resFrame=new ResListFramePage();
    @DataProvider(name="resCase")
    public Iterator dataDriver(Method method) throws IOException, BiffException {
        ExcelDriver excelDriver=new ExcelDriver("RES",method.getName());
        excelHead=excelDriver.getHead(0);
        return excelDriver;
    }
    @Test(dataProvider = "resCase")
    public void searchByTree(String str[])
    {
        map=tools.changeStringToMap(excelHead,str);
        GridPage gritTable=resFrame.searchResByTree(tools.getMapValue(map, "TREE值"));
        int rowNum=gritTable.getRowNum();
        tools.assertEquals(rowNum,Integer.parseInt(tools.getMapValue(map,"期望值")));
        String colStr=tools.getMapValue(map,"验证字段");
        String search=tools.getMapValue(map,"TREE值");
        tools.assertTrue(gritTable.equalsSearch(colStr,rowNum,search));
    }
    @Test(dataProvider = "resCase")
    public void searchRes(String str[])
    {
        map=tools.changeStringToMap(excelHead,str);
        resFrame.searchRes(map);
        GridPage gritTable=new GridPage();
        int rowNum=gritTable.getRowNum();
        tools.assertEquals(rowNum,map,"期望值");
    }

    @Test(dataProvider = "resCase")
    public void addRes(String str[] )
    {
        map=tools.changeStringToMap(excelHead,str);
        GridPage gritTable=resFrame.addRes(map);
        int rowNum=gritTable.getRowNum();
        tools.assertEquals(rowNum,Integer.parseInt(tools.getMapValue(map,"期望值")));
    }
    @Test(dataProvider = "resCase" ,dependsOnMethods="addRes")
    public void editRes(String str[])
    {
        map=tools.changeStringToMap(excelHead,str);
        GridPage gritTable=resFrame.editRes(map);
        int rowNum=gritTable.getRowNum();
        tools.assertEquals(rowNum,Integer.parseInt(tools.getMapValue(map,"期望值")));
    }
    @Test(dataProvider = "resCase",dependsOnMethods="addRes")
    public void delRes(String str[])
    {
        map=tools.changeStringToMap(excelHead,str);
        GridPage gritTable=resFrame.delRes(map);
        int rowNum=gritTable.getRowNum();
        tools.assertEquals(rowNum,Integer.parseInt(tools.getMapValue(map,"期望值")));
    }

    @BeforeMethod
    @Parameters({"Base_URL","Action_URL"})
    public void beforeMethod(String baseUrl,String actionUrl)
    {
        this.eventDriver.get(baseUrl+actionUrl);
    }
}
