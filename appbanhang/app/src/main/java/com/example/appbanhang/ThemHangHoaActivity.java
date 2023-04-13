package com.example.appbanhang;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appbanhang.Activity.LoginActivity;
import com.example.appbanhang.Database.HanghoaDAO;
import com.example.appbanhang.Database.TheLoaiDAO;
import com.example.appbanhang.Model.Hanghoa;
import com.example.appbanhang.Model.TheLoai;

import java.util.ArrayList;
import java.util.List;

public class ThemHangHoaActivity extends AppCompatActivity {
    HanghoaDAO hanghoaDAO;
    TheLoaiDAO theLoaiDAO;
    Spinner spnTheLoai;
    EditText edMaHangHoa, edTenHangHoa, edNXB, edTacGia, edGiaBia, edSoLuong;
    String maTheLoai = "";
    List<TheLoai> listTheLoai = new ArrayList<>();
    Button btnaddbook,btnhuy,btnshow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_hanghoa);
        setTitle("THÊM HÀNG HÓA");
        spnTheLoai = (Spinner) findViewById(R.id.spnTheLoai);
        getTheLoai();
        btnaddbook = (Button) findViewById ( R.id.btnAddBook );
        btnhuy = (Button) findViewById ( R.id .btnCancelBook);
        btnshow=(Button) findViewById ( R.id.btnShowBook );
        edMaHangHoa = (EditText) findViewById(R.id.edMaSach);
        edTenHangHoa = (EditText) findViewById(R.id.edTenSach);
        edNXB = (EditText) findViewById(R.id.edNXB);
        edTacGia = (EditText) findViewById(R.id.edTacGia);
        edGiaBia = (EditText) findViewById(R.id.edGiaBia);
        edSoLuong = (EditText) findViewById(R.id.edSoLuong);
        spnTheLoai.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maTheLoai = listTheLoai.get(spnTheLoai.getSelectedItemPosition()).getMaTheLoai();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        //load data into form
        Intent in = getIntent();
        Bundle b = in.getExtras();
        if (b != null) {
            edMaHangHoa.setText(b.getString("MAHANGHOA"));
            String maTheLoai = b.getString("MATHELOAI");
            edTenHangHoa.setText(b.getString("TENHANGHOA"));
            edNXB.setText(b.getString("NXB"));
            edTacGia.setText(b.getString("TACGIA"));
            edGiaBia.setText(b.getString("GIABIA"));
            edSoLuong.setText(b.getString("SOLUONG"));
            spnTheLoai.setSelection(checkPositionTheLoai(maTheLoai));
        }
        kt();
    }

    private void kt() {
        if(LoginActivity.strUser.equals("admin") && LoginActivity.strPass.equals("admin")){
            btnshow.setVisibility (View.VISIBLE );
            btnaddbook.setVisibility (View.VISIBLE );
            btnhuy.setVisibility ( View.VISIBLE);
            btnshow.setOnClickListener ( new View.OnClickListener () {
                @Override
                public void onClick(View view) {
                    showBook ();
                }
            } );
            btnaddbook.setOnClickListener ( new View.OnClickListener () {
                @Override
                public void onClick(View view) {
                    addBook ();
                }
            } );
        }
        else{
            btnhuy.setVisibility ( View.INVISIBLE );
            btnshow.setVisibility (View.INVISIBLE );
            btnaddbook.setVisibility (View.INVISIBLE );
        }
    }

    public void showSpinner(View view) {
        hanghoaDAO = new HanghoaDAO(ThemHangHoaActivity.this);
        hanghoaDAO.getAllSach();
    }

    public void getTheLoai() {
        theLoaiDAO = new TheLoaiDAO(ThemHangHoaActivity.this);
        listTheLoai = theLoaiDAO.getAllTheLoai();
        ArrayAdapter<TheLoai> dataAdapter = new ArrayAdapter<TheLoai>(this, android.R.layout.simple_spinner_item, listTheLoai);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnTheLoai.setAdapter(dataAdapter);
    }

    public void addBook() {
        hanghoaDAO = new HanghoaDAO(ThemHangHoaActivity.this);
        Hanghoa hanghoa = new Hanghoa(edMaHangHoa.getText().toString(), maTheLoai, edTenHangHoa.getText().toString(), edTacGia.getText().toString(), edNXB.getText().toString(), Double.parseDouble(edGiaBia.getText().toString()), Integer.parseInt(edSoLuong.getText().toString()));
        try {
            if (hanghoaDAO.inserSach(hanghoa) > 0) {
                Toast.makeText(getApplicationContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception ex) {
            Log.e("Error", ex.toString());
        }
    }

    public void showBook() {
        finish();
    }

    public int checkPositionTheLoai(String strTheLoai) {
        for (int i = 0; i < listTheLoai.size(); i++) {
            if (strTheLoai.equals(listTheLoai.get(i).getMaTheLoai())) {
                return i;
            }
        }
        return 0;
    }



}
