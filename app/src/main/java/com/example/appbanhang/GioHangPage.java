package com.example.appbanhang;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class GioHangPage extends Fragment {
    public GioHangPage(){

    }

    public ImageView getTxtChecked() {
        return txtChecked;
    }

    public void setTxtChecked(ImageView txtChecked) {
        this.txtChecked = txtChecked;
    }

    public ImageView getTxtUnchecked() {
        return txtUnchecked;
    }

    public void setTxtUnchecked(ImageView txtUnchecked) {
        this.txtUnchecked = txtUnchecked;
    }
    Toolbar toolbarGioHang;
    ImageView txtChecked, txtUnchecked;
    String edthoten,edtSDT,edtDiaChi;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.giohang, container , false);
        txtChecked = view.findViewById(R.id.txtChecked);
//        toolbarGioHang = view.findViewById(R.id.toolbarGioHang);
        txtUnchecked = view.findViewById(R.id.txtUnchecked);
        if (savedInstanceState == null){
            gioHangTinhTien fragment = new gioHangTinhTien(txtChecked,txtUnchecked,toolbarGioHang);
            FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.framelayoutGioHang,fragment, "backStateName");
            fragmentTransaction.addToBackStack("backStateName");
            fragmentTransaction.commit();
        }
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            String myString = bundle.getString("key",edthoten);
            myString  = bundle.getString("key",edtSDT);
            myString  = bundle.getString("key",edtDiaChi);
        }


        return view;
    }

}
