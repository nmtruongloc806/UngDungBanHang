package com.example.appbanhang;


import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.appbanhang.models.SanPham;
import com.example.appbanhang.models.ThuongHieu;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class SanPhamPage extends AppCompatActivity {
    public static String tensp;
    GridView gridView;
    TextView txttensp;
    DatabaseReference reference;
    ArrayList<SanPham> sanpham= new ArrayList<SanPham>();
    public ArrayList<SanPham> list = new ArrayList<SanPham>();
    sanPhamAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sanpham);
        gridView = findViewById(R.id.grid_view_sanpham);
        txttensp = findViewById(R.id.txttensp);
        reference = FirebaseDatabase.getInstance().getReference().child("sanpham");
        //Intent intent1 = getIntent();
        DataFromFirebaseListener();
        adapter = new sanPhamAdapter(SanPhamPage.this,list);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                reference = FirebaseDatabase.getInstance().getReference().child("sanpham");
          SanPham sanPhamItem = list.get(position);
          tensp = sanPhamItem.getTenSP();
          MainActivity.checkFavorite = true;
          MainActivity.TEN = sanPhamItem.getTenSP();
          MainActivity.HINH = sanPhamItem.getHinhSP();
          MainActivity.GIA = sanPhamItem.getGiaSP();
          Intent intent = new Intent(SanPhamPage.this,chiTietSanPham.class);
            intent.putExtra("ten",sanPhamItem.getTenSP());
            intent.putExtra("hinh",sanPhamItem.getHinhSP());
            intent.putExtra("gia",sanPhamItem.getGiaSP());
            intent.putExtra("mota",sanPhamItem.getMotaSP());
            intent.putExtra("tenth",sanPhamItem.getTenTH());
            startActivity(intent);
            }
        });
    }
    public void DataFromFirebaseListener() {
        reference.addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    // TODO: handle the post
                    String key = ds.getKey();
                    String hinhsp = ds.child("hinhSP").getValue(String.class);
                    String tensp = ds.child("tenSP").getValue(String.class);
                    int giasp = ds.child("giaSP").getValue(Integer.class);
                    String tenth = ds.child("tenTH").getValue(String.class);
                    String motasp = ds.child("motaSP").getValue(String.class);



                    AtomicBoolean isDaTonTai = new AtomicBoolean(false);
                    sanpham.forEach(sanpham -> {
                        if (sanpham.getID() == key) {
                            isDaTonTai.set(true);
                        }
                    });
                    if (isDaTonTai.get() == false) {
                        SanPham sp = new SanPham(key, tensp, hinhsp, giasp, tenth,motasp);
                        if (HomePage.ten.equals(tenth)) {
                            list.add(sp);
                        }
                    }
                }
                adapter.notifyDataSetChanged();
                }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
