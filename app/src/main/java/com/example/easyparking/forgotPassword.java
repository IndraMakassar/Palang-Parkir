package com.example.easyparking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class forgotPassword extends AppCompatActivity {

    private EditText etEmail;
    private TextView sendButton;
    private ImageView back;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        etEmail = (EditText) findViewById(R.id.inputEmail);
        sendButton = (TextView) findViewById(R.id.btnbooking);

        back = (ImageView) findViewById(R.id.backImage);

        auth = FirebaseAuth.getInstance();


        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetPassword();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(forgotPassword.this, login.class));
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }
        });
    }


    private void resetPassword() {
        String email = etEmail.getText().toString().trim();

        if (email.isEmpty()) {
            etEmail.setError("Email is required");
            etEmail.requestFocus();
            return;
        }

        auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if(task.isSuccessful()){
                    Toast.makeText(forgotPassword.this, "Check your email to reset your password" , Toast.LENGTH_LONG).show();
                    Toast.makeText(forgotPassword.this, "Masuk di Gmail --> Spam" , Toast.LENGTH_LONG).show();
//                    progressBar.setVisibility(View.GONE);
//                    progressDialog.hide();

                } else{
                    Toast.makeText(forgotPassword.this, "Something wrong, Try Again", Toast.LENGTH_LONG).show();
//                    progressBar.setVisibility(View.GONE);
//                    progressDialog.hide();
                }
            }
        });

    }

}