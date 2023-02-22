package com.example.myapplication;

import java.sql.Connection;
import java.sql.DriverManager;

public class MYSQLConnections {
    private String driver = "";
    private String dbURL = "";
    private String user = "";
    private String password = "";
    private MYSQLConnections(){
        driver = "com.mysql.jdbc.Driver";
        dbURL = "jdbc:mysql://bj-cdb-qp1cvnm2.sql.tencentcdb.com:59877/communicate?serverTimezone=GMT%2B8&characterEncoding=utf8&useUnicode=true&useSSL=false&autoReconnect=true&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true";
        user = "root";
        password = "19011902abc";
        System.out.println("dbURL:" + dbURL);
    }
    private static MYSQLConnections connection = null;

    public static Connection getConnection() {
        Connection conn = null;
        if (connection == null) {
            try {
                connection = new MYSQLConnections();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        try {
            Class.forName(connection.driver);
            conn = DriverManager.getConnection(connection.dbURL,
                    connection.user, connection.password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
}

