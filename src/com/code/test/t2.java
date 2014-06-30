package com.code.test;

import com.code.common.ExcelDriver2;
import jxl.read.biff.BiffException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * Created by jinkai on 2014/6/24.
 */
public class t2
{
    @Test(dataProvider = "test1")
    public void verifyData1(Object[] str) {

        System.out.println(str[1]);
        System.out.println(str[2]);
        System.out.println(str[3]);
    }
    @DataProvider(name = "test1")

    public Iterator createData1() throws IOException, BiffException {

        return new ExcelDriver2("CASE","searchByClass");

        };
}

