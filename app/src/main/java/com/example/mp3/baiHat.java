package com.example.mp3;

public class baiHat {
    private int image;
    private String ten;
    private String caSi;

    public baiHat(int image, String ten, String caSi) {
        this.image = image;
        this.ten = ten;
        this.caSi = caSi;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getCaSi() {
        return caSi;
    }

    public void setCaSi(String caSi) {
        this.caSi = caSi;
    }
}
