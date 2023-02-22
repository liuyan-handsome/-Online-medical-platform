package users;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.Fedback;
import com.example.myapplication.Medicine;
import com.example.myapplication.R;

import java.util.List;

public class MedicineAdapter extends ArrayAdapter<Medicine> {
    public MedicineAdapter(@NonNull Context context, int resource, @NonNull List<Medicine> objects) {
        super(context, resource, objects);
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Medicine medicine=getItem(position);//得到当前项的 Fruit 实例
        //为每一个子项加载设定的布局
        View view= LayoutInflater.from(getContext()).inflate(R.layout.medicine_item,parent,false);
        //分别获取 image view 和 textview 的实例
        TextView medicinename =view.findViewById(R.id.medicine_name);
        TextView medicineprice =view.findViewById(R.id.medicine_price);
        ImageView medicineimage =view.findViewById(R.id.medicine_image);
        // 设置要显示的图片和文字
        medicinename.setText(medicine.getName());
        medicineprice.setText(medicine.getPrice());
        medicineimage.setImageResource(medicine.getImage_id());
        return view;
    }



}