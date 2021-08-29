package com.example.everythingstore.buyer.post_login.cart;

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


import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class CartRecyclerViewAdapter extends RecyclerView.Adapter<CartRecyclerViewAdapter.Viewholder> {
    private Context context;
    private ArrayList<CartItemModel> cartItemModelArrayList;

    //done
    public CartRecyclerViewAdapter(Context context, ArrayList<CartItemModel> cartItemModelArrayList) {
        this.context = context;
        this.cartItemModelArrayList = cartItemModelArrayList;
    }

    @NonNull
    @NotNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull @NotNull ViewGroup viewGroup, int i) {
        Log.d("cartItemRecyclerView", "onCreateViewHolder: inside");
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cart_item_for_recycler_view, viewGroup, false);
        Viewholder viewHolder = new Viewholder(view);
        return viewHolder;
    }
    //done
    @Override
    public void onBindViewHolder(@NonNull @NotNull Viewholder holder, @SuppressLint("RecyclerView") int position) {
        CartItemModel cartItemModel = cartItemModelArrayList.get(position);
        holder.name.setText(cartItemModel.getName());
        holder.cost.setText("₹"+String.valueOf(cartItemModel.getCost()));
        Drawable res = context.getResources().getDrawable(cartItemModelArrayList.get(position).getImgId());
        holder.img.setImageDrawable(res);

    }
    //done
    @Override
    public int getItemCount() {
        return cartItemModelArrayList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{
        TextView name;
        TextView cost;
        ImageView img;

        public Viewholder (View itemView){
            super(itemView);
            name=itemView.findViewById(R.id.cart_item_name);
            cost=itemView.findViewById(R.id.cart_item_cost);
            img=itemView.findViewById(R.id.cart_item_image);

        }
    }
}