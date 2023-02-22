package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class businessman_home extends AppCompatActivity {
    Button btn_medcine;
    Button btn_myself;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_businessman_home);
        btn_medcine=(Button) findViewById(R.id.btn_bussinessman_medcine);
        btn_medcine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_medcine=new Intent(businessman_home.this,bussinessman_medcine.class);
                startActivity(intent_medcine);
            }
        });
        btn_myself=(Button) findViewById(R.id.btn_bussinessman_myself);
        btn_myself.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent_myself=new Intent(businessman_home.this,bus)
            }
        });

    }
}