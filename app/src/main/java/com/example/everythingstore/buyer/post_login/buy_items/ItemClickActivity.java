package com.example.everythingstore.buyer.post_login.buy_items;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.everythingstore.R;
import com.example.everythingstore.database_helper.DBHelper;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;

public class ItemClickActivity extends AppCompatActivity {
    TextView desc,cost,name,cartCount;
    ImageView img;
    String item_id;
    ImageButton prevImg,nextImg;
    ArrayList<String> imageUriArrayList;
    int current_image_pos_display=0;
    Button addToCart;
    DisplayImageOptions options;
    ImageLoader imageLoader;
    DatabaseReference root;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_click);
        name=findViewById(R.id.item_click_name_buyer );
        cost=findViewById(R.id.item_click_cost_buyer);
        desc=findViewById(R.id.item_click_desc_buyer);
        img=findViewById(R.id.item_image_view_buyer);
        prevImg=findViewById(R.id.prev_image_buyer_item);
        nextImg=findViewById(R.id.next_image_buyer_item);
        addToCart=findViewById(R.id.add_to_cart_buyer);
        cartCount=findViewById(R.id.cart_count_item_click_page);
        root=FirebaseDatabase.getInstance("https://everything-store-2137d-default-rtdb.asia-southeast1.firebasedatabase.app").getReference("Cart");


        options = new DisplayImageOptions.Builder()
                .showStubImage(R.drawable.placeholder_image_loading)
                .showImageForEmptyUri(R.drawable.placeholder_image_loading)
                .cacheInMemory()
                .cacheOnDisc()
                .build();
        imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(getApplicationContext()));
        getIncomingIntent();

        prevImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(imageUriArrayList.size()>0){
                    current_image_pos_display=(current_image_pos_display+1)%imageUriArrayList.size();
                    if(current_image_pos_display<0)
                        current_image_pos_display=imageUriArrayList.size()-1;
                    imageLoader.displayImage(imageUriArrayList.get(current_image_pos_display), img, options, null);
                }
            }
        });
        nextImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(imageUriArrayList.size()>0){
                    Log.d("ItemClickActivity ", "onClick: Image next clicked");
                    current_image_pos_display=(current_image_pos_display+1)%imageUriArrayList.size();
                    imageLoader.displayImage(imageUriArrayList.get(current_image_pos_display), img, options, null);
                }
            }
        });

        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                root.child(item_id).setValue(item_id);
                Toast.makeText(ItemClickActivity.this,"adding to cart",Toast.LENGTH_SHORT).show();

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


    }

    private void getIncomingIntent(){
        String item_name=getIntent().getStringExtra("item_name");
        String item_desc=getIntent().getStringExtra("item_desc");
        String item_cost=getIntent().getStringExtra("item_cost");
        String imgUriString=getIntent().getStringExtra("item_imgURIs");
        item_id=getIntent().getStringExtra("item_id");
        name.setText(item_name);
        desc.setText(item_desc);
        cost.setText("₹"+item_cost);
        imageUriArrayList= DBHelper.StringconvertStringToArrayList(imgUriString);
        imageLoader.displayImage(imageUriArrayList.get(0), img, options, null);




    }
}