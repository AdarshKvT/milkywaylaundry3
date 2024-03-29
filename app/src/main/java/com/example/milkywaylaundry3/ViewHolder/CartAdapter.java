package com.example.milkywaylaundry3.ViewHolder;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.milkywaylaundry3.Cart;
import com.example.milkywaylaundry3.Database.Database;
import com.example.milkywaylaundry3.Interface.ItemClickListener;
import com.example.milkywaylaundry3.Model.Order;
import com.example.milkywaylaundry3.R;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

class  CartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView txt_cart_name,txt_price;
    public ElegantNumberButton btn_quantity;
    //public ImageView img_cart_count;

    private ItemClickListener itemClickListener;

    public void setTxt_cart_name(TextView txt_cart_name){
        this.txt_cart_name = txt_cart_name;
    }

    public CartViewHolder(@NonNull View itemView) {
        super(itemView);
        txt_cart_name =(TextView) itemView.findViewById(R.id.cart_item_name);
        txt_price = (TextView) itemView.findViewById(R.id.cart_item_Price);
        //img_cart_count = (ImageView) itemView.findViewById(R.id.cart_item_count);
        btn_quantity = itemView.findViewById(R.id.btn_quantity);

    }

    @Override
    public void onClick(View view) {

    }
}


public class CartAdapter extends RecyclerView.Adapter<CartViewHolder>{

    private List<Order> listData = new ArrayList<>();
    private Cart cart;

    public CartAdapter(List<Order> lisData, Cart cart) {
        this.listData = lisData;
        this.cart = cart;

    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from((cart));
        View itemView = inflater.inflate(R.layout.cart_layout,parent,false);
        return  new CartViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, final int position) {
/*
        TextDrawable drawable = TextDrawable.builder()
                .buildRound(""+listData.get(position).getQuantity(), Color.RED);
        holder.img_cart_count.setImageDrawable(drawable);
*/
/*        try {
            holder.btn_quantity.setNumber(listData.get(position).getQuantity());
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        //if (listData != null)
        holder.btn_quantity.setNumber(listData.get(position).getQuantity());
        holder.btn_quantity.setOnValueChangeListener(new ElegantNumberButton.OnValueChangeListener() {
            @Override
            public void onValueChange(ElegantNumberButton view, int oldValue, int newValue) {
                Order order = listData.get(position);
                order.setQuantity(String.valueOf(newValue));
                new Database(cart).updateCart(order);

                //update txttotal
                //total value increase when quantity increace in orderactivity
                //Calculation total price
                int total = 0;
                List<Order> orders = new  Database(cart).getCarts();
                for (Order item : orders)
                    total += (Integer.parseInt(order.getPrice()) * (Integer.parseInt(item.getQuantity())));
                    Locale locale = new Locale("en", "US");

                    NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);
                    cart.txtTotalPrice.setText(fmt.format(total));


            }
        });

        Locale locale = new Locale("en", "US");
        NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);


/*        try {
        int price = (Integer.parseInt(listData.get(position).getPrice()))*(Integer.parseInt(listData.get(position).getQuantity()));
        holder.txt_price.setText(fmt.format(price));

       } catch (Exception e) {
            e.printStackTrace();
        }*/

        assert listData != null;
        int price = (Integer.parseInt(listData.get(position).getPrice()))*(Integer.parseInt(listData.get(position).getQuantity()));
        holder.txt_price.setText(fmt.format(price));
        holder.txt_cart_name.setText((listData.get(position).getProductName()));

    }

    @Override
    public int getItemCount() {
        return listData.size();
    }
}
