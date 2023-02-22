package com.example.myapplication;

import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Connection1 {
    private static final String TAG = "mysql";
    public java.sql.Connection conn=null;
    public static void mymysql(){
        final Thread thread =new Thread(new Runnable() {
            @Override
            public void run() {

                while (!Thread.interrupted()) {
                    try {
                        Thread.sleep(100);  // 每隔0.1秒尝试连接
                    } catch (InterruptedException e) {
                        Log.e(TAG, e.toString());
                    }

// 1.加载JDBC驱动
                    try {
                        Class.forName("com.mysql.jdbc.Driver");
                        Log.v(TAG, "加载JDBC驱动成功");
                    } catch (ClassNotFoundException e) {
                        Log.e(TAG, "加载JDBC驱动失败");
                        return;
                    }

                    // 2.设置好IP/端口/数据库名/用户名/密码等必要的连接信息
                    String ip = "192.168.43.97";
                    int port = 3306;
                    String dbName = "mysql";
                    String url = "jdbc:mysql://192.168.137.1:3306/demo?serverTimezone=GMT%2B8&characterEncoding=utf8&useUnicode=true&useSSL=false&autoReconnect=true&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true";
                    // 构建连接mysql的字符串
                    String user = "root";
                    String password = "lcl123456ABC";

                    // 3.连接JDBC
                    try {
                        java.sql.Connection conn = DriverManager.getConnection(url, user, password);
                        System.out.println("数据库连接成功");
                        Log.d(TAG, "数据库连接成功");

                        String sql = "INSERT INTO test (user,pwd) VALUES ('233335','2')";
                        PreparedStatement ps=conn.prepareStatement(sql);
                        ps.execute();
                        conn.close();
                        break;
                    }
                    catch (SQLException e) {
                        Log.e(TAG, e.getMessage());
                    }
                }
            }
        });
        thread.start();

    }
}
