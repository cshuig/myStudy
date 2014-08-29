package com.cshuig.genetorCode.DBcommon;

import org.junit.Test;

/**
 * Created by Administrator on 2014/8/29 0029.
 */
public class TestDBFactory {

    @Test
    public void test01(){
        DBConnection dbConnection = DBFactory.newDBConnectionInstance("jdbc:mysql://localhost:3306/hibernate","mysql","hibernate","root","");
        DBFactory.getColumns(dbConnection.getConnection(),"t_classroom");
        DBFactory.closeConnection(dbConnection.getConnection());
    }
}
