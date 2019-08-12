package com.example.milkywaylaundry3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.andremion.counterfab.CounterFab;
import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.milkywaylaundry3.Database.Database;
import com.example.milkywaylaundry3.Model.Order;
import com.example.milkywaylaundry3.Model.Pant;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class PantDetail extends AppCompatActivity {

    TextView pant_name, pant_price, pant_description;
    ImageView pant_image;
    CollapsingToolbarLayout collapsingToolbarLayout;
    //FloatingActionButton btnCart;
    CounterFab btnCart;
    ElegantNumberButton numberButton;

    String pantId="";

    FirebaseDatabase database;
    DatabaseReference pants;


    Pant currentPant;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  setContentView(R.layout.activity_pant_detail);
        setContentView(R.layout.activity_pant_detail);


        //Firebase
        database = FirebaseDatabase.getInstance();
        pants = database.getReference("PANTS");

        //init view
        numberButton = (ElegantNumberButton)findViewById(R.id.number_button);
        btnCart = (CounterFab) findViewById(R.id.btnCart);

        //Function for button add to cart in pant_detail_activity
        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                assert currentPant != null;
                new Database(getBaseContext()).addToCart(new Order(
                        pantId,
                        currentPant.getName(),
                        numberButton.getNumber(),
                        currentPant.getPrice()
                        //currentPant.getDiscount()

                ));

                Toast.makeText(PantDetail.this, "Added to cart",Toast.LENGTH_SHORT).show();
            }
        });

        btnCart.setCount(new  Database(this).getCountCart());

        //pant_description = (TextView) findViewById(R.id.pant_description);
        pant_name = (TextView) findViewById(R.id.pant_name);
        pant_price = (TextView) findViewById(R.id.pant_price);
        //pant_image = (ImageView) findViewById(R.id.img);


        //Get Pant Id from Intent
        if (getIntent() !=null)
            pantId = getIntent().getStringExtra("categoryId");
       // assert pantId != null;
        if (pantId != null && !pantId.isEmpty()) {
            getDetailPant(pantId);
        }

    }


    private void getDetailPant(String pantId) {
        pants.child(pantId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                currentPant = dataSnapshot.getValue(Pant.class);
                assert currentPant != null;
                pant_price.setText(currentPant.getPrice());
                pant_name.setText(currentPant.getName());
                //pant_description.setText(pant.getDescription());

            }



            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


}




