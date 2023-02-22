package users;

import android.widget.ArrayAdapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.Purchase;
import com.example.myapplication.R;


//用于将上下文、listview 子项布局的 id 和数据都传递过来
public class PurchaseAdapter extends ArrayAdapter<Purchase> {
    public PurchaseAdapter(@NonNull Context context, int resource, @NonNull List<Purchase> objects) {
        super(context, resource, objects);
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Purchase purchase=getItem(position);//得到当前项的 Fruit 实例
        //为每一个子项加载设定的布局
        View view=LayoutInflater.from(getContext()).inflate(R.layout.purchase_item,parent,false);
        //分别获取 image view 和 textview 的实例
        TextView purchasename =view.findViewById(R.id.purchase_name);
        TextView purchaseprice =view.findViewById(R.id.purchase_price);
        ImageView purchaseimage =view.findViewById(R.id.purchase_image);
        // 设置要显示的图片和文字
        purchasename.setText(purchase.getName());
        purchaseprice.setText(purchase.getPrice());
        purchaseimage.setImageResource(purchase.getImage_id());
        return view;
    }



}