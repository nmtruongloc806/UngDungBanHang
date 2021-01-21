package com.example.appbanhang;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

public class giohangthongtin extends Fragment {
    EditText edthoten,edtsodienthoai,edtdiachi;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.giohangthongtin,container,false);
        edthoten = view.findViewById(R.id.edthoten);
        edtsodienthoai = view.findViewById(R.id.edtsodienthoai);
        edtdiachi = view.findViewById(R.id.edtDiaChi);

        edthoten.setText(MainActivity.hoten);
        edtsodienthoai.setText(MainActivity.sodienthoai);
        edtdiachi.setText(MainActivity.diachi);
        return view;
    }
}
