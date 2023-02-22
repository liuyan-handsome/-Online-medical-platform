package fragement;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.doctor_information;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link room2Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class room2Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public room2Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment room2Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static room2Fragment newInstance(String param1, String param2) {
        room2Fragment fragment = new room2Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_room2, container, false);
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Intent intent_information = new Intent(getActivity(), doctor_information.class);
        Button btn_pickup = (Button) getActivity().findViewById(R.id.btn_zhaodoctor);
        btn_pickup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent_information);
            }
        });
        Button btn_pickup2 = (Button) getActivity().findViewById(R.id.btn_sundoctor);
        btn_pickup2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent_information);
            }
        });
        Button btn_pickup3 = (Button) getActivity().findViewById(R.id.btn_jiangdoctor);
        btn_pickup2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent_information);
            }
        });
    }
}