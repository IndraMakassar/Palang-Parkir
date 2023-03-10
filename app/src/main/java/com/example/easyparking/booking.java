package com.example.easyparking;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.Nullable;

public class booking extends Fragment {
    private DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    private EditText nama, plat, type, warna;
    private TextView booking;
    private ImageView back;
    private Boolean terisi;

    @Nullable
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {

        View c = inflater.inflate(R.layout.fragment_booking, container, false);

        back = (ImageView) c.findViewById(R.id.backImage);
        nama = (EditText) c.findViewById(R.id.inputNama);
        plat = (EditText) c.findViewById(R.id.inputPlat);
        type = (EditText) c.findViewById(R.id.inputType);
        warna = (EditText) c.findViewById(R.id.inputWarna);
        back = (ImageView) c.findViewById(R.id.backImage);
        booking = (TextView) c.findViewById(R.id.btnbooking);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fm = getActivity().getSupportFragmentManager().beginTransaction();
                fm.replace(R.id.fragmentView, new Pilih_Tempat()).commit();
            }
        });


        booking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.btnbooking:
                        booking();
                        break;
                }
                if (terisi) {
                    String getNama = nama.getText().toString();
                    String getPlat = plat.getText().toString();
                    String getWarna = warna.getText().toString();
                    String getType = type.getText().toString();
                    database.child("Booking").push().setValue(new dataBooking(getNama, getPlat, getType, getWarna));
                    FragmentTransaction fm = getActivity().getSupportFragmentManager().beginTransaction();
                    fm.replace(R.id.fragmentView, new palang_Parkir()).commit();
                }
            }
        });


        return c;
    }
    public void booking() {
        String isinama = nama.getText().toString();
        String isiplat = plat.getText().toString();
        String isitype = type.getText().toString();
        String isiwarna = warna.getText().toString();
        terisi = true;

        if (isinama.isEmpty()) {
            nama.setError("Nama Belum Di input");
            nama.requestFocus();
            terisi = false;
            return;
        }

        if (isiplat.isEmpty()) {
            plat.setError("Plat Belum Di input");
            plat.requestFocus();
            terisi = false;
            return;
        }

        if (isitype.isEmpty()) {
            type.setError("Plat Belum Di input");
            type.requestFocus();
            terisi = false;
            return;
        }

        if (isiwarna.isEmpty()) {
            warna.setError("Plat Belum Di input");
            warna.requestFocus();
            terisi = false;
            return;
        }


    }
}
