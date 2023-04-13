package com.example.appbanhang.DeTai;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appbanhang.Database.TheLoaiDAO;
import com.example.appbanhang.R;

public class TheLoaiDetailActivity extends AppCompatActivity {
    EditText tentl, vitri;
    TheLoaiDAO theLoaiDAO;
    String strMaTL, strTenTL, srtvitri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_loai_detail);
        setTitle("Thông tin thể Loại");

        tentl = findViewById(R.id.edtentl);
        vitri = findViewById(R.id.edtenvt);

        theLoaiDAO = new TheLoaiDAO(this);
        Intent i = getIntent();
        Bundle b = i.getExtras();
        strMaTL = b.getString("MATHELOAI");
        strTenTL = b.getString("TENTHELOAI");
        srtvitri = b.getString("VITRI");
        tentl.setText(strTenTL);
        vitri.setText(srtvitri);
    }

    public void updatetl(View view) {
        if (theLoaiDAO.updateinfoTheLoai(strMaTL, tentl.getText().toString(), Integer.parseInt(vitri.getText().toString())) > 0) {
            Toast.makeText(this, "Đổi thành công", Toast.LENGTH_SHORT).show();

        }

    }
}
