package com.example.appbanhang.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Database;

import com.example.appbanhang.R;
import com.example.appbanhang.Database.DatabaseHelper;

public class DangkyActivity extends AppCompatActivity {
    EditText edttaikhoan, edtmatkhau, edthoten, edtgioitinh;
    Button btndangky, btnthoat;
    DatabaseHelper database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dang_ky);
        setTitle("Đăng ký");
        Anhxa();
        btndangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dangkytk();
            }
        });
        btnthoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnthoat();
            }
        });
    }

    private void btnthoat() {
        Intent intent = new Intent(DangkyActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    private void Anhxa() {
        edttaikhoan = (EditText) findViewById(R.id.edttaikhoandk);
        edtmatkhau = (EditText) findViewById(R.id.edtmatkhaudk);
        edthoten = (EditText) findViewById(R.id.edthotendk);
        edtgioitinh = (EditText) findViewById(R.id.edtgioitinhdk);
        btndangky = findViewById(R.id.btndangkytk);
        btnthoat = findViewById(R.id.btnthoatdk);
        database = new DatabaseHelper(this);
    }

    private void Dangkytk() {
        String taikhoan = edttaikhoan.getText().toString();
        String matkhau = edtmatkhau.getText().toString();
        String hoten = edthoten.getText().toString();
        String gioitinh = edtgioitinh.getText().toString();
        if (database.ktDangky(taikhoan)) {
            Toast.makeText(DangkyActivity.this, "Tài khoản đã tồn tại!", Toast.LENGTH_SHORT).show();
        }else if(taikhoan.equals("") || matkhau.equals("")|| hoten.equals("")||gioitinh.equals("")){
            Toast.makeText(this, "Hãy nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(DangkyActivity.this, "Đăng ký tài khoản thành công!", Toast.LENGTH_SHORT).show();
            database.Dangky(taikhoan, matkhau, hoten, gioitinh);
            Intent intent = new Intent(DangkyActivity.this, LoginActivity.class);
            startActivity(intent);
        }
    }
}

