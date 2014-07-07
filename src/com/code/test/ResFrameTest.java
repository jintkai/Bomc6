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
    public void searchByUtilID(String str[])
    {
        map=tools.changeStringToMap(excelHead,str);
        GridPage gritTable=resFrame.searchResByUnitID(tools.getMapValue(map, "UNIT_ID"));
        int rowNum=gritTable.getRowNum();
        tools.assertEquals(rowNum,Integer.parseInt(tools.getMapValue(map,"期望值")));
    }
    @Test(dataProvider = "resCase")
      public void searchByName(String str[])
    {
        map=tools.changeStringToMap(excelHead,str);
        GridPage gritTable=resFrame.searchResByName(tools.getMapValue(map, "资源名称"));
        int rowNum=gritTable.getRowNum();
        tools.assertEquals(rowNum,Integer.parseInt(tools.getMapValue(map,"期望值")));
    }
    @Test(dataProvider = "resCase")
    public void searchByIp(String str[])
    {
        map=tools.changeStringToMap(excelHead,str);
        GridPage gritTable=resFrame.searchResByIp(tools.getMapValue(map, "IP地址"));
        int rowNum=gritTable.getRowNum();
        tools.assertEquals(rowNum,Integer.parseInt(tools.getMapValue(map,"期望值")));
    }
    @Test(dataProvider = "resCase")
    public void searchByType(String str[])
    {
        map=tools.changeStringToMap(excelHead,str);
        GridPage gritTable=resFrame.searchResByType(tools.getMapValue(map, "业务类型"));
        int rowNum=gritTable.getRowNum();
        tools.assertEquals(rowNum,Integer.parseInt(tools.getMapValue(map,"期望值")));
    }
    @Test(dataProvider = "resCase")
    public void searchByMulit(String str[])
    {
        map=tools.changeStringToMap(excelHead,str);
        GridPage gritTable=resFrame.searchResByMulit(tools.getMapValue(map, "UNIT_ID"),tools.getMapValue(map, "资源名称"),
                tools.getMapValue(map, "IP地址"),tools.getMapValue(map, "业务类型"));
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
