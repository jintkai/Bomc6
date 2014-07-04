package com.code.test;

import com.code.common.ExcelDriver;
import com.code.common.GridTablePage;
import com.code.common.TestCase;
import com.code.page.kbplist.KbpListFramePage;
import jxl.read.biff.BiffException;
import org.testng.Assert;
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
    KbpListFramePage kbpFrame=new KbpListFramePage();

    @BeforeMethod
    @Parameters({"Base_URL","Action_URL"})
    public void beforeMethod(String baseUrl,String actionUrl)
    {
        TestCase.eventDriver.get(baseUrl + actionUrl);
    }

    @DataProvider(name="kbpList")
    public Iterator dataForKbp(Method method) throws IOException, BiffException {
        ExcelDriver excelDriver=new ExcelDriver("KBP",method.getName());
        excelHead=excelDriver.getHead(0);
        return excelDriver;
    }

    @Test(dataProvider = "kbpList")
    public void searchKbpByTreeCaption(String[] obj)
    {
        map=tools.changeStringToMap(excelHead,obj);
        GridTablePage gridTable=kbpFrame.searchKbpByTree(tools.getMapValue(map,"KBP名称"));
        tools.assertTrue(gridTable.equalsSearch("./td[2]",Integer.parseInt(tools.getMapValue(map,"期望值")),
                tools.getMapValue(map,"KBP编号")));
    }

    @Test(dataProvider = "kbpList")
    public void searchKbpByTreeClass(String[] obj)
    {
        map=tools.changeStringToMap(excelHead,obj);
        GridTablePage gridTable=kbpFrame.searchKbpByTree(tools.getMapValue(map,"KBP编号"));
        tools.assertTrue(gridTable.equalsSearch("./td[2]",Integer.parseInt(tools.getMapValue(map,"期望值")),
                tools.getMapValue(map,"KBP编号")));
    }

    @Test(dataProvider = "kbpList")
    public void searchByClass(String[] obj)
    {
        map=tools.changeStringToMap(excelHead,obj);
        GridTablePage gridTable=kbpFrame.searchByClass(tools.getMapValue(map,"KBP编号"));
        tools.assertTrue(gridTable.equalsSearch("./td[2]",Integer.parseInt(tools.getMapValue(map,"期望值")),
                tools.getMapValue(map,"KBP编号")));
    }

    @Test(dataProvider = "kbpList")
    public void searchByCaption(String[] obj)
    {
        map=tools.changeStringToMap(excelHead,obj);
        GridTablePage gridTable=kbpFrame.searchByCaption(tools.getMapValue(map,"KBP名称"));
        tools.assertTrue(gridTable.equalsSearch("./td[3]",Integer.parseInt(tools.getMapValue(map,"期望值")),
                tools.getMapValue(map,"KBP名称")));
    }

    @Test(dataProvider = "kbpList")
    public void searchByMulti(String[] obj)
    {
        map=tools.changeStringToMap(excelHead,obj);
        GridTablePage gridTable=kbpFrame.searchByMulti(tools.getMapValue(map,"KBP编号"),tools.getMapValue(map,"KBP名称"));
        tools.assertTrue(gridTable.equalsSearch("./td[2]",Integer.parseInt(tools.getMapValue(map,"期望值")),
                tools.getMapValue(map,"KBP编号")));
        tools.assertTrue(gridTable.equalsSearch("./td[3]",Integer.parseInt(tools.getMapValue(map,"期望值")),
                tools.getMapValue(map,"KBP名称")));
    }
    @Test(dataProvider = "kbpList")
    public void addKbp(String[] obj)
    {
        int row=kbpFrame.addKbp(obj);
        tools.assertEquals(row, Integer.parseInt(obj[3]));
    }
    @Test(dataProvider="kbpList")
    public void delKbp(String[] obj)
    {
        //GridTablePage gridTable=kbpFrame.searchByMulti(obj[5],obj[6]);
        kbpFrame.delKbp(obj);
    }

}
