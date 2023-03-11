package com.example.easyparking;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.FirebaseDatabase;

public class DaftarAkun extends AppCompatActivity implements View.OnClickListener {

    GoogleSignInOptions googleSignInOptions;
    GoogleSignInClient googleSignInClient;
    private FirebaseAuth mAuth, auth;
    private EditText etemail, etpassword, etNama;
    //    private ProgressDialog progressDialog;
    private TextView daftar, tvGoogle;
    //    private ProgressBar progressBar;
    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_akun);

        mAuth = FirebaseAuth.getInstance();

        back = (ImageView) findViewById(R.id.backImage);

        daftar = (TextView) findViewById(R.id.btnDaftar);
        daftar.setOnClickListener(this);

        etemail = (EditText) findViewById(R.id.inputEmail);
        etpassword = (EditText) findViewById(R.id.search);
        etNama = (EditText) findViewById(R.id.inputNama);

        tvGoogle = findViewById(R.id.google);

        auth = FirebaseAuth.getInstance();

//        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();

        googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions);

        tvGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Signin();

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DaftarAkun.this, Login.class));
//                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                task.getResult(ApiException.class);
                HomeActivity();
            } catch (ApiException e) {
                Toast.makeText(this, " " + e.getMessage(), Toast.LENGTH_LONG).show();
            }
        }
    }

    private void Signin() {
        Intent intent = googleSignInClient.getSignInIntent();
        startActivityForResult(intent, 100);
    }

    private void HomeActivity() {
        finish();
        Intent intent = new Intent(getApplicationContext(), Login.class);
        startActivity(intent);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnDaftar:
                registerUser();
                break;

        }

    }

    private void registerUser() {

        String email = etemail.getText().toString().trim();
        String password = etpassword.getText().toString().trim();
        String nama = etNama.getText().toString().trim();

        if (email.isEmpty()) {
            etemail.setError("Email Belum Di input");
            etemail.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            etpassword.setError("Password Belum Di input");
            etpassword.requestFocus();
            return;
        }

        if (password.length() < 6) {
            etpassword.setError("Password Min 6 Characters");
            etpassword.requestFocus();
            return;
        }

//        progressDialog = new ProgressDialog(daftar_akun.this);
//        progressDialog.show();
//        progressDialog.setContentView(R.layout.progress_dialog);
//        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

//        progressBar.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                User user = new User(email, password, nama);

                FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user).addOnCompleteListener(task1 -> {

                    if (task1.isSuccessful()) {
                        FirebaseUser userfire = FirebaseAuth.getInstance().getCurrentUser();

                        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder().setDisplayName(nama).build();

                        userfire.updateProfile(profileUpdates).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(DaftarAkun.this, "Akun berhasil didaftar", Toast.LENGTH_LONG).show();
                                    startActivity(new Intent(DaftarAkun.this, Login.class));
                                }
                            }
                        });

//                                        progressBar.setVisibility(View.VISIBLE);
                    } else {
                        Toast.makeText(DaftarAkun.this, "Terjadi Kesalahan, Silahkan Coba Lagi", Toast.LENGTH_LONG).show();
                    }
                });
            } else {
                Toast.makeText(DaftarAkun.this, "Akun Sudah Terdaftar", Toast.LENGTH_LONG).show();
            }
        });
    }

}
