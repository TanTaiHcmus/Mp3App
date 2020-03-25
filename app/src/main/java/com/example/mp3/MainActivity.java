package com.example.mp3;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import static com.example.mp3.R.*;

public class MainActivity extends AppCompatActivity {

    ListView lvBaiHat;
    ArrayList<baiHat> listBaiHat;
    baiHatAdapter baiHatAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);

        khoiTao();

        baiHatAdapter = new baiHatAdapter(this, layout.layout_baihat, listBaiHat);

        lvBaiHat.setAdapter(baiHatAdapter);

        lvBaiHat.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, hienthibaihat.class);
                intent.putExtra("id", i);
                startActivity(intent);
            }
        });
    }

    private void khoiTao() {

        lvBaiHat = (ListView) findViewById(id.listviewbaihat);

        listBaiHat = new ArrayList<>();
        listBaiHat.add(new baiHat(drawable.quachbeem, "Gánh mẹ", "Quách Beem"));
        listBaiHat.add(new baiHat(drawable.soobin, "Phía sau một cô gái", "Soobin Hoàng Sơn"));
        listBaiHat.add(new baiHat(drawable.nactv, "Nơi ấy con tìm về", "Hồ Quang Hiếu"));
        listBaiHat.add(new baiHat(drawable.chipheo, "Chí phèo", "Bùi Công Nam"));
        listBaiHat.add(new baiHat(drawable.loyt, "Lỗi ở yêu thương", "Thanh Duy Idol"));
        listBaiHat.add(new baiHat(drawable.eslcd, "Em sẽ là cô dâu", "Minh Vương M4U"));
        listBaiHat.add(new baiHat(drawable.songgio, "Sóng gió", "K-ICM x Jack"));
        listBaiHat.add(new baiHat(drawable.nabvt, "Ngày ấy bạn và tôi", "Lynk Lee"));
    }

}
