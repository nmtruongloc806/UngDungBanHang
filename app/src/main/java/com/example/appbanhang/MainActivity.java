package com.example.appbanhang;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.appbanhang.models.SanPham;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    ViewPager viewPager;
    public static  ArrayList<SanPham> listGH = new ArrayList<>();
    public static  ArrayList<SanPham> listYT = new ArrayList<>();
    public static Boolean dadangnhap = false;
    public static String hoten;
    public static String sodienthoai;
    public static String matkhau;
    public static String diachi;
    public static String ngaysinh;
    public static String gioitinh;
    public static Boolean checkFavorite = false;
    public static Boolean themVaoGioHang = false;
    public static String HINH;
    public static String TEN;
    public static int GIA;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        viewPager = findViewById(R.id.viewpapermain);
        setUpViewpaper();
        luuData();
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.ic_home:
                            viewPager.setCurrentItem(0);
                        break;
                    case R.id.ic_favorite:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.ic_giohang:
                        viewPager.setCurrentItem(2);
                        break;
                    case R.id.ic_me:
                        viewPager.setCurrentItem(3);
                        break;
                    default:
                        viewPager.setCurrentItem(0);
                        break;
                }
                return true;
            }
        });
    }
    private void setUpViewpaper() {
        ViewPaperAdapter viewPaperAdapter = new ViewPaperAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(viewPaperAdapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                switch (position){
                    case 0:
                        bottomNavigationView.getMenu().findItem(R.id.ic_home).setChecked(true);
                        break;
                    case 1:
                        bottomNavigationView.getMenu().findItem(R.id.ic_favorite).setChecked(true);
                        break;
                    case 2:
                        bottomNavigationView.getMenu().findItem(R.id.ic_giohang).setChecked(true);
                        break;
                    case 3:
                        bottomNavigationView.getMenu().findItem(R.id.ic_me).setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void luuData(){
        Intent intent = getIntent();
        hoten = intent.getStringExtra("name");
        Log.d("haha", "onCreate: "+hoten);
        sodienthoai = intent.getStringExtra("sodienthoai");
        matkhau = intent.getStringExtra("matkhau");
        diachi = intent.getStringExtra("diachi");
        ngaysinh = intent.getStringExtra("ngaysinh");
        gioitinh = intent.getStringExtra("gioitinh");
    }

}