package com.example.everythingstore.buyer.post_login.cart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.everythingstore.R;
import com.example.everythingstore.buyer.post_login.buy_items.BuyerItemModel;
import com.example.everythingstore.buyer.post_login.buy_items.BuyerItemRecyclerViewAdapter;
import com.example.everythingstore.buyer.post_login.cart.Checkout.ShippingBillingAddress;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Cart extends AppCompatActivity {
Button checkout;
TextView cartCount,clearCart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        RecyclerView recyclerView=findViewById(R.id.cart_recycler_view_container);
        checkout=findViewById(R.id.cart_to_checkout_button);
        cartCount=findViewById(R.id.cart_count_cart_page);
        clearCart=findViewById(R.id.clear_cart);

//        sample array of items for buyer
        ArrayList<CartItemModel> cartItemModelArrayList =new ArrayList<CartItemModel>();
        String descSample="This T-Shirts made of our high end 100% Polyester Fabric which is skin-friendly, comfortable, breathable and quick drying with high-quality sublimation print that never fades out. \n" +
                "Sizes available: XS M XXL ";
        cartItemModelArrayList.add(new CartItemModel(getResources().getIdentifier("tshirt1","drawable",getPackageName()),"Indian Tshirt",descSample,1000));
        cartItemModelArrayList.add(new CartItemModel(getResources().getIdentifier("tshirt2","drawable",getPackageName()),"US POLO Tshirt",descSample,100));
        cartItemModelArrayList.add(new CartItemModel(getResources().getIdentifier("tshirt3","drawable",getPackageName()),"American Express Tshirt",descSample,500));
        cartItemModelArrayList.add(new CartItemModel(getResources().getIdentifier("tshirt4","drawable",getPackageName()),"Marco Polo Premium Tshirt",descSample,1120));
        cartItemModelArrayList.add(new CartItemModel(getResources().getIdentifier("tshirt5","drawable",getPackageName()),"Lamda Tshirt Sweat Proof Design for Sports",descSample,1900));

//        attaching the adapter and enabling recycler view.
        CartRecyclerViewAdapter cartItemRecyclerViewAdapter=new CartRecyclerViewAdapter(this, cartItemModelArrayList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(cartItemRecyclerViewAdapter);

        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Cart.this, ShippingBillingAddress.class);
                startActivity(i);
            }
        });

        DatabaseReference cartReference= FirebaseDatabase.getInstance("https://everything-store-2137d-default-rtdb.asia-southeast1.firebasedatabase.app").getReference("Cart");;

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
       clearCart.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Toast.makeText(Cart.this, "Cart cleared!", Toast.LENGTH_SHORT).show();
               DatabaseReference deleteItem= FirebaseDatabase.getInstance("https://everything-store-2137d-default-rtdb.asia-southeast1.firebasedatabase.app").getReference("Cart");
               deleteItem.removeValue();
           }
       });

    }




}