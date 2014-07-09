package com.code.common;

import javax.activation.DataSource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * Created by jinkai on 2014/7/8.
 */
public class DBcon {

    //创建数据库链接库对象
    public static Connection con=null;
    //
    public static Statement stat=null;
    //创建结果集
    public static ResultSet rs=null;

    public static Connection getCon() throws IOException, ClassNotFoundException, SQLException {
        Properties prop = new Properties();
        FileInputStream in = new FileInputStream(".\\config\\jdbc.properties");
        prop.load(in);
        String jdbc= (String) prop.get("jdbc");
        Class.forName(jdbc);
        String database= (String) prop.get("database");
        String userName=prop.getProperty("username");
        String passWord=prop.getProperty("password");
        //DriverManager的getConnection方法，建立一个指定数据库的链接，返回一个Connection对象;
        con= DriverManager.getConnection(database, userName, passWord);
        return  con;
    }

    public static Statement getStat() throws SQLException {
        return con.createStatement();
    }
        /*
        //执行sql语句，前需要创建一个Statement对象，Connection.createStatement()创建；
        Statement 是 Java 执行数据库操作的一个重要方法，用于在已经建立数据库连接的基础上，向数据库发送要执行的SQL语句。
        stat=con.createStatement();
        每一个？号表示一个宿主变量；
        String sql="select * from user_tables where table_name like ? and tablespace_name= ?";
        PreparedStatement preStat=con.prepareStatement(sql);//创建一个PreparedStatement对象
        使用set方法来绑定每一个实际的值，每个宿主变量都需要绑定，setInt，setString等方法。如果需要重用已经执行的预编译查询语句，那么
        除非使用set方法或调用clearParameters方法，否则所有宿主变量的值都不会改变，则意味着，只需要使用set方法，绑定需要改变的变量即可；
        preStat.setString(1,"%LOG%");
        preStat.setString(2,"SYSAUX");

        rs=preStat.executeQuery();
        while(rs.next())
        {
            System.out.println(rs.getString(1));
            System.out.println(rs.getString(2));
        }
        */
}
