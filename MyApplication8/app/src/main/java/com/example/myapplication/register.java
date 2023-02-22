package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Connection1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class register extends AppCompatActivity {
    String url = "jdbc:mysql://192.168.137.1:3306/demo?serverTimezone=GMT%2B8&characterEncoding=utf8&useUnicode=true&useSSL=false&autoReconnect=true&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true";
    //连接数据库使用的用户名
    String userName = "root";
    //连接的数据库时使用的密码
    String password = "lcl123456ABC";
    Connection connection=null;
    EditText phone;
    EditText password2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Button button= (Button) findViewById(R.id.zhuce);
        phone= (EditText) findViewById(R.id.phone);
        password2= (EditText) findViewById(R.id.password);
        ImageView left = (ImageView)findViewById(R.id.leftbutton);
        Intent intent=new Intent(register.this,login.class);

        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Looper.prepare();
                        //获取输入框的数据
                        String phone1=phone.getText().toString();
                        String password1=password2.getText().toString();
                        try {
                            //1、加载驱动
                            Class.forName("com.mysql.jdbc.Driver").newInstance();
                            System.out.println("驱动加载成功！！！");
                            //2、获取与数据库的连接
                            connection = DriverManager.getConnection(url, userName, password);
                            System.out.println("连接数据库成功！！！");
                            //3.sql添加数据语句
                            String sql = "INSERT INTO test (user, pwd) VALUES ( ?, ?)";
                            if (!phone1.equals("")&&!password1.equals("")){//判断输入框是否有数据
                                //4.获取用于向数据库发送sql语句的ps
                                PreparedStatement ps=connection.prepareStatement(sql);
                                //获取输入框的数据 添加到mysql数据库
                                ps.setString(1,phone1);
                                ps.setString(2,password1);
                                ps.executeUpdate();//更新数据库
                                Toast.makeText(register.this,"注册成功",Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Toast.makeText(register.this,"账号或密码不能为空",Toast.LENGTH_SHORT).show();
                            }
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
