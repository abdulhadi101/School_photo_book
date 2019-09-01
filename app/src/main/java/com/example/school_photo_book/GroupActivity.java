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

public class GroupActivity extends AppCompatActivity {


    int currentPage = 0;

    Timer timer;
    final long DELAY_MS = 500;//delay in milliseconds before task is to be executed
    final long PERIOD_MS = 5000; // time in milliseconds between successive task executions.

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(GroupActivity.this, Categories.class);
        startActivity(intent);
        releaseMediaPlayer();
        finish();
    }

    // Declare Variables
    ViewPager viewPager;
    PagerAdapter adapter;

    int[] fullPhoto;

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
                mMediaPlayer.seekTo(0);
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
        setContentView(R.layout.activity_group);
        // Generate sample data
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);


        fullPhoto = new int[] { R.drawable.group1, R.drawable.group2, R.drawable.group3, R.drawable.group4, R.drawable.group5, R.drawable.group6,R.drawable.group7, R.drawable.group8,
                R.drawable.group9, R.drawable.group10, R.drawable.group11, R.drawable.group12, R.drawable.group13, R.drawable.group14, R.drawable.group15, R.drawable.group16, R.drawable.group17,
                R.drawable.group18, R.drawable.group19, R.drawable.group20, R.drawable.group21, R.drawable.group22, R.drawable.group23, R.drawable.group24, R.drawable.group25, R.drawable.group26,
                R.drawable.group27, R.drawable.group28, R.drawable.group29, R.drawable.group30, R.drawable.group31, R.drawable.group32, R.drawable.group33, R.drawable.group34, R.drawable.group35,
                R.drawable.group36, R.drawable.group37, R.drawable.group38, R.drawable.group39, R.drawable.group40, R.drawable.group41, R.drawable.group42, R.drawable.group43, R.drawable.group44,
                R.drawable.group45, R.drawable.group46, R.drawable.group47, R.drawable.group48, R.drawable.group49, R.drawable.group50, R.drawable.group51, R.drawable.group52, R.drawable.group53,
                R.drawable.group54, R.drawable.group55, R.drawable.group56, R.drawable.group57, R.drawable.group58, R.drawable.group59, R.drawable.group60, R.drawable.group61, R.drawable.group62,
                R.drawable.group63, R.drawable.group64, R.drawable.group65, R.drawable.group66, R.drawable.group67, R.drawable.group68, R.drawable.group69, R.drawable.group70, R.drawable.group71, R.drawable.group72,
                R.drawable.group73, R.drawable.group74, R.drawable.group75, R.drawable.group76, R.drawable.group77, R.drawable.group78, R.drawable.group79, R.drawable.group80, R.drawable.group81, R.drawable.group82,
                R.drawable.group83, R.drawable.group84, R.drawable.group85, R.drawable.group86, R.drawable.group87, R.drawable.group88, R.drawable.group89, R.drawable.group90, R.drawable.group91, R.drawable.group92,
                R.drawable.group93, R.drawable.group94, R.drawable.group95, R.drawable.group96, R.drawable.group97, R.drawable.group98, R.drawable.group99, R.drawable.group100, R.drawable.group101, R.drawable.group102,
                R.drawable.group103, R.drawable.group104, R.drawable.group105, R.drawable.group106,R.drawable.girl  } ;

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
                    releaseMediaPlayer();
                    Intent intent = new Intent(GroupActivity.this, Categories.class);
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
            mMediaPlayer = MediaPlayer.create(GroupActivity.this, R.raw.group );
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