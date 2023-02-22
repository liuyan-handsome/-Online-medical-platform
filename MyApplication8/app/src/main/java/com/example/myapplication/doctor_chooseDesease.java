package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import users.FedbackAdapter;
import users.PurchaseAdapter;

public class doctor_chooseDesease extends AppCompatActivity {
    ListView listview_fedback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_choose_desease);

        listview_fedback = (ListView) findViewById(R.id.listview_fedback);

        List<Fedback> fedbacklist = new ArrayList<>();
       Fedback fedback1 = new Fedback(R.drawable.desease, "张三  ", "男");
       fedbacklist.add(fedback1);
        Fedback fedback2 = new Fedback(R.drawable.desease, "李四", "男");
        fedbacklist.add(fedback2);
        Fedback fedback3 = new Fedback(R.drawable.desease, "王五", "男");
        fedbacklist.add(fedback3);

        /*
         *设置左侧导航栏内容
         */

        FedbackAdapter adapter_fedback = new FedbackAdapter (doctor_chooseDesease.this, R.layout.fedback_item, fedbacklist);
        listview_fedback.setAdapter(adapter_fedback);
        /*
         *适配器，控制listview
         */

        Intent totest = new Intent(doctor_chooseDesease.this, doctor_desease_information.class);
        listview_fedback.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Fedback fedbacknow = fedbacklist.get(position);
                startActivity(totest);
            }
        });
    }
}