package com.example.appbanhang;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentChuaDangNhap extends Fragment {
    TextView txtDNDK,txtXemTT;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmentchuadangnhap,container,false);
        txtDNDK = (TextView) view.findViewById(R.id.txtDNDK);
        txtXemTT = (TextView) view.findViewById(R.id.txtXemTT);
        txtDNDK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),FormDNDK.class);
                startActivity(intent);
            }
        });
        txtXemTT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),FormDNDK.class);
                startActivity(intent);
            }
        });
        return view;
    }
}
