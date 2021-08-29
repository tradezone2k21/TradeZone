package com.example.everythingstore.buyer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.everythingstore.R;
import com.example.everythingstore.buyer.post_login.PostBuyerLogin;

public class OtpBuyerActivty extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_buyer);

        EditText otpDigit1=findViewById(R.id.otp_digit_1);
        EditText otpDigit2=findViewById(R.id.otp_digit_2);
        EditText otpDigit3=findViewById(R.id.otp_digit_3);
        EditText otpDigit4=findViewById(R.id.otp_digit_4);

        Button confirmOtp=findViewById(R.id.get_otp_button);
        confirmOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(OtpBuyerActivty.this, PostBuyerLogin.class);
                startActivity(i);
            }
        });

    }
}