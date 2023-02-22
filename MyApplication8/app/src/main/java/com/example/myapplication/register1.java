package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import utils.JDBCUtils;


public class register1 extends AppCompatActivity {
    Connection connection;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register1);
        Button button=(Button) findViewById(R.id.register_check);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JDBCUtils.getConn();
                String sql = "INSERT INTO test (user, pwd) VALUES ('555','666')";
                PreparedStatement ps= null;
                try {
                    ps = JDBCUtils.connection.prepareStatement(sql);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                try {
                    ps.executeUpdate();//更新数据库
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}