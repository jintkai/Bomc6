package com.code.test.ibnmsConfig;

import com.code.common.Data;
import com.code.common.ExcelDriver;
import com.code.common.GridPage;
import com.code.common.TestCase;
import com.code.page.ibnmsConfig.templateDev.TemplateDevListPage;
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
 * Created by Jin on 2014/8/21.
 */
public class TemplateDevListTest extends TestCase {
    TemplateDevListPage devList=new TemplateDevListPage();
    GridPage gridTable=new GridPage();
    @BeforeMethod
    @Parameters({"Action_URL"})
    public void beforeMethod(String actionUrl)
    {
        TestCase.eventDriver.get(Data.baseUrl + actionUrl);
    }

    @DataProvider(name="TemplateDev")
    public Iterator dataDriver(Method method) throws IOException, BiffException {
        ExcelDriver excelDriver=new ExcelDriver("告警模板关联",method.getName());
        excelHead=excelDriver.getHead(0);
        return excelDriver;
    }

    @Test(dataProvider = "TemplateDev",priority = 0,description = "告警模板关联查询")
    public void searchTemplateDev(String[] str)
    {
        map=tools.changeStringToMap(excelHead,str);
        gridTable=devList.search(map);
        tools.assertEquals(gridTable.getRowNum(),Integer.parseInt(tools.getMapValue(map, "期望值")),map);
    }

    @Test(dataProvider = "TemplateDev",priority = 1,description = "告警模板关联资源查询")
    public void searchTemplateDevSet(String[] str)
    {
        map=tools.changeStringToMap(excelHead,str);
        int row=devList.searchDevSet(map);
        tools.assertEquals(row,Integer.parseInt(tools.getMapValue(map, "期望值")),map);
    }

    @Test(dataProvider = "TemplateDev",priority = 2,description = "告警模板关联操作")
    public void operateTemplateDev(String[] str)
    {
        map=tools.changeStringToMap(excelHead,str);
        String operation=tools.getMapValue(map,"操作类型");
        gridTable=devList.operate(map);
        gridTable=devList.search(map);
        if (operation.equals("二次同步"))
        {
            String[] syncStatus=gridTable.getTdOfAllTr(tools.getMapValue(map,"状态字段"));
            for (int i=0;i<syncStatus.length;i++)
            {
                System.out.println(syncStatus[i]);
                tools.assertEquals(syncStatus[i],tools.getMapValue(map, "期望值"),map);
            }
        }
        else
            tools.assertEquals(gridTable.getRowNum(),Integer.parseInt(tools.getMapValue(map, "期望值")),map);
    }
}
