package com.example.appbanhang.Model;

public class HoaDonChiTiet {
    private int maHDCT;
    private HoaDon hoaDon;
    private Hanghoa hanghoa;
    private int soLuongMua;

    public HoaDonChiTiet(int maHDCT, HoaDon hoaDon, Hanghoa hanghoa, int soLuongMua) {
        this.maHDCT = maHDCT;
        this.hoaDon = hoaDon;
        this.hanghoa = hanghoa;
        this.soLuongMua = soLuongMua;
    }

    public HoaDonChiTiet() {

    }

    public int getMaHDCT() {
        return maHDCT;
    }

    public void setMaHDCT(int maHDCT) {
        this.maHDCT = maHDCT;
    }

    public HoaDon getHoaDon() {
        return hoaDon;
    }

    public void setHoaDon(HoaDon hoaDon) {
        this.hoaDon = hoaDon;
    }

    public Hanghoa getHanghoa() {
        return hanghoa;
    }

    public void setHangHoa(Hanghoa HangHoa) {
        this.hanghoa = HangHoa;
    }

    public int getSoLuongMua() {
        return soLuongMua;
    }

    public void setSoLuongMua(int soLuongMua) {
        this.soLuongMua = soLuongMua;
    }

    @Override
    public String toString() {
        return "HoaDonChiTiet{" +
                "maHDCT=" + maHDCT +
                ", hoaDon=" + hoaDon +
                ", hanghoa=" + hanghoa +
                ", soLuongMua=" + soLuongMua +
                '}';
    }
}
///16 cái vopc
///16 cái tuần tự