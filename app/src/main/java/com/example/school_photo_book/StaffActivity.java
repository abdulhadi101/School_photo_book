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

public class StaffActivity extends AppCompatActivity {

    int currentPage = 0;

    Timer timer;
    final long DELAY_MS = 500;//delay in milliseconds before task is to be executed
    final long PERIOD_MS = 5000; // time in milliseconds between successive task executions.

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(StaffActivity.this, Categories.class);
        startActivity(intent);
        finish();

    }

    // Declare Variables
    ViewPager viewPager;
    PagerAdapter adapter;
    String[] StaffName;
    String[] Rank;
    String[] StaffPhonenumber;
    String[] StaffAddress;
   String[] StaffComment;
    int[] Staffphoto;

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
        setContentView(R.layout.activity_staff);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        // Generate sample data
        StaffName = new String[] { "Sa'adatu Shu'aibu ","Ladi Hamma Muhammad","Hassan Abdullahi Maikano","Muhammad habib idris ","Maryam Abdullahi","Kabiru Ibrahim",
                "Bala Dayyabu Lere ","Fatima Muhammad Tinja ","Abdullahi Dahiru	","Rabiatu Muhammad Ahmad ","Jamila Ibrahim Zwall",
                "Nazir Mustapha Ahmad ","Yahaya Ahmad Isah","Muhammad Lawan ","Muhammad Shu’aibu Saba","Isma’il Garba ","Yusuf Bako Tunga",
                "Abdullahi Dahiru Balbaya ","Abdul Ahmed","Zainab Ibrahim Haruna ","Abubakar Isah ","Babangida Adamu Maikano",
                "Isyaka Muhammad Inuwa ","Ahmad Abdullahi Jibrin ","Ruth Taro ","Mustapha Shuaibu","Ayuba Garba ","Bilyaminu Ibrahim ",
                "Mallam Dando K Alabe","Saminu Ladan Zailani ","Alhaji Abba Muhammad Inuwa (Ciroman Toro)","Munirat Abubakar ",
                "Halima Ibrahim Abdullahi ","Usman Ahmad","Baba Sama’ila JIBRIN","Aliyah zubairu ","" };

        Rank = new String[] { "Principal (Pioneer) ","Vice Principal Academics ","Vice Principal  Admin/Geography Teacher",
                "Examination Officer Internal/History Teacher","H.O.D Biology Department ","H.O.D Chemistry Department  ","H.O.D Physics Department ",
                "Revenue/Agric Teacher ","Labour Master ","Teacher (English) ","-","Physics Teacher ","House Master (Blue House) ",
                "Discipline Master/Teacher; Data Processing ","Class Teacher (Geography) ","Admission Officer/HOD Government ", "E.O VII",
                "H.O.D Computer Science","Maths Teacher/Quiz and Games Master/Club Patron ","Teacher IRS/Blue House Mistress","HOD Hausa",
                "Chemistry Teacher ","Health Science Teacher ","H.O.D Mathematics ","ChairPerson Press Club Society ","H.O.D Arabic . PAEO (1) ","Senior Master ",
                "Patron MSSN","Agricultural Sci. Teacher/House Master Yellow ","SMBC chariman ","P.T.A Chairman ","Business Studies Teacher",
                "Senior Mistress","E.O VI","Security","Economics Teacher",""};

        StaffPhonenumber = new String[] { "08023539671","08188369360","08033062965","08055037675","07031103392","08167927010","08024476692","07030243203","08035467133",
                "08022283882/ 08063150390","08053666194","08030695959","08038600285","08023282366","08162513577","07063589404","07011146855/ 07037062018","07037074540/ 08080655115",
                "08064385561","09031654513","08133452461","08039129440", "07037508752","08062446425","07056409605","08067789184","08066567881/ 08023612600","08037474468",
                "08067261641/ 08098309732","08023622194","08031184275/ 08086657079","08038400678","07035103988",
                "08028711323","-","08064411318",""};

        StaffAddress = new String[] { "Federal Lowcost Makama New Extension Bauchi ","Dutsen Tanshi Qtrs;  Near Tanshi Police Station Bauchi ",
        "Near Bauchin Bauchi’s Masjid Unguwar Bauchi ","Inkil Unguwar Magaji ","Near Bayan Fada Meternity, Bauchi ",
        "No. 1oa, Behind Old Cemetery, Fadamar Mada, Bauchi ","Nassarawa Jahun Bauchi ","Kobi Street House No. K Ii ",
        "No: 44 Nufawa Quarters ","Nassarawa Jahun Opp. Cikare Mosque Bauchi ","Zango Sa’idawa","Sir, Kashim Ibrahim Road Old Gra Bauchi. ",
        "Tudun Salmanu Opposite Malia Furniture ","Ganjuwa Jahun Street Bauchi ","Zango Area, Bauchi State ",
        "Zannuwa Off Yakubu Wanka Street ","No. 7 – 22 Unity Housing Estate Dungal, Bauchi ",
        "Makama Jahun Qtrs. Old Musaba Junction Bauchi ","Dr. Ibrahim Tahir Comprehensive School ","Wunti Street Bauchi  ",
        "Unguwan Borno Bayan Primary Bauchi ","Wunti Street Bauchi ","-","Federal Low-Cost ","Yelwan Makaranta Behind Sabo Bakery ",
        "Filin Kwallon Kobi  ","Jahun, Galadiman Bauchi Palace ","No. B373 Wunti Street Bauchi ",
        "House No. 5 Herwagana Quarters Gombe Gombe State  ","K1 Kobi Street Bauchi  ",
        "General Admin Department, Office Of The Head Of Service Bauchi ","House No C293 Gidan Liman Wase Raod Bauchi  ",
        "Federal Low-Cost Bauchi ","Sii Jahun Street Barde Close, Neat Citu Stores, Bauchi State ","Kobi",
        "Behind Primary Sch. Gida Dubu ",""};


        StaffComment = new String[] { "I Am Proud Of My Second Set Of Students, They Are All Good. I Wish Them The Best ","Satisfactory",
        "This Set Of Students Are Also Hard Working And Disciplined. I Wish Them Good Luck In Their Career ",
        "A Responsible Student Is An Asset To The Society And This Is What We In Dr. Ibrahim Tahir GCMDSS Produce!","Education Is The Key To The World So Be Wise And Read ",
        "One Of The Dedicated And Hard Working Set Of Students I Have Met, I Wish You All The Best In Your Future Endeavors ",
        "Satisfactory","A Very Good Student, Keep It Up ","Good Conduct And Satisfactory For Them ",
        "A Memorable Set With All Sorts Of Characters. I Wish You All A Very Successful Life, Remain Blessed.",
        "When You Finish, Pls Develop Your Study Don’t Waste Your Time. I Wish You The Best In Life And Success In Your All Undertakings ",
        "The Single Most Powerful Word That Make Or Destroy Anyone Is Acceptance. So Try As Much As Possible To Accept And Build From Your Humble Beginnings. I Wish You All The Best In Your Future Endeavors ", "They Are All Well Respected And Obedient Student During Their Period, I Wish Them Good Luck.",
        "Be Faith And Honest Where Ever You Find Yourself And Be A Good Samaritan ","This Class Of Students Are The Most Dedicated Hardworking And Committed Set Of Student I Have Ever Thought In My Teaching Career. ","I Wish All The Out Going Ss Iii Students Class Of 2019 Success ",
        "Fear Allah Everywhere You Go And Be A Good Helper ","My Students: May Allah Help You Through Out Your Studies And Your Life ",
        "Life Is Full Of Ups And Downs, Thus Be Prepared To Face Reality Of Life And Let Your Secondary School Education Be A Stepping Stone In Your Life Career","-","Allah Ya Saku Sami Sakamakon Jarabawa Mai Kyau, Kuma Ya Zame Muku Mai Albarka",
        "Always Be Good To Your Partners, They Should Be Good For You. ","If You Love Your Mental Health, Avoid Associating With The Unwise. ","-","I Have No Doubt, You Will Make It As You Go Fore. ",
        "Our Prime Purpose In This World Is To Help Others, But If You Cant Help Them At Least Don’t Hurt Them  ","Hardworking, Dedication And Discipline Students  ","Self Discipline Is Commendable ","They Are Hardworking Diligence To Their Studies.  ","Very Obedient And Tolerant Student  ",
        "Well Discipline And Committed Set Of Students  Commendable ","Satisfactory And Good Conduct  ",
        "-","The Out Going Set Of 2018/2019 Academic Session Are Fully Committed To Their Academic Activities. I Will Use These Opportunity To Call On All Of You To Sit Up And Face The Future Challenges, May God Bless You All",
        "-","Well Discipline Student",""
};




        Staffphoto = new int[] {R.drawable.principal, R.drawable.viceprincipal, R.drawable.vpadmin, R.drawable.examofficerinternal, R.drawable.hodbiology, R.drawable.hodchemistry,
        R.drawable.hodphysics, R.drawable.agricteacherrevenue, R.drawable.labourmaster, R.drawable.englishrabiatu, R.drawable.jamilastaff,R.drawable.physicsnazir, R.drawable.bluehousemaster,
        R.drawable.displinemaster,R.drawable.geographysaba, R.drawable.adminofficer, R.drawable.eovii,R.drawable.hodcompscienc, R.drawable.abdulahmedstaff,R.drawable.zainabstaff, R.drawable.hodhausa,
        R.drawable.chemteacher, R.drawable.healthscience, R.drawable.hodmaths, R.drawable.pressclub, R.drawable.arabichod,
        R.drawable.seniormaster, R.drawable.patronmssn, R.drawable.yellowhousemaster, R.drawable.sbmcchairman,R.drawable.ptachairman,
        R.drawable.businessstudies, R.drawable.seniormistress, R.drawable.eovi, R.drawable.security, R.drawable.girl,R.drawable.girl};

        // Locate the ViewPager in viewpager_main.xml
        viewPager = findViewById(R.id.pager_staff);
        // Pass results to ViewPagerAdapter Class
        adapter = new ViewPagerAdapterStaff(StaffActivity.this, StaffName, Rank, StaffPhonenumber, StaffAddress,  StaffComment, Staffphoto);
        // Binds the Adapter to the ViewPager
        viewPager.setAdapter(adapter);


        /*After setting the adapter use the timer */
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                final int NUM_PAGES = StaffName.length;
                if (currentPage == NUM_PAGES-1) {
                    releaseMediaPlayer();
                    Intent intent = new Intent(StaffActivity.this, Categories.class);
                    startActivity(intent);
                    finish();
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
    protected void onStop() {
        super.onStop();

        releaseMediaPlayer();
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
            mMediaPlayer = MediaPlayer.create(StaffActivity.this, R.raw.champions );
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

