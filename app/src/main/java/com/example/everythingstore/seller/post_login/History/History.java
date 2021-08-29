package com.example.everythingstore.seller.post_login.History;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.everythingstore.R;
import com.example.everythingstore.seller.post_login.AddItems.AddItemModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.pixplicity.easyprefs.library.Prefs;


import java.util.ArrayList;
import java.util.List;

public class History extends AppCompatActivity {

    ArrayList<AddItemModel> sellerItemsHistoryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        RecyclerView recyclerView=findViewById(R.id.history_recycler_view_container);
        DatabaseReference root= FirebaseDatabase.getInstance("https://everything-store-2137d-default-rtdb.asia-southeast1.firebasedatabase.app").getReference("SellerHistory");
        recyclerView.setHasFixedSize(true);
        //        sample array of items for buyer

//        ArrayList<HistoryItemModel> historyItemModelArrayList =new ArrayList<HistoryItemModel>();
//        String descSample="This T-Shirts made of our high end 100% Polyester Fabric which is skin-friendly, comfortable, breathable and quick drying with high-quality sublimation print that never fades out. \n" +
//                "Sizes available: XS M XXL ";
//        historyItemModelArrayList.add(new HistoryItemModel(getResources().getIdentifier("tshirt1","drawable",getPackageName()),"Indian Tshirt",descSample,1000));
//        historyItemModelArrayList.add(new HistoryItemModel(getResources().getIdentifier("tshirt2","drawable",getPackageName()),"US POLO Tshirt",descSample,100));
//        historyItemModelArrayList.add(new HistoryItemModel(getResources().getIdentifier("tshirt3","drawable",getPackageName()),"American Express Tshirt",descSample,500));
//        historyItemModelArrayList.add(new HistoryItemModel(getResources().getIdentifier("tshirt4","drawable",getPackageName()),"Marco Polo Premium Tshirt",descSample,1120));
//        historyItemModelArrayList.add(new HistoryItemModel(getResources().getIdentifier("tshirt5","drawable",getPackageName()),"Lamda Tshirt Sweat Proof Design for Sports",descSample,1900));

//        attaching the adapter and enabling recycler view.
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        sellerItemsHistoryList=new ArrayList<>();
        HistoryRecyclerViewAdapter historyRecyclerViewAdapter=new HistoryRecyclerViewAdapter(this,sellerItemsHistoryList);

        recyclerView.setAdapter(historyRecyclerViewAdapter);

        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                sellerItemsHistoryList.clear();
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    String ref=dataSnapshot.child("itemID").getValue(String.class);

//                    Log.d("phone no test= ", "onDataChange: size="+ref.length()+" -> "+ref.substring(0,12));
                    if(ref.substring(0,12).toString().equals(Prefs.getString(getString(R.string.PrefsSellerPhone)))) {
                        AddItemModel tempItem = dataSnapshot.getValue(AddItemModel.class);

                        sellerItemsHistoryList.add(tempItem);
                        historyRecyclerViewAdapter.notifyDataSetChanged();

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}