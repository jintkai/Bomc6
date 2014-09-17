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
    PFListPage pfList;
    @Parameters({"node"})
    public PFListTest(String node)
    {
        super(node);
        pfList=new PFListPage(eventDriver);
    }
    @BeforeMethod
    @Parameters({"Action_URL"})
    public void beforeMethod(String actionUrl)
    {
        eventDriver.get(Data.baseUrl + actionUrl);
    }

    @DataProvider(name="PFlist")
    public Iterator dataDriver(Method method) throws IOException, BiffException {
        ExcelDriver excelDriver=new ExcelDriver("PERFORMANCE",method.getName());
        excelHead=excelDriver.getHead(0);
        return excelDriver;
    }
    @Test(dataProvider = "PFlist",priority = 0)
    public void operatePF(String[] str)
    {
        map=tools.changeStringToMap(excelHead,str);

        GridPage gridTable=pfList.operatePF(map);
        tools.assertEquals(gridTable.getListOftr(tools.getMapValue(map,"列表选择器"),tools.getMapValue(map,"列表匹配数据")).size(),
                Integer.parseInt(tools.getMapValue(map,"期望值")),map);
    }
    @Test(dataProvider = "PFlist",priority = 1)
    public void deployPF(String[] str)
    {
        Map<String,String> map=tools.changeStringToMap(excelHead,str);
        GridPage gridTable=pfList.deployPF(map);
        Map<String, String> MqMap = gridTable.getTrOfAllTd(gridTable.getListOftr(tools.getMapValue(map,"列表选择器"),tools.getMapValue(map,"列表匹配数据")).get(0));
        tools.assertEquals(tools.getMapValue(MqMap,tools.getMapValue(map,"状态字段")),tools.getMapValue(map,"期望值"),map);
    }
}
