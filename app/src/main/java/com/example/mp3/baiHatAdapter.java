package com.example.mp3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class baiHatAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<baiHat> listBaiHat;

    public baiHatAdapter(Context context, int layout, List<baiHat> listBaiHat) {
        this.context = context;
        this.layout = layout;
        this.listBaiHat = listBaiHat;
    }

    @Override
    public int getCount() {
        return listBaiHat.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        view = inflater.inflate(layout, null);

        TextView txtTen = (TextView) view.findViewById(R.id.txtten);
        TextView txtCaSi = (TextView) view.findViewById(R.id.txtcaSi);
        ImageView image = (ImageView) view.findViewById(R.id.imageview);

        baiHat song = listBaiHat.get(i);

        txtTen.setText(song.getTen());
        txtCaSi.setText(song.getCaSi());
        image.setImageResource(song.getImage());

        return view;
    }
}
