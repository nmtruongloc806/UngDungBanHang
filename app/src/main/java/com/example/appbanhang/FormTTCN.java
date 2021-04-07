package com.example.appbanhang;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.appbanhang.models.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class FormTTCN extends AppCompatActivity {
    LinearLayout formdoimatkhau;
    ImageButton imBack;
    EditText edthoten, edtsodienthoai, edtdiachi, edtngaysinh, edtmkcu,edtmkmoi,edtxnmk;
    CheckBox cbNam,cbNu,cbDoiMK;
    Button btnLuuTD;
    DatabaseReference reference;
    final Calendar myCalendar = Calendar.getInstance();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.formttcn);
        anhxa();
        imBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, month);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateTime();
            }
        };
        edtngaysinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(FormTTCN.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        updateCheckGT();
        updateCheckChangePassword();
        updateTime();
        updateData();
        btnLuuTD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatatoFirebaseDatabase();
                finish();
                Toast.makeText(FormTTCN.this, "Lưu thông tin thành công", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void anhxa(){
        imBack = findViewById(R.id.imgBack);
        edthoten = findViewById(R.id.edthoten);
        edtsodienthoai = findViewById(R.id.edtsodienthoai);
        edtdiachi = findViewById(R.id.edtdiachi);
        edtngaysinh = findViewById(R.id.edtngaysinh);
        cbNam = findViewById(R.id.cbnam);
        cbNu = findViewById(R.id.cbnu);
        cbDoiMK = findViewById(R.id.cbdoimatkhau);
        btnLuuTD = findViewById(R.id.btnLuuTD);
        edtmkcu = findViewById(R.id.edtmkCu);
        edtmkmoi = findViewById(R.id.edtmkMoi);
        edtxnmk = findViewById(R.id.edtmkXacNhanMK);
        formdoimatkhau = findViewById(R.id.formdoimatkhau);
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
    private void updateTime() {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        edtngaysinh.setText(sdf.format(myCalendar.getTime()));

    }
    private void updateCheckGT(){
        cbNam.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    cbNu.setChecked(false);
                }
            }
        });
        cbNu.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    cbNam.setChecked(false);
                }
            }
        });
    }
    private void updateCheckChangePassword(){
        cbDoiMK.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    formdoimatkhau.setVisibility(LinearLayout.VISIBLE);
                }else {
                    formdoimatkhau.setVisibility(LinearLayout.GONE);
                }
            }
        });
    }
    private void updateData(){
        edthoten.setText(MainActivity.hoten);
        edtsodienthoai.setText(MainActivity.sodienthoai);
        edtngaysinh.setText(MainActivity.ngaysinh);
        edtdiachi.setText(MainActivity.diachi);
        if(MainActivity.gioitinh.equals("Nam")){
            cbNam.setChecked(true);
        }else {
            cbNu.setChecked(true);
        }
    }
    private void DatatoFirebaseDatabase(){
        reference = FirebaseDatabase.getInstance().getReference().child("taikhoan");
        Query query = reference.orderByChild("sodienthoai").equalTo(MainActivity.sodienthoai);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()) {
                    MainActivity.hoten = edthoten.getText().toString();
                    MainActivity.sodienthoai = edtsodienthoai.getText().toString();
                    MainActivity.diachi = edtdiachi.getText().toString();
                    MainActivity.ngaysinh = edtngaysinh.getText().toString();
                    reference.child(MainActivity.sodienthoai).child("hoten").setValue(MainActivity.hoten);
                    reference.child(MainActivity.sodienthoai).child("sodienthoai").setValue(MainActivity.sodienthoai);
                    reference.child(MainActivity.sodienthoai).child("diachi").setValue(MainActivity.diachi);
                    reference.child(MainActivity.sodienthoai).child("ngaysinh").setValue(MainActivity.ngaysinh).toString();
                    String gioitinh = "";
                    if (cbNam.isChecked()) {
                        gioitinh = (cbNam.getText().toString());
                        reference.child(MainActivity.sodienthoai).child("gioitinh").setValue(gioitinh);
                    } else if (cbNu.isChecked()) {
                        gioitinh = (cbNu.getText().toString());
                        reference.child(MainActivity.sodienthoai).child("gioitinh").setValue(gioitinh);
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private Boolean validateHoTen (){
        String val = edthoten.getText().toString();

        if(val.isEmpty()){
            edthoten.setError("Vui lòng nhập tên đăng nhập");
            return false;
        }else {
            edthoten.setError(null);
            return true;
        }
    }
    private Boolean validateSoDienThoai (){
        String val = edtsodienthoai.getText().toString();

        if(val.isEmpty()){
            edtsodienthoai.setError("Vui lòng nhập số điện thoại");
            return false;
        }else {
            edtsodienthoai.setError(null);
            return true;
        }
    }
    private Boolean validateDiaChi (){
        String val = edtdiachi.getText().toString();

        if(val.isEmpty()){
            edtdiachi.setError("Vui lòng nhập địa chỉ");
            return false;
        }else {
            edtdiachi.setError(null);
            return true;
        }
    }
    private Boolean validateNgaySinh(){
        String val = edtngaysinh.getText().toString();

        if(val.isEmpty()){
            edtngaysinh.setError("Vui lòng nhập ngày sinh");
            return false;
        }else {
            edtngaysinh.setError(null);
            return true;
        }
    }
}
