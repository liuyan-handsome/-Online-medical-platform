package fragement;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.desease_chooseDoctor;
import com.example.myapplication.desease_home;
import com.example.myapplication.feedback;
import com.example.myapplication.payment;
import com.example.myapplication.test;
import com.example.myapplication.PurchaseActivity;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends Fragment {
    String usernames;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MainFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainFragment newInstance(String param1, String param2) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater,@Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        /*Bundle bundle=getArguments();
        username=bundle.getString("username");
        System.out.println("haha"+username);*/
        // Inflate the layout for this fragment

        // return inflater.inflate(R.layout.fragment_main, container, false);
        View view=inflater.inflate(R.layout.fragment_main,container,false);
        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);



        Intent to_test =  new Intent(getActivity(), PurchaseActivity.class);
        Intent intent_select = new Intent(getActivity(), desease_chooseDoctor.class);
        Button btn_pickup = (Button) getActivity().findViewById(R.id.btn_select);
        Button btn_topurchase = (Button) getActivity().findViewById(R.id.btn_purchase);
        btn_topurchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(to_test);
            }
        });

        btn_pickup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent_select.putExtra("username",usernames);
                startActivity(intent_select);
            }
        });
        Intent intent_feedback = new Intent(getActivity(), feedback.class);
        Button button_feedback = (Button) getActivity().findViewById(R.id.btn_feedback);
        button_feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent_feedback.putExtra("username",usernames);
                startActivity(intent_feedback);
            }
        });
        Intent intent_payment = new Intent(getActivity(), payment.class);
        Button btn_payment = (Button) getActivity().findViewById(R.id.btn_payment);
        btn_payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent_payment.putExtra("username",usernames);
                startActivity(intent_payment);
            }
        });
        /*
         *设置三个按钮监听以及访问对应的activity
         */


    }


    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        usernames= ( (desease_home) activity).getTitles();
    }
}