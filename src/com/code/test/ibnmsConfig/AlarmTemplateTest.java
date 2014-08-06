package com.code.test.ibnmsConfig;

import com.code.common.ExcelDriver;
import com.code.common.GridPage;
import com.code.common.TestCase;
import com.code.page.ibnmsConfig.alarmTemplate.AlarmTemplateList;
import jxl.read.biff.BiffException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Iterator;

/**
 * Created by Jin on 2014/8/4.
 */
public class AlarmTemplateTest extends TestCase {
    AlarmTemplateList alarmTemplate=new AlarmTemplateList();
    @BeforeMethod
    @Parameters({"Base_URL","Action_URL"})
    public void beforeMethod(String baseUrl,String actionUrl)
    {
        TestCase.eventDriver.get(baseUrl + actionUrl);
    }

    @DataProvider(name="alarmTemplate")
    public Iterator dataDriver(Method method) throws IOException, BiffException {
        ExcelDriver excelDriver=new ExcelDriver("ALARMTEMPLATE_NEW",method.getName());
        excelHead=excelDriver.getHead(0);
        return excelDriver;
    }
    @Test(dataProvider = "alarmTemplate",description = "告警模板查询")
    public void search(String[] str)
    {
        map=tools.changeStringToMap(excelHead,str);
        GridPage gridTable=alarmTemplate.search(map);
        tools.assertEquals(gridTable.getRowNum(),tools.getMapValue(map,"期望值"),"更新列表数据与预期值不一致");
    }
    @Test(dataProvider = "alarmTemplate",description = "告警模板增加")
    public void add(String[] str)
    {
        map=tools.changeStringToMap(excelHead,str);
        String option=tools.getMapValue(map,"操作类型");
        GridPage gridTable=new GridPage();
        if (option.equals("增加"))
        {
            gridTable=alarmTemplate.add(map);
        }
        if (option.equals("修改"))
        {
            gridTable=alarmTemplate.add(map);
        }
        if (option.equals("删除"))
        {
            gridTable=alarmTemplate.search(map);
            gridTable.selectTr(1);
            gridTable=alarmTemplate.delete();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        gridTable=alarmTemplate.search(map);
        tools.assertEquals(gridTable.getRowNum(),tools.getMapValue(map,"期望值"),"增加成功，但列表中数据错误");
    }
}
