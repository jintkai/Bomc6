package com.code.page.unicom;

import com.code.common.Data;
import com.code.common.TestCase2;
import com.code.unicom.main.MainPage;
import com.code.unicom.stock.InStockPage;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 16-6-14.
 */
public class InStockTest  extends TestCase2 {
    MainPage mainPage=new MainPage(eventDriver);
    InStockPage inStockPage=new InStockPage(eventDriver);

    @Parameters({"node"})
    public InStockTest(String node) {
        super(node);
    }

    @BeforeTest
    @Parameters({"Base_URL"})
    public void beforeMethod(String base_url)
    {
        eventDriver.get(Data.baseUrl);
        beforeClass(base_url);
    }

    @Test(description = "号码入库")
    public void inStock(){
        mainPage.menuPage.selectMenu("库存管理","号码入库");
        Map<String ,String > map = new HashMap<String ,String >();
        map.put("操作方式","文件方式");
        //map.put("操作方式","手工方式");
        map.put("文本路径","C:\\Driver\\inStock.txt");
        map.put("操作类型","起止号段入库");
        map.put("开始号段","15536849038");
        map.put("终止号段","15536849039");
        map.put("业务分类","集团");
        map.put("号码用途","手机号码");
        map.put("入库仓库","北京市区1");
        map.put("备注","备注");
        String result = inStockPage.inStock(map);
        System.out.println(result);
        tools.assertTrue(result.contains("失败：0条"),"期望值：【失败：0条】，实际值："+result);
        inStockPage.clickDialogBtn();
    }
}
