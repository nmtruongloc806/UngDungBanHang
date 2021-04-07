package com.example.appbanhang;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import at.favre.lib.crypto.bcrypt.BCrypt;

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
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("taikhoan");

        Query checkTenDangNhap = reference.orderByChild("sodienthoai").equalTo(phone);

        checkTenDangNhap.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()) {
                    edtsodienthoai.setError(null);
                    String passwordFromDB = snapshot.child(phone).child("matkhau").getValue(String.class);
                    BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), passwordFromDB);
                    if (result.verified) {
                        edtsodienthoai.setError(null);
                        String hotenFromDB = snapshot.child(phone).child("hoten").getValue(String.class);
                        String sodienthoaiFromDB = snapshot.child(phone).child("sodienthoai").getValue(String.class);
                        String matkhauFromDB = snapshot.child(phone).child("matkhau").getValue(String.class);
                        String diachiFromDB = snapshot.child(phone).child("diachi").getValue(String.class);
                        String ngaysinhFromDB = snapshot.child(phone).child("ngaysinh").getValue(String.class);
                        String gioitinhFromDB = snapshot.child(phone).child("gioitinh").getValue(String.class);
                        String tenLoaiFromDB = snapshot.child(phone).child("tenLoai").getValue(String.class);
                        String ngaythamgiaFromDB = snapshot.child(phone).child("ngaythamgia").getValue(String.class);
                        boolean hoatdong = snapshot.child(phone).child("hoatdong").getValue(Boolean.class);
                        MainActivity.dadangnhap = true;
                        MainActivity.hoten = hotenFromDB;
                        MainActivity.sodienthoai = sodienthoaiFromDB;
                        MainActivity.diachi = diachiFromDB;
                        MainActivity.ngaysinh = ngaysinhFromDB;
                        MainActivity.gioitinh = gioitinhFromDB;
                        MainActivity.tenLoai = tenLoaiFromDB;
                        MainActivity.ngaythamgia = ngaythamgiaFromDB;
                        if(hoatdong == true){
                            Toast.makeText(getContext(),"Đăng Nhập Thành Công",Toast.LENGTH_LONG).show();
                            getActivity().finish();
                        }
                        else {
                            AlertDialog alertDialog   = new AlertDialog.Builder(getActivity()).create();
                            alertDialog.setTitle("Thông báo");
                            alertDialog.setMessage("Tài khoản hiện đã tạm khóa.");
                            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    });
                            alertDialog.show();
                        }
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
