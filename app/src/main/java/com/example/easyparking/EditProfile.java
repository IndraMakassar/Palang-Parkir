package com.example.easyparking;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class EditProfile extends Fragment {
    private View view;

    private TextView editNama;
    private TextView editEmail;
    private TextView editNomor;
    private TextView simpan;
    private FirebaseUser user;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_edit__profile, container, false);
        user = FirebaseAuth.getInstance().getCurrentUser();

        editNama = view.findViewById(R.id.editNama);
        editEmail = view.findViewById(R.id.editEmail);
        editNomor = view.findViewById(R.id.editNomor);
        simpan = view.findViewById(R.id.simpan);

        editNama.setText(user.getDisplayName());
        editEmail.setText(user.getEmail());
        editNomor.setText(user.getPhoneNumber());

        String nama = editNama.getText().toString();

        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                        .setDisplayName(editNama.getText().toString().trim())
                        .build();

                user.updateProfile(profileUpdates)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Log.d(TAG, "User profile updated.");
                                }
                            }
                        });

                getParentFragmentManager().beginTransaction()
                        .setReorderingAllowed(true)
                        .replace(R.id.fragmentView, Profile.class, null)
                        .commit();

            }
        });

        return view;
    }
}