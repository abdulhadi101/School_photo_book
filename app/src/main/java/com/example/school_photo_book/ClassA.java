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
import java.util.jar.Attributes;

public class ClassA extends AppCompatActivity {

    int currentPage = 0;

    Timer timer;
    final long DELAY_MS = 500;//delay in milliseconds before task is to be executed
    final long PERIOD_MS = 5500; // time in milliseconds between successive task executions.



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
        setContentView(R.layout.activity_class);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        // Generate sample data
        Name = new String[] { "Usman Usman Tama", "Safwan Aminu", "Hafsat Muhammad Danfulani", "Nuhu Muhammad Malami",
                "Muhammad Zakariyya", "Zainab Badamasi", "Munirat Usman Abubakar", "Pherteemarh Muhammad Zubair", "Hauwa'u Muhammad Kabir", "Ummisalma Muhammad Kabir",
                "Saadatu Sani Baraya", "Suwaiba Nuru Uba", "Salihu Musa GHani", "Usman Baba Shehu", "Rukayyah Haruna Waziri", "Rukayya Haruna",
                "Sulaiman Danjuma Adamu","Abubakar Adamu Sallama", "Abubakar Salisu Abubakar", "Amina Saminu Ladan", "Fatima Abdullahi Musa", "Ummukhulthum Yero Ahmad",
                "Hauwa'u Usman Abdullahi", "Khadija Kabir Mu'azu", "Musa Bala Wunti", "Rukayya Hussaini Umar", "Shu'aibu Mu'azu", "Zaidu Muhammad Nasir",
                "Sa'adatu Muhammad Umar","Khadija Salisu Muhammad", "Hauwa'u Badamasi", "Khadija Yakubu", "Sa'adatu Ladan Zailani", "Hafsat Ibrahim Abdullahi","Khadija Hussaini Idris",
                "Fatima Bala Baba", "Lawziah Ayuba Abubakar", "Abubakar A Garba", "Aisha Abubakar Kobi", "Badiatu Abubakar","Dauda MUhammad Babaji", "Fatima Aliyu Ibrahim",
                "Fatima Aliyu Isah", "Fatima Aminu Ahmad", "Hamisu Khadija Abubakar", "Ameenah Abdullahi Dogo", "Maryam Salihu", "Sam'anatu Shuaibu Abdulhamid",
               "Fatima Idris", "Fatima Shehu Aminu", "Hafiza Abdullahi Launi", "Halima Abba Jabir", "Ibrahim Aminu Vono", "Khadija Adamu Tateemu", "Maryam Adamu Sakwa",
                "Maryam Sulaiman Muhammad", "Musa Adamu Sallma", "Nabil Abdulmajid", "Naja'atu Ibrahim Hassan", "Rukayya Zakariyya", "Sani Isah Sabo","Sulaiman Alkasim Ahmad",
                "Umar Adamu Jibrin", "Umar Ibrahim", "Yusuf Adamu MUhammad", "Zainab Ahmad A;iyu", "Fatima Ya'u", "Jabir Yusuf",
                "Abubakar Sadiq Musa", "Usman NAsir ABubakar","Hajara Salihu Adam", "Sadiq Usama Zakhir",""};

        Nickname = new String[] { "Abberh Tama","S.Yellow","Sweet Danfulani","Amir Alarama","Solves","Zee Baby","Hanan","Kayanta","Jiddah","Ummreery","Kayanta","Subeey","Ghaneey","Tijey",
        "Rukayyah","Preety", "Sulaiman", "Abba Sallama",
        "Mal Liman ", "Amzee", "Teemar", "Ummu", "Aunty Hauwa","Kherdeey","Abba","Ambassador","Baffa", "Zaid", "Dimple Baby", "Momy", "Mohiny", "Hamra", "Hafdat",
        "Hafsat", "Mamee","Teema" ,"Ammeey", "Abba", "Kobi", "Baby", "Mal. Dauda",
        "Fateey", "Faty", "Faty On Top", "Nernerh", "Ammad Or Muhna", "Yusmar", "Baby Sam'anatu", "Princess Teema", "Sister/Ashfarh", "Hafiza", "Ummi", "Dan Doctor", "Damseenah", "Baby", "Walidah", "Soso", "Smally",
        "Najah", "Precious Queen", "Ameer", "Sule", "Fulani/Lab. Prefect","Faruq",
        "Ustaz","Mama Zee","Princess Zahra","Jabir","Baban Budurwa","Chuskey","Iyatu","Dan Vigilante","" };

