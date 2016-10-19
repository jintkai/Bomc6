package com.code.common;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.*;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by jon on 2016/10/9.
 *
 *
 *
 *
 ResultSetHandler接口的实现类

 ArrayHandler：把结果集中的第一行数据转成对象数组。
 ArrayListHandler：把结果集中的每一行数据都转成一个数组，再存放到List中。
 BeanHandler：将结果集中的第一行数据封装到一个对应的JavaBean实例中。
 BeanListHandler：将结果集中的每一行数据都封装到一个对应的JavaBean实例中，存放到List里。
 ColumnListHandler：将结果集中某一列的数据存放到List中。
 KeyedHandler(name)：将结果集中的每一行数据都封装到一个Map里，再把这些map再存到一个map里，其key为指定的key。
 MapHandler：将结果集中的第一行数据封装到一个Map里，key是列名，value就是对应的值。
 MapListHandler：将结果集中的每一行数据都封装到一个Map里，然后再存放到List
 */
public class DBTools {
//    private static String dirverClassName = "com.mysql.jdbc.Driver";
//    public static String url = "jdbc:mysql://172.21.2.96:3306/bnms_cs?useUnicode=true&characterEncoding=utf8";
//    private static String user = "bnms15";
//    private static String password = "bnms15";

    private static String dirverClassName = "oracle.jdbc.driver.OracleDriver";
    public static String url = "jdbc:oracle:thin:@172.21.1.5:1523:bnms";
    private static String user = "bnms3";
    private static String password = "bnms3";
    public static Connection makeConnection() {
        Connection conn = null;
        try {
            Class.forName(dirverClassName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            conn = java.sql.DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
    @Test
    public void insert(){
        Connection conn = DBTools.makeConnection();

        QueryRunner qRunner = new QueryRunner();
        int n = 0;
        try {
            n = qRunner.update(conn, "insert into user(name,pswd) values('iii','iii')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("成功插入" + n + "条数据！");
        DbUtils.closeQuietly(conn);
    }

    @Test
    public void select() throws SQLException {
        System.out.println("-------------test_find()-------------");
        //创建连接
        Connection conn = DBTools.makeConnection();
        //创建SQL执行工具
        QueryRunner qRunner = new QueryRunner();
        //执行SQL查询，并获取结果
        /*List<User> list = (List<User>) qRunner.query(conn, "select id,name,pswd from user", new BeanListHandler(User.class));
        //输出查询结果
        for (User user : list) {
            System.out.println(user);
        }

        //关闭数据库连接 */
        ResultSetHandler h = new KeyedHandler("id");
        Map found = (Map) qRunner.query("select id, name, from user", h);
        Map jane = (Map) found.get(new Long(1)); // jane's id is 1
        String janesName = (String) jane.get("id");
        Integer janesAge = (Integer) jane.get("name");
        System.out.println(janesName+"..."+janesAge);
        DbUtils.closeQuietly(conn);
    }

    @Test
    public void add(){
        Connection conn = DBTools.makeConnection();
        QueryRunner qRunner = new QueryRunner();
        String sql = "insert into user(name,pswd) values(?,?)";
        Object params[] ={"jinkai","12345678"};
        try {
            qRunner.update(conn,sql,params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DbUtils.closeQuietly(conn);
    }

    @Test
    public void delete(){
        Connection conn = DBTools.makeConnection();
        QueryRunner qRunner = new QueryRunner();
        String sql = "delete from user where id = ?";

        try {
            qRunner.update(conn,sql,5);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DbUtils.closeQuietly(conn);
    }

    @Test
    public void update(){
        Connection conn = DBTools.makeConnection();
        QueryRunner qRunner = new QueryRunner();
        String sql = "update user set name= ? ,pswd=? where id = ?";
        Object params[] ={"test","12345678","1"};
        try {
            qRunner.update(conn,sql,params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DbUtils.closeQuietly(conn);
    }

    @Test
    public void testArrayHandler(){
        Connection conn = DBTools.makeConnection();
        QueryRunner qRunner = new QueryRunner();
        String sql = "select * from user";

        try {
            Object result[]=qRunner.query(conn,sql,new ArrayHandler());
            System.out.println(Arrays.asList(result));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DbUtils.closeQuietly(conn);
    }

    @Test
    public void testArrayListHandler(){
        Connection conn = DBTools.makeConnection();
        QueryRunner qRunner = new QueryRunner();
        String sql = "select * from user";

        try {
            List<Object[]> list=qRunner.query(conn,sql,new ArrayListHandler());
            for(Object[] o : list){
                System.out.println(Arrays.asList(o));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DbUtils.closeQuietly(conn);
    }

    @Test
    public void testColumnListHandler() throws SQLException{
        Connection conn = DBTools.makeConnection();
        QueryRunner qRunner = new QueryRunner();
        String sql = "select * from user";

        try {
            List list= (List) qRunner.query(conn,sql, new ColumnListHandler("name"));
            System.out.println(list);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DbUtils.closeQuietly(conn);
    }
    @Test
    public void testKeyedHandler() throws Exception{
        Connection conn = DBTools.makeConnection();
        QueryRunner qRunner = new QueryRunner();
        String sql = "select * from user";


        Map<Integer,Map> map = (Map)qRunner.query(conn,sql, new KeyedHandler("id"));
        System.out.println(map);

        for(Map.Entry<Integer, Map> me : map.entrySet()){
            int  id = me.getKey();
            Map<String,Object> innermap = me.getValue();
            for(Map.Entry<String, Object> innerme : innermap.entrySet()){
                String columnName = innerme.getKey();
                Object value = innerme.getValue();
                System.out.println(columnName + "=" + value);
               }
            System.out.println("----------------");
        }

        DbUtils.closeQuietly(conn);
    }

    @Test
    public void testMapHandler() throws SQLException{

        Connection conn = DBTools.makeConnection();
        QueryRunner qRunner = new QueryRunner();
        String sql = "select * from user";

        Map<String,Object> map = (Map) qRunner.query(conn,sql, new MapHandler());
        for(Map.Entry<String, Object> me : map.entrySet())
        {
            System.out.println(me.getKey() + "=" + me.getValue());
        }
        DbUtils.closeQuietly(conn);
    }


    @Test
    public void testMapListHandler() throws SQLException{
        Connection conn = DBTools.makeConnection();
        QueryRunner qRunner = new QueryRunner();
        String sql = "select * from tb_ato_kpidetail";
        List<Map> list = (List) qRunner.query(conn,sql, new MapListHandler());
        System.out.println(list);
        for(Map<String,Object> map :list){
            for(Map.Entry<String, Object> me : map.entrySet())
            {
                System.out.println(me.getKey() + "=" + me.getValue());
            }
        }
        DbUtils.closeQuietly(conn);
    }

    public List queryMapListHandler(String sqlStr){
        System.out.println(">>>>>>>>>>>SQL:"+sqlStr+";");
        Connection conn = DBTools.makeConnection();
        List<Map> list=null;
        QueryRunner qRunner = new QueryRunner();
        try {
            list = (List) qRunner.query(conn, sqlStr, new MapListHandler());
        }catch(SQLException e){
            System.out.println(">>>>>>>>>>>执行sql异常!");
            e.printStackTrace();
        }
        DbUtils.closeQuietly(conn);
        return list;
    }

    public List queryMapListHandler(String sqlStr,Object[] objects){
        System.out.print(">>>>>>>>>>>SQL:"+sqlStr+";"+objects.toString()+";");
        Connection conn = DBTools.makeConnection();
        List<Map> list=null;
        QueryRunner qRunner = new QueryRunner();
        try {
            list = (List) qRunner.query(conn, sqlStr, new MapListHandler(),objects);
        }catch(SQLException e){
            System.out.println(">>>>>>>>>>>执行sql异常!");
            e.printStackTrace();
        }
        DbUtils.closeQuietly(conn);
        return list;
    }

    @Test
    public void testScalarHandler() throws SQLException {
        Connection conn = DBTools.makeConnection();
        QueryRunner qRunner = new QueryRunner();
        String sql = "select * from user";
        int count = ((Long) qRunner.query(conn,sql, new ScalarHandler(1))).intValue();
        System.out.println(count);
        DbUtils.closeQuietly(conn);
    }

}
