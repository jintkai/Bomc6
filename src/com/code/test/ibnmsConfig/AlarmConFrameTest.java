package com.code.test.ibnmsConfig;

import com.code.common.ExcelDriver;
import com.code.common.GridPage;
import com.code.common.TestCase;
import com.code.page.ibnmsConfig.alarmConfigFrame.AlarmConfigFrame;
import jxl.read.biff.BiffException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Iterator;

/**
 * Created by jinkai on 2014/7/16.
 */
public class AlarmConFrameTest extends TestCase {
    public AlarmConfigFrame alarmFrame=new AlarmConfigFrame();
    @BeforeMethod
     @Parameters({"Base_URL","Action_URL"})
     public void beforeMethod(String baseUrl,String actionUrl)
    {
        TestCase.eventDriver.get(baseUrl + actionUrl);
    }

    @DataProvider(name="alarmFrame")
    public Iterator dataDriver(Method method) throws IOException, BiffException {
        ExcelDriver excelDriver=new ExcelDriver("ALARMCONF_NEW",method.getName());
        excelHead=excelDriver.getHead(0);
        return excelDriver;
    }
    @Test(dataProvider = "alarmFrame",description = "通过Tree查询告警集中配置")
    public void searchByTree(String[] str)
    {
        map=tools.changeStringToMap(excelHead,str);
        GridPage gridTable=alarmFrame.searchByTree(map);
        tools.assertEquals(gridTable.getRowNum(),map,"期望值");
    }
    @Test(dataProvider = "alarmFrame",description = "通过告警配置表头查询告警集中配置")
    public void searchByHead(String[] str)
    {
        map=tools.changeStringToMap(excelHead,str);
        GridPage gridTable=alarmFrame.searchByHead(map);
        tools.assertEquals(gridTable.getRowNum(),map,"期望值");
    }

    @Test(dataProvider = "alarmFrame",description = "增加、修改")
    public void addAlarm(String[] str)
    {
        map=tools.changeStringToMap(excelHead,str);
        String option=tools.getMapValue(map,"操作类型");
        if (option.equals("增加"))
            alarmFrame.addConf(map,1);
        if(option.equals("修改"))
            alarmFrame.edit(map,1);
        if(option.equals("删除"))
            alarmFrame.delete(map);
        GridPage gridTable=alarmFrame.searchByHead(map);
        tools.assertEquals(gridTable.getRowNum(),map,"期望值");
    }
    @Test(dataProvider = "alarmFrame",description="增加变更项")
    public void addEdit(String[] str)
    {
        map=tools.changeStringToMap(excelHead,str);
        String option=tools.getMapValue(map,"操作类型");
        if (option.equals("增加"))
            alarmFrame.addConf(map,2);
    }
}
