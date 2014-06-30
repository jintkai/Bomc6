package com.code.common;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import java.io.File;
import java.io.IOException;

/**
 * Created by jinkai on 2014/6/23.
 */
public class ExcelDriver {
    private Workbook book;
    private Sheet sheet;
    private int rowNum=0;
    private int curRowNum=1;
    private int colNum=0;
    public ExcelDriver(String fileName,String sheetName) throws IOException, BiffException {
        File dir=new File(".");
        this.book=Workbook.getWorkbook(new File(dir.getCanonicalPath()+"\\Case\\"+fileName+".xls"));
        this.sheet=book.getSheet(sheetName);
        rowNum=sheet.getRows();
        colNum=sheet.getColumns();
    }
    public boolean hasNext()
    {
        if (rowNum==1 || curRowNum>=rowNum ||rowNum==0)
        {

            book.close();
            return false;
        }
        else
        {
            curRowNum++;
            return true;
        }
    }
    /*
    将每一行保存为一个数组；
     */
    public String[] getCols()
    {
        String[] colsStr=new String[colNum];
        Cell[] cell=sheet.getRow(curRowNum-1);
        for (int i=0;i<cell.length;i++)
        {
            colsStr[i]=cell[i].getContents();
        }
        return colsStr;
    }

    public String[][] getDate()
    {
        String str[][]=new String[rowNum-1][colNum];
        while(hasNext())
        {
            str[curRowNum-2]=getCols();
        }
        return str;
    }
}
