package com.code.test;

import com.code.common.LoginPage;
import com.code.common.TestCase;
import com.code.page.kbplist.KbpListFramePage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Created by jinkai on 2014/6/21.
 */
public class KbpListFrameTest extends TestCase{
    @Test
    public void searchKbpTree()
    {
        KbpListFramePage kbpFrame= new KbpListFramePage();
        kbpFrame.switchToTree();
        //kbpFrame.kbpTree.searchByFuzzy();
        kbpFrame.kbpTree.searchByTree();
    }
    @BeforeClass
    public void beforeClass()
    {
        //DriverManager.driver=new FirefoxDriver();
        driver.get("http://192.168.1.103");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new LoginPage().login();
        driver.get("http://192.168.1.103/ibnms/config/kbpListFrame.action");
    }
}
