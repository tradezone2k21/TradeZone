package com.example.everythingstore.seller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.everythingstore.R;
import com.example.everythingstore.seller.post_login.PostSellerLogin;
import com.example.everythingstore.seller.register.SellerRegister;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.pixplicity.easyprefs.library.Prefs;

public class SellerLogin extends AppCompatActivity {
EditText phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_login);
        new Prefs.Builder()
                .setContext(this)
                .setMode(ContextWrapper.MODE_PRIVATE)
                .setPrefsName(getPackageName())
                .setUseDefaultSharedPreference(true)
                .build();

        phone=findViewById(R.id.editTextPhone);
        Button login=findViewById(R.id.login_button_seller);
        TextView register=findViewById(R.id.register_seller_text_view);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(SellerLogin.this, SellerRegister.class);
                startActivity(i);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                Intent i=new Intent(SellerLogin.this, PostSellerLogin.class);
//                startActivity(i);

                DatabaseReference reference= FirebaseDatabase.getInstance("https://everything-store-2137d-default-rtdb.asia-southeast1.firebasedatabase.app").getReference("Sellers");
                Query checkUser=reference.orderByChild("phone").equalTo(phone.getText().toString());

                checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                Log.d("Firebase", "checking if user exists> "+"FDB test usernameExist?="+snapshot.child("pincode")+" "+snapshot.toString());
                        if(snapshot.exists()){
                            String userName=snapshot.child(phone.getText().toString()).child("name").getValue(String.class);
                            Prefs.putString(getString(R.string.PrefsSellerName),userName);
                            Prefs.putString(getString(R.string.PrefsSellerPhone),phone.getText().toString());

                            Intent i=new Intent(SellerLogin.this, PostSellerLogin.class);
                            startActivity(i);
                        }
                        else{
                            Toast.makeText(SellerLogin.this,"Please register before logging in.", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });


    }
}