package com.code.common;

/**
 * Created by jinkai on 02/07/2014.
 */
public interface Data {
    public static String browserType="IE";
    public static String baseDir="D:\\Test\\Bomc6\\";
    public static String baseUrl="http://172.21.0.31:8084";
    /*
    KbpList，KBP列表对应的tableXpath，td，tr。
    gridXpath表示data区域的数据table，
    headXpath表示表头区域的数据table；
    headThXpaht表示表头的th，在headXpath中定位
    dataTableTrXpath表示数据区域tr的xpath。
    dataTableTdXpath表示数据区域td的xpath，在dataTableTrXpath下；
     */
    public  static String gridXpath=".//*[@id=\"gridTable\"]";
    public  static String headXpath=".//*[@id=\"gview_gridTable\"]/div[2]/div/table";
    public  static String headThXpath=".//th[@id]";
    public  static String dataTableTrXpath=".//tr[@id]";
    public  static String dataTableTdXpath=".//td";
    public  static String selectBtId="cb_gridTable";


}
