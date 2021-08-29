package com.example.everythingstore.seller.post_login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.everythingstore.R;
import com.example.everythingstore.buyer.post_login.buy_items.BuyerItemModel;
import com.example.everythingstore.buyer.post_login.buy_items.BuyerItemRecyclerViewAdapter;
import com.example.everythingstore.seller.post_login.AddItems.AddItemModel;
import com.example.everythingstore.seller.post_login.AddItems.AddItems;
import com.example.everythingstore.seller.post_login.History.History;
import com.example.everythingstore.seller.post_login.History.HistoryRecyclerViewAdapter;
import com.example.everythingstore.seller.post_login.Your_Items.SellerItemModel;
import com.example.everythingstore.seller.post_login.Your_Items.SellerItemRecyclerViewAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.pixplicity.easyprefs.library.Prefs;

import java.util.ArrayList;

public class PostSellerLogin extends AppCompatActivity {
    ArrayList<AddItemModel> sellerItemsList;

    @Override
    public void onBackPressed() {

    }

    ActionBarDrawerToggle actionBarDrawerToggle;
    DrawerLayout navDrawer;
    ImageButton addItemNavBar,historyNavBar,yourItemsNavBar,signOutNavBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_seller_login);

        addItemNavBar=findViewById(R.id.add_item_seller_nav);
        historyNavBar=findViewById(R.id.history_buyer_nav);
        yourItemsNavBar=findViewById(R.id.your_items_seller_nav);
        navDrawer = findViewById(R.id.my_drawer_layout_seller);
        signOutNavBar=findViewById(R.id.sign_out_seller_nav);

        RecyclerView recyclerView=findViewById(R.id.seller_items_recycler_view);
        DatabaseReference root= FirebaseDatabase.getInstance("https://everything-store-2137d-default-rtdb.asia-southeast1.firebasedatabase.app").getReference("Items");
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        sellerItemsList=new ArrayList<>();
        SellerItemRecyclerViewAdapter sellerItemRecyclerViewAdapter=new SellerItemRecyclerViewAdapter(this,sellerItemsList);

        recyclerView.setAdapter(sellerItemRecyclerViewAdapter);

        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                sellerItemsList.clear();
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    String ref=dataSnapshot.child("itemID").getValue(String.class);
                    if(ref.substring(0,12).toString().equals(Prefs.getString(getString(R.string.PrefsSellerPhone)))) {
                        AddItemModel tempItem=dataSnapshot.getValue(AddItemModel.class);

                    sellerItemsList.add(tempItem);
                        sellerItemRecyclerViewAdapter.notifyDataSetChanged();


                    }
//
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


//        RecyclerView recyclerView=findViewById(R.id.seller_items_recycler_view);
//
////        sample array of items for seller
//        ArrayList<SellerItemModel> sellerItemModelArrayList =new ArrayList<SellerItemModel>();
//        String descSample="This T-Shirts made of our high end 100% Polyester Fabric which is skin-friendly, comfortable, breathable and quick drying with high-quality sublimation print that never fades out. \n" +
//                "Sizes available: XS M XXL ";
//        sellerItemModelArrayList.add(new SellerItemModel(getResources().getIdentifier("tshirt1","drawable",getPackageName()),"Indian Tshirt",descSample,1000));
//        sellerItemModelArrayList.add(new SellerItemModel(getResources().getIdentifier("tshirt2","drawable",getPackageName()),"US POLO Tshirt",descSample,100));
//        sellerItemModelArrayList.add(new SellerItemModel(getResources().getIdentifier("tshirt3","drawable",getPackageName()),"American Express Tshirt",descSample,500));
//        sellerItemModelArrayList.add(new SellerItemModel(getResources().getIdentifier("tshirt4","drawable",getPackageName()),"Marco Polo Premium Tshirt",descSample,1120));
//        sellerItemModelArrayList.add(new SellerItemModel(getResources().getIdentifier("tshirt5","drawable",getPackageName()),"Lamda Tshirt Sweat Proof Design for Sports",descSample,1900));
//
//        //attaching the adapter and enabling recycler view.
//        SellerItemRecyclerViewAdapter sellerItemRecyclerViewAdapter=new SellerItemRecyclerViewAdapter(this, sellerItemModelArrayList);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
//        recyclerView.setLayoutManager(linearLayoutManager);
//        Log.d("PostSellerLogin", "onCreate: before calling rv.setAdapter");
//        recyclerView.setAdapter(sellerItemRecyclerViewAdapter);



        ImageButton hamButton=findViewById(R.id.hamButtonSeller);
        hamButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // If the navigation drawer is not open then open it, if its already open then close it.
                if(!navDrawer.isDrawerOpen(GravityCompat.START))
                    navDrawer.openDrawer(GravityCompat.START);
                else navDrawer.closeDrawer(GravityCompat.END);
            }
        });
        yourItemsNavBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navDrawer.closeDrawer(GravityCompat.START);
            }
        });
        addItemNavBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(PostSellerLogin.this, AddItems.class);
                startActivity(i);
            }
        });

        historyNavBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(PostSellerLogin.this, History.class);
                startActivity(i);
            }
        });

        signOutNavBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Prefs.putString(getString(R.string.PrefsSellerName),"null");
                Prefs.putString(getString(R.string.PrefsSellerPhone),"null");

                PostSellerLogin.this.finish();
            }
        });

    }
}