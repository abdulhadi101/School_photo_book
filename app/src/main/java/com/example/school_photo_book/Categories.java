package com.example.school_photo_book;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Categories extends AppCompatActivity {

    CardView cardA, cardB, cardC, cardStaff, cardExcos, cardGroup;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(Categories.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

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
                finish();
            }
        });

        cardB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Categories.this, ClassB.class);
                startActivity(intent);
                finish();
            }
        });

        cardC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Categories.this, ClassC.class);
                startActivity(intent);
                finish();
            }
        });

        cardStaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Categories.this, StaffActivity.class);
                startActivity(intent);
                finish();
            }
        });

        cardExcos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Categories.this, ExcosActivity.class);
                startActivity(intent);
                finish();
            }
        });

        cardGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Categories.this, GroupActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    protected void onPause() {
        finish();
        super.onPause();
    }
}
