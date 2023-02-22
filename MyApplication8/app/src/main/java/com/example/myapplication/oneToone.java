package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class oneToone extends AppCompatActivity {

    //以下为假设

    private String user1="user2";//sender
    private String user2="user1";//receiver,你是这个b

    //以上为假设
    //数据库导入
    private static final int COMPLETED = 0;
    private String net_user1;
    private String net_user2;
    private String net_msg;
    private ListView mListView;
    private Button button;
    private boolean T=false;//发送标志位
    List<msgBean> data = new ArrayList<msgBean>();
    msgAdapter adapter= new msgAdapter();
    private EditText et_msg;//用户输入信息框

    private static Connection con = null;
    private static PreparedStatement stmt = null;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == COMPLETED) {
                msgBean bean = new msgBean();
                bean.setType(0);
                bean.setIcon(BitmapFactory.decodeResource(getResources(), R.drawable.headportrait6));
                bean.setText(net_msg);
                data.add(bean);
                mListView.setAdapter(new msgAdapter(oneToone.this,data));
                mListView.setSelection(data.size());
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_toone);
        Intent intent_get=getIntent();
        //user2=intent_get.getStringExtra("reciver");
        //user1=intent_get.getStringExtra("sender");
        mListView = (ListView) findViewById(R.id.list_view);

        button = findViewById(R.id.send1);
        et_msg = findViewById(R.id.input_text);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //定义一个String类型的  input变量  返回input_text里的内容
                String input = et_msg.getText().toString();
                if(input.equals("")){Toast.makeText(oneToone.this,"发送内容不能为空",Toast.LENGTH_SHORT).show();}
                else {
                    //定义一个msgBean类型的对象  发送消息的实例
                    msgBean bean = new msgBean();
                    bean.setType(1);
                    bean.setIcon(BitmapFactory.decodeResource(getResources(), R.drawable.headportrait4));
                    bean.setText(input);
                    data.add(bean);
                    mListView.setAdapter(new msgAdapter(oneToone.this, data));
                    mListView.setSelection(data.size());
                }

                T=true;
            }
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
                            Toast.makeText(oneToone.this, "消息为空", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                            T=false;
                            break;
                        }
                        if (msg.length()>199){
                            Looper.prepare();
                            Toast.makeText(oneToone.this, "消息长度超过限制", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                            T=false;
                            break;
                        }

                        if (con!=null) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(oneToone.this, "发送成功", Toast.LENGTH_SHORT).show();
                                }
                            });
                            String sql = "INSERT into comdata(user1,user2,msg,sign) values(?,?,?,?)";
                            String a ="0";
                            stmt = con.prepareStatement(sql);

                            // 关闭事务自动提交
                            con.setAutoCommit(false);
                            System.out.println(user2);
                            stmt.setString(1,user2);
                            stmt.setString(2,user1);
                            stmt.setString(3,msg);
                            stmt.setString(4,a);    //代表导入数据库但未传递
                            stmt.addBatch();
                            stmt.executeBatch();
                            con.commit();
                            et_msg.setText("");
                        }
                    }catch (SQLException e){
                        System.out.println(e);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(oneToone.this,"请输入正确的语句",Toast.LENGTH_SHORT).show();
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
                    String sql = "select user1,user2,msg from comdata where (user1 = ?) and (user2 = ?) and (sign='0')";
                    if (con != null) {
                        stmt = con.prepareStatement(sql);
                        // 关闭事务自动提交
                        con.setAutoCommit(false);
                        stmt.setString(1,user1);
                        stmt.setString(2,user2);

                        rs = stmt.executeQuery();//创建数据对象
                        while(rs.next()){
                            net_user1 = rs.getString(1);
                            net_user2 = rs.getString(2);
                            net_msg = rs.getString(3);
                            System.out.println(rs.getString(3));
                            System.out.println(net_msg);
                            //对方发给我的信息
                            //传递给主线程让它来更新
                            Message msg = Message.obtain();
                            msg.what = COMPLETED;
                            handler.sendMessage(msg);
                            sleep(500);
                        }
                        con.commit();
                        rs.close();
                        stmt.close();
                        sql = "UPDATE comdata SET sign='1' WHERE (user1 = ?) and (user2 = ?) and (sign='0')";
                        stmt = con.prepareStatement(sql);
                        stmt.setString(1,user1);
                        stmt.setString(2,user2);

                        // 关闭事务自动提交
                        con.setAutoCommit(false);
                        stmt.executeUpdate();
                        con.commit();
                        sleep(500);
                    }
                } catch (Exception e) {
                    System.out.println(e);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //Toast.makeText(oneToone.this,"请输入正确的语句",Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                /*try{
                    String sql1 = "UPDATE comdata SET sign = 1 WHERE (user1 = 'user1') and (user2 = 'user2') and (sign = 0)";

                    stmt = con.prepareStatement(sql1);
                    // 关闭事务自动提交
                    con.setAutoCommit(false);
                    stmt.executeUpdate();
                    stmt.close();
                }catch (Exception e){
                    System.out.println(e);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //Toast.makeText(oneToone.this,"请输入正确的语句",Toast.LENGTH_SHORT).show();
                        }
                    });
                }*/

            }
        }
    }
}