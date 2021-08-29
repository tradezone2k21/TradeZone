package com.example.everythingstore.buyer.post_login.buy_items;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.everythingstore.R;
import com.example.everythingstore.database_helper.DBHelper;
import com.example.everythingstore.seller.post_login.AddItems.AddItemModel;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
public class BuyerItemRecyclerViewAdapter extends RecyclerView.Adapter<BuyerItemRecyclerViewAdapter.Viewholder>{

    Context context;
    ArrayList<AddItemModel> list;

    public BuyerItemRecyclerViewAdapter(Context context, ArrayList<AddItemModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.buyer_item_for_recycler_view,parent, false);
        Viewholder viewHolder = new Viewholder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        AddItemModel addItemModel=list.get(position);
        holder.name.setText(addItemModel.getItemName());
        holder.cost.setText("₹"+String.valueOf(addItemModel.getCost()));
        holder.desc.setText("Description\n"+addItemModel.getItemDesc());


        String imgUriStrings=addItemModel.getImageUriList();
        if(imgUriStrings!=null){
            ArrayList<String> imgList= DBHelper.StringconvertStringToArrayList(imgUriStrings);
            DisplayImageOptions options = new DisplayImageOptions.Builder()
                    .showStubImage(R.drawable.placeholder_image_loading)
                    .showImageForEmptyUri(R.drawable.placeholder_image_loading)
                    .cacheInMemory()
                    .cacheOnDisc()
                    .build();
            ImageLoader imageLoader;
            imageLoader = ImageLoader.getInstance();
            imageLoader.init(ImageLoaderConfiguration.createDefault(context));
            imageLoader.displayImage(imgList.get(0), holder.img, options, null);

//            if(imgList.size()!=0){
//                Log.d("SellerItems RecyclerVie", "onBindViewHolder: " + imgList);
//                Picasso.get().load(imgList.get(0)).resize(200,200).placeholder(R.drawable.placeholder_image_loading).into(holder.img);
//            }
        }

        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("RecyclerViewOnClick:- ", "onClick: "+holder.name.getText());
                Intent intent=new Intent(context, ItemClickActivity.class);
                intent.putExtra("item_name", addItemModel.getItemName());
                intent.putExtra("item_desc", addItemModel.getItemDesc());
                intent.putExtra("item_cost",addItemModel.getCost().toString());
                intent.putExtra("item_imgURIs",addItemModel.getImageUriList());
                intent.putExtra("item_id",addItemModel.getItemID());
                context.startActivity(intent);
            }
        });





    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class Viewholder extends RecyclerView.ViewHolder{
        TextView name,desc;
        TextView cost;
        ImageView img;
        CardView card;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.buy_item_name);
            desc=itemView.findViewById(R.id.buy_item_desc);
            cost=itemView.findViewById(R.id.buy_item_price);
            img=itemView.findViewById(R.id.buy_item_image);
            card=itemView.findViewById(R.id.buyer_item_card_view);



        }
    }
};




//public class BuyerItemRecyclerViewAdapter extends RecyclerView.Adapter<BuyerItemRecyclerViewAdapter.Viewholder> {
//    private Context context;
//    private ArrayList<BuyerItemModel> buyerItemModelArrayList;
//
//    //done
//    public BuyerItemRecyclerViewAdapter(Context context, ArrayList<BuyerItemModel> buyerItemModelArrayList) {
//        this.context = context;
//        this.buyerItemModelArrayList = buyerItemModelArrayList;
//    }
//
//    @NonNull
//    @NotNull
//    @Override
//    public Viewholder onCreateViewHolder(@NonNull @NotNull ViewGroup viewGroup, int i) {
//        Log.d("buyerItemRecyclerView", "onCreateViewHolder: inside");
//        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.buyer_item_for_recycler_view, viewGroup, false);
//        BuyerItemRecyclerViewAdapter.Viewholder viewHolder = new BuyerItemRecyclerViewAdapter.Viewholder(view);
//        return viewHolder;
//    }
////done
//    @Override
//    public void onBindViewHolder(@NonNull @NotNull BuyerItemRecyclerViewAdapter.Viewholder holder, @SuppressLint("RecyclerView") int position) {
//        BuyerItemModel buyerItemModel = buyerItemModelArrayList.get(position);
//        holder.name.setText(buyerItemModel.getName());
//        holder.desc.setText("Description\n"+ buyerItemModel.getDescription());
//        holder.cost.setText("₹"+String.valueOf(buyerItemModel.getCost()));
//        Drawable res = context.getResources().getDrawable(buyerItemModelArrayList.get(position).getImgId());
//        holder.img.setImageDrawable(res);
//
//
//
//
//        holder.card.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d("RecyclerViewOnClick:- ", "onClick: "+holder.name.getText());
//                Intent intent=new Intent(context, ItemClickActivity.class);
//                intent.putExtra("item_name", buyerItemModelArrayList.get(position).getName());
//                intent.putExtra("item_desc", buyerItemModelArrayList.get(position).getDescription());
//                intent.putExtra("item_cost",String.valueOf(buyerItemModelArrayList.get(position).getCost()));
//                intent.putExtra("item_imgId",String.valueOf(buyerItemModelArrayList.get(position).getImgId()));
//                context.startActivity(intent);
//            }
//        });
//    }
////done
//    @Override
//    public int getItemCount() {
//        return buyerItemModelArrayList.size();
//    }
//
//    public class Viewholder extends RecyclerView.ViewHolder{
//         TextView name,desc;
//         TextView cost;
//         ImageView img;
//         CardView card;
//        public Viewholder (View itemView){
//            super(itemView);
//            name=itemView.findViewById(R.id.buy_item_name);
//            desc=itemView.findViewById(R.id.buy_item_desc);
//            cost=itemView.findViewById(R.id.buy_item_price);
//            img=itemView.findViewById(R.id.buy_item_image);
//            card=itemView.findViewById(R.id.buyer_item_card_view);
//        }
//    }
//}
