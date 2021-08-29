package com.example.everythingstore.buyer.post_login.your_orders;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.everythingstore.R;
import com.example.everythingstore.buyer.post_login.cart.CartItemModel;
import com.example.everythingstore.buyer.post_login.cart.CartRecyclerViewAdapter;

import java.util.ArrayList;

public class YourOrders extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_orders);

        RecyclerView recyclerView=findViewById(R.id.your_orders_recycler_view_container);

//        sample array of items for buyer
        ArrayList<YourOrdersItemModel> yourOrdersItemModelArrayList =new ArrayList<YourOrdersItemModel>();
        String descSample="This T-Shirts made of our high end 100% Polyester Fabric which is skin-friendly, comfortable, breathable and quick drying with high-quality sublimation print that never fades out. \n" +
                "Sizes available: XS M XXL ";
        yourOrdersItemModelArrayList.add(new YourOrdersItemModel(getResources().getIdentifier("tshirt1","drawable",getPackageName()),"Indian Tshirt",descSample,1000));
        yourOrdersItemModelArrayList.add(new YourOrdersItemModel(getResources().getIdentifier("tshirt2","drawable",getPackageName()),"US POLO Tshirt",descSample,100));
        yourOrdersItemModelArrayList.add(new YourOrdersItemModel(getResources().getIdentifier("tshirt3","drawable",getPackageName()),"American Express Tshirt",descSample,500));
        yourOrdersItemModelArrayList.add(new YourOrdersItemModel(getResources().getIdentifier("tshirt4","drawable",getPackageName()),"Marco Polo Premium Tshirt",descSample,1120));
        yourOrdersItemModelArrayList.add(new YourOrdersItemModel(getResources().getIdentifier("tshirt5","drawable",getPackageName()),"Lamda Tshirt Sweat Proof Design for Sports",descSample,1900));

//        attaching the adapter and enabling recycler view.
        YourOrdersRecyclerViewAdapter yourOrdersRecyclerViewAdapter=new YourOrdersRecyclerViewAdapter(this, yourOrdersItemModelArrayList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(yourOrdersRecyclerViewAdapter);
    }
}