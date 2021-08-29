package com.example.everythingstore.home_screen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


import com.example.everythingstore.buyer.BuyerLogin;
import com.example.everythingstore.R;
import com.example.everythingstore.database_helper.DBHelper;
import com.example.everythingstore.seller.SellerLogin;
import com.example.everythingstore.seller.post_login.AddItems.AddItems;
import com.example.everythingstore.seller.register.SellerSignUpModel;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.joda.time.DateTime;

import java.util.Locale;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //we have to call load locale before setContentView to load the prev selected lang
        //from shared prefs and set it as the current lang.
        loadLocale();
        setContentView(R.layout.activity_home);




//sample for add item testing
//        startActivity(new Intent(HomeActivity.this, AddItems.class));

        ImageButton buyerButton=findViewById(R.id.buyer_button_home);
        ImageButton sellerButton=findViewById(R.id.seller_button_home);
        Button langButton=findViewById(R.id.language_change_button);

        buyerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(HomeActivity.this, BuyerLogin.class);
                startActivity(i);
//                throw new RuntimeException("Test Crash");
            }
        });
        sellerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(HomeActivity.this, SellerLogin.class);
                startActivity(i);
            }
        });
        langButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button mBottton = findViewById(R.id.language_change_button);
                mBottton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //shows the lang selection bottom sheet
                        showBottomSheetDialog();
                    }
                });

            }
        });

        DBHelper.logAllSellersObjects();

    }

    private void showBottomSheetDialog() {
        Log.d("bottomup", "showBottomSheetDialog: entered");
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(R.layout.lang_select_bottom_up_sheet);
        //the below variables are atached to the bottom sheet layout file along with their listeners
         TextView eng = bottomSheetDialog.findViewById(R.id.eng);
        TextView fre = bottomSheetDialog.findViewById(R.id.fr);
        TextView hin = bottomSheetDialog.findViewById(R.id.hin);
        Log.d("bottomup", "showBottomSheetDialog: before show");

        eng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setLocale("en");
                recreate();
            }
        });
        hin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setLocale("hi");
                recreate();
            }
        });

        fre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setLocale("fr");
                recreate();
            }
        });

        //Then we call the show bottom sheet
        bottomSheetDialog.show();

    }

    private void createLangPopup() {
        //add the list of languages here.
        final String[] listItems={"English","Hindi"};
        AlertDialog.Builder mBuilder=new AlertDialog.Builder(HomeActivity.this);
        mBuilder.setTitle("Choose Language...");
        mBuilder.setSingleChoiceItems(listItems, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(i==0){
                    setLocale("en");
                    recreate();
                }
                else if(i==1){
                    setLocale("hi");
                    recreate();
                }
                else if(i==2){
                    setLocale("fr");
                    recreate();
                }
                //to dismiss the bottom sheet after selection
                dialogInterface.dismiss();
            }
        });
        //we build and show the bottom sheet
        AlertDialog mDialog=mBuilder.create();
        mDialog.show();
    }

    private void setLocale(String lang) {
        //this function sets the locale based on the selection in bottom up sheet and
        //refresh the layout using the recreate in the createLangPopup() and also set the
        //desired lang inside the sharedPrefs
        Locale locale =new Locale(lang);
        Locale.setDefault(locale);
        Configuration config=new Configuration();
        config.locale=locale;
        getBaseContext().getResources().updateConfiguration(config,getBaseContext().getResources().getDisplayMetrics());
        Log.d("lang", "setLocale: locale="+locale);
        //save to sharedPrefs
        SharedPreferences.Editor editor=getSharedPreferences("Settings",MODE_PRIVATE).edit();
        editor.putString("My_Lang", lang);
        editor.apply();

    }

    public void loadLocale(){
        //this func fetches the locale from the shared prefs TAG caled My_Lang.
        Log.d("lang", "loadLocale: inside loadlocale");
        SharedPreferences prefs=getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        //fetches the set locale or the default string is en for english
        String language=prefs.getString("My_Lang","en");
        setLocale(language);
        Toast.makeText(HomeActivity.this,"Loaded locale ="+language,Toast.LENGTH_SHORT);
        Log.d("lang", "loadLocale: loading ="+language);

    }


}