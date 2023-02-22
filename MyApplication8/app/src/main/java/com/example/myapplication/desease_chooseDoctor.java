package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import bean.Room;
import fragement.room1Fragment;
import fragement.room2Fragment;
import fragement.room3Fragment;
import fragement.room4Fragment;
import fragement.room5Fragment;
import fragement.room6Fragment;
import fragement.room7Fragment;
import fragement.room8Fragment;
import fragement.room9Fragment;
import fragement.room10Fragment;
import fragement.room11Fragment;
import fragement.room12Fragment;
import fragement.room13Fragment;
import fragement.room14Fragment;
import fragement.room15Fragment;
import fragement.room16Fragment;
import fragement.room17Fragment;
import fragement.room18Fragment;
import fragement.room19Fragment;
import utils.RoomAdapter;

public class desease_chooseDoctor extends AppCompatActivity {
    ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desease_choose_doctor);
        listview = (ListView) findViewById(R.id.listview);
        Intent backtomain = new Intent(desease_chooseDoctor.this,desease_home.class);

        List<Room> roomlist = new ArrayList<>();
        Room room1 = new Room("C");
        roomlist.add(room1);
            Room room2 = new Room("产科门诊");
            roomlist.add(room2);
            Room room3 = new Room("创伤骨科");
            roomlist.add(room3);
        Room room4 = new Room("E");
        roomlist.add(room4);
            Room room5 = new Room("耳鼻喉科");
            roomlist.add(room5);
            Room room6 = new Room("儿科");
            roomlist.add(room6);
        Room room7 = new Room("F");
        roomlist.add(room7);
        Room room8 = new Room("妇科");
        roomlist.add(room8);
        Room room9 = new Room("风湿科");
        roomlist.add(room9);
        Room room10 = new Room("G");
        roomlist.add(room10);
        Room room11 = new Room("肛肠科");
        roomlist.add(room11);
        Room room12 = new Room("肝胆外科");
        roomlist.add(room12);
        Room room13 = new Room("关节外科");
        roomlist.add(room13);
        Room room14 = new Room("骨伤科");
        roomlist.add(room14);
        Room room15 = new Room("K");
        roomlist.add(room15);
        Room room16 = new Room("口腔科");
        roomlist.add(room16);
        Room room17 = new Room("M");
        roomlist.add(room17);
        Room room18 = new Room("内分泌科");
        roomlist.add(room18);
         Room room19 = new Room("P");
         roomlist.add(room19);

        Room room20 = new Room("皮肤科");
        roomlist.add(room20);
        Room room21 = new Room("S");
        roomlist.add(room21);
        Room room22 = new Room("神经内科");
        roomlist.add(room22);
         Room room23 = new Room("神经外科");
         roomlist.add(room23);
          Room room24 = new Room("肾内科科");
          roomlist.add(room24);
                        Room room25 = new Room("X");
                         roomlist.add(room25);
            Room room26 = new Room("小儿骨科");
            roomlist.add(room26);
             Room room27 = new Room("血管外科");
             roomlist.add(room27);
              Room room28 = new Room("消化内科科");
           roomlist.add(room28);
           Room room29 = new Room("Y");
           roomlist.add(room29);
            Room room30 = new Room("眼科");
            roomlist.add(room30);
        RoomAdapter adapter = new RoomAdapter(desease_chooseDoctor.this,R.layout.room_item,roomlist);
        listview.setAdapter(adapter);
        /*
         *适配器，控制listview
         */

        getSupportFragmentManager().beginTransaction().add(R.id.pickframe,new room1Fragment()).commitAllowingStateLoss();
        // 初始化fragment

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Room roomnow = roomlist.get(position);

                if(position==1){
                    getSupportFragmentManager().beginTransaction().replace(R.id.pickframe,new room1Fragment()).addToBackStack(null).commitAllowingStateLoss();
                }
                if(position==2){
                    getSupportFragmentManager().beginTransaction().replace(R.id.pickframe,new room2Fragment()).addToBackStack(null).commitAllowingStateLoss();
                }
                if(position==4){
                    getSupportFragmentManager().beginTransaction().replace(R.id.pickframe,new room3Fragment()).addToBackStack(null).commitAllowingStateLoss();
                }
                if(position==5){
                    getSupportFragmentManager().beginTransaction().replace(R.id.pickframe,new room1Fragment()).addToBackStack(null).commitAllowingStateLoss();
                }
                if(position==6){
                    getSupportFragmentManager().beginTransaction().replace(R.id.pickframe,new room2Fragment()).addToBackStack(null).commitAllowingStateLoss();
                }
                if(position==7){
                    getSupportFragmentManager().beginTransaction().replace(R.id.pickframe,new room3Fragment()).addToBackStack(null).commitAllowingStateLoss();
                }
                if(position==8){
                    getSupportFragmentManager().beginTransaction().replace(R.id.pickframe,new room1Fragment()).addToBackStack(null).commitAllowingStateLoss();
                }
                if(position==10){
                    getSupportFragmentManager().beginTransaction().replace(R.id.pickframe,new room2Fragment()).addToBackStack(null).commitAllowingStateLoss();
                }
                if(position==11){
                    getSupportFragmentManager().beginTransaction().replace(R.id.pickframe,new room3Fragment()).addToBackStack(null).commitAllowingStateLoss();
                }
                if(position==12){
                    getSupportFragmentManager().beginTransaction().replace(R.id.pickframe,new room1Fragment()).addToBackStack(null).commitAllowingStateLoss();
                }
                if(position==13){
                    getSupportFragmentManager().beginTransaction().replace(R.id.pickframe,new room2Fragment()).addToBackStack(null).commitAllowingStateLoss();
                }
                if(position==15){
                    getSupportFragmentManager().beginTransaction().replace(R.id.pickframe,new room3Fragment()).addToBackStack(null).commitAllowingStateLoss();
                }
                if(position==17){
                    getSupportFragmentManager().beginTransaction().replace(R.id.pickframe,new room1Fragment()).addToBackStack(null).commitAllowingStateLoss();
                }
                if(position==19){
                    getSupportFragmentManager().beginTransaction().replace(R.id.pickframe,new room2Fragment()).addToBackStack(null).commitAllowingStateLoss();
                }
                if(position==21){
                    getSupportFragmentManager().beginTransaction().replace(R.id.pickframe,new room3Fragment()).addToBackStack(null).commitAllowingStateLoss();
                }
                if(position==22){
                    getSupportFragmentManager().beginTransaction().replace(R.id.pickframe,new room2Fragment()).addToBackStack(null).commitAllowingStateLoss();
                }
                if(position==23){
                    getSupportFragmentManager().beginTransaction().replace(R.id.pickframe,new room1Fragment()).addToBackStack(null).commitAllowingStateLoss();
                }
                if(position==25){
                    getSupportFragmentManager().beginTransaction().replace(R.id.pickframe,new room3Fragment()).addToBackStack(null).commitAllowingStateLoss();
                }
                if(position==26){
                    getSupportFragmentManager().beginTransaction().replace(R.id.pickframe,new room2Fragment()).addToBackStack(null).commitAllowingStateLoss();
                }





            }
        });
        /*
         *监听Listview 并把界面置为相应的医生界面
         */

        Button back_tomain = (Button) findViewById(R.id.backtomain);
        back_tomain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(backtomain);
            }
        });
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager=getSupportFragmentManager(); //创建待添加的实例
        FragmentTransaction transaction =fragmentManager.beginTransaction(); //获取实例
        transaction.replace(R.id.pickframe,fragment);
        transaction.addToBackStack(null);
        transaction.commitAllowingStateLoss();
    }
    /*
     *替换Fragment方法构造
     */
}