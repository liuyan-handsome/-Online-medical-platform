package fragement;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.desease_home;
import com.example.myapplication.desease_information;
import com.example.myapplication.desease_search_information;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    String usernames;
    public MyFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MyFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MyFragment newInstance(String param1, String param2) {
        MyFragment fragment = new MyFragment();
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

    public View onCreateView(LayoutInflater inflater,@Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        // return inflater.inflate(R.layout.fragment_main, container, false);
        View view=inflater.inflate(R.layout.fragment_myfragment,container,false);
        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        Intent intent_information = new Intent(getActivity(), desease_information.class);
        Button btn_pickup = (Button) getActivity().findViewById(R.id.btn_information);
        btn_pickup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //intent_information.putExtra("username",username);
                //Intent intent=new Intent(getActivity(), login.class);
                //startActivity(intent);
                startActivity(intent_information);
            }
        });
        Intent intent_search_information = new Intent(getActivity(), desease_search_information.class);
        Button btn_search_information = (Button) getActivity().findViewById(R.id.btn_search_information);
        btn_search_information.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent_search_information);
            }
        });
    }
    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        usernames= ( (desease_home) activity).getTitles();
    }
}