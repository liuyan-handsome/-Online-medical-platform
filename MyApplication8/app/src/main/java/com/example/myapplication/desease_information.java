package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class desease_information extends AppCompatActivity {
    final String  []sex=new String[1];
    String url = "jdbc:mysql://192.168.137.1:3306/demo?serverTimezone=GMT%2B8&characterEncoding=utf8&useUnicode=true&useSSL=false&autoReconnect=true&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true";
    //连接数据库使用的用户名
    String userName = "root";
    //连接的数据库时使用的密码
    String password = "lcl123456ABC";
    Connection connection=null;
    String username="456";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desease_information);
        EditText textname1=(EditText) findViewById(R.id.edit_information_name1);
        EditText age=(EditText) findViewById(R.id.edit_information_age);
        EditText location=(EditText) findViewById(R.id.edit_information_location);
        Button btn_upload=(Button) findViewById(R.id.btn_information_upload);
        RadioGroup radioGroup=(RadioGroup) findViewById(R.id.radio_information_sex);

        Button btn_man=(Button) findViewById(R.id.btn_information_man);
        Button btn_women=(Button) findViewById(R.id.btn_information_woman);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (btn_man.getId() == checkedId) {
                    sex[0] = "1";
                } else if (btn_women.getId() == checkedId) {
                    sex[0] = "0";
                }
            }
        });

        btn_upload.setOnClickListener(new View.OnClickListener() {
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
                            String sql = "UPDATE  test SET name=?,sex=?,age=?,location=? where user=?";
                            PreparedStatement ps = connection.prepareStatement(sql);
                            //获取输入框的数据 添加到mysql数据库
                            ps.setString(1, textname1.getText().toString());
                            ps.setString(2, sex[0]);
                            ps.setString(3, age.getText().toString());
                            ps.setString(4, location.getText().toString());
                            ps.setString(5, username);
                            ps.executeUpdate();//更新数据库
                            Toast.makeText(desease_information.this,"修改成功", Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            if (connection != null) {
                                try {
                                    connection.close();
                                } catch (Exception e) {
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
    void search_information(){

    }
}