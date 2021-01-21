package com.example.appbanhang;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appbanhang.models.ThuongHieu;
import com.squareup.picasso.Picasso;

import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    ArrayList<ThuongHieu> imageUrl;
    @Override
    public int getCount() {
        return this.imageUrl.size();
    }
    CustomAdapter(Context context, ArrayList<ThuongHieu> RecipeImageUrl) {
        this.context = context;
        this.imageUrl = RecipeImageUrl;
    }
    @Override
    public Object getItem(int position) {
        return imageUrl.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private Context context;
    private LayoutInflater inflater;
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null) {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.row_item, null);
        }

        ImageView imageView = convertView.findViewById(R.id.image_view);

        /// url của firebase
        String url = imageUrl.get(position).HinhTH;
        Log.d("MTL", "getView: " + url);
        /// get image url firebase
//        imageView.setImageDrawable(LoadImageFromWebOperations(url));

        /// đây là image từ trong project (Resource)
        //imageView.setImageResource(Integer.parseInt(imageUrl.get(position)));

        Picasso.with(context).load(url).into(imageView);
        return convertView;
    }
}
