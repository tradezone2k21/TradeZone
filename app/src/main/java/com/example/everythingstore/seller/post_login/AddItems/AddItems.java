package com.example.everythingstore.seller.post_login.AddItems;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.net.UriCompat;

import android.content.ContentResolver;
import android.content.ContextWrapper;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.everythingstore.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pixplicity.easyprefs.library.Prefs;

import java.util.ArrayList;
import java.util.List;

public class AddItems extends AppCompatActivity {
ImageButton addImageButton,prevImage,nextImage;
ImageView itemImage;
ProgressBar progressBar;
Button uploadItem;
DatabaseReference root= FirebaseDatabase.getInstance("https://everything-store-2137d-default-rtdb.asia-southeast1.firebasedatabase.app").getReference("Items");
StorageReference reference= FirebaseStorage.getInstance().getReference();
Uri imageUri;
List<Uri> imageUriArrayList=new ArrayList<Uri>();
List<String> imageArrayFirebaseURI=new ArrayList<String>();
int current_image_pos_display=0;
boolean isImageUploadedSuccessfully=true;
EditText itemNameET,itemDescET,itemSizeET,itemCostET,itemQuantityET;
String itemName,itemDesc,itemSize;
Integer itemQuantity;
Float itemCost;
AddItemModel itemToUpload;
String commonKeyForItem;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_items);
        addImageButton=findViewById(R.id.add_image_in_add_item);
        itemImage=findViewById(R.id.add_item_image_view);
        progressBar=findViewById(R.id.add_item_progressBar);
        progressBar.setVisibility(View.INVISIBLE);
        uploadItem=findViewById(R.id.add_button_add_item);
        prevImage=findViewById(R.id.prev_image_add_item);
        nextImage=findViewById(R.id.next_image_add_item);
        itemNameET=findViewById(R.id.item_name_add_item);
        itemDescET=findViewById(R.id.Item_desc_add_item);
        itemSizeET=findViewById(R.id.item_sizes_add_item);
        itemQuantityET=findViewById(R.id.item_quantity_add_item);
        itemCostET=findViewById(R.id.item_cost_add_item);


        new Prefs.Builder()
                .setContext(AddItems.this)
                .setMode(ContextWrapper.MODE_PRIVATE)
                .setPrefsName(getPackageName())
                .setUseDefaultSharedPreference(true)
                .build();

//        Log.d("lkasdjf ", "onCreate: "+Prefs.getString(getString(R.string.PrefsSellerName),"NULL")+" "+Prefs.getString(getString(R.string.PrefsSellerPhone),"NULL"));

        prevImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(imageUriArrayList.size()>0){
                    current_image_pos_display=(current_image_pos_display+1)%imageUriArrayList.size();
                    if(current_image_pos_display<0)
                        current_image_pos_display=imageUriArrayList.size()-1;
                    itemImage.setImageURI(imageUriArrayList.get(current_image_pos_display));
                }
            }
        });
        nextImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(imageUriArrayList.size()>0){
                    current_image_pos_display=(current_image_pos_display+1)%imageUriArrayList.size();
                    itemImage.setImageURI(imageUriArrayList.get(current_image_pos_display));
                }
            }
        });

        addImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent=new Intent();
                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                galleryIntent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE,true);
                startActivityForResult(galleryIntent,2);

            }
        });

        uploadItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadItemDescriptionIntoVariables();


                if(imageUriArrayList.size()>0){
                    uploadToFirebase(imageUriArrayList);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            uploadItemToItemsTable();
                            uploadItemToSellerHistoryTable();
                        }
                    }, 8000);
//                    if(isImageUploadedSuccessfully){
//                        String stringOfImages=StringconvertImageArrayToString();
//                        itemToUpload=new AddItemModel(itemID,itemName,itemDesc,itemSize,itemQuantity,itemCost,stringOfImages);
//
//                        Toast.makeText(AddItems.this, "Uploading the item to db", Toast.LENGTH_SHORT).show();
//                            root.child(root.push().getKey()).setValue(itemToUpload);
////
//                    }
                }else{
                    Toast.makeText(AddItems.this,"Please select images.",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void uploadItemToSellerHistoryTable() {
        DatabaseReference root= FirebaseDatabase.getInstance("https://everything-store-2137d-default-rtdb.asia-southeast1.firebasedatabase.app").getReference("SellerHistory");


        String stringOfImages=StringconvertImageArrayToString();
        itemToUpload=new AddItemModel(commonKeyForItem,itemName,itemDesc,itemSize,itemQuantity,itemCost,stringOfImages);

        Toast.makeText(AddItems.this, "Uploading the Seller History to db", Toast.LENGTH_SHORT).show();
        root.child(commonKeyForItem).setValue(itemToUpload);
    }

    private void uploadItemToItemsTable() {

        commonKeyForItem=Prefs.getString(getString(R.string.PrefsSellerPhone))+root.push().getKey();
        String stringOfImages=StringconvertImageArrayToString();
        itemToUpload=new AddItemModel(commonKeyForItem,itemName,itemDesc,itemSize,itemQuantity,itemCost,stringOfImages);

        Toast.makeText(AddItems.this, "Uploading the item to Items in db", Toast.LENGTH_SHORT).show();

        root.child(commonKeyForItem).setValue(itemToUpload);
    }

    private String StringconvertImageArrayToString() {
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

    private void loadItemDescriptionIntoVariables() {
        itemName=itemNameET.getText().toString();
        itemDesc=itemDescET.getText().toString();
        itemSize=itemSizeET.getText().toString();
        itemQuantity=Integer.valueOf(itemQuantityET.getText().toString());
        itemCost=Float.valueOf(itemCostET.getText().toString());
        Log.d("ADD ITEM MODEL TEST ", itemName+" "+itemDesc+" "+itemSize+" "+itemQuantity+" "+itemCost);
    }

    private void uploadToFirebase(List<Uri> imageUriArrayList) {
        isImageUploadedSuccessfully=true;
        int count=1;
        for(Uri imageUri : imageUriArrayList){
            Toast.makeText(AddItems.this, "Uploading Img no("+count+")", Toast.LENGTH_SHORT).show();
            count++;
            StorageReference fileRef=reference.child(System.currentTimeMillis()+"."+getFileReference(imageUri));
            fileRef.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    progressBar.setVisibility(View.INVISIBLE);
                    fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            imageArrayFirebaseURI.add(uri.toString());

                        }
                    });
                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                    progressBar.setVisibility(View.VISIBLE);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    progressBar.setVisibility(View.INVISIBLE);
                    isImageUploadedSuccessfully=false;
                    Toast.makeText(AddItems.this,"Image Upload Failed!",Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private String getFileReference(Uri imageUri) {
        ContentResolver cr=getContentResolver();
        MimeTypeMap mime=MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(imageUri));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==2 && resultCode==RESULT_OK && data!=null){
//            imageUri=data.getData();
            for(int i=0;i<data.getClipData().getItemCount();i++){
                imageUri=data.getClipData().getItemAt(i).getUri();
                imageUriArrayList.add(imageUri);
            }

//            Log.d("imageItem", "onActivityResult: uri="+data.getData());
//            Toast.makeText(AddItems.this, data.getData().toString(), Toast.LENGTH_SHORT).show();
            itemImage.setImageURI(data.getClipData().getItemAt(0).getUri());
        }
    }
}