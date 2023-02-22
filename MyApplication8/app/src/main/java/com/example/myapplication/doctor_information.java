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

public class doctor_information extends AppCompatActivity {
    String username="456";
    String doctor_id="2";
    String username2="林医生";
    String hospital="协和医院";
    String  star="星级：4";
    String location="山东青岛";
    String url = "jdbc:mysql://192.168.137.1:3306/demo?serverTimezone=GMT%2B8&characterEncoding=utf8&useUnicode=true&useSSL=false&autoReconnect=true&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true";
    //连接数据库使用的用户名
    String userName = "root";
    //连接的数据库时使用的密码
    String password = "lcl123456ABC";
    Connection connection=null;
    int flag;
    Button com;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_information2);

        //跳转聊天
        com = (Button)findViewById(R.id.btn_start_comm);
        Intent tp = new Intent(this,oneToone.class);
        com.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(tp);
            }
        });
        /*search();
        while(flag==0){
            continue;
        }*/
        System.out.println(hospital);
        TextView textView_doctor_name=(TextView) findViewById(R.id.text_doctor_name);
        textView_doctor_name.setText(username2);
        TextView textView_doctor_hospital=(TextView) findViewById(R.id.text_doctor_hospital);
        textView_doctor_hospital.setText(hospital);
        TextView textView_docotor_star=(TextView) findViewById(R.id.text_doctor_star);
        textView_docotor_star.setText(star);
        Intent intent=new Intent(doctor_information.this,oneToone.class);
        Button btn_start=(Button) findViewById(R.id.btn_start_comm);
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("reciver",username);
                intent.putExtra("sender",doctor_id);
                startActivity(intent);
            }
        });
        Button btn_back=(Button) findViewById(R.id.btn_doctor_information_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_back=new Intent(doctor_information.this,desease_home.class);
                startActivity(intent_back);
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
                    //1、加载驱动
                    Class.forName("com.mysql.jdbc.Driver").newInstance();
                    System.out.println("驱动加载成功！！！");
                    //2、获取与数据库的连接
                    connection = DriverManager.getConnection(url, userName, password);
                    System.out.println("连接数据库成功！！！");
                    //3.sql添加数据语句
                    String sql = "select * from test where user = ? ";
                    PreparedStatement pst=connection.prepareStatement(sql);
                    pst.setString(1,doctor_id);
                    ResultSet rs=pst.executeQuery();
                    while (rs.next()){
                        username2 = rs.getString(4);
                        hospital = rs.getString(8);
                        star= rs.getString(9);
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
