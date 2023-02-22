package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class doctor_feedback extends AppCompatActivity {
    String username;
    String url = "jdbc:mysql://192.168.137.1:3306/demo?serverTimezone=GMT%2B8&characterEncoding=utf8&useUnicode=true&useSSL=false&autoReconnect=true&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true";
    //连接数据库使用的用户名
    String userName = "root";
    //连接的数据库时使用的密码
    String password = "lcl123456ABC";
    Connection connection=null;
    int flag=0;
    String star;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_feedback);
        Intent intent_get=getIntent();
        username=intent_get.getStringExtra("username");
        TextView text_detail=(TextView)findViewById(R.id.text_doctor_feedback) ;
        Button btn_update=(Button) findViewById(R.id.btn_doctor_feedback_update);
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search();
                while(flag==0){
                    continue;
                }
                Toast.makeText(doctor_feedback.this,"更新成功",Toast.LENGTH_SHORT).show();
                text_detail.setText("您现在被评价为"+star+"星！！！");
            }
        });
    }
    public void search(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                //获取输入框的数据
                try {
                    flag=0;
                    //1、加载驱动
                    Class.forName("com.mysql.jdbc.Driver").newInstance();
                    System.out.println("驱动加载成功！！！");
                    //2、获取与数据库的连接
                    connection = DriverManager.getConnection(url, userName, password);
                    System.out.println("连接数据库成功！！！");
                    //3.sql添加数据语句
                    String sql = "select * from test where  user= ? ";
                    PreparedStatement pst=connection.prepareStatement(sql);
                    if(username==null)username="2";
                    pst.setString(1,username);
                    ResultSet rs=pst.executeQuery();
                    flag=0;
                    while (rs.next()){
                        star=rs.getString(9);
                    }
                    flag=1;
                }catch (Exception e) {
                    e.printStackTrace();
                }
                finally {
                    if(connection!=null){
                        try {
                            connection.close();
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }
                Looper.loop();
            }

        }).start();
    }
}