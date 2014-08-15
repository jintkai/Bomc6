package com.code.common;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import org.testng.Reporter;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

/**
 * Created by jinkai on 2014/6/24.
 */
public class ExcelDriver implements Iterator {
    private Workbook book;
    private Sheet sheet;
    private int rowNum=0;
    private int curRowNum=1;
    private int colNum=0;
    public ExcelDriver(String fileName,String sheetName) {
        try {
            File dir = new File(".");
            this.book = Workbook.getWorkbook(new File(dir.getCanonicalPath() + "\\Case\\" + fileName + ".xls"));
            this.sheet = book.getSheet(sheetName);
            rowNum = sheet.getRows();
            colNum = sheet.getColumns();
        }catch(Exception e)
        {
            e.printStackTrace();
            Reporter.log("读取Sheel文件失败，检查文件名称！");
        }
    }
    public int getRowNum(){
        return rowNum;
    }
    public int getColNum()
    {return colNum;}

    /**
     * 返回table的表头数组，一般rowNum从0开始；
     * @param rowNum
     * @return
     */
    public String[] getHead(int rowNum)
    {
        String head[]=new String[colNum];
        for (int i = 0; i < colNum; i++) {
            head[i]=getColumValue(rowNum,i);
        }
        return head;
    }

    public String getColumValue(int rowNum, int colNum) {
        Cell cell = sheet.getCell(colNum, rowNum);
        return cell.getContents();
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
        if (cell.getContents().equals("是"))
            return  true;
        else
            return false;
    }
    @Override
    public Object[] next() {
        Cell[] cells=sheet.getRow(curRowNum-1);
        //Cell[] cells=sheet.getRow(sheet.getColumns()-1);
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
