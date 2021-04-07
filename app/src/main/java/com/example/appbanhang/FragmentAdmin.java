package com.example.appbanhang;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class FragmentAdmin  extends Fragment {
    @Nullable
    RelativeLayout rlttcn;
    TextView txtQLDH,txtQLTK,txtQLDT,txtCNSP,txthoten,txtsodienthoai;
    Button btnDangXuat;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmentadmin,container,false);
        rlttcn = view.findViewById(R.id.rlttcn);
        txtQLDH = view.findViewById(R.id.txtQLDH);
        txtQLTK = view.findViewById(R.id.txtQLTK);
        txtCNSP = view.findViewById(R.id.txtCNSP);
        txtQLDT = view.findViewById(R.id.txtQLDT);
        txthoten = view.findViewById(R.id.txthoten);
        txtsodienthoai = view.findViewById(R.id.txtsodienthoai);
        btnDangXuat = view.findViewById(R.id.btnDangXuat);
        txthoten.setText(MainActivity.hoten);
        txtsodienthoai.setText(MainActivity.sodienthoai);
        rlttcn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),FormTTCN.class);
                startActivity(intent);
            }
        });
        txtQLTK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), QLND.class);
                startActivity(intent);
            }
        });
        return view;
    }
}
