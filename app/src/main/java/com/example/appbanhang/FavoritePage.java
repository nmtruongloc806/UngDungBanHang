package com.example.appbanhang;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class FavoritePage extends Fragment {
    Toolbar toolbar;
    public FavoritePage(){

    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.favorite, container, false);
        toolbar = (Toolbar) view.findViewById(R.id.toolbarfavorite);
        if(MainActivity.listYT.size() == 0) {
            DanhSachYeuThichTrong Fragment = new DanhSachYeuThichTrong();
            FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.framelayoutFavorite, Fragment);
            fragmentTransaction.commit();
        }
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if(MainActivity.listYT.size() > 0) {
            ListFavorite Fragment2 = new ListFavorite();
            FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.framelayoutFavorite, Fragment2);
            fragmentTransaction.commit();
        }
    }
}
