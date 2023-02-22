package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class communicate extends AppCompatActivity {

    private TextView t1;
    private Button sendmsg;
    private EditText et_msg;//用户输入信息框
    private String user="user";
    private boolean T=false;//发送标志位
    private static Connection con = null;
    private static PreparedStatement stmt = null;
    private Button revise;
    private EditText et_revise;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_communicate);

        t1 = findViewById(R.id.t1);
        et_msg = findViewById(R.id.msg);
        et_revise = findViewById(R.id.reviseText);
        sendmsg = findViewById(R.id.button);
        revise = findViewById(R.id.revise);
        revise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user = et_revise.getText().toString();
                Toast.makeText(communicate.this,"修改成功",Toast.LENGTH_SHORT).show();
            }
        });
        sendmsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { T=true; }
        });
        //TODO 启动发送线程，用按钮控制发送标志位T，来进行发送信息
        Threads_sendmsg threads_sendmsg = new Threads_sendmsg();
        threads_sendmsg.start();
        //TODO 启动获取数据线程，读取数据库里的信息
        Threads_readSQL threads_readSQL = new Threads_readSQL();
        threads_readSQL.start();
    }

    class Threads_sendmsg extends Thread {

        @Override
        public void run() {
            while (true){
                while (T){
                    try {
                        con = MYSQLConnections.getConnection();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    try {
                        String msg =et_msg.getText().toString().trim(); //用户发送的信息

                        if (msg.isEmpty()){

                            Looper.prepare();
                            Toast.makeText(communicate.this, "消息为空", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                            T=false;
                            break;
                        }
                        if (msg.length()>199){
                            Looper.prepare();
                            Toast.makeText(communicate.this, "消息长度超过限制", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                            T=false;
                            break;
                        }

                        if (con!=null) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(communicate.this, "发送成功", Toast.LENGTH_SHORT).show();
                                }
                            });
                            String sql = "insert into test(user,msg,sign) values(?,?,?)";
                            stmt = con.prepareStatement(sql);
                            // 关闭事务自动提交
                            con.setAutoCommit(false);
                            stmt.setString(1,user);
                            stmt.setString(2,msg);
                            stmt.setInt(3,1);
                            stmt.addBatch();
                            stmt.executeBatch();
                            con.commit();
                        }
                    }catch (SQLException e){
                        System.out.println(e);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(communicate.this,"请输入正确的语句",Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                    T=false;
                }
            }
        }
    }
    class Threads_readSQL extends Thread {

        ResultSet rs;


        @Override
        public void run() {
            while (true) {
                try {
                    con = MYSQLConnections.getConnection();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    String sql = "select user,msg,sign from test";
                    if (con != null) {
                        stmt = con.prepareStatement(sql);
                        // 关闭事务自动提交
                        con.setAutoCommit(false);
                        rs = stmt.executeQuery();//创建数据对象
                        //清空上次发送的信息
                        t1.setText(null);
                        while (rs.next() ) {
                            t1.append(rs.getString(1) + "\n" + rs.getString(2) + "\n\n");
                        }
                        con.commit();
                        rs.close();
                        stmt.close();
                    }
                    //2秒更新一次
                    sleep(2000);
                } catch (Exception e) {
                    System.out.println(e);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //Toast.makeText(communicate.this,"请输入正确的语句",Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        }
    }
}