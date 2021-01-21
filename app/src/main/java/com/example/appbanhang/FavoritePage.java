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
//        if(savedInstanceState == null && MainActivity.checkFavorite == false) {
//            DanhSachYeuThichTrong Fragment = new DanhSachYeuThichTrong();
//            FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
//            fragmentTransaction.add(R.id.framelayoutFavorite, Fragment);
//            fragmentTransaction.commit();
//        }else {
            ListFavorite Fragment2 = new ListFavorite();
            FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.framelayoutFavorite, Fragment2);
            //fragmentTransaction.addToBackStack("backStateName");
            fragmentTransaction.commit();
        //}
        return view;
    }
}
