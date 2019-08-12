package com.example.milkywaylaundry3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.example.milkywaylaundry3.Database.Database;
import com.example.milkywaylaundry3.Interface.ItemClickListener;
import com.example.milkywaylaundry3.Model.Category;
import com.example.milkywaylaundry3.Model.Order;
import com.example.milkywaylaundry3.Model.Pant;
import com.example.milkywaylaundry3.ViewHolder.PantViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;

public class PantList extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    FirebaseDatabase database;
    DatabaseReference pantList;

    String categoryId="";

    FirebaseRecyclerAdapter<Pant, PantViewHolder>adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pant_list);

        //Firebase

        database = FirebaseDatabase.getInstance();
        pantList = database.getReference("PANTS");

        recyclerView = (RecyclerView)findViewById(R.id.recycler_pant);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //Get intent here
        if ((getIntent() != null))
            categoryId = getIntent().getStringExtra("CategoryId");
        assert categoryId != null;
        if (!categoryId.isEmpty())
        {
            loadListPANT(categoryId);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (adapter != null)
            adapter.startListening();

    }


    private void loadListPANT(String categoryId) {


        Query getPantList = pantList.orderByChild("MenuId").equalTo(categoryId);
        FirebaseRecyclerOptions<Pant> pantoptions = new FirebaseRecyclerOptions.Builder<Pant>()
                .setQuery(getPantList,Pant.class)
                .build();

        adapter = new FirebaseRecyclerAdapter<Pant, PantViewHolder>(pantoptions) {

            @NonNull
            @Override
            public PantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View itemView = LayoutInflater.from(parent.getContext())
                        .inflate( R.layout.pant_item,parent,false);
                return new PantViewHolder(itemView);
            }

            @Override
            protected void onBindViewHolder(PantViewHolder viewHolder, final int position, final Pant model) {
                //viewHolder.pant_name.setText(model.getName());
                //viewHolder.pant_name.setText(String.format("%s",model.getName().toString()));
                viewHolder.pant_price.setText(String.format("$ %s", model.getPrice().toString()));


                //Quick Cart
                 viewHolder.quick_cart.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View view) {
                         new Database(getBaseContext()).addToCart(new Order(
                                 adapter.getRef(position).getKey(),
                                 model.getName(),
                                 "1",
                                 model.getPrice()
                                 //currentPant.getDiscount()
                         ));

                         Toast.makeText(PantList.this,"Added to Cart",Toast.LENGTH_SHORT).show();
                     }
                 });

                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {

                        //Start new Activity
                        Intent pantDetail = new Intent(PantList.this, PantDetail.class);
                        pantDetail.putExtra("PANT",adapter.getItem(position)); //Send PantId to another activity
                        startActivity(pantDetail);
                    }
                });

            }
        };


        adapter.startListening();
        //Set Adapter
        recyclerView.setAdapter(adapter);
    }


    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}
