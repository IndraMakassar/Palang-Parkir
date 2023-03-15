package com.example.easyparking;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.Nullable;

public class Booking extends Fragment {
    private DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    private EditText nama, plat;
    private TextView booking;
    private ImageView back;
    private Boolean terisi;
    private FirebaseUser user;
    private static final String ARG_ITEM = "item";

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
        back = (ImageView) c.findViewById(R.id.backImage);
        booking = (TextView) c.findViewById(R.id.btnbooking);

        user = FirebaseAuth.getInstance().getCurrentUser();
        nama.setText(user.getDisplayName());

        Bundle bundle = getArguments();
        String lokasi = bundle.getString(ARG_ITEM);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fm = getActivity().getSupportFragmentManager().beginTransaction();
                fm.replace(R.id.fragmentView, new PilihTempat()).commit();
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
                    database.child("PlatBooking").child(getPlat).setValue(new User3(lokasi));
                    database.child("Booking").push().setValue(new DataBooking(getNama, getPlat, lokasi));
                    Toast.makeText(getActivity(), "Booking Berhasil",
                            Toast.LENGTH_LONG).show();
                    getParentFragmentManager().beginTransaction()
                            .setReorderingAllowed(true)
                            .replace(R.id.fragmentView, PilihTempat.class, null)
                            .commit();
                }
            }
        });


        return c;
    }
    public void booking() {
        String isinama = nama.getText().toString();
        String isiplat = plat.getText().toString();
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

    }
}
