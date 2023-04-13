package com.example.appbanhang.Them;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appbanhang.Database.TheLoaiDAO;
import com.example.appbanhang.Model.TheLoai;
import com.example.appbanhang.R;

public class TheLoaiActivity extends AppCompatActivity {

    Button btThemTheLoai;
    TheLoaiDAO theLoaiDAO;
    EditText editMaTL, edittenTheLoai, editViTri, editMoTa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_loai);
        setTitle("Thêm Người dùng");
        btThemTheLoai = findViewById(R.id.btthemTheLoai);
        editMaTL = findViewById(R.id.maTheLoai);
        edittenTheLoai = findViewById(R.id.tenTheLoai);
        editViTri = findViewById(R.id.vitriTheLoai);
        editMoTa = findViewById(R.id.motaTheLoai);

    }

    public void showtheloai(View view)   {
        finish();
    }

    public void HuyTheLoai(View view) {
    }

    public void them(View view) {
        theLoaiDAO = new TheLoaiDAO(TheLoaiActivity.this);
        TheLoai user = new TheLoai(editMaTL.getText().toString(), edittenTheLoai.getText().toString(),
                editMoTa.getText().toString(), Integer.parseInt(editViTri.getText().toString()));
        try {
            if (validateform() > 0) {
                if (theLoaiDAO.inserTheLoai(user) > 0) {
                    Toast.makeText(this, "Thêm Thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                }

            }

        } catch (Exception e) {
            Log.e("Error", e.toString());
        }
    }

    private int validateform() {
        int check = 1;
        if (editMaTL.getText().length() == 0 || edittenTheLoai.getText().length() == 0
                || editMoTa.getText().length() == 0 || editViTri.getText().length() == 0 ){
            Toast.makeText(this, "Bạn phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            check = -1;
        }
        return check;
    }
}
