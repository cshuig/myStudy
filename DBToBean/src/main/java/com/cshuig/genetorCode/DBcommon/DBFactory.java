package com.cshuig.genetorCode.DBcommon;

import java.sql.*;
import java.util.Vector;

/**
 * 数据库工厂类
 * Created by Administrator on 2014/8/29 0029.
 */
public class DBFactory {

    /** 数据库连接的对象 */
    private static DBConnection connection = null;

    /**
     * 获取数据库连接的对象
     * @param dbUrl         数据库连接
     * @param dbType        数据库类型
     * @param dbName        数据库名字
     * @param username      数据库登陆用户名
     * @param password      数据库登陆密码
     * @return
     */
    public static DBConnection newDBConnectionInstance(String dbUrl,String dbType,String dbName,String username,String password){
        connection = new DBConnection(dbUrl,dbType,dbName,username,password);
        return connection;
    }

    /**
     * 关闭数据库连接对象
     * @param _connection   数据库连接对象
     */
    public static void closeConnection(Connection _connection){
        connection.closeConnection(_connection);
    }

    /**
     * 获取指定表的所有列名
     * @param conn          数据库连接对象
     * @param tableName     指定的：表名
     * @return  所有列名
     */
    public static Vector getColumns(Connection conn,String tableName){
        Vector vector = new Vector();
        //获取预编译SQL的对象
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(" SELECT * FROM " +tableName);
            ResultSet  resultSet = preparedStatement.executeQuery();
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int count = resultSetMetaData.getColumnCount();
            //注意，是从1开始
            for(int i=1;i<=count;i++){
                System.out.println(tableName +">> 字段"+i+"的名字：" + resultSetMetaData.getColumnName(i));
                vector.add(resultSetMetaData.getColumnName(i));
            }
            return vector;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
