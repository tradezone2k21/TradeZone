package com.example.everythingstore.seller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.everythingstore.R;
import com.example.everythingstore.seller.post_login.PostSellerLogin;

public class OtpSellerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_seller);
        Button confirmOtp=findViewById(R.id.confirm_otp_button_seller);
        confirmOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(OtpSellerActivity.this, PostSellerLogin.class);
                startActivity(i);
            }
        });
    }






}