package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import users.MedicineAdapter;
import users.PurchaseAdapter;

public class bussinessman_medcine extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ListView listview_medicine;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bussinessman_medcine);
        listview_medicine = (ListView) findViewById(R.id.listview_medicine);

        List<Medicine> medicinelist = new ArrayList<>();

            Medicine medicine1 = new Medicine(R.drawable.wantongjingutie, "订单号：123  ", "20元");
            medicinelist.add(medicine1);




        /*
         *设置左侧导航栏内容
         */

        MedicineAdapter adapter_medicine = new MedicineAdapter(bussinessman_medcine.this, R.layout.medicine_item, medicinelist);
        listview_medicine.setAdapter(adapter_medicine);
        /*
         *适配器，控制listview
         */

        Intent totest = new Intent(bussinessman_medcine.this, bussinessman_medcine_dispose.class);
        listview_medicine.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Medicine medicine = medicinelist.get(position);
                startActivity(totest);
            }
        });
    }
}