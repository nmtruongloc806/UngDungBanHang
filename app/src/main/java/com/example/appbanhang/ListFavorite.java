package com.example.appbanhang;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.appbanhang.models.SanPham;

public class ListFavorite extends Fragment {
    GridView gridView;
    sanPhamAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.listfavorite, container , false);
        gridView = (GridView) view.findViewById(R.id.grid_view_yeuthich);
        adapter = new sanPhamAdapter(getActivity(),MainActivity.listYT);
        gridView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        return view;
    }

}
