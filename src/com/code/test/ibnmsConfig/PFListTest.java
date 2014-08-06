package com.code.test.ibnmsConfig;

import com.code.common.Data;
import com.code.common.ExcelDriver;
import com.code.common.GridPage;
import com.code.common.TestCase;
import com.code.page.ibnmsConfig.PfList.PFListPage;
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
 * Created by jinkai on 2014/7/11.
 */
public class PFListTest extends TestCase {
    PFListPage pfList=new PFListPage();
    @BeforeMethod
    @Parameters({"Base_URL","Action_URL"})
    public void beforeMethod(String baseUrl,String actionUrl)
    {
        TestCase.eventDriver.get(baseUrl + actionUrl);
    }

    @DataProvider(name="PFlist")
    public Iterator dataDriver(Method method) throws IOException, BiffException {
        ExcelDriver excelDriver=new ExcelDriver("PF_NEW",method.getName());
        excelHead=excelDriver.getHead(0);
        return excelDriver;
    }
    @Test(dataProvider = "PFlist")
    public void addPF(String[] str)
    {
        map=tools.changeStringToMap(excelHead,str);
        if(tools.getMapValue(map,"操作类型").equals("增加"))
            pfList.add(map);
        if(tools.getMapValue(map,"操作类型").equals("修改"))
            pfList.edit(map);
        if(tools.getMapValue(map,"操作类型").equals("删除"))
            pfList.delete(map);
        GridPage gridTable=new GridPage();
        tools.assertEquals(gridTable.getListOftr("Performance名称",tools.getMapValue(map,"Performance名称")).size(),tools.getMapValue(map,"期望值"),map);
    }
    @Test(dataProvider = "PFlist")
    public void deployPF(String[] str)
    {
        Map<String,String> map=tools.changeStringToMap(excelHead,str);
        String option=tools.getMapValue(map,"操作类型");
        pfList.deploy(map);
        GridPage gridTable=new GridPage();
        Map<String, String> MqMap = gridTable.getTrOfAllTd(gridTable.getListOftr("Performance名称", tools.getMapValue(map, "选择名称")).get(0));
        if (option.equals("部署") || option.equals("卸载")) {
            tools.assertEquals(tools.getMapValue(MqMap,"部署状态"),tools.getMapValue(map,"期望值"),map);
        }
        if (option.equals("启动") || option.equals("停止")) {
            tools.assertEquals(tools.getMapValue(MqMap,"运行状态"),tools.getMapValue(map,"期望值"),map);
        }
    }
}
