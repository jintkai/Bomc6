package com.code.test.bnms;


import com.code.common.Data;
import com.code.common.ExcelDriver;
import com.code.common.GridPage;
import com.code.common.TestCase;
import com.code.bnms.alarmShield.AlarmShieldCfgPage;
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
 * Created by Jin on 2014/9/4.
 * 告警屏蔽过滤器
 */
public class AlarmShieldCfgTest extends TestCase{
    AlarmShieldCfgPage shieldCfg;//=new AlarmShieldCfgPage();
    GridPage gridTable;//=new GridPage(eventDriver);
    @Parameters({"node"})
    public AlarmShieldCfgTest(String node)
    {
        super(node);
        shieldCfg=new AlarmShieldCfgPage(eventDriver);
        gridTable=new GridPage(eventDriver);
    }
    @BeforeMethod
    @Parameters({"Action_URL"})
    public void beforeMethod(String actionUrl)
    {
        eventDriver.get(Data.baseUrl + actionUrl);
    }

    @DataProvider(name="AlarmShield")
    public Iterator dataDriver(Method method) throws IOException, BiffException {
        ExcelDriver excelDriver=new ExcelDriver("告警屏蔽过滤器",method.getName());
        excelHead=excelDriver.getHead(0);
        return excelDriver;
    }
    @Test(dataProvider = "AlarmShield",priority =0,description = "告警屏蔽过滤器查询功能")
    public void searchAlarmShield(String[] str)
    {
        map=tools.changeStringToMap(excelHead,str);
        gridTable=shieldCfg.search(map);
        tools.assertEquals(gridTable.getRowNum(),
                Integer.parseInt(tools.getMapValue(map,"期望值")),map.toString());
        Map<String, String> resultMap=gridTable.getTrOfAllTd(1);
        tools.assertEquals(tools.getMapValue(resultMap,"关联监控点个数"),tools.getMapValue(map,"关联监控点个数"),map.toString());
    }
    @Test(dataProvider = "AlarmShield",priority = 1,description = "告警屏蔽过滤器操作功能：增、删、改")
    public void operateAlarmShield(String[] str)
    {
        map=tools.changeStringToMap(excelHead,str);

        gridTable=shieldCfg.opearte(map);
        gridTable=shieldCfg.search(map);
        tools.assertEquals(gridTable.getRowNum(),
                Integer.parseInt(tools.getMapValue(map, "期望值")), map.toString());
        if (!tools.getMapValue(map,"操作类型").equals("删除"))
        {
        Map<String, String> resultMap=gridTable.getTrOfAllTd(1);
        tools.assertEquals(tools.getMapValue(resultMap,"关联监控点个数"),tools.getMapValue(map,"关联监控点个数"),map.toString());}
    }
}
