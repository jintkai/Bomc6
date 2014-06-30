package com.code.test;

        import com.code.common.ExcelDriver;
        import com.code.common.TestCase;
        import jxl.read.biff.BiffException;
        import org.testng.Assert;
        import org.testng.annotations.AfterClass;
        import org.testng.annotations.Test;

        import java.io.IOException;

/**
 * Example to demonstrate use of @AfterClass annotation of TestNG framework
 *
 * @author Jagadeesh Motamarri
 * @version 1.0
 */
public class tt {

    public static void main(String args[])
    {
        ExcelDriver excel = null;


        try {
            excel = new ExcelDriver("CASE","searchByClass");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        }
        String str[][]=excel.getDate();
        for (int i=0;i<str.length;i++)
        {
            for (int j=0;j<str[i].length;j++)
                System.out.print(str[i][j]);
            System.out.println("");
        }

    }

}