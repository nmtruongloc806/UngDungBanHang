package com.example.appbanhang;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.appbanhang.models.SanPham;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class gioHangTinhTien extends Fragment {
    Button btnNhapThongTin;
    String haha = "\u2713";
    EditText edtHoten,edtSDT,edtDiaChi;
    ListView lvgh;
    TextView txttongtien;
    public static int tongtien = 0;
    gioHangAdapter gioHangAdapter;
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

    ImageView txtChecked, txtUnchecked;
    Toolbar toolbar;
    public  gioHangTinhTien(ImageView txtChecked, ImageView txtUnchecked, Toolbar toolbar){
        this.txtChecked = txtChecked;
        this.txtUnchecked = txtUnchecked;
        this.toolbar = toolbar;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.giohangtinhtien, container, false);
        btnNhapThongTin = view.findViewById(R.id.btnNhapThongTin);
        lvgh = (ListView) view.findViewById(R.id.listGioHang);
        txttongtien = (TextView) view.findViewById(R.id.txttongtien);
        gioHangAdapter = new gioHangAdapter(getActivity(), MainActivity.listGH, txttongtien);
        lvgh.setAdapter(gioHangAdapter);
        MainActivity.listGH.forEach(sanPham -> {
            tongtien += sanPham.getTongtien();
            txttongtien.setText("Giá: " + tongtien +" VNĐ");
        });
        gioHangAdapter.notifyDataSetChanged();
        btnNhapThongTin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MainActivity.dadangnhap == true && MainActivity.listGH.size() == 0) {
                    Toast.makeText(getActivity(), "Giỏ hàng đang trống", Toast.LENGTH_SHORT).show();
                } else if(MainActivity.dadangnhap == true && MainActivity.listGH.size() > 0){
                    giohangthongtin fragment = new giohangthongtin();
                    FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.framelayoutGioHang, fragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                    txtChecked.setBackgroundResource(R.drawable.circle);
                    txtUnchecked.setBackgroundResource(R.drawable.circle);
                }else {
                    Intent intent = new Intent(getActivity(), FormDNDK.class);
                    startActivity(intent);
                }

            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        txtChecked.setBackgroundResource(R.drawable.circle);
        txtUnchecked.setBackgroundResource(R.drawable.circle2);
        tongtien = 0;
    }
}