        Phonenumber = new String[] { "07084552019/08163565849","08140701900/09077316886","08129897337","08062670429","08148710044","07065898388","081028871246",
                "07069799074", "08130919198/ 08148804045","09032363199","09077267313","07069418288/09079299827","08130089812", "08135714343", "07068008155", "09017851575/081734509009",
                "08065439649", "08100225633", "08020533229", "09037599170", "09038403118","07065821093","08130405337/08062268277","08169251120",
                "09068004104", "08107861802","08160667241","09055226489/07066249421","09034328164","08069188869","08168669192","09039984192","09038500610/07084815416","08038194147","081427393339",
                "08101134727","07053296653/081095450531","0816636580","08160551945","09036003187/0803642908",
                "09035288468/08099029329","09060919849","","","09060721690","07063559384/08034026316", "07062685235","07030412669","08109646140","09068315578/0901696169","08135836592",
                "08036480400","09066367483/081333335349", "08080324211","07032954966","08167499942/08168706415",
                "08164571559","09069769454", "0814946615",
                "07014232814","09068343270","08163111571","08100707014/08176081808","08082435915","08172013472",
                "08147432584","08168413556","07067931690","09082026204","09066232358","09065382912/09034832351","07062674960",""};

        Address = new String[] { "Filin Kobi Opp. Markazi","Kobi Street Behind Sharia Court Bauchi State","Nasarawa","Bayan Fomwan Ganjuwa, Behind Zonal Education Bauchi",
        "Nassarawa Jahun, Saleh Saleh Junction","Fillin Kwallon Kobi Bauchi ","Yelwan Makaranta","Ganjuwa Lungun Gidan Ciroma Opp Gidan Su Abubakar Assistant Head Boy",
        "Atb Housing Estate Bauchi Near Police Station ", "Atb Housing Estate Opp No. 3 House Near Police Station Bauchi", "Late Tafida House Ganjuwa Jahun Road Bauchi, Bauchi State",
        "Unguwan Jaki Lungun Gidan Pali Gidan Baba Nuru", "Shehu Wunti Estate House No. 7", "Nassarawa, Behind Gwallaga Mosque", "Gwallaga Opp. Gwallaga Mosque","Yakubu Wanka Opp Zaranda Store",
        "Nufawa","Cikin Fada","Unguwar Ganjuwa Opp. Jahun Street Gidan Chiroma Gidan Baba Yahya", "Kobi","Nassarawa Garun Galadima Gidan Suya","Nassarawa Street Near Shukrah Academy",
        "Unguwan Bauchi","Kobi Street", "Wunti Street Gidan Kanwa","Fadan Bayak Behind Sawaba Pharmacy","Fadan Bayak Behind Sawaba Pharmacy","Igbo Quarters", "Madina Qtrs. Along Turun Road Bauchi", "Karofin Madaki Bauchi",
        "Fillin Kwallon Kobi, Kobi Bauchi", "Kobi Street","Kobi Street","Madinah Quarters Right Hand Corner From Mai Anguwa Mosque", "Nassarawa Jahun Isa Yuguda Crecent, Bauchi",
        "Karofin Madaki Street Bauchi", "Kobi Behind Pri. School Bauchi, Bauchi State", "Nassarawa Street, Nassarawa ‘Yankifi","Behind Kobi Pri Sch", "Kobi Football Field",
        "Railway Road  Lungun Muhd Sade", "Igbo Quarters Bayan Stadium","-","-","Fadamar Mada, Behind N.I.D.B", "Gwabbah", "Magaji Quarters Behind Nnpc",
        "Sabuwar Kasuwa, Railway Road Bauchi", "Ajiya Adamu Road Digan Yaya Filin Taki Ganuwa", "Nufawa Bayan Bata, Gidan Malam Attah", "Wunti Street","Kobi",
        "Bayan Gidan Bare-Bari Kofar Ran Bauchi, Opp. Nanee Nasfat Village ", "Unguwan Jaki Street (Alhazai House) ","Ganjuwa Saleh Saleh", "Karofin Madaki First Transformer Junction",
        "Cikin Fadan Bauchi", "B313 Wunti Street Gidan Kanwa","Unguwar Jaki Quarters Front Of Abdullahi Sogiji House","Ganjuwa Sale-Sale", "Bayan Bata Nufawa",
        "Karofi Unguwan Gayya(Temporary)","Yakubu Wanka Beside Dawaki’s House", "Kobi Football Field Bauchi", "Dutsen Tanshi","Kobi Street Daurah Da Unguwar Jaki Bauchi",
        "Nasarawa Jahun ", "Fadaman Mada Gamawa Close", "Jahun Street Near Nafsi Nafsi Shop", "Wunti","Nassarawa Near Gwallaga Mosque Bauchi ","Nassarawa",""
};

