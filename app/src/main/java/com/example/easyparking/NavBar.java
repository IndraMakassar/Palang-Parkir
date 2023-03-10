package com.example.easyparking;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class NavBar extends AppCompatActivity {
    private int selectedTab = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_bar);

        final LinearLayout homeLayout = findViewById(R.id.homeLayout);
        final LinearLayout profileLayout = findViewById(R.id.profileLayout);

        final ImageView homeImage = findViewById(R.id.homeImage);
        final ImageView profileImage = findViewById(R.id.profileImage);


        final TextView homeTxt = findViewById(R.id.homeTxt);
        final TextView profileTxt = findViewById(R.id.profileTxt);


        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragmentView, PilihTempat.class, null)
                .commit();


        profileTxt.setVisibility(View.GONE);

        profileImage.setImageResource(R.drawable.profile);

        profileLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));

        homeTxt.setVisibility(View.VISIBLE);
        homeImage.setImageResource(R.drawable.home);
        homeLayout.setBackgroundResource(R.drawable.round_back_home);

        ScaleAnimation scaleAnimation = new ScaleAnimation(1f, 1f, 0.8f, 1f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f);
        scaleAnimation.setDuration(200);
        scaleAnimation.setFillAfter(true);
        homeLayout.startAnimation(scaleAnimation);


        selectedTab = 1;


        homeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedTab != 1) {
                    getSupportFragmentManager().beginTransaction()
                            .setReorderingAllowed(true)
                            .replace(R.id.fragmentView, PilihTempat.class, null)
                            .commit();

//                                orderTxt.setVisibility(View.GONE);
//                        historyTxt.setVisibility(View.GONE);
                    profileTxt.setVisibility(View.GONE);

//                                orderImage.setImageResource(R.drawable.order_icon);
//                        historyImage.setImageResource(R.drawable.history_icon);
                    profileImage.setImageResource(R.drawable.profile);

//                                orderLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
//                        historyLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    profileLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));

                    homeTxt.setVisibility(View.VISIBLE);
                    homeImage.setImageResource(R.drawable.home);
                    homeLayout.setBackgroundResource(R.drawable.round_back_home);

                    ScaleAnimation scaleAnimation = new ScaleAnimation(1f, 1f, 0.8f, 1f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f);
                    scaleAnimation.setDuration(200);
                    scaleAnimation.setFillAfter(true);
                    homeLayout.startAnimation(scaleAnimation);

                    selectedTab = 1;
                }

            }
        });


        profileLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (selectedTab != 2) {
                    getSupportFragmentManager().beginTransaction()
                            .setReorderingAllowed(true)
                            .replace(R.id.fragmentView, Profile.class, null)
                            .commit();


                    homeTxt.setVisibility(View.GONE);
//                    orderTxt.setVisibility(View.GONE);
                    profileTxt.setVisibility(View.GONE);

                    homeImage.setImageResource(R.drawable.home);
//                    orderImage.setImageResource(R.drawable.order_icon);
                    profileImage.setImageResource(R.drawable.profile);

                    homeLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
//                    orderLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    profileLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));

                    profileTxt.setVisibility(View.VISIBLE);
                    profileImage.setImageResource(R.drawable.profile);
                    profileLayout.setBackgroundResource(R.drawable.round_back_home);

                    ScaleAnimation scaleAnimation = new ScaleAnimation(1f, 1f, 0.8f, 1f, Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
                    scaleAnimation.setDuration(200);
                    scaleAnimation.setFillAfter(true);
                    profileLayout.startAnimation(scaleAnimation);

                    selectedTab = 2;
                }

            }
        });


    }
}