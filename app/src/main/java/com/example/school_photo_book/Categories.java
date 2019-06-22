package com.example.school_photo_book;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Categories extends AppCompatActivity {

    CardView cardA, cardB, cardC, cardStaff, cardExcos, cardGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        cardA = findViewById(R.id.card_view4);
        cardB = findViewById(R.id.card_view);
        cardC = findViewById(R.id.card_view3);
        cardExcos = findViewById(R.id.card_view5);
        cardStaff = findViewById(R.id.card_view1);
        cardGroup = findViewById(R.id.card_view2);


        cardA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Categories.this, ClassA.class);
                startActivity(intent);
            }
        });

        cardB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Categories.this, ClassB.class);
                startActivity(intent);
            }
        });

        cardC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Categories.this, ClassC.class);
                startActivity(intent);
            }
        });

        cardStaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Categories.this, StaffActivity.class);
                startActivity(intent);
            }
        });

        cardExcos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Categories.this, ExcosActivity.class);
                startActivity(intent);
            }
        });

        cardGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Categories.this, GroupActivity.class);
                startActivity(intent);
            }
        });

    }
}
