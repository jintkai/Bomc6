package com.code.test.ibnmsConfig;

import com.code.common.Data;
import com.code.common.ExcelDriver;
import com.code.common.GridPage;
import com.code.common.TestCase;
import com.code.page.ibnmsConfig.alarmCorrelation.AlarmCorrelationCfgList;
import jxl.read.biff.BiffException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Jin on 2014/9/11.
 * 数据库宕告警关联配置
 */
public class AlarmCorrelationCfgListTest extends TestCase {
    AlarmCorrelationCfgList alrmCorrelation=new AlarmCorrelationCfgList();
    GridPage gridTable=new GridPage();
    @BeforeMethod
    @Parameters({"Action_URL"})
    public void beforeMethod(String actionUrl)
    {
        TestCase.eventDriver.get(Data.baseUrl + actionUrl);
    }

    @DataProvider(name="AlarmCorrelation")
    public Iterator dataDriver(Method method) throws IOException, BiffException {
        ExcelDriver excelDriver=new ExcelDriver("数据库宕告警关联配置",method.getName());
        excelHead=excelDriver.getHead(0);
        return excelDriver;
    }
    @Test(dataProvider = "AlarmCorrelation",priority =0,description = "数据库宕告警关联配置")
    public void operateAlarmCorrelation(String[] str)
    {
        map=tools.changeStringToMap(excelHead,str);
        gridTable=alrmCorrelation.operate(map);

        ArrayList<Integer> list=gridTable.getListOftr(tools.getMapValue(map,"验证列"),tools.getMapValue(map,"目标UNITID"));
        tools.assertEquals(list.size(),Integer.parseInt(tools.getMapValue(map,"期望值")),map);
        if (!tools.getMapValue(map,"操作类型").equals("删除")) {
            Map<String, String> resultMap = gridTable.getTrOfAllTd(
                    list.get(0)
            );
            tools.assertEquals(tools.getMapValue(resultMap, "关联监控点个数"), tools.getMapValue(map, "关联监控点个数"), map);
        }
    }
}
