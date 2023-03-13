package com.example.easyparking;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;


public class DetailTempat extends Fragment {
    Bundle bundle = getArguments();
    private TextView namaMall;
    private TextView persenKosong;
    private static final String ARG_ITEM = "item";
    private String item;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_detail_tempat, container, false);

        Bundle bundle = getArguments();
        String nama = bundle.getString(ARG_ITEM);

        namaMall = v.findViewById(R.id.namaMall);
        persenKosong = v.findViewById(R.id.persenKosong);
        namaMall.setText(nama);

        return v;
    }
}