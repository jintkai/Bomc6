package com.code.test.bnms;

import com.code.common.Data;
import com.code.common.ExcelDriver;
import com.code.common.GridPage;
import com.code.common.TestCase;
import com.code.bnms.workstation.WKlistPage;
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
    WKlistPage wkList;
    @Parameters({"node"})
    public WKlistTest(String node)
    {
        super(node);
        wkList=new WKlistPage(eventDriver);
    }
    @BeforeMethod
    @Parameters({"Action_URL"})
    public void beforeMethod(String actionUrl)
    {
        eventDriver.get(Data.baseUrl + actionUrl);
    }

    @DataProvider(name="WKlist")
    public Iterator dataDriver(Method method) throws IOException, BiffException {
        ExcelDriver excelDriver=new ExcelDriver("WORKSTATION",method.getName());
        excelHead=excelDriver.getHead(0);
        return excelDriver;
    }
    @Test(dataProvider = "WKlist",priority = 0,description = "增加、修改、删除WORKSTATION")
    public void operateWK(String[] str)
    {
        map=tools.changeStringToMap(excelHead,str);
        GridPage gridTable=wkList.operateWK(map);
        tools.assertEquals(gridTable.getListOftr(tools.getMapValue(map,"列表选择器"),tools.getMapValue(map,"列表匹配数据")).size(),
                Integer.parseInt(tools.getMapValue(map,"期望值")),map.toString());
    }
    @Test(dataProvider="WKlist",priority = 1,description = "部署、启动、停止、卸载WORKSTATION")
    public void deployWK(String[] str)
    {
        Map<String,String> map=tools.changeStringToMap(excelHead,str);
        GridPage gridTable=wkList.deployWK(map);
        Map<String, String> MqMap = gridTable.getTrOfAllTd(gridTable.getListOftr(tools.getMapValue(map,"列表选择器"),tools.getMapValue(map,"列表匹配数据")).get(0));
        tools.assertEquals(tools.getMapValue(MqMap,tools.getMapValue(map,"状态字段")),tools.getMapValue(map,"期望值"),map.toString());
    }
}
