package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bean.User;

public class feedback extends AppCompatActivity {
    Button btn1;
    Button btn2;
    Button btn3;
    Button btn4;
    Button btn5;
    String url = "jdbc:mysql://192.168.137.1:3306/demo?serverTimezone=GMT%2B8&characterEncoding=utf8&useUnicode=true&useSSL=false&autoReconnect=true&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true";
    //连接数据库使用的用户名
    String userName = "root";
    //连接的数据库时使用的密码
    String password = "lcl123456ABC";
    Connection connection=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        Intent intent_get=getIntent();
        String username=intent_get.getStringExtra("username");
        Button button_upload=(Button) findViewById(R.id.button_upload);
        RadioGroup r = (RadioGroup) findViewById(R.id.radioGroup);
        final double[] star_shu = new double[1];
        btn1 =(Button) findViewById(R.id.star_1);
        btn2 =(Button) findViewById(R.id.star_2);
        btn3 =(Button) findViewById(R.id.star_3);
        btn4 =(Button) findViewById(R.id.star_4);
        btn5 =(Button) findViewById(R.id.star_5);
        r.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(btn1.getId()==checkedId){
                    star_shu[0] =1;
                }
                else if(btn2.getId()==checkedId){
                    star_shu[0]=2;
                }
                else if(btn3.getId()==checkedId){
                    star_shu[0]=3;
                }
                else if(btn4.getId()==checkedId){
                    star_shu[0]=4;
                }
                else if(btn5.getId()==checkedId){
                    star_shu[0]=5;
                }
            }
        });
        button_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Looper.prepare();
                        //获取输入框的数据
                        try {
                            //1、加载驱动
                            Class.forName("com.mysql.jdbc.Driver").newInstance();
                            System.out.println("驱动加载成功！！！");
                            //2、获取与数据库的连接
                            connection = DriverManager.getConnection(url, userName, password);
                            System.out.println("连接数据库成功！！！");
                            //3.sql添加数据语句
                            String sql = "INSERT INTO feedback (desease_id,doctor_id,star ) VALUES ( ?, ?,?)";
                            PreparedStatement ps=connection.prepareStatement(sql);
                                //获取输入框的数据 添加到mysql数据库
                            ps.setString(1,username);
                            ps.setString(2,"222");
                            ps.setDouble(3,star_shu[0]);
                            ps.executeUpdate();//更新数据库
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
        });
    }
}