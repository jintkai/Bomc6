package com.code.test.ibnmsConfig;

import com.code.common.Data;
import com.code.common.TestCase;
import com.code.page.ibnmsConfig.entity.EntityPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * Created by jon on 2016/10/22.
 */
public class EntityTest extends TestCase {
    EntityPage entityPage=new EntityPage(eventDriver);
    @Parameters({"node"})
    public EntityTest(String node) {
        super(node);
    }
    @Test
    public void test(){
        entityPage.selectTree("数据库","MySql","bnms15(172.21.2.96)");
    }
//    @BeforeMethod
//    @Parameters({"Action_URL"})
//    public void beforeMethod(String actionUrl)
//    {
//        eventDriver.get(Data.baseUrl + actionUrl);
//    }
}
