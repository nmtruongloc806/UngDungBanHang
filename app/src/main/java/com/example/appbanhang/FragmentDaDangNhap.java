package com.example.appbanhang;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class FragmentDaDangNhap extends Fragment {
    TextView txthoten,txtsodienthoai;
    Button btnDangXuat;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmentdadangnhap,container,false);
        txthoten = view.findViewById(R.id.txthoten);
        txtsodienthoai = view.findViewById(R.id.txtsodienthoai);
        btnDangXuat = view.findViewById(R.id.btnDangXuat);
        txthoten.setText(MainActivity.hoten);
        txtsodienthoai.setText(MainActivity.sodienthoai);
        return view;
    }
}
