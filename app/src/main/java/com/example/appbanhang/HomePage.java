package com.example.appbanhang;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.appbanhang.models.SanPham;
import com.example.appbanhang.models.ThuongHieu;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class HomePage extends Fragment {
    public HomePage(){

    }
    public static String ten;
    TextView txtSearch;
    GridView gridView;
    ArrayList<ThuongHieu> RecipeImageUrl= new ArrayList<ThuongHieu>();
    DatabaseReference reference;
    ThuongHieu thuongHieu;
    CustomAdapter customAdapter;
    ArrayAdapter<String> arrayAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home, container , false);
        gridView = view.findViewById(R.id.grid_view);
        txtSearch = view.findViewById(R.id.txtSearch);
        reference = FirebaseDatabase.getInstance().getReference().child("thuonghieu");
        /// lắng nghe sự thay đổi của data trên firebase
        DataFromFirebaseListener();
        customAdapter = new CustomAdapter(getActivity(),RecipeImageUrl);
        gridView.setAdapter(customAdapter);
        txtSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),SeachView.class);
                startActivity(intent);
            }
        });

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               // Toast.makeText(getActivity(),"You clicked" + position,Toast.LENGTH_LONG).show();
                reference = FirebaseDatabase.getInstance().getReference("thuonghieu");
                ThuongHieu thuongHieuItem = RecipeImageUrl.get(position);
                ten = thuongHieuItem.getTenTH();
                Intent intent = new Intent(getActivity(),SanPhamPage.class);
                startActivity(intent);
            }

        });
        return view;
    }
    public void DataFromFirebaseListener() {
        reference.addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    // TODO: handle the post
                    String key = ds.getKey();
                    String hinhTH = ds.child("hinhTH").getValue(String.class);
                    String tenTH = ds.child("tenTH").getValue(String.class);
                    AtomicBoolean isDaTonTai = new AtomicBoolean(false);
                    RecipeImageUrl.forEach(thuongHieu -> {
                        if(thuongHieu.getID() == Integer.parseInt(key)){
                            isDaTonTai.set(true);
                        }
                    });
                    if(isDaTonTai.get() == false){
                        ThuongHieu thuongHieu = new ThuongHieu(Integer.parseInt(key) , tenTH, hinhTH);
                        RecipeImageUrl.add(thuongHieu);
                    }
                }
                customAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                // ...
            }
        });
    }
}
