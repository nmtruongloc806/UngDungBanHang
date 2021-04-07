package com.example.appbanhang;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.appbanhang.models.SanPham;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.concurrent.atomic.AtomicBoolean;

public class chiTietSanPham extends AppCompatActivity {
    ImageView image_sanpham;
    ImageButton imBack;
    TextView txttensp, txtgiasp, txtmotasp;
    DatabaseReference reference;
    Button btnThemGioHang;
    static chiTietSanPham INSTANCE;
    SanPham sanPhamSelected;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chitietsanpham);
        image_sanpham = findViewById(R.id.image_sp);
        txttensp = findViewById(R.id.txttenSP);
        txtgiasp = findViewById(R.id.txtgiaSP);
        txtmotasp = findViewById(R.id.txtmotaSP);
        imBack = findViewById(R.id.imgBack);
        imBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnThemGioHang = findViewById(R.id.btnthemGioHang);
        btnThemGioHang.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                MainActivity.listGH.forEach(sanphamGH -> {
                    if (sanphamGH.getID().equals(sanPhamSelected.getID())) {
                        sanPhamSelected = sanphamGH;
                    }
                });
                if( sanPhamSelected.getSoluong() == 0) {
                    sanPhamSelected.setSoluong(sanPhamSelected.getSoluong() + 1);
                    sanPhamSelected.setTongtien(sanPhamSelected.getSoluong()*sanPhamSelected.getGiaSP());
                    MainActivity.listGH.add(sanPhamSelected);

                    Toast.makeText(chiTietSanPham.this, "thêm giỏ hàng thành công", Toast.LENGTH_SHORT).show();
                }
                else {
                    sanPhamSelected.setSoluong(sanPhamSelected.getSoluong() + 1);
                    sanPhamSelected.setTongtien(sanPhamSelected.getSoluong()*sanPhamSelected.getGiaSP());
                    Toast.makeText(chiTietSanPham.this, "thêm giỏ hàng thành công", Toast.LENGTH_SHORT).show();
                }
            }

        });

        reference = FirebaseDatabase.getInstance().getReference("sanpham");
        image_sanpham.buildDrawingCache();

        Bundle extras = new Bundle();
        Intent intent = getIntent();
        String ten = intent.getStringExtra("ten");
        String id = intent.getStringExtra("id");
        String hinh = intent.getStringExtra("hinh");
        int gia = intent.getIntExtra("gia",0);
        String mota = intent.getStringExtra("mota");
        String tenth = intent.getStringExtra("tenth");
        String giaSPStr = intent.getStringExtra("giastr");

        sanPhamSelected = new SanPham(id,ten,hinh,gia,"",mota,0,giaSPStr , false, 0,0);

        MainActivity.HINH = hinh;
        MainActivity.GIA = gia;
        MainActivity.TEN = ten;

        if(SanPhamPage.tensp.equals(ten)) {
            Picasso.with(chiTietSanPham.this).load(hinh).into(image_sanpham);
            txttensp.setText(ten);
            txtmotasp.setText(mota);
            txtgiasp.setText("đ"+String.valueOf(gia));
        }
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
