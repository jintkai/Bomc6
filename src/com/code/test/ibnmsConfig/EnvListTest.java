package com.code.test.ibnmsConfig;

import com.code.common.ExcelDriver;
import com.code.common.GridPage;
import com.code.common.TestCase;
import com.code.page.ibnmsConfig.envList.EnvListPage;
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
        ExcelDriver excelDriver=new ExcelDriver("ENV_NEW",method.getName());
        excelHead=excelDriver.getHead(0);
        return excelDriver;
    }
    @Test(dataProvider="envList")
    public void search(String[] str)
    {
        Map<String,String> map=tools.changeStringToMap(excelHead,str);
        envList.search(map);
        GridPage gridTable=new GridPage();
        tools.assertEquals(gridTable.getRowNum(),map,"期望值");
    }
    @Test(dataProvider="envList")
    public void addENV(String[] str)
    {
        Map<String,String> map=tools.changeStringToMap(excelHead,str);
        String option=tools.getMapValue(map,"操作类型");
        if(option.equals("增加")) {
            envList.add(map);
        }
        if (option.equals("修改"))
        {
            envList.edit(map);
        }
        if (option.equals("删除"))
        {
            envList.delete(map);
        }
        GridPage gridTable=envList.search(map);
        tools.assertEquals(gridTable.getRowNum(),map,"期望值");
    }
}
