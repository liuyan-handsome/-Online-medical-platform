package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

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

public class bussinessman_medcine_dispose extends AppCompatActivity {
    String username="456";
    String id="123";
    String username2="万痛筋骨贴";
    String date="2022年6月29日";
    String price="20元";
    String location="山东省青岛市黄岛区长江西路66号";
    String url = "jdbc:mysql://192.168.137.1:3306/demo?serverTimezone=GMT%2B8&characterEncoding=utf8&useUnicode=true&useSSL=false&autoReconnect=true&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true";
    //连接数据库使用的用户名
    String userName = "root";
    //连接的数据库时使用的密码
    String password = "lcl123456ABC";
    Connection connection=null;
    int flag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bussinessman_medcine_dispose);
        flag=0;
        /*search_information();
        while(flag==0){
            if(flag==1)break;
        }*/
        TextView textView_name=(TextView) findViewById(R.id.text_bussinessman_dispose_name);
        textView_name.setText(username2);
        TextView textView_date=(TextView) findViewById(R.id.text_bussinessman_dispose_date);
        textView_date.setText(date);
        TextView textView_price=(TextView) findViewById(R.id.text_bussinessman_dispose_price);
        textView_price.setText(price);
        TextView textView_location=(TextView) findViewById(R.id.text_bussinessman_medcine_location);
        textView_location.setText(location) ;
        TextView textView_id=(TextView) findViewById(R.id.text_bussinessman_medcine_id);
        textView_location.setText(id) ;
        Button btn_dispose=(Button) findViewById(R.id.btn_bussinessman_medcine_dispose);
        btn_dispose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customDialog22();
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
    public void customDialog22(){
        AlertDialog.Builder builder = new AlertDialog.Builder(bussinessman_medcine_dispose.this);
        final AlertDialog dialog = builder.create();
        View dialogView = View.inflate(bussinessman_medcine_dispose.this, R.layout.dialog_dispose, null);
        dialog.setView(dialogView);
        dialog.show();

        final TextView et_name = dialogView.findViewById(R.id.textView);
        final TextView et_pwd = dialogView.findViewById(R.id.textView2);

        TextView textView_id=(TextView) findViewById(R.id.text_dialog_bussinessman_id);
        TextView textView_price=(TextView) findViewById(R.id.text_dialog_bussinessman_price);

        final Button btn_login = dialogView.findViewById(R.id.btn_dialog_bussinessman_purchase);
        final Button btn_cancel = dialogView.findViewById(R.id.btn_bussinessman_cancel);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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
                Toast.makeText(bussinessman_medcine_dispose.this,"处理成功", Toast.LENGTH_SHORT).show();
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