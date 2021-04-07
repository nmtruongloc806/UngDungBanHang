package com.example.appbanhang;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class chiTietUser extends AppCompatActivity {
    EditText edthoten, edtsodienthoai, edtdiachi, edtngaysinh;
    Button btnKhoaTaiKhoan,btnMoKhoaTaiKhoan;
    ImageView imback;
    CheckBox cbNam,cbNu;
    private DatabaseReference reference;
    String hoten,ngaysinh,diachi,sodienthoai,gioitinh;
    boolean hoatdong;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user);
        anhxa();
        Intent intent = getIntent();
        cbNam.setEnabled(false);
        cbNu.setEnabled(false);
        if(intent.hasExtra("hoten") && intent.hasExtra("sodienthoai") && intent.hasExtra("diachi") && intent.hasExtra("ngaysinh") && intent.hasExtra("gioitinh"))
        {
             ngaysinh = intent.getStringExtra("ngaysinh");
             hoten = intent.getStringExtra("hoten");
             sodienthoai = intent.getStringExtra("sodienthoai");
             diachi = intent.getStringExtra("diachi");
             hoatdong = intent.getBooleanExtra("hoatdong",true);
             gioitinh = intent.getStringExtra("gioitinh");
            edthoten.setText(hoten);
            edtsodienthoai.setText(sodienthoai);
            edtngaysinh.setText(ngaysinh);
            edtdiachi.setText(diachi);
            if(gioitinh.equals("Nam")){
                cbNam.setChecked(true);
            }else{
                cbNu.setChecked(true);
            }
        }
        imback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnKhoaTaiKhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reference = FirebaseDatabase.getInstance().getReference().child("taikhoan");
                Query query = reference.orderByChild("sodienthoai").equalTo(sodienthoai);

                query.addValueEventListener(new ValueEventListener() {
                    @SuppressLint("ResourceAsColor")
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            reference.child(sodienthoai).child("hoatdong").setValue(false);
                            btnKhoaTaiKhoan.setEnabled(false);
                            btnKhoaTaiKhoan.setBackgroundColor(getResources().getColor(R.color.grey));
                            Toast.makeText(chiTietUser.this, "Đã khóa tài khoản", Toast.LENGTH_SHORT).show();
                            query.removeEventListener(this);
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
        btnMoKhoaTaiKhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reference = FirebaseDatabase.getInstance().getReference().child("taikhoan");
                Query query = reference.orderByChild("sodienthoai").equalTo(sodienthoai);
                query.addValueEventListener(new ValueEventListener() {
                    @SuppressLint("ResourceAsColor")
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            reference.child(sodienthoai).child("hoatdong").setValue(true);
                            btnKhoaTaiKhoan.setEnabled(true);
                            btnKhoaTaiKhoan.setBackgroundColor(getResources().getColor(R.color.aqua));
                            Toast.makeText(chiTietUser.this, "Đã mở khóa tài khoản", Toast.LENGTH_SHORT).show();
                            query.removeEventListener(this);
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }
    private void anhxa(){
        edthoten = findViewById(R.id.edthoten);
        edtsodienthoai = findViewById(R.id.edtsodienthoai);
        edtdiachi = findViewById(R.id.edtdiachi);
        edtngaysinh = findViewById(R.id.edtngaysinh);
        cbNam = findViewById(R.id.cbnam);
        cbNu = findViewById(R.id.cbnu);
        btnKhoaTaiKhoan = findViewById(R.id.btnKhoaTaiKhoan);
        btnMoKhoaTaiKhoan = findViewById(R.id.btnMoTaiKhoan);
        imback = findViewById(R.id.imgBack);
    }

    @Override
    protected void onStart() {
        super.onStart();
        getSupportActionBar().hide();
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.aqua));
        }
    }
}
