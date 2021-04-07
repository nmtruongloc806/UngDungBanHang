package com.example.appbanhang;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.Toolbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;

import com.example.appbanhang.models.SanPham;
import com.example.appbanhang.models.ThuongHieu;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class SeachView extends AppCompatActivity {
    ImageButton imb;
    ListView listView;
    EditText txtSearch;
    DatabaseReference reference;
    ArrayList<String> arrayList = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;
    ArrayList<SanPham> sanpham = new ArrayList<SanPham>();
    sanPhamAdapter adapter;
    public ArrayList<SanPham> list = new ArrayList<SanPham>();

    @RequiresApi(api = Build.VERSION_CODES.M)
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchview);
        listView = findViewById(R.id.listview);
        imb =  findViewById(R.id.imgb);
        imb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Intent intent = getIntent();
        EditText editText = (EditText) findViewById(R.id.txtSearch);
        editText.requestFocus();
        if (savedInstanceState != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
        } else {
            InputMethodManager imm = (InputMethodManager) getSystemService(
                    Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
        }
//        arrayAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,android.R.id.text1,listViewItems);
//        listView.setAdapter(arrayAdapter);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                (SeachView.this).arrayAdapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        reference = FirebaseDatabase.getInstance().getReference().child("danhmuc");
        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String ten = snapshot.child("tenDM").getValue(String.class);
                arrayList.add(ten);
                arrayAdapter = new ArrayAdapter<String>(SeachView.this, android.R.layout.simple_list_item_1, arrayList);
                listView.setAdapter(arrayAdapter);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void DataFromFirebaseListener() {
        reference.addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    // TODO: handle the post
                    String key = ds.getKey();
                    String hinhsp = ds.child("hinhSP").getValue(String.class);
                    String tensp = ds.child("tenSP").getValue(String.class);
                    int giasp = ds.child("giaSP").getValue(Integer.class);
                    String tenth = ds.child("tenTH").getValue(String.class);
                    String motasp = ds.child("motaSP").getValue(String.class);
                    String giaSPStr = ds.child("giaSPStr").getValue(String.class);
                    int idTH = ds.child("idTH").getValue(Integer.class);

                    AtomicBoolean isDaTonTai = new AtomicBoolean(false);
                    sanpham.forEach(sanpham -> {
                        if (sanpham.getID() == key) {
                            isDaTonTai.set(true);
                        }
                    });
                    if (isDaTonTai.get() == false) {
                        SanPham sp = new SanPham(key, tensp, hinhsp, giasp, tenth, motasp, idTH, giaSPStr,false, 0,0);
                        if (HomePage.ten.equals(tenth)) {
                            list.add(sp);
                        }
                    }
                }
                adapter.notifyDataSetChanged();
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                // ...
            }
        });
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
}
