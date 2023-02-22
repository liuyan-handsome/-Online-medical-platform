package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class doctor_home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_home);
        Intent intent=getIntent();
        String username=intent.getStringExtra("username");
        Button button_select=(Button) findViewById(R.id.btn_select);
        Intent intent_select=new Intent(doctor_home.this,doctor_chooseDesease.class);
        button_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent_select.putExtra("username",username);
                startActivity(intent_select);
            }
        });
        Button button_searchFeedback=(Button) findViewById(R.id.btn_searchFeedback);
        Intent intent_Feedback=new Intent(doctor_home.this,doctor_feedback.class);
        button_searchFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent_Feedback.putExtra("username",username);
                startActivity(intent_Feedback);
            }
        });
        Button btn_identity=(Button) findViewById(R.id.btn_doctor_identity);
        Intent intent_identity=new Intent(doctor_home.this,shimingrenzheng.class);
        btn_identity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent_identity);
            }
        });
    }
}