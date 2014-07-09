package com.code.test;

import com.code.common.ExcelDriver;
import com.code.common.GridPage;
import com.code.common.TestCase;
import com.code.page.envList.EnvListPage;
import jxl.read.biff.BiffException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by jinkai on 2014/7/9.
 */
public class EnvListTest  extends TestCase{
    EnvListPage envList=new EnvListPage();
    @BeforeMethod
    @Parameters({"Base_URL","Action_URL"})
    public void beforeMethod(String baseUrl,String actionUrl)
    {
        TestCase.eventDriver.get(baseUrl + actionUrl);
    }

    @DataProvider(name="envList")
    public Iterator dataDriver(Method method) throws IOException, BiffException {
        ExcelDriver excelDriver=new ExcelDriver("listEnvironment",method.getName());
        excelHead=excelDriver.getHead(0);
        return excelDriver;
    }
    //@Test(dataProvider="envList")
    public void searchEnv(String[] str)
    {
        Map<String,String> map=tools.changeStringToMap(excelHead,str);
        envList.searchEnv(map);
        GridPage gridTable=new GridPage();
        tools.assertEquals(gridTable.getRowNum(),map,"期望值");
    }
    //@Test(dataProvider="envList")
    public void addEnv(String[] str)
    {
        Map<String,String> map=tools.changeStringToMap(excelHead,str);
        envList.addEnv(map);
        envList.searchEnv(map);
        GridPage gridTable=new GridPage();
        tools.assertEquals(gridTable.getRowNum(),map,"期望值");
    }
    @Test(dataProvider="envList")
    public void devEnv(String[] str)
    {
        Map<String,String> map=tools.changeStringToMap(excelHead,str);
        envList.delEnv(map);
        envList.searchEnv(map);
        GridPage gridTable=new GridPage();
        tools.assertEquals(gridTable.getRowNum(),map,"期望值");
    }
}
