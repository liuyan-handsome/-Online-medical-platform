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
import com.example.myapplication.Purchase;
import com.example.myapplication.R;

import java.util.List;

//用于将上下文、listview 子项布局的 id 和数据都传递过来
public class FedbackAdapter extends ArrayAdapter<Fedback> {
    public FedbackAdapter(@NonNull Context context, int resource, @NonNull List<Fedback> objects) {
        super(context, resource, objects);
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Fedback fedback=getItem(position);//得到当前项的 Fruit 实例
        //为每一个子项加载设定的布局
        View view= LayoutInflater.from(getContext()).inflate(R.layout.fedback_item,parent,false);
        //分别获取 image view 和 textview 的实例
        TextView fedbackname =view.findViewById(R.id.fedback_name);
        TextView fedbackprice =view.findViewById(R.id.fedback_price);
        ImageView fedbackimage =view.findViewById(R.id.fedback_image);
        // 设置要显示的图片和文字
        fedbackname.setText(fedback.getName());
        fedbackprice.setText(fedback.getPrice());
        fedbackimage.setImageResource(fedback.getImage_id());
        return view;
    }



}