package com.example.appbanhang;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appbanhang.models.SanPham;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class gioHangAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    ArrayList<SanPham> sanPham;
    public gioHangAdapter(Context context, ArrayList<SanPham> sanPham) {
        this.context = context;
        this.sanPham = sanPham;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        SanPham currentItem = (SanPham) getItem(position);
        if (inflater == null) {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_giohang, null);
        }
        ImageView imageView = convertView.findViewById(R.id.image_hinhgiohang);
        TextView textView = convertView.findViewById(R.id.txttengiohang);
        textView.setText(currentItem.getTenSP());
        TextView textView1 = convertView.findViewById(R.id.txtgiagiohang);
        CheckBox checkBox = convertView.findViewById(R.id.cbgiohang);
        Button button = convertView.findViewById(R.id.btncong);
        Button button1 = convertView.findViewById(R.id.btnsoluong);
        Button button2= convertView.findViewById(R.id.btntru);
        String url = sanPham.get(position).hinhSP;
        Picasso.with(context).load(url).into(imageView);
        return convertView;
    }
}
