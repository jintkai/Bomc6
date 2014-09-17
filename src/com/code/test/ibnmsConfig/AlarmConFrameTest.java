package com.code.test.ibnmsConfig;

import com.code.common.Data;
import com.code.common.ExcelDriver;
import com.code.common.GridPage;
import com.code.common.TestCase;
import com.code.page.ibnmsConfig.alarmConfigFrame.AlarmConfigFrame;
import jxl.read.biff.BiffException;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;

/**
 * Created by jinkai on 2014/7/16.
 */
public class AlarmConFrameTest extends TestCase {
    public AlarmConfigFrame alarmFrame;
    @Parameters({"node"})
    public AlarmConFrameTest(String node)
    {
        super(node);
        alarmFrame=new AlarmConfigFrame(eventDriver);
    }

    GridPage gridTable=new GridPage(eventDriver);
    @BeforeMethod
     @Parameters({"Action_URL"})
     public void beforeMethod(String actionUrl)
    {
        eventDriver.get(Data.baseUrl + actionUrl);
    }

    @DataProvider(name="alarmFrame")
    public Iterator dataDriver(Method method) throws IOException, BiffException {
        ExcelDriver excelDriver=new ExcelDriver("告警集中配置",method.getName());
        excelHead=excelDriver.getHead(0);
        return excelDriver;
    }
    @Test(dataProvider = "alarmFrame",priority = 0,description = "通过Tree查询告警集中配置")
    public void searchByTree(String[] str)
    {
        map=tools.changeStringToMap(excelHead,str);
        gridTable=alarmFrame.searchByTree(map);
        tools.assertEquals(gridTable.getRowNum(),Integer.parseInt(tools.getMapValue(map,"期望值")),map);
    }
    @Test(dataProvider = "alarmFrame",priority = 1,description = "通过告警配置表头查询告警集中配置")
    public void searchByHead(String[] str)
    {
        map=tools.changeStringToMap(excelHead,str);
        gridTable=alarmFrame.searchByHead(map);
        tools.assertEquals(gridTable.getRowNum(),Integer.parseInt(tools.getMapValue(map,"期望值")),map);
    }

    @Test(dataProvider = "alarmFrame",priority = 2,description = "增加、修改、删除告警集中配置")
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
        tools.assertEquals(gridTable.getRowNum(),Integer.parseInt(tools.getMapValue(map,"期望值")),map);
    }
    //@Test(dataProvider = "alarmFrame",description="增加变更项")
    public void addEdit(String[] str)
    {
        map=tools.changeStringToMap(excelHead,str);
        String option=tools.getMapValue(map,"操作类型");
        if (option.equals("增加"))
            alarmFrame.addConf(map,2);
    }
    //@Test
    public void exportExcel()
    {
        alarmFrame.exportExcel();
    }
    //@Test
    public void openEditedList()
    {
        alarmFrame.openEditedList();
        String hand=tools.switchToWindowByTitle("变更");
        //tools.assertNotEquals("FALSE",hand,"通过窗口Title切换窗口失败，窗口Title可能错误",map);
        tools.closeWindow();
        tools.switchToWindowByHand(hand);

    }
    //@Test
    public void openFilteredList()
    {
        alarmFrame.openFilteredList();
        String hand=tools.switchToWindowByTitle("过滤");
        //tools.assertNotEquals("FALSE",hand,"通过窗口Title切换窗口失败，窗口Title可能错误");
        tools.closeWindow();
        tools.switchToWindowByHand(hand);

    }
    //@Test
    public void addFilter()
    {
        //tools.assertEquals(1, alarmFrame.addFilter(), "添加到待过滤列表失败，第一条记录未添加到待过滤列表");
    }
    @Test(dataProvider = "alarmFrame",description="过滤器")
    public void queryFilterAdd(String[] str)
    {
        map=tools.changeStringToMap(excelHead,str);
        alarmFrame.queryFilterAdd(map);
        List<WebElement> list=tools.getOptions(alarmFrame.confBtn.queryFilter);
        boolean isExist=false;
        for (int i=0;i<list.size();i++)
        {
            if(list.get(i).getText().equals(tools.getMapValue(map,"期望值")))
                isExist=true;
        }
        if (tools.getMapValue(map,"操作类型").equals("增加"))
            tools.assertTrue(isExist,"增加的过滤器（"+tools.getMapValue(map,"期望值")+"）未显示在过滤器列表中。");

        if (tools.getMapValue(map,"操作类型").equals("删除"))
            tools.assertTrue(!isExist,"删除过滤器（"+tools.getMapValue(map,"期望值")+"）仍然显示在过滤器列表中。");
    }
}
