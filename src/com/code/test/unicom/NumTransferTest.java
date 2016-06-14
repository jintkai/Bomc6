package com.code.test.unicom;

import com.code.common.Data;
import com.code.common.TestCase2;
import com.code.page.unicom.main.MainPage;
import com.code.page.unicom.numAssign.NumAssignPage;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 16-6-15.
 */
public class NumTransferTest extends TestCase2 {
    @Parameters({"node"})
    public NumTransferTest(String node) {
        super(node);
    }
    MainPage mainPage=new MainPage(eventDriver);
    NumAssignPage numAssignPage=new NumAssignPage(eventDriver);
    @BeforeTest
    @Parameters({"Base_URL"})
    public void beforeMethod(String base_url)
    {
        eventDriver.get(Data.baseUrl);
        beforeClass(base_url);
    }
    @Test(description = "号码调拨")
    public void numAssignTest(){
        mainPage.menuPage.selectMenu("库存管理","号码调拨");
        Map<String ,String > map = new HashMap<String ,String >();
        map.put("操作方式","手工方式");
        map.put("开始号段","15536849016");
        map.put("终止号段","15536849017");
        map.put("业务分类","集团");
        map.put("目的机构","北京区县1");
        map.put("号码用途","手机号码");
        map.put("号码分类","普通号码");
        map.put("备注","备注");
        String result=numAssignPage.numAssegn(map);
        tools.assertTrue(result.contains("失败：0条"),"期望值：【失败：0条】，实际值："+result);
        numAssignPage.clickDialogBtn();
    }
}
