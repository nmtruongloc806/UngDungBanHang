package com.example.appbanhang;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.appbanhang.models.ChiTietHoaDon;
import com.example.appbanhang.models.HoaDon;
import com.example.appbanhang.models.SanPham;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class giohangthongtin extends Fragment {
    EditText edthoten,edtsodienthoai,edtdiachi;
    Button btnHoanTat;
    SanPham sanPham;
    HoaDon hoaDon;
    ChiTietHoaDon chiTietHoaDon;
    DatabaseReference referenceHD;
    DatabaseReference referenceCTHD;
    final Calendar myCalendar= Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.giohangthongtin,container,false);
        edthoten = view.findViewById(R.id.edthoten);
        edtsodienthoai = view.findViewById(R.id.edtsodienthoai);
        edtdiachi = view.findViewById(R.id.edtDiaChi);
        btnHoanTat = view.findViewById(R.id.btnHoanTat);
        referenceHD = FirebaseDatabase.getInstance().getReference().child("hoadon");
        referenceCTHD = FirebaseDatabase.getInstance().getReference().child("chitiethoadon");
        edthoten.setText(MainActivity.hoten);
        edtsodienthoai.setText(MainActivity.sodienthoai);
        edtdiachi.setText(MainActivity.diachi);

        String hoten = edthoten.getText().toString();
        String sodienthoai = edtsodienthoai.getText().toString();
        String diachi = edtdiachi.getText().toString();

        btnHoanTat.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                if (!validateHoTen() | !validateSoDienThoai() | !validateDiaChi()) {
                    return;
                }else {
                    String keyHD = referenceHD.push().getKey();
                    String ngaytaodon = sdf.format(myCalendar.getTime());
                    String ngayhoanthanh = "";
                    String trangthai = "Đặt hàng";
                    HoaDon hoaDon = new HoaDon(keyHD,gioHangTinhTien.TT,ngaytaodon,ngayhoanthanh,hoten,sodienthoai,diachi,trangthai,MainActivity.id);
                    referenceHD.child(keyHD).setValue(hoaDon);
                    Toast.makeText(view.getContext(), "Tạo đơn hàng thành công", Toast.LENGTH_LONG).show();
//                    MainActivity.listGH.forEach(sanPham -> {
//                        String keyCTHD = referenceCTHD.push().getKey();
//                        int gia = sanPham.getGiaSP();
//                        String hinh = sanPham.getHinhSP();
//                        String tensp = sanPham.getTenSP();
//                    });
                }
            }
        });
        return view;
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
}
