package com.example.appbanhang;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appbanhang.Adapter.CartAdapter;
import com.example.appbanhang.Database.HanghoaDAO;
import com.example.appbanhang.Database.HoaDonChiTietDAO;
import com.example.appbanhang.Model.Hanghoa;
import com.example.appbanhang.Model.HoaDon;
import com.example.appbanhang.Model.HoaDonChiTiet;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HoaDonChiTietActivity extends AppCompatActivity {
    EditText edMaSach, edMaHoaDon, edSoLuong;
    TextView tvThanhTien;
    HoaDonChiTietDAO hoaDonChiTietDAO;
    HanghoaDAO hanghoaDAO;
    public List<HoaDonChiTiet> dsHDCT = new ArrayList<>();
    ListView lvCart;
    CartAdapter adapter = null;
    double thanhTien = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoa_don_chi_tiet);
        edMaSach = (EditText) findViewById(R.id.edMaSach);
        edMaHoaDon = (EditText) findViewById(R.id.edMaHoaDon);
        edSoLuong = (EditText) findViewById(R.id.edSoLuongMua);
        lvCart = (ListView) findViewById(R.id.lvCart);
        tvThanhTien = (TextView) findViewById(R.id.tvThanhTien);
        adapter = new CartAdapter(this, dsHDCT);
        lvCart.setAdapter(adapter);

        Intent in = getIntent();
        Bundle b = in.getExtras();
        if (b != null) {
            edMaHoaDon.setText(b.getString("MAHOADON"));
        }
    }


    public void ADDHoaDonCHITIET(View view) {
        hoaDonChiTietDAO = new HoaDonChiTietDAO(HoaDonChiTietActivity.this);
        hanghoaDAO = new HanghoaDAO(HoaDonChiTietActivity.this);
        try {
            if (validation() < 0) {
                Toast.makeText(getApplicationContext(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            } else {
                Hanghoa hanghoa = hanghoaDAO.getSachByID(edMaSach.getText().toString());
                if (hanghoa != null) {
                    int pos = checkMaHangHoa(dsHDCT, edMaSach.getText().toString());
                    HoaDon hoaDon = new HoaDon(edMaHoaDon.getText().toString(), new Date());
                    HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet(1, hoaDon, hanghoa, Integer.parseInt(edSoLuong.getText().toString()));
                    if (pos >= 0) {
                        int soluong = dsHDCT.get(pos).getSoLuongMua();
                        hoaDonChiTiet.setSoLuongMua(soluong + Integer.parseInt(edSoLuong.getText().toString()));
                        dsHDCT.set(pos, hoaDonChiTiet);
                    } else {
                        dsHDCT.add(hoaDonChiTiet);
                    }
                    adapter.changeDataset(dsHDCT);
                } else {
                    Toast.makeText(getApplicationContext(), "Mã hàng hóa không tồn tại", Toast.LENGTH_SHORT).show();
                }
            }
        } catch (Exception ex) {
            Log.e("Error", ex.toString());
        }
    }

    public int checkMaHangHoa(List<HoaDonChiTiet> lsHD, String maHangHoa) {
        int pos = -1;
        for (int i = 0; i < lsHD.size(); i++) {
            HoaDonChiTiet hd = lsHD.get(i);
            if (hd.getHanghoa().getMaHangHoa().equalsIgnoreCase(maHangHoa)) {
                pos = i;
                break;
            }
        }
        return pos;
    }

    public int validation() {
        if (edMaSach.getText().toString().isEmpty() || edSoLuong.getText().toString().isEmpty() || edMaHoaDon.getText().toString().isEmpty()) {
            return -1;
        }
        return 1;
    }

    public void thanhToanHoaDon(View view) {
        hoaDonChiTietDAO = new HoaDonChiTietDAO(HoaDonChiTietActivity.this);
        thanhTien = 0;
        try {
            for (HoaDonChiTiet hd : dsHDCT) {
                hoaDonChiTietDAO.inserHoaDonChiTiet(hd);
                thanhTien = thanhTien + hd.getSoLuongMua() * hd.getHanghoa().getGiaBia();
            }
            tvThanhTien.setText("Tổng tiền: " + thanhTien);
        } catch (Exception ex) {
            Log.e("Error", ex.toString());
        }
    }
}



