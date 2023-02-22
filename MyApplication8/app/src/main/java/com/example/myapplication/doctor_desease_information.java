package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class doctor_desease_information extends AppCompatActivity {
    String username="456";
    String username2="张三";
    String sex="女";
    String age="22";
    String location="山东青岛";
    String url = "jdbc:mysql://192.168.137.1:3306/demo?serverTimezone=GMT%2B8&characterEncoding=utf8&useUnicode=true&useSSL=false&autoReconnect=true&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true";
    //连接数据库使用的用户名
    String userName = "root";
    //连接的数据库时使用的密码
    String password = "lcl123456ABC";
    Connection connection=null;
    Button com ;
    int flag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_desease_information);

        //跳转聊天

        flag=0;
        /*search_information();
        while(flag==0){
            if(flag==1)break;
        }*/
        TextView textView_name=(TextView) findViewById(R.id.text_doctor_search_information_name);
        textView_name.setText(username2);
        TextView textView_sex=(TextView) findViewById(R.id.text_doctor_search_information_sex);
        if(sex.equals("0")){
            textView_sex.setText("女");
        }
        else if(sex.equals("1")){
            textView_sex.setText("男");
        }
        else{
            textView_sex.setText("未知");
        }
        TextView textView_age=(TextView) findViewById(R.id.text_search_information_age);
        textView_age.setText(age);
        TextView textView_location=(TextView) findViewById(R.id.text_doctor_search_information_location);
        textView_location.setText(location) ;
        Button btn_addfeedback=(Button) findViewById(R.id.btn_doctor_desease_diagnosis);
        Button btn_comm=(Button) findViewById(R.id.btn_doctor_desease_comm);
        btn_addfeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(doctor_desease_information.this,doctor_addFeedback.class);
                startActivity(intent);
            }
        });
        btn_comm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tp = new Intent(doctor_desease_information.this,oneToone.class);
                startActivity(tp);
            }
        });
    }
    public void search_information(){
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
                    String sql = "select * from test where user = ? ";
                    PreparedStatement pst=connection.prepareStatement(sql);
                    pst.setString(1,username);
                    ResultSet rs=pst.executeQuery();
                    while (rs.next()){
                        username2 = rs.getString(4);
                        sex  = rs.getString(5);
                        age= rs.getString(6);
                        location=rs.getString(7);
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