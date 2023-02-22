package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bean.User;

public class login extends AppCompatActivity {
    String url = "jdbc:mysql://bj-cdb-qp1cvnm2.sql.tencentcdb.com:59877/schema?serverTimezone=GMT%2B8&characterEncoding=utf8&useUnicode=true&useSSL=false&autoReconnect=true&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true";
    //连接数据库使用的用户名
    String userName = "root";
    //连接的数据库时使用的密码
    String password = "19011902abc";
    Connection connection=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button button=(Button) findViewById(R.id.login_btn);
        EditText user=(EditText) findViewById(R.id.login_account_text);
        EditText password2=(EditText) findViewById(R.id.login_password_text);

        //测试，跳转聊天
        Button button2 = (Button) findViewById(R.id.button4);
        Intent intent2 = new Intent(this,oneToone.class);

        //跳转注册
        TextView reg = (TextView)findViewById(R.id.login_register);
        Intent reg_intent = new Intent(this,register.class);

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(reg_intent);
            }
        });

        //测试，跳转聊天界面
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent2);
            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(login.this,desease_home.class);
                startActivity(intent);
                /*Intent intent =new Intent(login.this,doctor_home.class);
                startActivity(intent);*/
                /*Intent intent=new Intent(login.this,businessman_home.class);
                startActivity(intent);*/
                /*new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Looper.prepare();
                        //获取输入框的数据
                        String user1=user.getText().toString();
                        String password1=password2.getText().toString();
                        try {
                            //1、加载驱动
                            Class.forName("com.mysql.jdbc.Driver").newInstance();
                            System.out.println("驱动加载成功！！！");
                            //2、获取与数据库的连接
                            connection = DriverManager.getConnection(url, userName, password);
                            System.out.println("连接数据库成功！！！");
                            //3.sql添加数据语句
                            String sql = "select * from test where user = ? and pwd = ?";
                            if (!user1.equals("")&&!password1.equals("")){//判断输入框是否有数据
                                //4.获取用于向数据库发送sql语句的ps
                                PreparedStatement pst=connection.prepareStatement(sql);

                                pst.setString(1,user1);
                                pst.setString(2,password1);
                                ResultSet rs=pst.executeQuery();
                                while (rs.next()){
                                    String username = rs.getString(1);
                                    String passworddb  = rs.getString(2);
                                    String identity= rs.getString(3);
                                    if(passworddb.equals(password1)) {
                                        if(identity.equals("1")) {
                                            Intent intent = new Intent(login.this, desease_home.class);
                                            intent.putExtra("username", user1);
                                            startActivity(intent);
                                        }
                                        else if(identity.equals("2")){
                                            Intent intent2=new Intent(login.this,doctor_home.class);
                                            intent2.putExtra("username",user1);
                                            startActivity(intent2);
                                        }
                                    }
                                }
                            }
                            else {
                                Toast.makeText(login.this,"账号或密码不能为空",Toast.LENGTH_SHORT).show();
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

                }).start();*/
            }
        });
    }
}