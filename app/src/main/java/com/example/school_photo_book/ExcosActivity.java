package com.example.school_photo_book;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import java.util.Timer;
import java.util.TimerTask;

public class ExcosActivity extends AppCompatActivity {


    int currentPage = 0;

    Timer timer;
    final long DELAY_MS = 500;//delay in milliseconds before task is to be executed
    final long PERIOD_MS = 4000; // time in milliseconds between successive task executions.

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(ExcosActivity.this, Categories.class);
        startActivity(intent);
        releaseMediaPlayer();
        finish();
    }

    // Declare Variables
    ViewPager viewPager;
    PagerAdapter adapter;
    String[] excosName;
    String[] excosPost;
      int[] excosPhoto;


    private MediaPlayer mMediaPlayer;

    private AudioManager mAudioManager;

    AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                    focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                // AUDIOFOCUS_LOSS TRANSIENT means we have lost audio focus for a short amount of time
                // and AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK means we have lost audio focus
                // our app still continues to play song at lower volume but in both cases,
                // we want our app to pause playback and start it from beginning.
                mMediaPlayer.pause();

            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                // it means we have gained focused and start playback
                mMediaPlayer.start();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                // it means we have completely lost the focus and we
                // have to stop the playback and free up the playback resources
                releaseMediaPlayer();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_excos);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        // Generate sample data
        excosName = new String[] { "ABUBAKAR SALISU ABUBAKAR","AISHA SANUSI TATIMU ","SALIHU MUSA GHANI ","AISHA MUHAMMAD ","ALIYU ADAMU ALKALI ","UMAR IBRAHIM JALO ",
                "MARYAM SULAIMAN MUHAMMAD ","ZAIDU MUHAMMAD NASIR ","RUKAYYA HUSSAINI UMAR ","SALMANU ABDULLAHI  ","HAUWA’U HUDU DUGURI ",
                "ALIYU ADAMU ALKALI ","ZAINAB A. ABDULLAHI ","UMAR IBRAHIM JALO ","AISHAT SANUSI TATIMU ","ABUBAKAR SALISU ABUBAKAR ",
                "MARYAM SULAIMAN MUHAMMAD ","AUWAL M. UMAR ","SALIHU INUWA ABUBAKAR ","SALMANU ABDULLAHI ","HAUWA’U HUDU DUGURI","UMAR ADAMU JIBRIN",
                "MARDIYA NURU UBA","ABUBAKAR ADAMU SALLAMA","SUWAIBA NURU UBA","DAUDA MUHAMMAD BABAJI","ALIYU ADAMU ALKALI ","SALIHU MUSA GHANI ",
                "AISHA MUHAMMAD ","MUSTAPHA M. KABIR ","ABDULLAHI IBRAHIM MARAFA ","YASMIN IBRAHIM ABUBAKAR","ABDULRASHID ABDULHAMID","HABAB AMNU",
                "HAFSAT MUHAMMAD DANFULANI ","SHAMSUDDEEN AHMAD ","HAFIZA ABDULLAHI LAUNI "," MUHAMMAD SABO","AISHA MUHAMMAD RAHAMA","ABDULMALIK ADAMU MANGA",
                "HAFSAT MUHAMMAD DANFULANI","AHMAD TIJJANI","RUKAYYA ZAKARIYYA","Abdulwahab M. Bawa","AL’AMIN M SAMBO","HAFSAT MUHAMMAD BAWA",
                "KHAIRAT IBRAHIM ABUBAKAR","NUHU MUHAMMAD MALAMI","RUKAYYA HUSSAINI UMAR","UMMUKULTHUM YERO AHMAD","ZAIBU NASIR","KHADIJAH HAMISU ABUBAKAR ",
                "SAFWAN AMINU","ABDULLAHI UMAR ABDULLAHI ","HAUWA’U HUDU DUGURI ","HAFSAT M SAMBO ","MAHMOOD JIBRIN ","ZAINAB A ABDULLAHI ",
                "KHADIJA ADAMU TATIMU ","MUHAMMAD ZAKARIYYA ", ""};

        excosPost = new String[] { "PRESIDENT","VICE PRESIDENT ","SECRETARY ","ASST. SECRETARY ","SPECIAL ADVISER","TREASURER","ASST. TREASURER ","PRO 1 A-CLASS ","PRO 2 A-CLASS ",
                "PRO 1 B-CLASS","PRO 2 B-CLASS ","PRO 1 C-CLASS ","PRO 2 C-CLASS ","HEAD BOY ","HEAD GIRL ","ASST. HEAD BOY ","ASST. HEAD GIRL ","LABOUR PREFECT ",
                "ASST. LABOUR PREFECT ","HEALTH PREFECT ","ASST. HEALTH PREFECT ","LABORATORY PREFECT ","ASST. LABORATORY PREFECT","TIME KEEPER ","ASST. TIME KEEPER",
                "COMPOUND PREFECT ","GAMES PREFECT ","ASST. GAMES PREFECT ","UTILITY PREFECT ","ASST. UTILITY PREFECT ","LIBRARY PREFECT ",
                "SOCIAL PREFECT ","ASST. SOCIAL PREFECT","CAPTAIN RED HOUSE ","ASST. CAPTAIN RED HOUSE","CAPTAIN YELLOW HOUSE ",
                "ASST. CAPTAIN YELLOW HOUSE","CAPTAIN GREEN HOUSE ","ASST. CAPTAIN GREEN HOUSE ","CAPTAIN BLUE HOUSE ","ASST. CAPTAIN BLUE HOUSE ",
                "Chairman Daawah Committee  ","AMIRA MSSN ","SECRETARY MSSN ","PRO 1 MSSN","DAAWAH COMMITTEE MSSN ","DISCIPLINE COMMITTEE ","AMIR MSSN",
                "DAAWAH COMMITTEE  ","DISCIPLINE COMMITTEE ","CHAIRMAN DISCIPLINE COMMITTEE  ","FINANCIAL SECRETARY","TREASURER ","PRO 2 ","CHAIR LADY ",
                "DAAWAH COMMITTEE ","DAAWAH COMMITTEE ","DAAWAH COMMITTEE ","DISCIPLINE COMMITTEE ","DISCIPLINE COMMITTEE", ""};

        excosPhoto = new int[] {R.drawable.abubakarsalisu, R.drawable.sanusiaisha, R.drawable.salihumusa, R.drawable.aishamuhammad, R.drawable.aliyuadamu, R.drawable.umaribrahimjalo,
        R.drawable.maryamsulaiman, R.drawable.zaidunasir, R.drawable.rukayyahusaini, R.drawable.salmanuabdullahi, R.drawable.hauwauhudu,R.drawable.aliyuadamu,
        R.drawable.zainababdullahi, R.drawable.umaribrahimjalo, R.drawable.sanusiaisha,R.drawable.abubakarsalisu, R.drawable.maryamsulaiman, R.drawable.auwalumar, R.drawable.salihuinuwa,
        R.drawable.salmanuabdullahi, R.drawable.hauwauhudu, R.drawable.umaradamu, R.drawable.mardiyyanuru,R.drawable.abubakaradamu,R.drawable.suwaibanuru, R.drawable.daudamuhammad, R.drawable.aliyuadamu, R.drawable.salihumusa,
        R.drawable.aishamuhammad, R.drawable.mustaphamkabir, R.drawable.abdullahiibrahim, R.drawable.yasminibrahim, R.drawable.abdurrashidabdulhamid, R.drawable.habaaminu, R.drawable.hafsatdanfulani,
        R.drawable.shamsudeenahmad, R.drawable.hafizalauni, R.drawable.muhammadsabo, R.drawable.aishatumuhammadrahma, R.drawable.abdulmalikmanga, R.drawable.hafsatdanfulani, R.drawable.ahmadtijjani,
        R.drawable.rukayyazakariyya, R.drawable.abdulwahabmuhammad, R.drawable.alaminmsabo, R.drawable.hafsatmuhdbawa, R.drawable.khairatibrahim, R.drawable.nuhumuhammad, R.drawable.rukayyahusaini,
        R.drawable.ummukhultum, R.drawable.zaidunasir, R.drawable.khadijahamisu, R.drawable.safwanaminu,R.drawable.abdullahiumar,R.drawable.hauwauhudu,
        R.drawable.muhammadhafsatsambo, R.drawable.mahmoodjibrin, R.drawable.zainababdullahi, R.drawable.khadijaadamu, R.drawable.muhammadzakariya, R.drawable.girl};

        // Locate the ViewPager in viewpager_main.xml
        viewPager = findViewById(R.id.pager_excos);
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
                    releaseMediaPlayer();
                    Intent intent = new Intent(ExcosActivity.this, Categories.class);
                    startActivity(intent);
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

    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;

            // Regardless of whether or not we were granted audio focus, abandon it. This also
            // unregisters the AudioFocusChangeListener so we don't get anymore callbacks.
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }



    @Override
    protected void onStart() {
        super.onStart();
        releaseMediaPlayer();

        // Request audio focus so in order to play the audio file. The app needs to play a
        // short audio file, so we will request audio focus with a short amount of time
        // with AUDIOFOCUS_GAIN_TRANSIENT.
        int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

        if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
            //we have the audio focus now

            // creates new media player object
            // mMediaPlayer = MediaPlayer.create(getActivity(), words.get(position).getAudioResourceId());
            mMediaPlayer = MediaPlayer.create(ExcosActivity.this, R.raw.leadersoftomorrow );
            mMediaPlayer.start();

            /*
             * set on completion listener on the mediaplayer object
             * and release media player object as soon song stops playing
             */
            mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {

                    // now the sound file has finished player, so free up the media player resources
                    releaseMediaPlayer();
                }
            });
        }


    }

}