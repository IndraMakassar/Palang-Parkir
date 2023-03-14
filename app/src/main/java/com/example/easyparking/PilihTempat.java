package com.example.easyparking;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class PilihTempat extends Fragment {
    private static final String ARG_ITEM = "item";
    private DatabaseReference database;
    private TextView nama;
    private FirebaseUser user;
    private RecyclerView recyclerView;
    private MyAdapter myAdapter;
    private ArrayList<User2> list;
    private ArrayList<Long> diisi = new ArrayList<Long>();
    private ArrayList<Long> kosong = new ArrayList<Long>();

    @Nullable
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View c = inflater.inflate(R.layout.fragment_pilih__tempat, container, false);

        nama = c.findViewById(R.id.nama);
        user = FirebaseAuth.getInstance().getCurrentUser();
        nama.setText(user.getDisplayName());

        recyclerView = c.findViewById(R.id.listnama);
        database = FirebaseDatabase.getInstance().getReference("Data Booking");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        list = new ArrayList<>();

        myAdapter = new MyAdapter(this.getContext(), list, diisi, kosong,new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(User2 item) {
                Booking fragment = new Booking();
                Bundle bundle = new Bundle();
                bundle.putString(ARG_ITEM, item.getNamaMall());
                fragment.setArguments(bundle);

                System.out.println(item.getNamaMall());

                getParentFragmentManager().beginTransaction().setReorderingAllowed(true).replace(R.id.fragmentView, fragment).commit();
            }
        });
        recyclerView.setAdapter(myAdapter);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                diisi.clear();
                kosong.clear();
                if (snapshot.exists()) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        User2 user = dataSnapshot.getValue(User2.class);

                        diisi.add((Long) dataSnapshot.child("diisi").getValue());
                        kosong.add((Long) dataSnapshot.child("kosong").getValue());

                        user.modelUser2(dataSnapshot.child("Nama Mall").getValue().toString());
                        list.add(user);
                    }
                }
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        return c;
    }
}