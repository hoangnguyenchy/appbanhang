package com.example.appbanhang.Model;

public class Hanghoa {
    private String maHangHoa;
    private String maTheLoai;
    private String tenHangHoa;
    private String tacGia;
    private String NXB;
    private double giaBia;
    private int soLuong;

    public Hanghoa(String maHangHoa, String maTheLoai, String tenHangHoa, String tacGia, String NXB, double giaBia, int soLuong) {
        this.maHangHoa = maHangHoa;
        this.maTheLoai = maTheLoai;
        this.tenHangHoa = tenHangHoa;
        this.tacGia = tacGia;
        this.NXB = NXB;
        this.giaBia = giaBia;
        this.soLuong = soLuong;
    }

    public Hanghoa() {

    }

    public String getMaHangHoa() {
        return maHangHoa;
    }

    public void setMaHangHoa(String maHangHoa) {
        this.maHangHoa = maHangHoa;
    }

    public String getMaTheLoai() {
        return maTheLoai;
    }

    public void setMaTheLoai(String maTheLoai) {
        this.maTheLoai = maTheLoai;
    }

    public String getTenHangHoa() {
        return tenHangHoa;
    }

    public void setTenHangHoa(String tenHangHoa) {
        this.tenHangHoa = tenHangHoa;
    }

    public String getTacGia() {
        return tacGia;
    }

    public void setTacGia(String tacGia) {
        this.tacGia = tacGia;
    }

    public String getNXB() {
        return NXB;
    }

    public void setNXB(String NXB) {
        this.NXB = NXB;
    }

    public double getGiaBia() {
        return giaBia;
    }

    public void setGiaBia(double giaBia) {
        this.giaBia = giaBia;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    @Override
    public String toString() {
        return "HangHoa{" +
                "maHangHoa='" + maHangHoa + '\'' +
                ", maTheLoai='" + maTheLoai + '\'' +
                ", tenSach='" + tenHangHoa + '\'' +
                ", tacGia='" + tacGia + '\'' +
                ", NXB='" + NXB + '\'' +
                ", giaBia=" + giaBia +
                ", soLuong=" + soLuong +
                '}';
    }


}
