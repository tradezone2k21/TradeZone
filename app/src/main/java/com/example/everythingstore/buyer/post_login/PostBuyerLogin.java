package com.example.everythingstore.buyer.post_login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.everythingstore.R;
import com.example.everythingstore.buyer.post_login.buy_items.BuyerItemModel;
import com.example.everythingstore.buyer.post_login.buy_items.BuyerItemRecyclerViewAdapter;
import com.example.everythingstore.buyer.post_login.cart.Cart;
import com.example.everythingstore.buyer.post_login.your_orders.YourOrders;
import com.example.everythingstore.seller.post_login.AddItems.AddItemModel;
import com.example.everythingstore.seller.post_login.Your_Items.ItemClickActivity;
import com.example.everythingstore.seller.post_login.Your_Items.SellerItemRecyclerViewAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.pixplicity.easyprefs.library.Prefs;

import java.util.ArrayList;

public class PostBuyerLogin extends AppCompatActivity {
    ActionBarDrawerToggle actionBarDrawerToggle;
    SwitchCompat switchCompat;
    DrawerLayout navDrawer;
    ImageButton signout;
    ArrayList<AddItemModel> buyItemsList;
    TextView cartCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_buyer_login);



        signout=findViewById(R.id.sign_out_buyer_nav);
        cartCount=findViewById(R.id.cart_count_buy_items_page);

        RecyclerView recyclerView=findViewById(R.id.buyer_items_recycler_view);
        DatabaseReference root= FirebaseDatabase.getInstance("https://everything-store-2137d-default-rtdb.asia-southeast1.firebasedatabase.app").getReference("Items");
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        buyItemsList=new ArrayList<>();
        BuyerItemRecyclerViewAdapter buyerItemRecyclerViewAdapter=new BuyerItemRecyclerViewAdapter(this,buyItemsList);
        recyclerView.setAdapter(buyerItemRecyclerViewAdapter);

        //as soon as buyer logs in we empty cart
        DatabaseReference deleteItem= FirebaseDatabase.getInstance("https://everything-store-2137d-default-rtdb.asia-southeast1.firebasedatabase.app").getReference("Cart");
        deleteItem.removeValue();



        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                buyItemsList.clear();
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
//                    String ref=dataSnapshot.child("itemID").getValue(String.class);

                        AddItemModel tempItem=dataSnapshot.getValue(AddItemModel.class);

                        buyItemsList.add(tempItem);
                        buyerItemRecyclerViewAdapter.notifyDataSetChanged();

//
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

//        sample array of items for buyer
//        ArrayList<BuyerItemModel> buyerItemModelArrayList =new ArrayList<BuyerItemModel>();
//        String descSample="This T-Shirts made of our high end 100% Polyester Fabric which is skin-friendly, comfortable, breathable and quick drying with high-quality sublimation print that never fades out. \n" +
//                "Sizes available: XS M XXL ";
//        buyerItemModelArrayList.add(new BuyerItemModel(getResources().getIdentifier("tshirt1","drawable",getPackageName()),"Indian Tshirt",descSample,1000));
//        buyerItemModelArrayList.add(new BuyerItemModel(getResources().getIdentifier("tshirt2","drawable",getPackageName()),"US POLO Tshirt",descSample,100));
//        buyerItemModelArrayList.add(new BuyerItemModel(getResources().getIdentifier("tshirt3","drawable",getPackageName()),"American Express Tshirt",descSample,500));
//        buyerItemModelArrayList.add(new BuyerItemModel(getResources().getIdentifier("tshirt4","drawable",getPackageName()),"Marco Polo Premium Tshirt",descSample,1120));
//        buyerItemModelArrayList.add(new BuyerItemModel(getResources().getIdentifier("tshirt5","drawable",getPackageName()),"Lamda Tshirt Sweat Proof Design for Sports",descSample,1900));
//
//        //attaching the adapter and enabling recycler view.
//        BuyerItemRecyclerViewAdapter buyerItemRecyclerViewAdapter=new BuyerItemRecyclerViewAdapter(this, buyerItemModelArrayList);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
//        recyclerView.setLayoutManager(linearLayoutManager);
//        recyclerView.setAdapter(buyerItemRecyclerViewAdapter);



//simple ham button to open  the custom navigation drawer
        ImageButton hamButton=findViewById(R.id.hamButtonBuyer);
        hamButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 navDrawer = findViewById(R.id.my_drawer_layout_seller);
                // If the navigation drawer is not open then open it, if its already open then close it.
                if(!navDrawer.isDrawerOpen(GravityCompat.START))
                    navDrawer.openDrawer(GravityCompat.START);
                else navDrawer.closeDrawer(GravityCompat.START);// i changed from start to end
            }
        });

        ImageButton buyItemsNavButton=findViewById(R.id.buy_items_buyer_nav);
        ImageButton cartNavButton=findViewById(R.id.cart_buyer_nav);
        ImageButton yourOrdersNavButton=findViewById(R.id.your_orders_buyer_nav);
        ImageButton signOutNavButton=findViewById(R.id.sign_out_buyer_nav);


        buyItemsNavButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(PostBuyerLogin.this,"Buy Items",Toast.LENGTH_SHORT).show();
                navDrawer.closeDrawer(GravityCompat.START);
            }
        });
        cartNavButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(PostBuyerLogin.this,"Cart",Toast.LENGTH_SHORT).show();
                Intent i=new Intent(PostBuyerLogin.this, Cart.class);
                startActivity(i);
            }
        });
        yourOrdersNavButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(PostBuyerLogin.this,"Your Orders",Toast.LENGTH_SHORT).show();
                Intent i=new Intent(PostBuyerLogin.this, YourOrders.class);
                startActivity(i);
            }
        });
        signOutNavButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(PostBuyerLogin.this,"Sign out",Toast.LENGTH_SHORT).show();
            }
        });

        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PostBuyerLogin.this.finish();
            }
        });

        DatabaseReference cartReference=FirebaseDatabase.getInstance("https://everything-store-2137d-default-rtdb.asia-southeast1.firebasedatabase.app").getReference("Cart");;

        cartReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.d("ItemClickActivityBuyer", "onDataChange: cart="+snapshot.getChildrenCount());
                cartCount.setText(Long.toString(snapshot.getChildrenCount()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
//  switch toggle for demo of login and logout
//        SwitchCompat switchButton=findViewById(R.id.acc_toggle);
//        switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
//        {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
//            {
//
//                if (isChecked) {
//                   Toast.makeText(PostBuyerLogin.this,"Signed in",Toast.LENGTH_SHORT).show();
//                }
//                else {
//                    Toast.makeText(PostBuyerLogin.this,"Signing out",Toast.LENGTH_SHORT).show();
//                }
//            }
//        }
//    );


    }

    @Override
    public void onBackPressed() {

    }
}