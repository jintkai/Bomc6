package com.code.common;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

/**
 * Created by jinkai on 2014/6/24.
 */
public class ExcelDriver2 implements Iterator {
    private Workbook book;
    private Sheet sheet;
    private int rowNum=0;
    private int curRowNum=1;
    private int colNum=0;
    public ExcelDriver2(String fileName,String sheetName) throws IOException, BiffException {
        File dir=new File(".");
        this.book=Workbook.getWorkbook(new File(dir.getCanonicalPath()+"\\Case\\"+fileName+".xls"));
        this.sheet=book.getSheet(sheetName);
        rowNum=sheet.getRows();
        colNum=sheet.getColumns();
    }
    @Override
    public boolean hasNext() {
        curRowNum++;
        if(rowNum==0 || rowNum==1 || curRowNum>rowNum)
        {
            book.close();

            return false;
        }

        if (!isEnable())
            return hasNext();
        return true;
    }
    public boolean isEnable()
    {
        //getCell(x,y),第y行的第x列坐标
        Cell cell=sheet.getCell(0,curRowNum-1);
        if (cell.getContents().equals("Y"))
            return  true;
        else
            return false;
    }
    @Override
    public Object[] next() {
        Cell[] cells=sheet.getRow(curRowNum-1);
        Object[] reslut=new Object[1];
        String[] str=new String[cells.length];
        for (int i=0;i<cells.length;i++)
            str[i]=cells[i].getContents();
        reslut[0]=str;
        return reslut;
    }

    @Override
    public void remove() {

    }
}
