package com.example.mp3;

public class Song {
    private String loiBaiHat;
    private int music;
    private int time;

    public Song(String loiBaiHat, int music, int time) {
        this.loiBaiHat = loiBaiHat;
        this.music = music;
        this.time = time;
    }

    public String getLoiBaiHat() {
        return loiBaiHat;
    }

    public void setLoiBaiHat(String loiBaiHat) {
        this.loiBaiHat = loiBaiHat;
    }

    public int getMusic() {
        return music;
    }

    public void setMusic(int music) {
        this.music = music;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
