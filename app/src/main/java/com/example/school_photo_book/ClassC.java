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
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import java.util.Timer;
import java.util.TimerTask;

public class ClassC extends AppCompatActivity {

    int currentPage = 0;

    Timer timer;
    final long DELAY_MS = 500;//delay in milliseconds before task is to be executed
    final long PERIOD_MS = 5000; // time in milliseconds between successive task executions.


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(ClassC.this, Categories.class);
        startActivity(intent);
        releaseMediaPlayer();
        finish();
    }

    // Declare Variables
    ViewPager viewPager;
    PagerAdapter adapter;
    String[] Name;
    String[] Nickname;
    String[] Phonenumber;
    String[] Address;
    String[] Hobby;
    String[] Ambition;
    String[] Comment;
    int[] photo;

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
        setContentView(R.layout.activity_class_c);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        // Generate sample data
        Name = new String[] { "Fatima Batula Sunusi","Hadiza Ahmad Muhammad ","Hauwa Musa","Kabir Muhammad ","Khadija Ibrahim","Zainab A. Abdullahi",
                "Maimunatu Mahmood","Maryam Salihu Usman","Shamsuddeen Ahmad","Nasir Abubakar","Nusaiba Mahmood Ari ","Yasmin Ibrahim Abubakar","Rufai Abdullahi ",
                "Sa’adatu Abubakar Yusuf","Saudat Ibrahim Bala","Abdulrahman Abubakar","Aisha Muhammad ","Alamin Kabir Mu’azu ","Aliyu Adamu Alkali","Asma’u Umar Gital ",
                "Adamu Muhammad ","Mahmood  Jibrin","Rayyanu Abubakar ","Abdulmajid Ag Yusuf 	","Ahmad Abdulhamid ","Aishatu Shehu ","Aishatu Yusuf ",
                "Auwal M. Umar ","Bushra Armaya’u ","Habbab Aminu ","Ibrahim Muhammad 	","Khadija Abdullahi Launi 	","Khadija Nuruddeen ","Nusaiba Sani Jibrin",
                "Sadiq Balarabe ","Usman Ibrahim Abubakar ","Sulaiman Ahmed Marafa ", "Sulaiman Adamu Sallama","Ummukulthum Ishaq ","Yusuf Abubakar Yusuf",
                "Zainab Adamu ","Mukhtar Ibrahim Abubakar ","Maimuna Usman","Aishatu Adamu Aliyu 	","Aishatu Muhd Sani ","Asma’u Bashir Adam ",""
        };

        Nickname = new String[] { "Batula","Deezou","-","-","Thailand","Aunty Zee	 ","Maimuna","Anty Maryam ","Burna Boy","Top Guy  ","Aree ","Yersmeen Luv",
                "Star/Sele ","Walida","Saude","Handsome  ","Ishat Chocolaty  ","Alamin","Aleeyous/Yaya Alkali","Gital","Suitor",
                "Officer","Sayyadee","Ag","Ahmad","Mai Gilashi","-","Waske ","Arma Baby","Boc","Brother","Khadeejah","Amira","-",
                "Danyarinya/Na Konami ","Undy","Sule Bobo","Yaya Totti","Ummu","Shola","Jebu","Yabanya","-","Nana Fara ","Nana Baka","Ummin Dady ",""
        };

        Phonenumber = new String[] { "07035833602","08140478849","08109025019","-","09038812093","09030475727","09032068542","08147327785/ 09076037447","09069360309",
                "08146697454", "07060612735","09063214882","08038831143","-","08126586679","08109774090","08105649797","07068453217","07061394488/08133087885",
                "08103848290", "09030695151","09030695151","09066817741","-","07038207627","08057232323","-","08132202478","08147575865","07036417765",
                "08103237773", "09037222487","08148331326","-","07066009840","09032816912","08141587444","-","08149402929/08060951866","09017750105",
                "08023321619", "08065838199","09032104398","07013317841","09065004969","08153011807/ 08143713853",""
        };
        Address = new String[] { "Gwallaga"," Nassarawa Gwabba ","Kobi Street ","-","Nasarawa Jahun Garun Galadima ","Nassarawa Opp. Shukrah Comp. Sch.","Kobi Street ",
                "Kobi Street Bauchi","Kobi","Khandahar Opp Sabben Shagona ","Emirs Drive Street Opposite Local Gov.T Office ","Kk 1 Zaria Rd. By Lagos Street Kd",
                "Kobi Street ","Kobi","M/Goje Garkuwan Fawa Residence ","Kandahar","Doya Oppsite  Jibwis ","Kobi", "House No C290 Gidan Sarkin Zungur,Railway Road Bauchi",
                "Gwallaga Quarters Kusa Da Gidan Malam Giddo","Kobi Street Lovely ","Jaja Quarters ","Nassarawa Street Bauchi, Behind Marafa House ","Bakaro Unguwan Sarkin Kofa  ",
                "Filin Kobi ","Aminu Street  ","Ganjuwa Garun Galadima","Doya Gombe Road","Near Anas Bn Malik Behind Kobi Priscool",
                "Kobi Street ","Makera Wunti Street Bauchi ","Wunti Street Lungun Kwara	  ","Ilelah Street ","-",
                "Bayan Kobi Pri Sch","Kobi Street ","Ilelah Street Gidan Marafa Opposite Central Mosque ","Cikin Fada Bauchi G-Sallama",
                "Sabuwar Kasuwa/ Ibrahim Bako","Kobi","Ilelah","U/Jaki Street Near Yabanya","-","Kobi Street ","Makera Street",
                "Khandahar Kwanan Sabin Shagon Gidan Auwal Manca",""};
        Hobby = new String[] { "Love","Qur’an Recitation ","Reading","-","Singing","Reading Novels	 ","Love","Love","Football","Football",
                "Singing","Reading, Listening To Music, And Travelling","Ps Game ","Love","Keeping Smile ","-","Reading Quran ",
                "Football","Reading,Research And Struggling In Life","Teaching","Lovely","Reading","Reading",
                "Football","Football","Reading","-","Football","Listening To Music","Football","Gangtism","Quranic Research","Love","-",
                "Football","Sister Halima ","Football","Jallof Rice ","Trading/Selling Of Goods","Ball","Dancing","Football",
                "-","Love","Love","Love",""
        };

        Ambition = new String[] { "Doctor","Barrister","Law","-","Barrister","Lawyer","Mass Comm.","Teacher","Soldier","Political", "I Want To Be A Lawyer ","Diplomat",
                "President","Mass Comm.","Journalist","Custom","Law/ Masscom ", "Soldier","Barrister/Public Admn","Business Woman","Reporter",
                "Journalist","Lecturer","Barrister", "Barrister","Doctor","-","-","Journalist","Soldier","Millionaire","Journalist","I Want To Be A Barrister ",
                "-","Soldier","News Paper Media ","Chief Judge Or Profession ","Barrister","Marketer","Sas","Journalist",
                " Medical Doctor ","-","Nurse","Doctor","Account",""};
        Comment = new String[] { "Alhamdulillah ","Alhamdulillah","Masha Allah ","-","Masha Allah ","Do Not Be A Loser My Friends Always Work Hard","-",
                "Alhamdulillah","-","Masha Allah ","Alhamdulillah May Allah Bless Our Studies ","I’m Nobody Tryna Become Somebody","Hamdan Kasiran ",
                "Masha Allah ","I Wish My All School Mates All The Best ","Alhamdulillah ","Alhamdulillah Ala Kulli Halin ",
                "Alhamdulillah","What U Are Passing Through Today; Write It Down Because One Day The World Would Be Ready To Read It. They Will Become Part Of Your Success Story.",
                "Masha Allah ","My Allah His Continuer Bless Our Life ","Masha Allah ","Wish Us The Best Of Luck ",
                "May Allah Help Us About Our Education ","School Life Is The Best ","Alhamdulillah","-"," Masha Allah",
                "May Allah Help Us To See One Another Again In Aljannatul Firdaus",
                "-","I Like Every Body ","Rabbi Ya Sa Mu Ga Junan Mu",
                "I Am Khadijah Nurudden, I Am Very Happy Because I Finish My Secondary School This Year ","-",
                "Alhamdulillah","The School Is Life And Best ","The School Is Life To Best ","Masha Allah ",
                "Alhamdulillah","Masha Allah ","Make Ur Friends Be Good Through Out Your Life, Miss You!","Allahummah Ameen ","-",
                "-","Masha Allah","In The Name Of Allah","" };




        photo = new int[] { R.drawable.fatimabatula,R.drawable.hadizaahmad, R.drawable.hauwamusa, R.drawable.kabirmuhammad,R.drawable.khadijaibrahimc, R.drawable.zainababdullahi,
        R.drawable.maimunatumahmood,R.drawable.maryamsalihu, R.drawable.shamsudeenahmad,R.drawable.nasirabubakar, R.drawable.nusaibamahmood, R.drawable.yasminibrahim,
        R.drawable.rufaiabdullahi,R.drawable.saadatuabubakar, R.drawable.saudatibrahim,R.drawable.abdulrahmanabubakar, R.drawable.aishamuhammad, R.drawable.alaminkabir,
        R.drawable.aliyuadamu, R.drawable.asmauumar, R.drawable.adamumuhammad, R.drawable.mahmoodjibrin,
        R.drawable.rayyanuabubakr, R.drawable.abdulmajidag, R.drawable.ahmadabdulhamid, R.drawable.aishatushehu,
        R.drawable.aishatuyusuf, R.drawable.auwalumar, R.drawable.bushraarmayau, R.drawable.habaaminu, R.drawable.ibrahimmuhammad,
        R.drawable.khadijaabdullahilauni,R.drawable.khadijanurudeen,R.drawable.nusaibasani,R.drawable.sadiqbalarabe, R.drawable.usmanibrahim, R.drawable.sulaimanahmed,
        R.drawable.sulaimanadamu, R.drawable.ummulkultumishaq, R.drawable.yusufabubakar, R.drawable.zainabadamu, R.drawable.mukhtaribrahim,
        R.drawable.maimunausman,R.drawable.aishatuadamualiyu, R.drawable.aishatumuhammadsani, R.drawable.girl,R.drawable.girl};

        // Locate the ViewPager in viewpager_main.xml
        viewPager = findViewById(R.id.pagerc);
        // Pass results to ViewPagerAdapter Class
        adapter = new ViewPagerAdapter(ClassC.this, Name, Nickname, Phonenumber, Address, Hobby, Ambition, Comment, photo);
        // Binds the Adapter to the ViewPager
        viewPager.setAdapter(adapter);




        /*After setting the adapter use the timer */
        final Handler handler = new Handler();



        final Runnable Update = new Runnable() {
            public void run() {
                final int NUM_PAGES = Name.length;
                if (currentPage == NUM_PAGES-1) {
                    releaseMediaPlayer();
                    Intent intent = new Intent(ClassC.this, Categories.class);
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
            mMediaPlayer = MediaPlayer.create(ClassC.this, R.raw.seeyouagain);
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
