package com.example.appbanhang;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class FragmentDangKy extends Fragment {
    EditText edthoten, edtsodienthoai, edtmatkhau, edtdiachi, edtngaysinh;
    Button btnDangKy;
    CheckBox cbnam,cbnu;
    final Calendar myCalendar = Calendar.getInstance();
    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_dang_ky,container,false);
        edthoten = view.findViewById(R.id.edthoten);
        edtsodienthoai = view.findViewById(R.id.edtsodienthoai);
        edtmatkhau = view.findViewById(R.id.edtmatkhau);
        edtdiachi = view.findViewById(R.id.edtdiachi);
        edtngaysinh = view.findViewById(R.id.edtngaysinh);
        cbnam = view.findViewById(R.id.cbnam);
        cbnu = view.findViewById(R.id.cbnu);
        btnDangKy = view.findViewById(R.id.btnDangKy);

        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, month);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }
        };
        edtngaysinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getContext(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        cbnam.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    cbnu.setChecked(false);
                }
            }
        });
        cbnu.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    cbnam.setChecked(false);
                }
            }
        });
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("users");

                //get all the values
                String hoten = edthoten.getText().toString();
                String sodienthoai = edtsodienthoai.getText().toString();
                String matkhau = edtmatkhau.getText().toString();
                String diachi = edtdiachi.getText().toString();
                String ngaysinh = edtngaysinh.getText().toString();
                String gioitinh = "";
                if(cbnam.isChecked()){
                    gioitinh = cbnam.getText().toString();
                }else if(cbnu.isChecked()){
                    gioitinh = cbnu.getText().toString();
                }
                User user = new User(hoten, sodienthoai, matkhau, diachi, ngaysinh, gioitinh);

                reference.child(sodienthoai).setValue(user);

                Toast.makeText(view.getContext(),"Đăng Ký Thành Công",Toast.LENGTH_LONG).show();
                getActivity().finish();

            }
        });
        return view;
    }

    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        edtngaysinh.setText(sdf.format(myCalendar.getTime()));
    }
};
