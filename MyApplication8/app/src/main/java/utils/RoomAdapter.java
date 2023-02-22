package utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.R;

import java.util.List;

import bean.Room;

public class RoomAdapter extends ArrayAdapter<Room> {
    public RoomAdapter(@NonNull Context context, int resource, @NonNull List<Room> objects) {
        super(context, resource, objects);
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Room room=getItem(position);//得到当前项的 Fruit 实例
        //为每一个子项加载设定的布局
        View view= LayoutInflater.from(getContext()).inflate(R.layout.room_item,parent,false);
        //分别获取 image view 和 textview 的实例
        TextView roomname =view.findViewById(R.id.room_name);
        // 设置要显示的图片和文字
        roomname.setText(room.getName());
        return view;
    }



}
