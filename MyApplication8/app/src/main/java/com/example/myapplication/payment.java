package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.text.TextUtils;
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

public class payment extends AppCompatActivity {
    String url = "jdbc:mysql://192.168.137.1:3306/demo?serverTimezone=GMT%2B8&characterEncoding=utf8&useUnicode=true&useSSL=false&autoReconnect=true&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true";
    //连接数据库使用的用户名
    String userName = "root";
    //连接的数据库时使用的密码
    String password = "lcl123456ABC";
    Connection connection=null;
    int price=0;
    String username;
    String [][] user=new String[10][10];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        Intent intent_get=getIntent();
        username=intent_get.getStringExtra("username");
        Button button=(Button) findViewById(R.id.search);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search();
            }
        });
        Button button1=(Button) findViewById(R.id.btn_payment);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search();
                customDialog();
            }
        });

    }
    public void customDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(payment.this);
        final AlertDialog dialog = builder.create();
        View dialogView = View.inflate(payment.this, R.layout.activity_dialog_payment, null);
        dialog.setView(dialogView);
        dialog.show();

        final TextView et_name = dialogView.findViewById(R.id.textView2);
        final TextView et_pwd = dialogView.findViewById(R.id.textView3);
        et_name.setText("万通筋骨贴");
        et_pwd.setText("支付金额：20元");
        final Button btn_login = dialogView.findViewById(R.id.btn_login);
        final Button btn_cancel = dialogView.findViewById(R.id.btn_cancel);

        String price1=Integer.toString(price);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String time = et_name.getText().toString();
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
                            String sql = "UPDATE order_detail SET state=0 where order_id=? ";
                            //4.获取用于向数据库发送sql语句的ps
                            for(int i=0;i<3;i++){
                                PreparedStatement pst=connection.prepareStatement(sql);
                                //System.out.println(user[i][1]);
                                pst.setString(1,user[i][0]);
                                pst.executeUpdate();
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
                Toast.makeText(payment.this,"支付成功", Toast.LENGTH_SHORT).show();
                dialog.dismiss();

            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
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
                    String sql = "select * from order_detail where user = ? ";
                    //4.获取用于向数据库发送sql语句的ps
                    PreparedStatement pst=connection.prepareStatement(sql);
                    pst.setString(1,username);
                    ResultSet rs=pst.executeQuery();
                    price=0;
                    int i=0;
                    while (rs.next()){
                        String order_id = rs.getString(1);
                        String desease_id  = rs.getString(2);
                        int price_now=rs.getInt(3);
                        String state=rs.getString(4);
                        user[i][0]=order_id;
                        user[i][1]=desease_id;
                        user[i][2]=Integer.toString(price_now);
                        user[i][3]=state;
                        System.out.println(user[i][1]);
                        if(state.equals("1")) {
                            price +=price_now;
                        }
                        else continue;
                        i++;
                        User user = new User();
                    }
                    Toast.makeText(payment.this,"查询成功，您需要支付"+price+"元", Toast.LENGTH_SHORT).show();
                    System.out.println(username);
                    System.out.println(price);
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