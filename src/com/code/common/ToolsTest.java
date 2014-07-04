package com.code.common;

import jxl.read.biff.BiffException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Iterator;
import javax.tools.Tool;
import java.io.IOException;
import java.util.Map;

/**
 * Created by jinkai on 03/07/2014.
 */
public class ToolsTest  {
    String[] excelHead;
    Tools tools=new Tools();
    @DataProvider(name="test")
    public Iterator test() throws IOException, BiffException {
        ExcelMapDriver excelDriver=new ExcelMapDriver("KBP","searchByClass");
        excelHead=excelDriver.getHead(0);
        return  excelDriver;
    }
    @DataProvider(name="test2")
    public Iterator tes2t() throws IOException, BiffException {
        ExcelDriver excelDriver=new ExcelDriver("KBP","searchByClass");
        return  excelDriver;
    }
    @Test(dataProvider="test")
    public void testmap(Object[] obj)
    {
       // System.out.println(map.get("测试用例编号"));
        Map<String,String> map=tools.change(excelHead, (String[]) obj);
        System.out.println(map.get("测试用例编号"));
        //System.out.println(obj[1]);
    }
    //@Test(dataProvider="test2")
    public void test2(Object[] obj)
    {
        // System.out.println(map.get("测试用例编号"));
        //Map<String,String> map=tools.change(excelHead, (String[]) obj);
        //System.out.println(map.get("测试用例编号"));
        System.out.println(obj[1]);
    }

}
