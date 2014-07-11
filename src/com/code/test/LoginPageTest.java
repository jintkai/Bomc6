package com.code.test;

import com.code.common.ExcelDriver;
import com.code.page.login.LoginPage;
import com.code.common.TestCase;
import jxl.read.biff.BiffException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Iterator;

/**
 * Created by jinkai on 2014/6/21.
 */
public class LoginPageTest  extends TestCase{

    LoginPage loginPage=new LoginPage();

    @DataProvider(name="Login")
    public Iterator test(Method method) throws IOException, BiffException {
        ExcelDriver excelDriver=new ExcelDriver("Login",method.getName());
        excelHead=excelDriver.getHead(0);
        return  excelDriver;
    }

    @Test(dataProvider="Login")
    public void login(String[] obj)
    {
        map=tools.changeStringToMap(excelHead, obj);
        loginPage.login(tools.getMapValue(map,"用户名"), tools.getMapValue(map, "密码"));
        tools.assertTrue(tools.getTitle(TestCase.eventDriver).contains(tools.getMapValue(map, "期望值")));
    }
}
