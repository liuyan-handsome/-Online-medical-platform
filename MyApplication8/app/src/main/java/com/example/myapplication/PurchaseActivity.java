package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import users.PurchaseAdapter;

public class PurchaseActivity extends AppCompatActivity {

    ListView listview_purchase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase2);

        listview_purchase = (ListView) findViewById(R.id.listview_purchase);

        List<Purchase> purchaselist = new ArrayList<>();
        Purchase purchase1 = new Purchase(R.drawable.tianmatoutongpian, "天麻头疼片  ", "13元");
        purchaselist.add(purchase1);
        Purchase purchase2 = new Purchase(R.drawable.weitongwan, "胃痛丸", "30元");
        purchaselist.add(purchase2);
        Purchase purchase3 = new Purchase(R.drawable.xiguashuang, "西瓜霜", "10元");
        purchaselist.add(purchase3);
        Purchase purchase4 = new Purchase(R.drawable.medcine_999, "999感冒灵", "15元");
        purchaselist.add(purchase4);
        Purchase purchase5 = new Purchase(R.drawable.buluofen, "布洛芬胶囊", "15元");
        purchaselist.add(purchase5);
        Purchase purchase6 = new Purchase(R.drawable.wantongjingutie, "万通筋骨贴", "20元");
        purchaselist.add(purchase6);
        Purchase purchase7 = new Purchase(R.drawable.shenbaopian, "肾宝片", "50元");
        purchaselist.add(purchase7);
        Purchase purchase8 = new Purchase(R.drawable.yunnanbaiyao, "云南白药", "20元");
        purchaselist.add(purchase8);
        Purchase purchase9 = new Purchase(R.drawable.lianhuaqingwen, "莲花清瘟胶囊", "15元");
        purchaselist.add(purchase9);
        Purchase purchase10 = new Purchase(R.drawable.liuweidihuangwan, "六味地黄丸", "10元");
        purchaselist.add(purchase10);
        Purchase purchase11 = new Purchase(R.drawable.baidi, "", "");
        purchaselist.add(purchase11);
        Purchase purchase12 = new Purchase(R.drawable.baidi, "", "");
        purchaselist.add(purchase12);
        /*
         *设置左侧导航栏内容
         */

        PurchaseAdapter adapter_purchase = new PurchaseAdapter(PurchaseActivity.this, R.layout.purchase_item, purchaselist);
        listview_purchase.setAdapter(adapter_purchase);
        /*
         *适配器，控制listview
         */

        Intent totest = new Intent(PurchaseActivity.this, medcine_information.class);
        listview_purchase.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Purchase purchasenow = purchaselist.get(position);
                String medcine_name=purchasenow.getName();
                String medcine_price=purchasenow.getPrice();
                Intent intent=new Intent(PurchaseActivity.this,medcine_information.class);
                intent.putExtra("medcine_name",medcine_name);
                intent.putExtra("medcine_price",medcine_price);
                startActivity(intent);
            }
        });

    }

}