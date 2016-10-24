package com.code.test.bnms;

import com.code.common.Data;
import com.code.common.ExcelDriver;
import com.code.common.GridPage;
import com.code.common.TestCase;
import com.code.bnms.alarmConfUpdate.AlarmConfUpdateList;
import jxl.read.biff.BiffException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Iterator;

/**
 * Created by jinkai on 2014/7/23.
 */
public class AlarmConfUpdateListTest extends TestCase {
    public AlarmConfUpdateList alarmConfUpdate;//=new AlarmConfUpdateList();
    GridPage gridTable;//=new GridPage(eventDriver);
    @Parameters({"node"})
    public AlarmConfUpdateListTest(String node)
    {
        super(node);
        alarmConfUpdate=new AlarmConfUpdateList(eventDriver);
        gridTable=new GridPage(eventDriver);
    }
    @BeforeMethod
    @Parameters({"Action_URL"})
    public void beforeMethod(String actionUrl)
    {
        eventDriver.get(Data.baseUrl + actionUrl);
    }

    @DataProvider(name="alarmFrame")
    public Iterator dataDriver(Method method) throws IOException, BiffException {
        ExcelDriver excelDriver=new ExcelDriver("ALARMCONFUPDATA",method.getName());
        excelHead=excelDriver.getHead(0);
        return excelDriver;
    }
    @Test(dataProvider = "alarmFrame",description = "查询更新列表")
    public void search(String[] str)
    {
        map=tools.changeStringToMap(excelHead,str);
        gridTable=alarmConfUpdate.search(map);
        tools.assertEquals(gridTable.getRowNum(),tools.getMapValue(map,"期望值"),map);
    }
    @Test
    public void update()
    {
        alarmConfUpdate.update();
    }
    @Test
    public void startTask()
    {
        String url=alarmConfUpdate.startTask();
        System.out.println(url);
        tools.assertTrue(url.contains("newTask"),"msg");
    }
    @Test
    public void delete()
    {
        alarmConfUpdate.delete();
        int row=gridTable.getRowNum();
        //tools.assertEquals(1,row,"删除列表数据错误：");
    }
}
