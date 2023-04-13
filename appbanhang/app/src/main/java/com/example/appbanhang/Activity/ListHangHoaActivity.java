package com.example.appbanhang.Activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appbanhang.Adapter.hanghoaAdapter;
import com.example.appbanhang.Database.HanghoaDAO;
import com.example.appbanhang.Model.Hanghoa;
import com.example.appbanhang.R;
import com.example.appbanhang.ThemHangHoaActivity;

import java.util.ArrayList;
import java.util.List;

public class ListHangHoaActivity extends AppCompatActivity {
    public static List<Hanghoa> dsSach = new ArrayList<>();
    ListView lvBook;
    hanghoaAdapter adapter= null;
    HanghoaDAO hanghoaDAO;
    String tt_menu= "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("QUẢN LÝ Hàng Hóa");
        setContentView(R.layout.activity_list_hanghoa);
        lvBook = (ListView) findViewById(R.id.lvBook);
        hanghoaDAO = new HanghoaDAO(ListHangHoaActivity.this);
        dsSach = hanghoaDAO.getAllSach();
        adapter = new hanghoaAdapter(this, dsSach);
        lvBook.setAdapter(adapter);
        lvBook.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Hanghoa hanghoa = (Hanghoa) parent.getItemAtPosition(position);
                Intent intent = new Intent(ListHangHoaActivity.this, ThemHangHoaActivity.class);
                Bundle b = new Bundle();
                b.putString("MAHANGHOA", hanghoa.getMaHangHoa());
                b.putString("MATHELOAI", hanghoa.getMaTheLoai());
                b.putString("TENSACH", hanghoa.getTenHangHoa());
                b.putString("TACGIA", hanghoa.getTacGia());
                b.putString("NXB", hanghoa.getNXB());
                b.putString("GIABIA", String.valueOf(hanghoa.getGiaBia()));
                b.putString("SOLUONG", String.valueOf(hanghoa.getSoLuong()));
                intent.putExtras(b);
                startActivity(intent);
            }
        });
        lvBook.setTextFilterEnabled(true);
//        EditText edSeach = (EditText) findViewById(R.id.edSearchBook);
//        edSeach.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                System.out.println("Text [" + s + "] - Start [" + start + "] - Before [" + before + "] - Count [" + count + "]");
//                if (count < before) {
//                    adapter.resetData();
//                }
//                adapter.getFilter().filter(s.toString());
//            }
//
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//            }
//        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_book, menu);
        if(LoginActivity.strUser.equals( "admin") && LoginActivity.strPass.equals("admin")){
            menu.findItem(R.id.them_sach).setVisible(true);
        }
        else{
            menu.findItem(R.id.them_sach).setVisible(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.them_sach:
                Intent intent = new Intent(ListHangHoaActivity.this, ThemHangHoaActivity.class);
                startActivity(intent);
                return (true);
        }
        return super.onOptionsItemSelected(item);
    }
}

