package com.code.common;

import jxl.read.biff.BiffException;

import java.io.IOException;

/**
 * Created by jinkai on 04/07/2014.
 */
public class test123 {
    public static void main(String arg[]) throws IOException, BiffException {
        int a;
        ExcelMapDriver excel = new ExcelMapDriver("KBP", "searchByClass");
        /*String res[]=excel.getHead(0);
        for (int i=0;i<res.length;i++)
        {
            System.out.println(res[i]);
        }
        System.out.println("******************************");
         */
        for (int i = 1; i <excel.getRowNum() ; i++) {
            excel.hasNext();
          //  System.out.println(excel.next().get("ClassID"));
        }


    }
}