        Hobby = new String[] {"Education ","Reading ","Reading","Reading Qur’an And Travelling  ","Seeing My Friends All The Time ","Azkar","Recitation Of The Holy Qur’an ","Playing",
                "Quranic Recitation ", "Quranic Reading ","Recitation Of The Holy Quran And Reading House Novel ","I Like My Self To Be Educated ",
                "Reading, Charting, Playing Games, Drawing And Music","Football","Quran",
                "Reading And Work ","Prophet Muh’d (S.A.W) ","Nana Khadijah","Reading, Research And Playing Games","Parade","Pardeen ","Reading, Recitation Of The Holy Book And Chatting",
                "Recitation/Listening To Music","Reading","Ball","I Love Reading ","Parent And Teacher ","Charting / Reading ","Reciting Qur’an",
                "Love","Qur’anic Reading ","Loving Allah And Al-Rasul ","Basket Ball ","Reading Novel And Recitation Of The Quran","Reading And Watching News ",
                "Reading Qur’an","Recitation Of The Holy Quran ","Recitation Of Qur’an ","Reading","Volley Ball","Recitation Of Quran","English Novel ","Playing Table Tennis",
                "Sleeping Travelling","Playing Games, Charting ","Recitation Of Qur’an ","Islam","Jallof Rice And Taliya ","Quranic Recitation ","Reading Novels And Recitation Of Holy Quran",
                "Recitation Of The Holy Quran", "Game"," Prayer, Playing, Writing, Reading And Charting","School Life ","Reading Quran ","Qur’an Recitation And Playing ","Football",
                "Read Qur’an  ","Games And Chating With Reciting Qur’an ","Reading","Being A Student","Riding Bicycle","Prophet Muhammad And My Parent ",
                "Quran And Read ","Recitation Of Quran","Quranil Karem ","Reciting Qur’an ","Tailoring","Listening To Quranic Recitation","Football","Read Hard ","Playing Footbll ",""};

        Ambition = new String[] { "I Want To Become A Chemistry Teacher ","Computer Software Programing ","Pharmacology","Alhamdulillah At Any Where I Fine My Self ","Nigerian Air Force ",
                "Medical Laboratory ","Nurse","Nurse","Medical Laboratory Scientist ","I Want To Be Ghaneey Doctor ","Gynaecology","Veterinary Doctor ",
                "Computer Analyst ", "To Be In Paradise ","Medicine","Medical Doctor ","Electrical Electronic Engineering","Micro – Biology ","To Study At Abroad(India / Saudi Arabia)",
                "Medical Doctor ", "Doctor","Medical Doctor  ","Medical Doctor","Doctor","Doctor","Medical Doctor ","I Want To Be A Software Engineer ","I Want To Be A Soldier ",
                "To Be A Chief Justice Of The Federal ( C. J. N)", "Barrister","I Want To Be A Nurse ","Nurse","I Want To Become A Medical Doctor ","Medical Doctor Or Pharmacy ",
                "Medical Doctor ", "To Become A Medical Doctor","I Want To Be A Medical Doctor","Medical Doctor/ Computer Operator","Physician","Nurse","Farmer","Teacher","Nurse",
                "Veterinary Doctor ","Medical Doctor ", "Doctor","Doctor","Nurse","Medical Doctor ","Gynaecology","Nurse","Medical Doctor","Any Good Work, Any Health Working, Engineer,",
                "Medical Doctor Or Teaching ", "I Want To Be A Dentist ","Medical Doctor/ Pharmacist ","Medical Doctor ","Doctor","Midwifery Doctor  ","Grammatology","Doctor",
                "Medical Doctor", "Computer Scientist ","Veterinary Doctor ","Pharmacist","Nurse","Medical Doctors ","Tailoring","Medical  Doctor","Veterinary Doctor ",
                "I Want To Be A Barrister ","Doctor", ""};

