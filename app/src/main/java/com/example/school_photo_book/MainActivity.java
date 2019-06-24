package com.example.school_photo_book;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    int currentPage = 0;

    Timer timer;
    final long DELAY_MS = 500;//delay in milliseconds before task is to be executed
    final long PERIOD_MS = 3000; // time in milliseconds between successive task executions.

    // Declare Variables
    ViewPager viewPager;
    PagerAdapter adapter;

    int[] fullPhoto;

    Button categories_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        categories_btn = findViewById(R.id.categories_btn);


        fullPhoto = new int[] { R.drawable.schoolphoto1, R.drawable.schoolphoto2, R.drawable.schoolphoto3, R.drawable.schoolphoto4,
                R.drawable.schoolphoto5, R.drawable.schoolphoto6,};

        categories_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Categories.class);
                startActivity(intent);
            }
        });

        // Locate the ViewPager in viewpager_main.xml
        viewPager = findViewById(R.id.pager_full);
        // Pass results to ViewPagerAdapter Class
        adapter = new ViewPagerAdapterFront(MainActivity.this,   fullPhoto);
        // Binds the Adapter to the ViewPager
        viewPager.setAdapter(adapter);


        /*After setting the adapter use the timer */
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                final int NUM_PAGES = fullPhoto.length;
                if (currentPage == NUM_PAGES-1) {
                    currentPage = 0;
                }
                viewPager.setCurrentItem(currentPage++, true);
            }
        };

        timer = new Timer(); // This will create a new Thread
        timer.schedule(new TimerTask() { // task to be scheduled
            @Override
            public void run() {
                handler.post(Update);
            }
        }, DELAY_MS, PERIOD_MS);


    }
}
