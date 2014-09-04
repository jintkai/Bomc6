package com.code.test.ibnmsConfig;


import com.code.common.Data;
import com.code.common.ExcelDriver;
import com.code.common.GridPage;
import com.code.common.TestCase;
import com.code.page.ibnmsConfig.alarmShield.AlarmShieldCfgPage;
import jxl.read.biff.BiffException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Iterator;

/**
 * Created by Jin on 2014/9/4.
 * 告警屏蔽过滤器
 */
public class AlarmShieldCfgTest extends TestCase{
    AlarmShieldCfgPage shieldCfg=new AlarmShieldCfgPage();
    GridPage gridTable=new GridPage();
    @BeforeMethod
    @Parameters({"Action_URL"})
    public void beforeMethod(String actionUrl)
    {
        TestCase.eventDriver.get(Data.baseUrl + actionUrl);
    }

    @DataProvider(name="AlarmShield")
    public Iterator dataDriver(Method method) throws IOException, BiffException {
        ExcelDriver excelDriver=new ExcelDriver("告警屏蔽过滤器",method.getName());
        excelHead=excelDriver.getHead(0);
        return excelDriver;
    }
    @Test(dataProvider = "AlarmShield",priority =0)
    public void searchAlarmShield(String[] str)
    {
        map=tools.changeStringToMap(excelHead,str);
        gridTable=shieldCfg.search(map);
        tools.assertEquals(gridTable.getRowNum(),
                Integer.parseInt(tools.getMapValue(map,"期望值")),map);
    }
    @Test(dataProvider = "AlarmShield",priority = 1)
    public void operateAlarmShield(String[] str)
    {
        map=tools.changeStringToMap(excelHead,str);

        gridTable=shieldCfg.opearte(map);
        gridTable=shieldCfg.search(map);
        tools.assertEquals(gridTable.getRowNum(),
                Integer.parseInt(tools.getMapValue(map,"期望值")),map);
    }
}
