package com.example.easyparking;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class PilihTempat extends Fragment {
    private DatabaseReference database;
    private TextView malltrans, mallpipo, mallGTC, mallkarebosi, mallMP, mallmari, mallnipah, mallMTC;
    RecyclerView recyclerView;
    MyAdapter myAdapter;
    ArrayList<User2> list;


    @Nullable
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {

        View c = inflater.inflate(R.layout.fragment_pilih__tempat, container, false);


        recyclerView = c.findViewById(R.id.listnama);
        database = FirebaseDatabase.getInstance().getReference("Data Booking");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        list = new ArrayList<>();

        myAdapter = new MyAdapter(this.getContext(),list);
        recyclerView.setAdapter(myAdapter);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    User2 user = dataSnapshot.getValue(User2.class);
                    user.modelUser2(dataSnapshot.child("Nama Mall").getValue().toString());
                    list.add(user);
                }
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

//        mallpipo = (TextView) c.findViewById(R.id.pipo);
//        mallGTC = (TextView) c.findViewById(R.id.gtc);
//        mallkarebosi = (TextView) c.findViewById(R.id.karbos);
//        mallMP = (TextView) c.findViewById(R.id.mp);
//        mallmari = (TextView) c.findViewById(R.id.mari);
//        mallnipah = (TextView) c.findViewById(R.id.nipah);
//        mallMTC = (TextView) c.findViewById(R.id.mp2);

//
//        malltrans.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String getTrans = malltrans.getText().toString();
//                database.child("Tempat Pakir").child("Pilihan Tempat").setValue(getTrans);// untuk database
//                FragmentTransaction fm = getActivity().getSupportFragmentManager().beginTransaction();
//                fm.replace(R.id.fragmentView, new booking()).commit();
//            }
//        });
//
//        mallmari.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String getMari = mallmari.getText().toString();
//                database.child("Tempat Pakir").child("Pilihan Tempat").setValue(getMari);// untuk database
//                FragmentTransaction fm = getActivity().getSupportFragmentManager().beginTransaction();
//                fm.replace(R.id.fragmentView, new booking()).commit();
//            }
//        });
//
//        mallpipo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String getPipo = mallpipo.getText().toString();
//                database.child("Tempat Pakir").child("Pilihan Tempat").setValue(getPipo);// untuk database
//                  FragmentTransaction fm = getActivity().getSupportFragmentManager().beginTransaction();
//                fm.replace(R.id.fragmentView, new booking()).commit();
//            }
//        });
//
//        mallpipo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String getPipo = mallpipo.getText().toString();
//                database.child("Data Booking").child("Pilihan Tempat").setValue(getPipo);// untuk database
//                FragmentTransaction fm = getActivity().getSupportFragmentManager().beginTransaction();
//                fm.replace(R.id.fragmentView, new booking()).commit();
//            }
//        });
//
//        mallGTC.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String getGTC = mallGTC.getText().toString();
//                database.child("Tempat Pakir").child("Pilihan Tempat").setValue(getGTC);// untuk database
//                FragmentTransaction fm = getActivity().getSupportFragmentManager().beginTransaction();
//                fm.replace(R.id.fragmentView, new Pilih_Tempat()).commit();
//            }
//        });
//
//        mallMP.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String getMP = mallMP.getText().toString();
//                database.child("Tempat Pakir").child("Pilihan Tempat").setValue(getMP);// untuk database
//                FragmentTransaction fm = getActivity().getSupportFragmentManager().beginTransaction();
//                fm.replace(R.id.fragmentView, new Pilih_Tempat()).commit();
//            }
//        });
//
//        mallnipah.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String getNipah = mallnipah.getText().toString();
//                database.child("Tempat Pakir").child("Pilihan Tempat").setValue(getNipah);// untuk database
//                FragmentTransaction fm = getActivity().getSupportFragmentManager().beginTransaction();
//                fm.replace(R.id.fragmentView, new Pilih_Tempat()).commit();
//            }
//        });
//
//        mallkarebosi.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String getKarebosi = mallkarebosi.getText().toString();
//                database.child("Tempat Pakir").child("Pilihan Tempat").setValue(getKarebosi);// untuk database
//                FragmentTransaction fm = getActivity().getSupportFragmentManager().beginTransaction();
//                fm.replace(R.id.fragmentView, new Pilih_Tempat()).commit();
//            }
//        });
//
//        mallMTC.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String getMTC = mallMTC.getText().toString();
//                database.child("Tempat Pakir").child("Pilihan Tempat").setValue(getMTC);// untuk database
//                FragmentTransaction fm = getActivity().getSupportFragmentManager().beginTransaction();
//                fm.replace(R.id.fragmentView, new Pilih_Tempat()).commit();
//            }
//        });

        return c;

    }

    }