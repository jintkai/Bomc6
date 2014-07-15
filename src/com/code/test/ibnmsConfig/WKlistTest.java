package com.code.test.ibnmsConfig;

import com.code.common.Data;
import com.code.common.ExcelDriver;
import com.code.common.GridPage;
import com.code.common.TestCase;
import com.code.page.ibnmsConfig.workstation.WKlistPage;
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
public class WKlistTest extends TestCase {
    WKlistPage wkList=new WKlistPage();
    @BeforeMethod
    @Parameters({"Base_URL","Action_URL"})
    public void beforeMethod(String baseUrl,String actionUrl)
    {
        TestCase.eventDriver.get(baseUrl + actionUrl);
    }

    @DataProvider(name="WKlist")
    public Iterator dataDriver(Method method) throws IOException, BiffException {
        ExcelDriver excelDriver=new ExcelDriver("WK_NEW",method.getName());
        excelHead=excelDriver.getHead(0);
        return excelDriver;
    }
    @Test(dataProvider = "WKlist")
    public void addWK(String[] str)
    {
        map=tools.changeStringToMap(excelHead,str);
        if(tools.getMapValue(map,"操作类型").equals("增加"))
            wkList.add(map);
        if(tools.getMapValue(map,"操作类型").equals("修改"))
            wkList.edit(map);
        if(tools.getMapValue(map,"操作类型").equals("删除"))
            wkList.delete(map);
        GridPage gridTable=new GridPage();
        tools.assertEquals(gridTable.getListOftr("名称",tools.getMapValue(map,"名称")).size(),map,"期望值");
    }
    @Test(dataProvider="WKlist")
    public void deployWK(String[] str)
    {
        Map<String,String> map=tools.changeStringToMap(excelHead,str);
        String option=tools.getMapValue(map,"操作类型");
        wkList.deploy(map);
        GridPage gridTable=new GridPage();
        Map<String, String> MqMap = gridTable.getTrOfAllTd(gridTable.getListOftr("名称", tools.getMapValue(map, "选择名称")).get(0));
        if (option.equals("部署") || option.equals("卸载")) {
            tools.assertEquals(tools.getMapValue(MqMap,"部署状态"),map,"期望值");
        }
        if (option.equals("启动") || option.equals("停止")) {
            tools.assertEquals(tools.getMapValue(MqMap,"运行状态"),map,"期望值");
        }
    }
}
