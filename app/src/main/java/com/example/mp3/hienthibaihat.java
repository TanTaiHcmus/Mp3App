package com.example.mp3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.SimpleTimeZone;

public class hienthibaihat extends AppCompatActivity {

    private ArrayList<Song> listSong;
    private ArrayList<baiHat> listBaiHat;
    private int id;
    private Boolean pause;
    private Boolean loop;
    private TextView txtloibaihat, txttenbaihat, txtcurrenttime, txtendtime;
    ImageButton imageButton, loopButton;
    MediaPlayer mediaPlayer;
    SeekBar seekBarSong;
    private int endTime = 0;
    private int currentTime = 0;
    ImageView imageAni;
    Animation animation;
    SimpleDateFormat time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hienthibaihat);
        khoitao();
        Intent intent = this.getIntent();
        id = intent.getIntExtra("id", 0);
        if (mediaPlayer != null) mediaPlayer.stop();
        mediaPlayer = MediaPlayer.create(hienthibaihat.this, listSong.get(id).getMusic());
        mediaPlayer.start();
        txtloibaihat.setText(listSong.get(id).getLoiBaiHat());
        txttenbaihat.setText(listBaiHat.get(id).getTen());
        setEndTime();
        setCurrentTimeAndSeekBar();
        animation = AnimationUtils.loadAnimation(this, R.anim.animationavatar);
        imageAni.setAnimation(animation);
        seekBarSong.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(seekBarSong.getProgress());
            }
        });
    }

    private void khoitao() {
        time = new SimpleDateFormat("mm:ss");

        listSong = new ArrayList<>();
        listSong.add(new Song(getString(R.string.ganhme), R.raw.ganhme, 334));
        listSong.add(new Song(getString(R.string.psmcg), R.raw.psmcg, 270));
        listSong.add(new Song(getString(R.string.nactv), R.raw.nactv, 260));
        listSong.add(new Song(getString(R.string.chipheo), R.raw.chipheo, 240));
        listSong.add(new Song(getString(R.string.loyt), R.raw.loyt, 264));
        listSong.add(new Song(getString(R.string.eslcd), R.raw.eslcd, 293));
        listSong.add(new Song(getString(R.string.songgio), R.raw.songgio, 254));
        listSong.add(new Song(getString(R.string.nabvt), R.raw.nabvt, 318));

        listBaiHat = new ArrayList<>();
        listBaiHat.add(new baiHat(R.drawable.quachbeem, "Gánh mẹ", "Quách Beem"));
        listBaiHat.add(new baiHat(R.drawable.soobin, "Phía sau một cô gái", "Soobin Hoàng Sơn"));
        listBaiHat.add(new baiHat(R.drawable.nactv, "Nơi ấy con tìm về", "Hồ Quang Hiếu"));
        listBaiHat.add(new baiHat(R.drawable.chipheo, "Chí phèo", "Bùi Công Nam"));
        listBaiHat.add(new baiHat(R.drawable.loyt, "Lỗi ở yêu thương", "Thanh Duy Idol"));
        listBaiHat.add(new baiHat(R.drawable.eslcd, "Em sẽ là cô dâu", "Minh Vương M4U"));
        listBaiHat.add(new baiHat(R.drawable.songgio, "Sóng gió", "K-ICM x Jack"));
        listBaiHat.add(new baiHat(R.drawable.nabvt, "Ngày ấy bạn và tôi", "Lynk Lee"));

        txtloibaihat = (TextView) findViewById(R.id.loibaihat);
        txttenbaihat = (TextView) findViewById(R.id.txttenbaihat);
        txtcurrenttime = (TextView) findViewById(R.id.currenttime);
        txtendtime = (TextView) findViewById(R.id.endtime);
        imageButton = (ImageButton) findViewById(R.id.btnpause);
        loopButton = (ImageButton) findViewById(R.id.btnloop);
        seekBarSong = (SeekBar) findViewById(R.id.seekbar);
        imageAni = (ImageView) findViewById(R.id.anima);

        loop = false; pause = false;
        imageButton.setImageResource(R.drawable.pause);
    }

    public void setEndTime()
    {
        endTime = mediaPlayer.getDuration();
        txtendtime.setText(time.format(endTime));
        seekBarSong.setMax(endTime);
    }

    public void setCurrentTimeAndSeekBar()
    {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                currentTime = mediaPlayer.getCurrentPosition();
                txtcurrenttime.setText(time.format(currentTime));
                seekBarSong.setProgress(currentTime);
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer media) {
                        if (loop == true)
                        {
                            if (mediaPlayer.isPlaying()) mediaPlayer.stop();
                            mediaPlayer = MediaPlayer.create(hienthibaihat.this, listSong.get(id).getMusic());
                            mediaPlayer.start();
                        }
                        else
                        {
                            pause = false;
                            imageButton.setImageResource(R.drawable.pause);
                            if (mediaPlayer.isPlaying()) mediaPlayer.stop();
                            id++;
                            if (id == listSong.size()) id = 0;
                            mediaPlayer = MediaPlayer.create(hienthibaihat.this, listSong.get(id).getMusic());
                            mediaPlayer.start();
                            txtloibaihat.setText(listSong.get(id).getLoiBaiHat());
                            txttenbaihat.setText(listBaiHat.get(id).getTen());
                            setEndTime();
                            setCurrentTimeAndSeekBar();
                        }
                    }
                });
                handler.postDelayed(this,1000);
            }
        }, 100);
    }

    public void toMain(View view) {
        if (mediaPlayer.isPlaying()) mediaPlayer.stop();
        Intent intent = new Intent(hienthibaihat.this, MainActivity.class);
        startActivity(intent);
    }

    public void backSong(View view) {
        pause = false;
        imageButton.setImageResource(R.drawable.pause);
        if (mediaPlayer.isPlaying()) mediaPlayer.stop();
        id--;
        if (id < 0) id = listSong.size() - 1;
        mediaPlayer = MediaPlayer.create(hienthibaihat.this, listSong.get(id).getMusic());
        mediaPlayer.start();
        txtloibaihat.setText(listSong.get(id).getLoiBaiHat());
        txttenbaihat.setText(listBaiHat.get(id).getTen());
        setEndTime();
        setCurrentTimeAndSeekBar();
    }

    public void pauseOrStartSong(View view) {
        if (pause == false) {
            mediaPlayer.pause();
            pause = true;
            imageButton.setImageResource(R.drawable.play);
        }
        else
        {
            mediaPlayer.start();
            setCurrentTimeAndSeekBar();
            pause = false;
            imageButton.setImageResource(R.drawable.pause);
        }
    }

    public void nextSong(View view) {
        pause = false;
        imageButton.setImageResource(R.drawable.pause);
        if (mediaPlayer.isPlaying()) mediaPlayer.stop();
        id++;
        if (id == listSong.size()) id = 0;
        mediaPlayer = MediaPlayer.create(hienthibaihat.this, listSong.get(id).getMusic());
        mediaPlayer.start();
        txtloibaihat.setText(listSong.get(id).getLoiBaiHat());
        txttenbaihat.setText(listBaiHat.get(id).getTen());
        setEndTime();
        setCurrentTimeAndSeekBar();
    }

    public void loopThisSong(View view) {
        if (loop == false)
        {
            loop = true;
            loopButton.setImageResource(R.drawable.loop1);
        }
        else
        {
            loop = false;
            loopButton.setImageResource(R.drawable.loop);
        }
    }
}
