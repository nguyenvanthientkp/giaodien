package com.example.giaodien;

public class data {
    private int Hinh;
    private String ASang,Nhiet,TGian;

    public data(int hinh, String ASang, String nhiet, String TGian) {
        Hinh = hinh;
        this.ASang = ASang;
        Nhiet = nhiet;
        this.TGian = TGian;
    }

    public int getHinh() {
        return Hinh;
    }

    public void setHinh(int hinh) {
        Hinh = hinh;
    }

    public String getASang() {
        return ASang;
    }

    public void setASang(String ASang) {
        this.ASang = ASang;
    }

    public String getNhiet() {
        return Nhiet;
    }

    public void setNhiet(String nhiet) {
        Nhiet = nhiet;
    }

    public String getTGian() {
        return TGian;
    }

    public void setTGian(String TGian) {
        this.TGian = TGian;
    }
}
