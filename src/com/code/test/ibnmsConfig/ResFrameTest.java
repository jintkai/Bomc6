package com.code.test.ibnmsConfig;

import com.code.common.Data;
import com.code.common.ExcelDriver;
import com.code.common.GridPage;
import com.code.common.TestCase;
import com.code.page.ibnmsConfig.reslist.ResListFramePage;
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
    @BeforeMethod
    @Parameters({"Action_URL"})
    public void beforeMethod(String actionUrl)
    {
        this.eventDriver.get(Data.baseUrl+actionUrl);
    }
    @Test(dataProvider = "resCase")
    public void searchRes(String str[])
    {
        map=tools.changeStringToMap(excelHead,str);
        GridPage gritTable=resFrame.search(map);
        tools.assertEquals(gritTable.getRowNum(),Integer.parseInt(tools.getMapValue(map,"期望值")),map);
    }
    @Test(dataProvider = "resCase")
    public void searchResByTree(String str[])
    {
        map=tools.changeStringToMap(excelHead,str);
        GridPage gritTable=resFrame.searchByTree(map);
        tools.assertEquals(gritTable.getRowNum(),Integer.parseInt(tools.getMapValue(map,"期望值")),map);
    }
    @Test(dataProvider = "resCase")
    public void operateRes(String str[])
    {
        map=tools.changeStringToMap(excelHead,str);
        resFrame.operateRes(map);
        GridPage gridTable=resFrame.search(map);
        tools.assertEquals(gridTable.getRowNum(),Integer.parseInt(tools.getMapValue(map,"期望值")),map);
    }


}
