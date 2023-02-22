package utils;

import android.os.Looper;
import android.widget.Toast;

import com.example.myapplication.register;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JDBCUtils {
    public static Connection connection;

    public static void getConn() {

        new Thread(new Runnable() {
            @Override
            public void run() {

                    //1、加载驱动
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                System.out.println("驱动加载成功！！！");
                    //2、获取与数据库的连接
                    String url = "jdbc:mysql://192.168.137.1:3306/demo?serverTimezone=GMT%2B8&characterEncoding=utf8&useUnicode=true&useSSL=false&autoReconnect=true&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true";
                    //连接数据库使用的用户名
                    String userName = "root";
                    //连接的数据库时使用的密码
                    String password = "lcl123456ABC";
                    System.out.println("准备连接");
                try {
                    connection = DriverManager.getConnection(url, userName, password);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                System.out.println("连接数据库成功！！");
                    //3.sql添加数据语句
                }

        }).start();
    }

    public static void close(Connection conn){
        try {
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
