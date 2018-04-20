package com.gioov.util;

import sun.security.krb5.Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018/4/17 9:11
 */
public class JdbcUtil implements Runnable {

    private static Connection instance = null;

    public static Connection getInstance() {

        if(instance==null){
            JdbcUtil jdbcUtil = new JdbcUtil();
            instance = jdbcUtil.connect();
        }
        return instance;
    }


    private Connection connect(){
        Connection connection = null;
        String datasourceDriver= ConfigUtil.getInstance().getProperty("datasource.driver");
        String datasourceUrl = ConfigUtil.getInstance().getProperty("datasource.url");
        try {
            Class.forName(datasourceDriver);
            connection = DriverManager.getConnection(datasourceUrl);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    @Override
    public void run() {
        connect();
    }






}