        Comment = new String[] { "Mashaallah I Thank God ","I Am Very Found You Are So Interligent Reading The Quran Not Anytime Sometime I Am Reading","I Wish I See My English Teacher Before I Leave ",
                "I Will Never Forget My Friends In My Life And Those Who Show Me Their Really Love ","No Hurry In Life, School Life Is The Best ",
                "Missing U All Is A Terrible Dease My Classmate  We Are Together Forever Insha Allah","Masha’allah",
                "Mashaallah Alhamdulillahi Ala Kulli Halin, We Have Come The End Of Sec. School, Wishing Us All The Best. Haert You So Much Frnds",
                "Missing You Could Turn From Pain To A Pleasure, If A Only Knew That You Were Missing Me Too. Missing U My Sweet Sate Mate",
                "Sad To Say Sai Wata Rana To My Gangacious Set Mates, Never Forget U In My Life ","Every Relationship Has It Problem But What Makes It Perfect Is If U Still Want To Be Together When Things Go Wrong ",
                "I Wish My School To Be The Best Among The Other Schools. And I Wish My Fellow Colleagues To Be Respect Our Elders In  Every Where In The World",
                "A Word Isn’t Enough To Say I Miss You Ss3 Students","School Life Is The Best ","Alhamdulillah","Masha Allah ","No Condition Is Permanent",
                "I Wish Anybody Who Finish Dr. Itcs To Win His Exams","My Friends, Never Stop Learning In What Ever Critical Situation You Are And Also Never Give Up In Your Studies I Mean Try  & Try And Try  One Day You Will Succeed. I Know I Must Miss You My Respected Friends.",
                "Alhamdulillah","Alhamdullah", "I Miss You So Much Friends. Skul Life Is The Best.Ss 3 Student Never Give Up, We The Best Among The Rest","Forget The Past And Fulfill The Future",
                "Masha Allah","-", "I Wish Us All The Best In Our Studies ","School Life Is The Best ","School Life Is The Best ",
                "Missing You Is Not My Wished My Sweet Set Mates; I’m Going, I’m Not Going Because I Dn’t Want To Go Missing Ladies Hauwar Muhd Khabir My Besty ",
                "Mashaallah ","Yesterday I Ask My Heart Something Is Missing What Happen To My Body Answered, There Is Not Heart ! Some One Took It !!..... I Miss My Sate Mate, Never Forget U ",
                "Insha Allah ","Hasbunallahu Wani-Imal Wakil ","I Really Loves My Friends And I Hope I Will Love Them Continuously ","Bravery Is Not Persist In You Errors, But To Acknowledge That U Are Wrong, And Not To Repeat The Error Again ",
                "Masha Allah.","By God Grace Insha Allah. ","Life Is Nothing Without Love And School Is Nothing Without Student !!!","May Allah Help Us Through Our Studies",
                "Masha Allah May Almighty Allah Help Us Through Out Our Studies. Miss U All.","Alhamdulillah","Masha Allah","Hamdan Kasiran","May Almghty Allah (Swt)Help Us  Miss U Friends",
                " Cloud Dance B’cox Of Wind, Flowers Sing B’cox Of Rain, Grass Grows B’cox Of Earth I Live B’cox Of U. U’re All The Reason. Miss U All Lovely Frnds (Ss 3 Student) ",
                "Inshaallah U Can Make It Well  ","Masha Allah ","Masha-Allah ","School Life Is The Best ","Life Is Nothing Without Friends","May Allah Help Us In Our Lives","Masha Allah ",
                "Sure I Miss My Rahama, I Miss My Friends All, May Make Us To Successful In What Ever We Want, I Miss Our School Life Thank God We Come To End Of Dr.Itcs May Allah Bless The Sch & Candidate Of 2019 Mashallah. I Miss You",
                "When Ever I Miss I Will Not Look 4 U In My Dreams Or Try To Hear Ur Voice In Ur Msgs.  I Just Put My Right Hand Across  My Chest And I Feel You.",
                "School Life Is The Best I Really Love Dr. Itc In My Life","May Allah Grants Us Into Jannatul Firdausi ","I Miss You All My Friends ","I Love School Life, School Life Is The Best ",
                "Alhamdulillah May Allah Help Us In Our Future ", "I Really Miss My Friends And Teachers At Large.","Masha Allah","Hmmm!!! Nothing To Say Much, But I Really Miss U Guys ",
                "Ohh! Allah! My Colleagues I Got It Very Painfully That I Am Going To Miss U But One Word,Wish U Among The Best Whereveryou Found Yourself. ",
                "I Wish All Friends, Every One I Wish U All Successful Life In The World ","Never Stop Seeking For Knowledge",
                "Masha Allah Masha Allah ","Really Miss My Friends And Teachers ","Alhamdulillah","I Really Miss My Form Master, And My Teachers And Also My Friends At Large ",
                "Masha Allah ","None Has The Right To Be Worshipped Except Allah ","Hahaha Guys I Ll Miss You A Lot",""};




