package com.example.appbanhang.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Button;

import com.example.appbanhang.Model.Hanghoa;

import java.util.ArrayList;
import java.util.List;

public class HanghoaDAO {
    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;
    public static final String TABLE_NAME = "HangHoa";
    public static final String SQL_SACH = "CREATE TABLE Hanghoa (maHangHoa text primary key, maTheLoai text, tenhanghoa text," + "tacGia text, NXB text, giaBia double, soLuong number);";
    public static final String TAG = "HanghoaDAO";

    public HanghoaDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    //insert
    public int inserSach(Hanghoa s) {
        ContentValues values = new ContentValues();
        values.put("maHanghoa", s.getMaHangHoa());
        values.put("maTheLoai", s.getMaTheLoai());
        values.put("tenHangHoa", s.getTenHangHoa());
        values.put("tacGia", s.getTacGia());
        values.put("NXB", s.getNXB());
        values.put("giaBia", s.getGiaBia());
        values.put("soLuong", s.getSoLuong());
        if (checkPrimaryKey(s.getMaHangHoa())) {
            int result = db.update(TABLE_NAME, values, "mahanghoa=?", new String[]{s.getMaHangHoa()});
            if (result == 0) {
                return -1;
            }
        } else {
            try {
                if (db.insert(TABLE_NAME, null, values) == -1) {
                    return -1;
                }
            } catch (Exception ex) {
                Log.e(TAG, ex.toString());
            }
        }
        return 1;
    }     //getAll


    public List<Hanghoa> getAllSach() {
        List<Hanghoa> dsHangHoa = new ArrayList<>();
        Cursor c = db.query(TABLE_NAME, null, null, null, null, null, null);
        c.moveToFirst();
        while (c.isAfterLast() == false) {
            Hanghoa s = new Hanghoa();
            s.setMaHangHoa(c.getString(0));
            s.setMaTheLoai(c.getString(1));
            s.setTenHangHoa(c.getString(2));
            s.setTacGia(c.getString(3));
            s.setNXB(c.getString(4));
            s.setGiaBia(c.getDouble(5));
            s.setSoLuong(c.getInt(6));
            dsHangHoa.add(s);
            Log.d("//=====", s.toString());
            c.moveToNext();
        }
        c.close();
        return dsHangHoa;
    }

    //update
    public int updateHangHoa(Hanghoa s) {
        ContentValues values = new ContentValues();
        values.put("maHangHoa", s.getMaHangHoa());
        values.put("maTheLoai", s.getMaTheLoai());
        values.put("tenhanghoa", s.getTenHangHoa());
        values.put("tacGia", s.getTacGia());
        values.put("NXB", s.getNXB());
        values.put("giaBia", s.getGiaBia());
        values.put("soLuong", s.getSoLuong());
        int result = db.update(TABLE_NAME, values, "maHangHoa=?", new String[]{s.getMaHangHoa()});
        if (result == 0) {
            return -1;
        }
        return 1;
    }

    //delete
    public int deleteSachByID(String maHangHoa) {
        int result = db.delete(TABLE_NAME, "maHangHoa=?", new String[]{maHangHoa});
        if (result == 0) return -1;
        return 1;
    }     //check

    public boolean checkPrimaryKey(String strPrimaryKey) {
        //SELECT
        String[] columns = {"mahanghoa"};
        //WHERE clause
        String selection = "mahanghoa=?";
        //WHERE clause arguments
        String[] selectionArgs = {strPrimaryKey};
        Cursor c = null;
        try {
            c = db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);
            c.moveToFirst();
            int i = c.getCount();
            c.close();
            if (i <= 0) {
                return false;
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //check
    public Hanghoa checkBook(String strPrimaryKey) {
        Hanghoa s = new Hanghoa();
        //SELECT
        String[] columns = {"mahanghoa"};
        //WHERE clause
        String selection = "mahanghoa=?";
        //WHERE clause arguments
        String[] selectionArgs = {strPrimaryKey};
        Cursor c = null;
        try {
            c = db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);
            c.moveToFirst();
            while (c.isAfterLast() == false) {
                s.setMaHangHoa(c.getString(0));
                s.setMaTheLoai(c.getString(1));
                s.setTenHangHoa(c.getString(2));
                s.setTacGia(c.getString(3));
                s.setNXB(c.getString(4));
                s.setGiaBia(c.getDouble(5));
                s.setSoLuong(c.getInt(6));
                Log.d("//=====", s.toString());
                break;
            }
            c.close();
            return s;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //getAll
    public Hanghoa getSachByID(String maSach) {
        Hanghoa s = null;
        //WHERE clause
        String selection = "mahanghoa=?";
        //WHERE clause arguments
        String[] selectionArgs = {maSach};
        Cursor c = db.query(TABLE_NAME, null, selection, selectionArgs, null, null, null);
        Log.d("getHangHoaByID", "===>" + c.getCount());
        c.moveToFirst();
        while (c.isAfterLast() == false) {
            s = new Hanghoa();
            s.setMaHangHoa(c.getString(0));
            s.setMaTheLoai(c.getString(1));
            s.setTenHangHoa(c.getString(2));
            s.setTacGia(c.getString(3));
            s.setNXB(c.getString(4));
            s.setGiaBia(c.getDouble(5));
            s.setSoLuong(c.getInt(6));
            break;
        }
        c.close();
        return s;
    }

    //getAll
    public List<Hanghoa> getSachTop10(String month) {
        List<Hanghoa> dsHangHoa = new ArrayList<>();
        if (Integer.parseInt(month) < 10) {
            month = "0" + month;
        }
        String sSQL = "SELECT maHangHoa, SUM(soLuong) as soluong FROM HoaDonChiTiet INNER JOIN HoaDon " + "ON HoaDon.maHoaDon = HoaDonChiTiet.maHoaDon WHERE strftime('%m',HoaDon.ngayMua) = '" + month + "' " + "GROUP BY maHangHoa ORDER BY soluong DESC LIMIT 10";
        Cursor c = db.rawQuery(sSQL, null);
        c.moveToFirst();
        while (c.isAfterLast() == false) {
            Log.d("//=====", c.getString(0));
            Hanghoa s = new Hanghoa();
            s.setMaHangHoa(c.getString(0));
            s.setSoLuong(c.getInt(1));
            s.setGiaBia(0);
            s.setMaTheLoai("");
            s.setTenHangHoa("");
            s.setTacGia("");
            s.setNXB("");
            dsHangHoa.add(s);
            c.moveToNext();
        }
        c.close();
        return dsHangHoa;
    }

}
