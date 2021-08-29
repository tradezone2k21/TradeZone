package com.example.everythingstore.seller.post_login.History;


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
import com.example.everythingstore.buyer.post_login.your_orders.YourOrdersItemModel;
import com.example.everythingstore.database_helper.DBHelper;
import com.example.everythingstore.seller.post_login.AddItems.AddItemModel;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;
import org.joda.time.DateTime;

import java.util.ArrayList;

public class HistoryRecyclerViewAdapter extends RecyclerView.Adapter<HistoryRecyclerViewAdapter.Viewholder>{

    Context context;
    ArrayList<AddItemModel> list;

    public HistoryRecyclerViewAdapter(Context context, ArrayList<AddItemModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.history_item_for_recycler_view,parent, false);
        Viewholder viewHolder = new Viewholder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        AddItemModel addItemModel=list.get(position);
        holder.name.setText(addItemModel.getItemName());
        holder.cost.setText("₹"+String.valueOf(addItemModel.getCost()));

        Long dt=addItemModel.getDateListed();
        DateTime jodaDt=new DateTime(dt);
        holder.date.setText("Listed on "+jodaDt.getDayOfMonth()+"-"+jodaDt.getMonthOfYear()+"-"+jodaDt.getYear());

        String imgUriStrings=addItemModel.getImageUriList();
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



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class Viewholder extends RecyclerView.ViewHolder{
    TextView name,date;
    TextView cost;
    ImageView img;

    public Viewholder(@NonNull View itemView) {
        super(itemView);
        name=itemView.findViewById(R.id.history_item_name);
        date=itemView.findViewById(R.id.history_item_order_date);
        cost=itemView.findViewById(R.id.history_item_price);
        img=itemView.findViewById(R.id.history_item_image);
    }
}
};

//public class HistoryRecyclerViewAdapter extends RecyclerView.Adapter<HistoryRecyclerViewAdapter.Viewholder> {
//    private Context context;
//    private ArrayList<HistoryItemModel> historyItemModelArrayList;
//
//    //done
//    public HistoryRecyclerViewAdapter(Context context, ArrayList<HistoryItemModel> historyItemModelArrayList) {
//        this.context = context;
//        this.historyItemModelArrayList = historyItemModelArrayList;
//    }
//
//    @NonNull
//    @NotNull
//    @Override
//    public Viewholder onCreateViewHolder(@NonNull @NotNull ViewGroup viewGroup, int i) {
//        Log.d("YourOrderRecyclerView", "onCreateViewHolder: inside");
//        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.history_item_for_recycler_view, viewGroup, false);
//        Viewholder viewHolder = new Viewholder(view);
//        return viewHolder;
//    }
//    //done
//    @Override
//    public void onBindViewHolder(@NonNull @NotNull Viewholder holder, @SuppressLint("RecyclerView") int position) {
//        HistoryItemModel historyItemModel = historyItemModelArrayList.get(position);
//        holder.name.setText(historyItemModel.getName());
//        holder.cost.setText("₹"+String.valueOf(historyItemModel.getCost()));
//        Drawable res = context.getResources().getDrawable(historyItemModelArrayList.get(position).getImgId());
//        holder.img.setImageDrawable(res);
//
//    }
//    //done
//    @Override
//    public int getItemCount() {
//        return historyItemModelArrayList.size();
//    }
//
//    public class Viewholder extends RecyclerView.ViewHolder{
//        TextView name,date;
//        TextView cost;
//        ImageView img;
//
//        public Viewholder (View itemView){
//            super(itemView);
//            name=itemView.findViewById(R.id.history_item_name);
//            date=itemView.findViewById(R.id.history_item_order_date);
//            cost=itemView.findViewById(R.id.history_item_price);
//            img=itemView.findViewById(R.id.history_item_image);
//
//        }
//    }
//}
