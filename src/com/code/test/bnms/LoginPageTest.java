package com.code.test.bnms;

import com.code.common.ExcelDriver;
import com.code.bnms.login.LoginPage;
import com.code.common.TestCase;
import jxl.read.biff.BiffException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Iterator;

/**
 * Created by jinkai on 2014/6/21.
 */
public class LoginPageTest  extends TestCase{

    LoginPage loginPage=new LoginPage(eventDriver);
    @Parameters({"node"})
    public LoginPageTest(String node)
    {
        super(node);

    }
    @DataProvider(name="Login")
    public Iterator test(Method method) throws IOException, BiffException {
        ExcelDriver excelDriver=new ExcelDriver("LOGIN_NEW",method.getName());
        excelHead=excelDriver.getHead(0);
        return  excelDriver;
    }

    @Test(dataProvider="Login",description = "登录方法")
    public void login(String[] obj)
    {
        map=tools.changeStringToMap(excelHead, obj);
        loginPage.login(tools.getMapValue(map,"用户名"), tools.getMapValue(map, "密码"));
        tools.assertTrue(tools.getTitle(eventDriver).contains(tools.getMapValue(map, "期望值")), tools.getMapValue(map,"用例描述"));
    }
}
