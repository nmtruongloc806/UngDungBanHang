package com.example.appbanhang;

import android.content.ClipData;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.appbanhang.models.SanPham;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class sanPhamAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    public ArrayList<SanPham> sanPham;
    public sanPhamAdapter(Context context, ArrayList<SanPham> sanpham) {
        this.context = context;
        this.sanPham = sanpham;
    }

    @Override
    public int getCount() {
        return this.sanPham.size();
    }

    @Override
    public Object getItem(int position) {
        return this.sanPham.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SanPham currentItem = (SanPham) getItem(position);
        if (inflater == null) {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_sanpham, null);
        }
        ImageView imageView = convertView.findViewById(R.id.image_sanpham);
        TextView textView = convertView.findViewById(R.id.txttensp);
        TextView giaTienText = convertView.findViewById(R.id.txtgiatien);
        textView.setText(currentItem.getTenSP());
        giaTienText.setText(String.valueOf("đ"+currentItem.giaSP));
        ImageButton imageButton = convertView.findViewById(R.id.imgyeuthich);
        if(currentItem.isYeuThich() == true){
            imageButton.setImageResource(R.drawable.favorite_icon);
        }
        else{
            imageButton.setImageResource(R.drawable.favorite_border_icon);
        }
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentItem.isYeuThich() == false) {
                    imageButton.setImageResource(R.drawable.favorite_icon);
                    currentItem.setYeuThich(true);
                    MainActivity.listYT.add(currentItem);
                    Toast.makeText(v.getContext(),"Đã Thêm Vào Yêu Thích",Toast.LENGTH_SHORT).show();
                }
                else {
                    currentItem.setYeuThich(false);
                    /// khi nó là false -> remove nó khỏi listYT
                    MainActivity.listYT.remove(currentItem);
                    notifyDataSetChanged();
                    imageButton.setImageResource(R.drawable.favorite_border_icon);
                    Toast.makeText(v.getContext(),"Xóa Khỏi Yêu Thích",Toast.LENGTH_SHORT).show();
                }
            }
        });
        /// url của firebase
        String url = sanPham.get(position).hinhSP;
        Picasso.with(context).load(url).into(imageView);
        return convertView;
    }
}
