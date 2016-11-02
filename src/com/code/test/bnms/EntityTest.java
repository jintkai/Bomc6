package com.code.test.bnms;

import com.code.common.TestCase;
import com.code.bnms.entity.EntityPage;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * Created by jon on 2016/10/22.
 */
public class EntityTest extends TestCase {
    EntityPage entityPage = new EntityPage(eventDriver);

    @Parameters({"node"})
    public EntityTest(String node) {
        super(node);
    }

    @Test
    public void test() {
        entityPage.selectTree("数据库", "MySql", "bnms15(172.21.2.96)");
    }
}
