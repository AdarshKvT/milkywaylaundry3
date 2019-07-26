package com.example.milkywaylaundry3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.milkywaylaundry3.Interface.ItemClickListener;
import com.example.milkywaylaundry3.Model.Pant;
import com.example.milkywaylaundry3.ViewHolder.PantViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
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
        if (!categoryId.isEmpty() && categoryId != null)
        {
            loadListPANT(categoryId);
        }

    }

    private void loadListPANT(String categoryId) {
        adapter = new FirebaseRecyclerAdapter<Pant, PantViewHolder>(Pant.class,
                R.layout.pant_item,
                PantViewHolder.class,

                //      pantList.orderByChild("MenuId").equalTo(categoryId))

                pantList.orderByChild(String.valueOf(("MenuID").equals(categoryId)))){
            @Override
           protected void populateViewHolder(PantViewHolder viewHolder, Pant model, int position) {
                viewHolder.pant_name.setText(model.getName());

          //      try {
                //                    Picasso.with(getBaseContext()).load(model.getImage())
                //                            .into(viewHolder.pant_image);
                //                } catch (Exception e) {
                //                    e.printStackTrace();
                //                }
        //        Picasso.with(getBaseContext()).load(model.getImage())
          //           .into(viewHolder.pant_image);

                final Pant local = model;
                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        Toast.makeText(PantList.this,""+local.getName(),Toast.LENGTH_SHORT).show();
                    }
                });

           }
       };

        //Set Adapter
        Log.d("TAG",""+adapter.getItemCount());
        recyclerView.setAdapter(adapter);

    }
}