        photo = new int[] {R.drawable.usmanusman, R.drawable.safwanaminu, R.drawable.hafsatdanfulani,R.drawable.nuhumuhammad, R.drawable.muhammadzakariya,
                R.drawable.zainabbadamasi, R.drawable.muniratusman,R.drawable.fatimamuhammad,R.drawable.hauwaumuhammadkabir,
                R.drawable.ummisalmakabir, R.drawable.saadatusani, R.drawable.suwaibanuru, R.drawable.salihumusa, R.drawable.usmanbaba,
                R.drawable.rukayyaharunawaziri, R.drawable.rukayyaharuna,R.drawable.sulaimanadamu, R.drawable.abubakaradamu, R.drawable.abubakarsalisu,
                R.drawable.aminasaminu, R.drawable.fatimaabdullahimusa, R.drawable.ummukhultum, R.drawable.hauwauusmanabdullahi, R.drawable.khadijakabir,
                R.drawable.musabala, R.drawable.rukayyahusaini, R.drawable.shuaibu, R.drawable.zaidunasir, R.drawable.saadatumuhammadumar,
                R.drawable.khadijasalisu, R.drawable.hauwaubadamasi, R.drawable.khadijayakubu, R.drawable.saadatuladan, R.drawable.hafsatibrahimabdullahi,
                R.drawable.khadijahussaini, R.drawable.fatimabala, R.drawable.lawziyaayuba,R.drawable.abubakargarba, R.drawable.aishaabubakar, R.drawable.badiatuabubakar,
                R.drawable.daudamuhammad, R.drawable.fatimaaliyu, R.drawable.fatimaaliyuisah, R.drawable.fatimaaminu, R.drawable.khadijahamisu,
                R.drawable.ameenahabdullahi, R.drawable.maryamsalisu, R.drawable.samanatu, R.drawable.fatimaidris, R.drawable.fatimashehuaminu,
                R.drawable.hafizalauni, R.drawable.halimaabba, R.drawable.ibrahim, R.drawable.khadijaadamu, R.drawable.maryamadamu, R.drawable.maryamsulaiman,
                R.drawable.musaadamu, R.drawable.nabilabdulmajid, R.drawable.najaatu, R.drawable.rukayyazakariyya, R.drawable.saniisah, R.drawable.sulaimanalkasim,
                R.drawable.umaradamu, R.drawable.umaribrahim, R.drawable.yusufadamu, R.drawable.zainabahmad, R.drawable.fatimayau,
                R.drawable.jabiryusuf,R.drawable.abubakarsadiqmusa, R.drawable.usmannasirabubakar, R.drawable.hajarasalihuadam,
                R.drawable.girl, R.drawable.girl};

        // Locate the ViewPager in viewpager_main.xml
        viewPager = findViewById(R.id.pager);
        // Pass results to ViewPagerAdapter Class
        adapter = new ViewPagerAdapter(ClassA.this, Name, Nickname, Phonenumber, Address, Hobby, Ambition, Comment, photo);
        // Binds the Adapter to the ViewPager
        viewPager.setAdapter(adapter);


        /*After setting the adapter use the timer */
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                final int NUM_PAGES = Name.length;
                if (currentPage == NUM_PAGES-1) {

                    releaseMediaPlayer();
                    Intent intent = new Intent(ClassA.this, Categories.class);
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
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(ClassA.this, Categories.class);
        startActivity(intent);
        releaseMediaPlayer();
        finish();
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
            mMediaPlayer = MediaPlayer.create(ClassA.this, R.raw.classa );
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

