package com.example.easyparking;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity implements View.OnClickListener {

    GoogleSignInOptions googleSignInOptions;
    GoogleSignInClient googleSignInClient;
    private TextView daftar, forgotPassword;
    private EditText etemail, etpassword;


    //    private ProgressBar progressBar;
//    private ProgressDialog progressDialog;
    private TextView tvGoogle, login;
    private FirebaseUser user;
    private FirebaseAuth mAuth, auth;

    @Override
    public void onBackPressed() {
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            startActivity(new Intent(Login.this, NavBar.class));
        }


        daftar = (TextView) findViewById(R.id.daftar);
        daftar.setOnClickListener(this);

        login = (TextView) findViewById(R.id.btnMasuk);
        login.setOnClickListener(this);

        etemail = (EditText) findViewById(R.id.inputEmail);
        etpassword = (EditText) findViewById(R.id.search);


        forgotPassword = (TextView) findViewById(R.id.lupaPass);
        forgotPassword.setOnClickListener(this);

//        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        tvGoogle = findViewById(R.id.google);

        auth = FirebaseAuth.getInstance();

        mAuth = FirebaseAuth.getInstance();

        googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions);

        tvGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Signin();

            }
        });

    }

//    @Override
//    public void onBackPressed() {
//
//        progressDialog.dismiss();
//    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.daftar:
                startActivity(new Intent(this, DaftarAkun.class));
                break;
            case R.id.btnMasuk:
                userLogin();

                break;

            case R.id.lupaPass:
                startActivity(new Intent(this, ForgotPassword.class));
                break;
        }
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
        Intent intent = new Intent(getApplicationContext(), NavBar.class);
        startActivity(intent);
    }


    private void userLogin() {
        String email = etemail.getText().toString().trim();
        String password = etpassword.getText().toString().trim();

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


//
//        progressDialog = new ProgressDialog(login.this);
//        progressDialog.show();
//        progressDialog.setContentView(R.layout.progress_dialog);
//        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);


//        progressBar.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        user = FirebaseAuth.getInstance().getCurrentUser();
                        startActivity(new Intent(Login.this, NavBar.class));
//                        progressDialog.hide();


                    } else {
                        Toast.makeText(Login.this, "Akun belum terdaftar / Password Salah", Toast.LENGTH_LONG).show();
//                        progressBar.setVisibility(View.GONE);
//                        progressDialog.hide(); // tambahan
                    }
                });
    }
}