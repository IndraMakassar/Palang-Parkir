package com.example.easyparking;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.Nullable;

public class palang_Parkir extends Fragment {
    @Nullable
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    private Button open;
    private ImageView back;
    private Boolean terbuka = false;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {

        View c = inflater.inflate(R.layout.fragment_palang__parkir, container, false);

        open = (Button) c.findViewById(R.id.palang);
        back = (ImageView) c.findViewById(R.id.backImage);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fm = getActivity().getSupportFragmentManager().beginTransaction();
                fm.replace(R.id.fragmentView, new booking()).commit();
            }
        });


        open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database.child("Tempat Pakir").child("Palang").setValue(true); // untuk database
                Toast.makeText(getActivity(), "Palang Sudah Terbuka", Toast.LENGTH_SHORT).show();
            }
        });

        return c;
    }
}
