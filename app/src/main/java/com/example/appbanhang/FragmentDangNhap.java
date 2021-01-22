package com.example.appbanhang;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class FragmentDangNhap extends Fragment {

    EditText edtsodienthoai,edtmatkhau;
    Button btnDangNhap;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_dang_nhap,container,false);
        edtsodienthoai = view.findViewById(R.id.edtsodienthoai);
        edtmatkhau = view.findViewById(R.id.edtmatkhau);
        btnDangNhap = view.findViewById(R.id.btnDangNhap);
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!validateTenDangNhap() | !validateMatKhau()){
                    return;
                }else {
                    isUser();
                }
            }
        });
        return view;
    }

    private Boolean validateTenDangNhap (){
        String val = edtsodienthoai.getText().toString();

        if(val.isEmpty()){
            edtsodienthoai.setError("Vui lòng nhập tên đăng nhập");
            return false;
        }else {
            edtsodienthoai.setError(null);
            return true;
        }
    }
    private Boolean validateMatKhau (){
        String val = edtsodienthoai.getText().toString();
        if(val.isEmpty()){
            edtmatkhau.setError("Vui lòng nhập mật khẩu");
            return false;
        }else {
            edtmatkhau.setError(null);
            return true;
        }
    }
    public  void isUser(){
        String phone = edtsodienthoai.getText().toString().trim();
        String password = edtmatkhau.getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");

        Query checkTenDangNhap = reference.orderByChild("sodienthoai").equalTo(phone);

        checkTenDangNhap.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()) {

                    edtsodienthoai.setError(null);

                    String passwordFromDB = snapshot.child(phone).child("matkhau").getValue(String.class);
                    if (passwordFromDB.equals(password)) {
                        edtsodienthoai.setError(null);
                        String hotenFromDB = snapshot.child(phone).child("hoten").getValue(String.class);
                        String sodienthoaiFromDB = snapshot.child(phone).child("sodienthoai").getValue(String.class);
                        String matkhauFromDB = snapshot.child(phone).child("matkhau").getValue(String.class);
                        String diachiFromDB = snapshot.child(phone).child("diachi").getValue(String.class);
                        String ngaysinhFromDB = snapshot.child(phone).child("ngaysinh").getValue(String.class);
                        String gioitinhFromDB = snapshot.child(phone).child("gioitinh").getValue(String.class);
                        getActivity().finish();
                        MainActivity.dadangnhap = true;
                        Intent intent = new Intent(getActivity(),MainActivity.class);
                        intent.putExtra("name",hotenFromDB);
                        Log.d("hichic", "onDataChange: "+hotenFromDB);
                        intent.putExtra("sodienthoai",sodienthoaiFromDB);
                        intent.putExtra("matkhau",matkhauFromDB);
                        intent.putExtra("diachi",diachiFromDB);
                        intent.putExtra("ngaysinh",ngaysinhFromDB);
                        intent.putExtra("gioitinh",gioitinhFromDB);
                        startActivity(intent);

                        Toast.makeText(getContext(),"Đăng Nhập Thành Công",Toast.LENGTH_LONG).show();

                    }else {
                        edtmatkhau.setError("Wrong Password");
                        edtmatkhau.requestFocus();
                    }
                }else {
                    edtsodienthoai.setError("No such User exist");
                    edtsodienthoai.requestFocus();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}
