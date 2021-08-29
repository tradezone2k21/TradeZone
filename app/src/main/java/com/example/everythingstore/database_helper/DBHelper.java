package com.example.everythingstore.database_helper;

import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.everythingstore.buyer.post_login.buy_items.ItemClickActivity;
import com.example.everythingstore.seller.post_login.AddItems.AddItemModel;
import com.example.everythingstore.seller.post_login.AddItems.AddItems;
import com.example.everythingstore.seller.register.SellerSignUpModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class DBHelper {
    public static void logAllSellersObjects(){
        DatabaseReference dbr= FirebaseDatabase.getInstance("https://everything-store-2137d-default-rtdb.asia-southeast1.firebasedatabase.app").getReference("Sellers");
        dbr.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.d("getAllSellerObjects", "onDataChange: started display of all objects ");
                for(DataSnapshot item_snapshot:snapshot.getChildren()) {

//                    Log.d("DB test name ",item_snapshot.child("name").getValue().toString());
//                    Log.d("DB test phone ",item_snapshot.child("phone").getValue().toString());
                    SellerSignUpModel sample=item_snapshot.getValue(SellerSignUpModel.class);
                    Log.d("DBtesting",sample.toString());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    private void uploadItemToSellerHistoryTable(AddItemModel itemToUpload) {
        DatabaseReference root= FirebaseDatabase.getInstance("https://everything-store-2137d-default-rtdb.asia-southeast1.firebasedatabase.app").getReference("SellerHistory");

        root.child(root.push().getKey()).setValue(itemToUpload);
    }
    private void uploadItemToItemsTable(AddItemModel itemToUpload) {
        DatabaseReference root= FirebaseDatabase.getInstance("https://everything-store-2137d-default-rtdb.asia-southeast1.firebasedatabase.app").getReference("SellerHistory");

        root.child(root.push().getKey()).setValue(itemToUpload);
    }
    private String StringconvertImageArrayToString(List<Uri>  imageArrayFirebaseURI) {
//        ArrayList<String> arrToStr=new ArrayList<String>();
//        Log.d("GsonTest ", "onCreate: "+sample);

        Log.d("GSON before convert ",  ""+imageArrayFirebaseURI);
        String json=new Gson().toJson(imageArrayFirebaseURI);
        Log.d("GSON after convert ",  ""+json);
//        ArrayList<> sample2=new ArrayList<String>();
//        sample2=new Gson().fromJson(json,new TypeToken<List<String>>(){}.getType());
//        Log.d("GsonTest convert", "onCreate: "+sample2);
        return json;
    }
    public static ArrayList<String> StringconvertStringToArrayList(String json) {
//        ArrayList<String> arrToStr=new ArrayList<String>();
//        Log.d("GsonTest ", "onCreate: "+sample);

//        Log.d("GSON before convert ",  ""+imageArrayFirebaseURI);
//        String json=new Gson().toJson(imageArrayFirebaseURI);
//        Log.d("GSON after convert ",  ""+json);
        ArrayList<String> sample2=new ArrayList<String>();
        sample2=new Gson().fromJson(json,new TypeToken<List<String>>(){}.getType());
        Log.d("Gson converted to array", "onCreate: "+sample2);
        return sample2;
    }


}
