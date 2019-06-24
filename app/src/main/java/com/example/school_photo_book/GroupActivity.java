package com.example.school_photo_book;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.os.Handler;

import java.util.Timer;
import java.util.TimerTask;

public class GroupActivity extends AppCompatActivity {


    int currentPage = 0;

    Timer timer;
    final long DELAY_MS = 500;//delay in milliseconds before task is to be executed
    final long PERIOD_MS = 3000; // time in milliseconds between successive task executions.

    // Declare Variables
    ViewPager viewPager;
    PagerAdapter adapter;

    int[] fullPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);
        // Generate sample data


        fullPhoto = new int[] { R.drawable.girl, R.drawable.sulaimanahmed, R.drawable.adamumuhammad, R.drawable.mahmoodjibrin, R.drawable.rayyanuabubakr, R.drawable.abdulmajidag,
                R.drawable.abdulrahmanabubakar, R.drawable.ahmadabdulhamid, R.drawable.aishamuhammad, R.drawable.aishatushehu, R.drawable.aishatuyusuf,
                R.drawable.alaminkabir, R.drawable.aliyuadamu, R.drawable.asmauumar, R.drawable.auwalumar, R.drawable.bushraarmayau, R.drawable.fatimabatula,
                R.drawable.habaaminu, R.drawable.hadizaahmad, R.drawable.hauwamusa, R.drawable.ibrahimmuhammad, R.drawable.kabirmuhammad, R.drawable.khadijaabdullahi1,
                R.drawable.khadijaibrahim, R.drawable.khadijanurudeen, R.drawable.maimunatumahmood, R.drawable.maryamsalisu, R.drawable.shamsudeenahmad, R.drawable.nasirabubakar,
                R.drawable.nusaibamahmood, R.drawable.nusaibasani, R.drawable.rufaiabdullahi, R.drawable.saudatibrahim, R.drawable.sulaimanadamu,
                R.drawable.ummukhultum, R.drawable.yusufabubakar, R.drawable.zaibaaabdullahi, R.drawable.zainabadamu};

        // Locate the ViewPager in viewpager_main.xml
        viewPager = findViewById(R.id.pager_group);
        // Pass results to ViewPagerAdapter Class
        adapter = new ViewPagerAdapterFront(GroupActivity.this,   fullPhoto);
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