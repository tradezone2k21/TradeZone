package com.example.everythingstore.buyer.post_login.your_orders;


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
import com.example.everythingstore.buyer.post_login.buy_items.BuyerItemModel;
import com.example.everythingstore.buyer.post_login.buy_items.BuyerItemRecyclerViewAdapter;
import com.example.everythingstore.buyer.post_login.buy_items.ItemClickActivity;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class YourOrdersRecyclerViewAdapter extends RecyclerView.Adapter<YourOrdersRecyclerViewAdapter.Viewholder> {
    private Context context;
    private ArrayList<YourOrdersItemModel> yourOrdersItemModelArrayList;

    //done
    public YourOrdersRecyclerViewAdapter(Context context, ArrayList<YourOrdersItemModel> yourOrdersItemModelArrayList) {
        this.context = context;
        this.yourOrdersItemModelArrayList = yourOrdersItemModelArrayList;
    }

    @NonNull
    @NotNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull @NotNull ViewGroup viewGroup, int i) {
        Log.d("YourOrderRecyclerView", "onCreateViewHolder: inside");
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.your_orders_item_for_recycler_view, viewGroup, false);
        Viewholder viewHolder = new Viewholder(view);
        return viewHolder;
    }
    //done
    @Override
    public void onBindViewHolder(@NonNull @NotNull Viewholder holder, @SuppressLint("RecyclerView") int position) {
        YourOrdersItemModel yourOrdersItemModel = yourOrdersItemModelArrayList.get(position);
        holder.name.setText(yourOrdersItemModel.getName());
        holder.cost.setText("₹"+String.valueOf(yourOrdersItemModel.getCost()));
        Drawable res = context.getResources().getDrawable(yourOrdersItemModelArrayList.get(position).getImgId());
        holder.img.setImageDrawable(res);

    }
    //done
    @Override
    public int getItemCount() {
        return yourOrdersItemModelArrayList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{
        TextView name,date;
        TextView cost;
        ImageView img;

        public Viewholder (View itemView){
            super(itemView);
            name=itemView.findViewById(R.id.buy_item_name);
            date=itemView.findViewById(R.id.buy_item_order_date);
            cost=itemView.findViewById(R.id.buy_item_price);
            img=itemView.findViewById(R.id.buy_item_image);

        }
    }
}
