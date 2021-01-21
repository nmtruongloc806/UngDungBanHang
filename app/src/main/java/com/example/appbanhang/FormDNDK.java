package com.example.appbanhang;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class FormDNDK extends AppCompatActivity {
    Toolbar toolbar;
    ViewPager viewPager;
    TabLayout tabLayout;
    PageAdapter pageAdapter;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dangnhapvadangky);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        viewPager = (ViewPager) findViewById(R.id.viewpaper);
        //toolbartab = (Toolbar) findViewById(R.id.toolbartab);
        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        Intent intent = this.getIntent();
        toolbar.setNavigationIcon(R.drawable.back_icon);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(getApplicationContext(),MePage.class));
                finish();
            }
        });

        pageAdapter = new PageAdapter(getSupportFragmentManager());
        pageAdapter.addFragment(new FragmentDangNhap(),"Đăng Nhập");
        pageAdapter.addFragment(new FragmentDangKy(),"Đăng Ký");

        viewPager.setAdapter(pageAdapter);

        tabLayout.setupWithViewPager(viewPager);


    }


}
