package com.cshuig.genetorCode.DBcommon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 数据库连接初始化类
 * 数据库种类： mySql、oracle、DB2
 * 通过构造函数来初始化不同的数据库
 * Created by Administrator on 2014/8/29 0029.
 */
public class DBConnection {

    /** 数据库类型 **/
    private static final String DB_ORACLE = "oracle";
    private static final String DB_MYSQL = "mysql";
    private static final String DB_DB2 = "db2";

    /**
     * 数据库：地址
     */
    private String dbUrl;
    /**
     * 数据库：驱动名
     * 不同的数据库，驱动都不同
     * mysql：   com.mysql.jdbc.Driver
     * oracle：  oracle.jdbc.driver.OracleDriver
     */
    private String dbDriver;
    /**
     * 数据库：类型
     * 如上三种
     */
    private String dbType;
    /**
     * 数据库：名字
     */
    private String dbName;
    /**
     * 数据库：登陆用户名
     */
    private String username;
    /**
     * 数据库：登陆密码
     */
    private String password;

    public DBConnection() {
    }

    /**
     * 通过构造函数来初始化数据库连接的信息
     * @param dbUrl         数据库地址
     * @param dbType        数据库类型
     * @param dbName        数据库名字
     * @param username      数据库登陆用户名
     * @param password      数据库登入密码
     */
    public DBConnection(String dbUrl, String dbType, String dbName, String username, String password) {
        this.dbUrl = dbUrl;
        switch (dbType){
            case DB_MYSQL :
                dbDriver = "com.mysql.jdbc.Driver";
                break;
            case DB_ORACLE :
                dbDriver = "oracle.jdbc.driver.OracleDriver";
                break;
        }
        this.dbName = dbName;
        this.username = username;
        this.password = password;
    }

    /**
     * 获取数据库的连接对象
     * @return  返回一个数据库连接 (Connection) 对象
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public Connection getConnection() {
        Connection connection;
        try {
            //通过Class.forName,来创建数据库驱动类对象,同时自动注册 DriverMananger,参数必须是带全路径的包名
            Class.forName(dbDriver);
            connection = (Connection) DriverManager.getConnection(dbUrl,username,password);
            return connection;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *关闭数据库连接
     * @param connection
     */
    public void closeConnection(Connection connection){
        try {
            if(connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 判断字符串是否为空
     * @param str
     * @return
     */
    public boolean checkIsNull(String str){
        boolean flag = false;
        if(str==null || "".equals(str)){
            flag = true;
        }
        return flag;
    }

    public static void main(String[] args){
        new DBConnection("jdbc:mysql://localhost:3306/",DB_MYSQL,"hibernate","root","").getConnection();
    }
}
