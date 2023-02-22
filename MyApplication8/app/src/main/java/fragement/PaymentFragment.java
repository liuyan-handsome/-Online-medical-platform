package fragement;

import android.app.Activity;
import android.os.Bundle;
import android.os.Looper;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import com.example.myapplication.R;
import com.example.myapplication.desease_home;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PaymentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PaymentFragment extends ListFragment {
    String url = "jdbc:mysql://192.168.137.1:3306/demo?serverTimezone=GMT%2B8&characterEncoding=utf8&useUnicode=true&useSSL=false&autoReconnect=true&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true";
    //连接数据库使用的用户名
    String userName = "root";
    //连接的数据库时使用的密码
    String password = "lcl123456ABC";
    Connection connection=null;
    String usernames;
    ListView listView;
    String [] medcine_name=new String[50];
    String [] medcine_price=new String[50];
    int flag;
    int[] imageArr=new int[50];
    String[] titleArr=new String[50];
    String[] priceArr=new String[50];
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PaymentFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PaymentFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PaymentFragment newInstance(String param1, String param2) {
        PaymentFragment fragment = new PaymentFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        flag=0;
        search_medcine();
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        listView = (ListView) getActivity().findViewById(R.id.listview_payment);
        while(flag==0) {
            continue;
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(this.getActivity(), getData(),
                R.layout.payment_item,
                new String[]{"img", "name", "price"},
                new int[]{R.id.payment_image, R.id.payment_name, R.id.payment_price});
        setListAdapter(simpleAdapter);
    }

    private List<? extends Map<String,?>> getData() {
        List<Map<String,Object>> list;
        Map<String,Object> map;
        list=new ArrayList<Map<String,Object>>();

        for(int i=0;i<imageArr.length;i++){
            map=new HashMap<String,Object>();
            map.put("img",imageArr[i]);
            map.put("name",titleArr[i]);
            map.put("price",priceArr[i]);
            list.add(map);
        }

        return list;
    }
    /*
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_payment, container, false);
    }
*/
    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        usernames= ( (desease_home) activity).getTitles();
    }

    /*@Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        ListView listview = (ListView) getActivity().findViewById(R.id.listview_payment);
        RoomAdapter adapter = new RoomAdapter(getActivity(),R.layout.room_item,roomlist);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent_medcine_payment = new Intent(getActivity(), medcine_payment.class);
                startActivity(intent_medcine_payment);
            }
        });

    }*/
    public void search_medcine(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                //获取输入框的数据
                try {
                    //1、加载驱动
                    Class.forName("com.mysql.jdbc.Driver").newInstance();
                    System.out.println("驱动加载成功！！！");
                    //2、获取与数据库的连接
                    connection = DriverManager.getConnection(url, userName, password);
                    System.out.println("连接数据库成功！！！");
                    //3.sql添加数据语句
                    String sql = "select * from medcine ";
                    PreparedStatement pst=connection.prepareStatement(sql);
                    ResultSet rs=pst.executeQuery();
                    int i=0;
                    while (rs.next()){
                        titleArr[i]=rs.getString(2);
                        priceArr[i]=rs.getString(3);
                        if(titleArr[i].equals("999")){
                            imageArr[i]= R.drawable.medcine_999;
                        }
                        else if(titleArr[i].equals("xiguashuang")){
                            imageArr[i]= R.drawable.xiguashuang;
                        }
                        else if(titleArr[i].equals("jianwei")){
                            imageArr[i]= R.drawable.jianwei;
                        }
                        i++;
                    }
                    flag=1;
                }catch (Exception e) {
                    e.printStackTrace();
                }
                finally {
                    if(connection!=null){
                        try {
                            connection.close();
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }
                Looper.loop();
            }

        }).start();
    }
}