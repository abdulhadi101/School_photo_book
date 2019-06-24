package com.example.school_photo_book;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.os.Handler;

import java.util.Timer;
import java.util.TimerTask;

public class ExcosActivity extends AppCompatActivity {


    int currentPage = 0;

    Timer timer;
    final long DELAY_MS = 500;//delay in milliseconds before task is to be executed
    final long PERIOD_MS = 3000; // time in milliseconds between successive task executions.

    // Declare Variables
    ViewPager viewPager;
    PagerAdapter adapter;
    String[] excosName;
    String[] excosPost;
      int[] excosPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_excos);
        // Generate sample data
        excosName = new String[] { "usman Ibrahim abubakar ","sulaiman ahmed marafa ","adamu Muhammad","Mahmood  jibrin","rayyanu abubakar","Abdulmajid AG yusuf",
                "Abdulrahman Abubakar ","ahmad abdulhamid"," aisha Muhammad ","aishatu shehu ","aishatu yusuf","alamin kabir mu’azu","aliyu adamu alkali ",
                "asma’u umar gital"," auwal m. umar ","bushra armaya’u ","Fatima batula sunusi","habab aminu ","hadiza ahmad Muhammad","hauwa musa ",
                "Ibrahim Muhammad","kabir Muhammad","Khadija abdullahi launi ","Khadija Ibrahim","Khadija nuruddeen ","maimunatu Mahmood","Maryam salihu usman",
                "shamsuddeen ahmad","nasir abubakar","nusaiba Mahmood ari","nusaiba sani jibrin","rufai abdullahi","sa’adtu abubakar yusuf","sadiq balarabe",
                "saudat Ibrahim bala","sulaiman adamu sallama","ummukulthum ishaq ","yusuf abubakar yusuf","Zainab A. Abdullahi","Zainab Adamu"};

        excosPost = new String[] { "undy","sule bobo","suitor","officer","sayyadee","","","","","","","","","","","","","","","","","","","","","","","","","",
                "","","","","","","","","","","" };

        excosPhoto = new int[] { R.drawable.girl, R.drawable.sulaimanahmed, R.drawable.adamumuhammad, R.drawable.mahmoodjibrin, R.drawable.rayyanuabubakr, R.drawable.abdulmajidag,
                R.drawable.abdulrahmanabubakar, R.drawable.ahmadabdulhamid, R.drawable.aishamuhammad, R.drawable.aishatushehu, R.drawable.aishatuyusuf,
                R.drawable.alaminkabir, R.drawable.aliyuadamu, R.drawable.asmauumar, R.drawable.auwalumar, R.drawable.bushraarmayau, R.drawable.fatimabatula,
                R.drawable.habaaminu, R.drawable.hadizaahmad, R.drawable.hauwamusa, R.drawable.ibrahimmuhammad, R.drawable.kabirmuhammad, R.drawable.khadijaabdullahi1,
                R.drawable.khadijaibrahim, R.drawable.khadijanurudeen, R.drawable.maimunatumahmood, R.drawable.maryamsalisu, R.drawable.shamsudeenahmad, R.drawable.nasirabubakar,
                R.drawable.nusaibamahmood, R.drawable.nusaibasani, R.drawable.rufaiabdullahi, R.drawable.saudatibrahim, R.drawable.sulaimanadamu,
                R.drawable.ummukhultum, R.drawable.yusufabubakar, R.drawable.zaibaaabdullahi, R.drawable.zainabadamu};

        // Locate the ViewPager in viewpager_main.xml
        viewPager = findViewById(R.id.pagerc);
        // Pass results to ViewPagerAdapter Class
        adapter = new ViewpagerAdapterExcos(ExcosActivity.this, excosName, excosPost, excosPhoto);
        // Binds the Adapter to the ViewPager
        viewPager.setAdapter(adapter);


        /*After setting the adapter use the timer */
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                final int NUM_PAGES = excosName.length;
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