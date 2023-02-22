package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class medcine_information extends AppCompatActivity {
    String username="456";

    String medcine_name;
    String medcine_price="15";
    String location="山东青岛";
    String url = "jdbc:mysql://192.168.137.1:3306/demo?serverTimezone=GMT%2B8&characterEncoding=utf8&useUnicode=true&useSSL=false&autoReconnect=true&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true";
    //连接数据库使用的用户名
    String userName = "root";
    //连接的数据库时使用的密码
    String password = "lcl123456ABC";
    Connection connection=null;
    String price1;
    int flag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medcine_information);
        Intent intent_get=getIntent();
        medcine_name="999";
        medcine_price="20元";
        medcine_name=intent_get.getStringExtra("medcine_name");
        medcine_price=intent_get.getStringExtra("medcine_price");
        System.out.println(medcine_name);
        /*search();
        while(flag==0){
            continue;
        }*/
        TextView textView_doctor_name=(TextView) findViewById(R.id.text_medcine_information_name);
        textView_doctor_name.setText(medcine_name);
        TextView textView_doctor_hospital=(TextView) findViewById(R.id.text_medcine_information_price);
        textView_doctor_hospital.setText(medcine_price);
        TextView textView_docotor_star=(TextView) findViewById(R.id.text_medcine_information_location);
        textView_docotor_star.setText(location);
        Button btn_start=(Button) findViewById(R.id.btn_medcine_purchase);
        ImageView imageView_medcine=(ImageView)findViewById(R.id.image_medcine) ;
        if(medcine_name.equals("胃痛丸")){
            imageView_medcine.setImageResource(R.drawable.weitongwan);
        }
        else if(medcine_name.equals("西瓜霜")){
            imageView_medcine.setImageResource(R.drawable.xiguashuang);
        }
        else if(medcine_name.equals("999感冒灵")){
            imageView_medcine.setImageResource(R.drawable.medcine_999);
        }
        else if(medcine_name.equals("布洛芬胶囊")){
            imageView_medcine.setImageResource(R.drawable.buluofen);
        }
        else if(medcine_name.equals("万通筋骨贴")){
            imageView_medcine.setImageResource(R.drawable.wantongjingutie);
        }
        else if(medcine_name.equals("肾宝片")){
            imageView_medcine.setImageResource(R.drawable.shenbaopian);
        }
        else if(medcine_name.equals("云南白药")){
            imageView_medcine.setImageResource(R.drawable.yunnanbaiyao);
        }
        else if(medcine_name.equals("莲花清瘟胶囊")){
            imageView_medcine.setImageResource(R.drawable.lianhuaqingwen);
        }
        else if(medcine_name.equals("六味地黄丸")){
            imageView_medcine.setImageResource(R.drawable.liuweidihuangwan);
        }
        else{
            imageView_medcine.setImageResource(R.drawable.tianmatoutongpian);
        }
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customDialog();
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

                    ResultSet rs=pst.executeQuery();
                    while (rs.next()){

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
    public void customDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(medcine_information.this);
        final AlertDialog dialog = builder.create();
        View dialogView = View.inflate(medcine_information.this, R.layout.purchase_dialog, null);
        dialog.setView(dialogView);
        dialog.show();

        final TextView et_name = dialogView.findViewById(R.id.text_dialog_medcine_information_name);
        final TextView et_pwd = dialogView.findViewById(R.id.text_dialog_medcine_information_price);
        et_name.setText(medcine_name);
        et_pwd.setText(medcine_price);
        final Button btn_login = dialogView.findViewById(R.id.btn_dialog_medcine_purchase);
        final Button btn_cancel = dialogView.findViewById(R.id.btn_medcine_cancel);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String time = et_name.getText().toString();
                String pwd = et_pwd.getText().toString();
                Toast.makeText(medcine_information.this,"购药成功", Toast.LENGTH_SHORT).show();
                /*new Thread(new Runnable() {
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

                }).start();*/
                Toast.makeText(medcine_information.this,"购药成功", Toast.LENGTH_SHORT).show();
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

}
