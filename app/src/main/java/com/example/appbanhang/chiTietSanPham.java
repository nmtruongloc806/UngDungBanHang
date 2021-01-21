package com.example.appbanhang;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class chiTietSanPham extends AppCompatActivity {
    ImageView image_sanpham;
    TextView txttensp, txtgiasp, txtmotasp;
    DatabaseReference reference;
    Button btnThemGioHang;
    static chiTietSanPham INSTANCE;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chitietsanpham);
        image_sanpham = findViewById(R.id.image_sp);
        txttensp = findViewById(R.id.txttenSP);
        txtgiasp = findViewById(R.id.txtgiaSP);
        txtmotasp = findViewById(R.id.txtmotaSP);
        btnThemGioHang = findViewById(R.id.btnthemGioHang);
        btnThemGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.themVaoGioHang = true;
                Toast.makeText(chiTietSanPham.this,"thêm giỏ hàng thành công",Toast.LENGTH_LONG).show();
            }
        });
        reference = FirebaseDatabase.getInstance().getReference("sanpham");
        image_sanpham.buildDrawingCache();

        Bundle extras = new Bundle();
        Intent intent = getIntent();
        String ten = intent.getStringExtra("ten");
        String hinh = intent.getStringExtra("hinh");
        int gia = intent.getIntExtra("gia",0);
        String mota = intent.getStringExtra("mota");
        String tenth = intent.getStringExtra("tenth");

        MainActivity.HINH = hinh;
        MainActivity.GIA = gia;
        MainActivity.TEN = ten;

        if(SanPhamPage.tensp.equals(ten)) {
            Picasso.with(chiTietSanPham.this).load(hinh).into(image_sanpham);
            txttensp.setText(ten);
            txtmotasp.setText(mota);
            //txtgiasp.setText(gia);
        }
    }
    //    private void showCTSP(){
//        Intent intent = getIntent();
//     String ten = intent.getStringExtra("ten");
//    String hinh = intent.getStringExtra("hinh");
//    int gia = intent.getIntExtra("gia",0);
//    String mota = intent.getStringExtra("mota");
//    String tenth = intent.getStringExtra("tenth");
//        if(SanPhamPage.tensp.equals(ten)) {
//            image_sanpham.setImageURI(Uri.parse(hinh));
//            txttensp.setText(ten);
//            txtmotasp.setText(mota);
//            txtgiasp.setText(gia);
//        }else {
//            return;
//        }
//    }
}
