package com.example.milkywaylaundry3.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.milkywaylaundry3.Interface.ItemClickListener;
import com.example.milkywaylaundry3.R;

public class PantViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView pant_name, pant_price;
    public ImageView quick_cart;
    //public ImageView pant_image;

    private ItemClickListener itemClickListener;

    //generate setter onClick

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public PantViewHolder(@NonNull View itemView) {
        super(itemView);

        //if (pant_name == null) throw new AssertionError();

        pant_name = (TextView)itemView.findViewById(R.id.pant_name);
        //pant_image = (ImageView)itemView.findViewById(R.id.pant_image);
        pant_price =  (TextView)itemView.findViewById(R.id.pant_price);
        quick_cart = (ImageView)itemView.findViewById(R.id.btn_quick_cart);

        itemView.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view,getAdapterPosition(),false);

    }
}
