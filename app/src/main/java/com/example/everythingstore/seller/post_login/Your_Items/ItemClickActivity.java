package com.example.everythingstore.seller.post_login.Your_Items;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.everythingstore.R;
import com.example.everythingstore.database_helper.DBHelper;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;

public class ItemClickActivity extends AppCompatActivity {

    TextView desc,cost,name;
    ImageView img;
    ImageButton prevImg,nextImg;
    ArrayList<String> imageUriArrayList;
    int current_image_pos_display;
    Button deleteButton;
    String item_id;
    DisplayImageOptions options;
    ImageLoader imageLoader;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_click2);
        name = findViewById(R.id.item_click_name);
        cost = findViewById(R.id.item_click_cost_buyer);
        desc = findViewById(R.id.item_click_desc);
        img = findViewById(R.id.item_image_view);
        prevImg=findViewById(R.id.prev_image_seller_item);
        nextImg=findViewById(R.id.next_image_sell_item);
        deleteButton=findViewById(R.id.delete_listing_seller);

        options = new DisplayImageOptions.Builder()
                .showStubImage(R.drawable.placeholder_image_loading)
                .showImageForEmptyUri(R.drawable.placeholder_image_loading)
                .cacheInMemory()
                .cacheOnDisc()
                .build();
        imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(getApplicationContext()));




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

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference deleteItem= FirebaseDatabase.getInstance("https://everything-store-2137d-default-rtdb.asia-southeast1.firebasedatabase.app").getReference("Items").child(item_id);
                deleteItem.removeValue();
                ItemClickActivity.this.finish();

            }
        });

        getIncomingIntent();
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