package com.example.appbanhang;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;
import com.example.appbanhang.Activity.ListHangHoaActivity;
import com.example.appbanhang.Activity.ListNguoiDungActivity;
import com.example.appbanhang.Activity.ListTheLoaiActivity;
import com.example.appbanhang.Activity.LoginActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ViewFlipper viewFlipper;
    ImageView imgnguoidung;
    TextView txtnguoidung;
    TextView txttheloai;
    ImageView imgtheloai;
    TextView txtthongke;
    ImageView imgthongke;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Anhxa();
        ActionViewFlipper();
        an();
    }

    private void an() {
        if(LoginActivity.strUser.equals("admin") && LoginActivity.strPass.equals("admin")){
            imgnguoidung.setVisibility(View.VISIBLE);
            txtnguoidung.setVisibility(View.VISIBLE);
            imgtheloai.setVisibility ( View.VISIBLE );
            txttheloai.setVisibility ( View.VISIBLE );
            imgthongke.setVisibility ( View.VISIBLE );
            txtthongke.setVisibility ( View.VISIBLE );

        }
        else{
            txtthongke.setVisibility ( View.INVISIBLE );
            imgthongke.setVisibility ( View.INVISIBLE );
            imgnguoidung.setVisibility(View.INVISIBLE);
            txtnguoidung.setVisibility(View.INVISIBLE);
            imgtheloai.setVisibility ( View.INVISIBLE );
            txttheloai.setVisibility ( View.INVISIBLE );
        }
    }

    private void Anhxa() {
        imgthongke=findViewById ( R.id.imgthongke );
        txtthongke = findViewById ( R.id.txtthongke );
        viewFlipper = findViewById(R.id.viewlipper);
        imgnguoidung=findViewById(R.id.imgnguoidung);
        txtnguoidung=findViewById(R.id.txtnguoidung);
        txttheloai=findViewById ( R.id.txttheloai );
        imgtheloai = findViewById (R.id.imgtheloai );
    }

    private void ActionViewFlipper() {
        List<String> mangquangcao = new ArrayList<>();
        mangquangcao.add("http://mauweb.monamedia.net/thegioididong/wp-content/uploads/2017/12/banner-Le-hoi-phu-kien-800-300.png");
        mangquangcao.add("http://mauweb.monamedia.net/thegioididong/wp-content/uploads/2017/12/banner-HC-Tra-Gop-800-300.png");
        mangquangcao.add("http://mauweb.monamedia.net/thegioididong/wp-content/uploads/2017/12/banner-big-ky-nguyen-800-300.jpg");
        for (int i = 0; i < mangquangcao.size(); i++) {
            ImageView imageView = new ImageView(getApplicationContext());
            Glide.with(getApplicationContext()).load(mangquangcao.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }
        viewFlipper.setFlipInterval(3000);
        viewFlipper.setAutoStart(true);
        Animation slide_in= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_in_right);
        Animation slide_out= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_out_right);
        viewFlipper.setInAnimation(slide_in);
        viewFlipper.setOutAnimation(slide_out);
    }

    public void viewSach(View view) {
        Intent SACH = new Intent(MainActivity.this, ListHangHoaActivity.class);
        startActivity(SACH);
    }

    public void viewTheLoai(View view) {
        Intent theloai = new Intent(MainActivity.this, ListTheLoaiActivity.class);
        startActivity(theloai);
    }

    public void viewNguoidung(View view) {
        Intent nguoidung = new Intent(MainActivity.this, ListNguoiDungActivity.class);
        startActivity(nguoidung);
    }

    public void viewHoaDon(View view) {
        Intent hoadon = new Intent(MainActivity.this, ListHoaDonActivity.class);
        startActivity(hoadon);
    }

    public void viewThongKe(View view) {
        Intent thongke = new Intent(MainActivity.this, ThongKeDoanhThuActivity.class);
        startActivity(thongke);
    }

}