package com.code.test.ibnmsConfig;

import com.code.common.Data;
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
    EnvListPage envList=new EnvListPage(eventDriver);
    GridPage gridTable=new GridPage(eventDriver);
    @Parameters({"node"})
    public EnvListTest(String node)
    {
        super(node);
        envList=new EnvListPage(eventDriver);
        gridTable=new GridPage(eventDriver);
    }
    @BeforeMethod
    @Parameters({"Action_URL"})
    public void beforeMethod(String actionUrl)
    {
        eventDriver.get(Data.baseUrl + actionUrl);
    }

    @DataProvider(name="envList")
    public Iterator dataDriver(Method method) throws IOException, BiffException {
        ExcelDriver excelDriver=new ExcelDriver("部署环境",method.getName());
        excelHead=excelDriver.getHead(0);
        return excelDriver;
    }
    @Test(dataProvider="envList",priority = 0,description = "查询部署环境")
    public void searchEnv(String[] str)
    {
        Map<String,String> map=tools.changeStringToMap(excelHead,str);
        envList.search(map);
        gridTable=new GridPage(eventDriver);
        tools.assertEquals(gridTable.getRowNum(),Integer.parseInt(tools.getMapValue(map,"期望值")),map);
    }
    @Test(dataProvider="envList",priority = 1,description = "操作部署环境，增加、修改、删除")
    public void operateEnv(String[] str)
    {
        Map<String,String> map=tools.changeStringToMap(excelHead,str);
        envList.operateRes(map);
        gridTable=envList.search(map);
        tools.assertEquals(gridTable.getRowNum(),Integer.parseInt(tools.getMapValue(map,"期望值")),map);
    }
}
