package com.example.easyparking;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Profile extends Fragment {

    private String mParam1;
    private String mParam2;

    private View view;
    private TextView namaBesar;
    private TextView namaKecil;
    private TextView emailKecil;
    private TextView telpKecil;
    private TextView logoutBtn;
    private TextView editProfile;
    private FirebaseUser user;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_profile, container, false);

        namaBesar = view.findViewById(R.id.namaBesar);
        namaKecil = view.findViewById(R.id.namaKecil);
        emailKecil = view.findViewById(R.id.emailKecil);
        telpKecil = view.findViewById(R.id.telpKecil);
        logoutBtn = view.findViewById(R.id.logoutBtn);
        editProfile = view.findViewById(R.id.edit_profile);

        user = FirebaseAuth.getInstance().getCurrentUser();

        String nama = user.getDisplayName();
        namaBesar.setText(nama);
        namaKecil.setText(nama);
        emailKecil.setText(user.getEmail());
        telpKecil.setText(user.getPhoneNumber());

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getActivity(), Login.class));
            }
        });

        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getParentFragmentManager().beginTransaction()
                        .setReorderingAllowed(true)
                        .replace(R.id.fragmentView, EditProfile.class, null)
                        .commit();

            }
        });

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        String nama = user.getDisplayName();
        namaBesar.setText(nama);
        namaKecil.setText(nama);
        emailKecil.setText(user.getEmail());
        telpKecil.setText(user.getPhoneNumber());
    }
}