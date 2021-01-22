package com.example.appbanhang;

import android.annotation.SuppressLint;
import android.content.Intent;
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
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.appbanhang.models.SanPham;

import java.util.ArrayList;

public class gioHangTinhTien extends Fragment {
    Button btnNhapThongTin;
    String haha = "\u2713";
    EditText edtHoten,edtSDT,edtDiaChi;
    ListView lvgh;
   // ArrayList<SanPham> sanPhams = new ArrayList<SanPham>();
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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.giohangtinhtien,container,false);
        btnNhapThongTin = view.findViewById(R.id.btnNhapThongTin);
        lvgh = (ListView) view.findViewById(R.id.listGioHang);
        getData();
        gioHangAdapter = new gioHangAdapter(getActivity(),MainActivity.listGH);
        lvgh.setAdapter(gioHangAdapter);
        btnNhapThongTin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MainActivity.dadangnhap == true) {
                    giohangthongtin fragment = new giohangthongtin();
                    FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.framelayoutGioHang, fragment);
                    fragmentTransaction.addToBackStack("fragment");
                    fragmentTransaction.commit();
                    txtChecked.setBackgroundResource(R.drawable.circle);
//                    txtChecked.setText(haha);
                    txtUnchecked.setBackgroundResource(R.drawable.circle);
//                    txtUnchecked.setText("");
//                    toolbar.setNavigationIcon(R.drawable.back_icon);
//                    toolbar.setTitle("Thông Tin Mua Hàng");
//                    toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            getActivity().onBackPressed();
//                            toolbar.setTitle("Giỏ Hàng");
//                            toolbar.setNavigationIcon(null);
//                            txtChecked.setBackgroundResource(R.drawable.circle);
////                            txtChecked.setText("");
//                            txtUnchecked.setBackgroundResource(R.drawable.circle2);
////                            txtUnchecked.setText("");
//                        }
//                    });
                }else{
                    Intent intent = new Intent(getActivity(),FormDNDK.class);
                    startActivity(intent);
                }

            }
        });
        return view;
    }
    public void getData(){
        if(MainActivity.themVaoGioHang == true){
            SanPham sp = new SanPham(null,MainActivity.TEN,MainActivity.HINH,0,null,null);
            MainActivity.listGH.add(sp);
            MainActivity.themVaoGioHang = false;
        }
    }
}
