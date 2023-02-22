package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import fragement.MyFragment;
import fragement.MyFragment;

public class desease_home extends AppCompatActivity {
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desease_home);
        Intent intent_get=getIntent();
        username=intent_get.getStringExtra("username");
        // 获取页面上的底部导航栏控件
        BottomNavigationView navView = findViewById(R.id.nav_view);

        // 配置navigation与底部菜单之间的联系
        // 底部菜单的样式里面的item里面的ID与navigation布局里面指定的ID必须相同，否则会出现绑定失败的情况
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.main_navi,R.id.mine_navi)
                .build();
        // 建立fragment容器的控制器，这个容器就是页面的上的fragment容器
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        // 启动
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);


    }

    public String getTitles(){
        if(username!=null)
            return username;
        else{
            Intent intent_get=getIntent();
            username=intent_get.getStringExtra("username");
            return username;
        }
    }

}