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

public class ClassB extends AppCompatActivity {
    int currentPage = 0;

    Timer timer;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(ClassB.this, Categories.class);
        startActivity(intent);
        releaseMediaPlayer();
        finish();
    }

    final long DELAY_MS = 500;//delay in milliseconds before task is to be executed
    final long PERIOD_MS = 5000; // time in milliseconds between successive task executions.



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
        setContentView(R.layout.activity_class_b);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        // Generate sample data
        Name = new String[] { "Khadija Haruna Waziri","Abdulwahab Muhammad Bawa ","Fatima Ibrahim Isah ","Salmanu Abdullahi","Mustapha M Kabir","Hafsat Muhd Bawa","Fatima Muhd Sani",
                "Alamin Musa Sambo","Aishatu Muhd Rahama","MUHAMMAD HAFSAT SAMBO","Hafsat Hussaini Ibrahim","Muhammad Sabo ","Abubakar Murtala Ladan ","Aishatu Shu’aibu Musa ",
                "Humaira Yusuf  ","Fatima Tahir Ahmad ","Hindatu Lawan Yusuf ","Aisha Aminu Habibu ","Umar Ibrahim Jalo  ","Fatima Ahmad Iscerh ","Habeebah Umar ","Abdullahi Mustapha Ilelah ",
                "Umar Garba Gamawa ","Abdullahi Umar Abdullahi ","Abdulmalik Adamu Manga ",
                "Fatima Ahmad Limanci","Amina Muhammad Danfulani ","Sanusi Aisha Tatimu","Fatima Tijjani Usman ","Ahmad Tijjani Sani ",
                "Khadija Ibrahim Jalo	","Ummul-kultum A. Abubakar ","Yahaya Usman ","Khairat Ibrahim Abubakar ","Shafa’atu Salihu ","Abbas Yusuf","Khadija Abdullahi ","Mardiyya Nuru Uba	",
                "Musa Ibrahim Musa","Rukayya Abubakar","Abdurrashid Abdulhamid","Ahmad Yahya Musa","Abubakar Muhd Giyade	","Hauwau Hudu Duguri","Ibrahim Ibrahim Alfa","Fadila Aminu",
                "Halima Adamu Tatimu", "Muhammad Kamal Abubakar","Abdulbasid Alkasim Ahmad","Isyaka Sani","Fatima Sirajo","Fatima Inuwa malamin kasuwa ","Amina Ibrahim",
                "Suwaiba Muhammad Sunusi ","Nuru Mahmood ", "Maryam Aliyu Sani ","Munirat Saleh","Nafia Dahiru","Fatima Abba Jabir	","ASIYA Nuru","Hansa’u Al-amin ",
                "Abdulfatah B. Ahmad ","Abdurrashid Salihu","Abubakar Salihu Muhammad ", "Amina Abdullahi Ishaq ","Fauziya Yakubu Ladan ","Fatima Sulaiman ","Aisha Humaira Ahmad ",
                "Abdullahi Ibrahim Marafa ", "Abubakar Muhammad Lawan","Salihu Inuwa Abubakar","Fatima Muhd Garba","Aisha Muhd Garba","Abubakar Fatima Baba ","Maryam  MB Ahmad",
                "Amina Muhammad ","Khadija Usman ", ""};

        Nickname = new String[] { "Khadija","Shugaba, Ligido, Shabalee, Shamatanzee ","Teemer","Abba, Mickey, and salboy ","Star rider ","Hafsat","Fatyn Baby ","B – Ladies ","Nuaeshl ummi",
                "Hafsy","SEEMERH","Ustaz","Soja","Miemie","Aisha", "Teema","Heendue","Salmanu Aminu Habibu ","Kanyara / jalo","Hupteey ","Momeey","ILELAH/ISCO/MAGAJI ","Umar Faruq (Garbu)",
                "Abdul","Mangha. 02 ","Mooheebbats","Fulani","Alieeyshert, Tertieeymurh, Glounous Queen, ","Teemah luv","Babban Mallami (GR)","Ummee",
                "Besty","Yahaya","khaerath – Ummul ","Bride","Tyga","Bitti","My Deeya ","Black Error ","Nickey","AA on top ","Dan beauty ","Giade","Waleedah","khalifa",
                "Fadee Baby ", "Leemat","Kamal","Malam Abdul ","Sufee","Feemah","pharteey Enuwarh/ homo","Asseyah","Sumy girl","Atama/ lash babaji ","baby","Mursat",
                " Nafee’at","babagana","Goruban Zuciya","Lady Sabira ","Attacker/ Oga","Abu Tarab ","Chairman or S.A ","Amincy ","Fauzee baby ","Zahrah",
                "Humaira","lil, marafs, marafancy, marafan job ","Abba","Prince Cele ","Xerreh","Ummi","Baba","Memeey","Meenat","Nancy",""};
        Phonenumber = new String[] { "08080618661","08131863942","08165171148","08105625565","08162191009","08168759232","08147107904","08148685107/ 08035840283","09033298284",
                "08144222326/09031121277","08036968421","09037098385","09053440905","08132444559","09036510031","08144637988","09030833245","08136832111","0903387840/07081809891","08166678944",
                "07066773597","08039227458/ 08024942505","08132800865/ 08088837565", "08108155036","08062596918/ 08029786855","08145159745","09037860187","07013287081","08133531094/09071701473",
                "08027518544/ 08160494976","08065069048","08031369531", "08064139828","08053444446","07012020080","08066549577","08063725747","08168882850,07084197327","09063203637/09038953609",
                "09029859404","07060678498","08032398078","09068872326","07068389926","07069706638","09066689925","08080324211","09030627080","08032852129","09060178496","08069337627",
                "08106244717/ 07033800081","07055968870","09020602235/ 0803136905", "08036406802/ 09064446569","08164422399","09037517179","08160521955","08038981808","08106021216/ 08036475390",
                "07033671494","09031821822","07088189959","08028564655/ 08021218759","08162933802","08165026021","08103610184",
                "08145643570","09039412239","07033362609","09068424143","08135686514","08112703244","09061966311","08032820269","-","07031193054","" };

        Address = new String[] { "Gwallaga Opposite Gwallaga Mosque","Kwallaga Kusa Da Gidan Sarkin Dawaki ","Wunti Street Bauchi Gidan Lalaye ","Karofin Madaki",
                "Kandahar Near Alhaq Private School Bauchi", "Gwallaga Opposite Shifa Royal Hospital","Lungun Garkuwa Unguwan Borno ","Gwallaga Street Behind Shifa Hospital ",
                "Bakin Kurah","Bakaro Opp. Shariah Commision",
                "Unguwan Jaki Street(Opp. Izalah Mosque)","Gwallaga Behind Babbale House ","Ajiya Adamu Road No.1 Lungun Matori ","Wunti Streat (Gidan Masa)","Malam Goje Baban Rogo Resident ",
                "Fadaman Mada ","Opp. Central Market ","Nassarawa Jahun Layin Isah Yuguda ","Gwallaga Near Aluminum Pot Producers ","Tsohon Masallacin Gwallaga ",
                "Wunti Street ","Ilelah Street Behind Garu Microfinance Bank Bauchi ","Muda Lawan Behind Azman Oil Ltd, Works Quarters ","Zonal Education Office Along Railway Road Bauchi ",
                "Gombe Road ","C293 Wase Raod Bauchi ","Nassarawa Primary School","Kobi Street, Limamin Kobi House Opposite Kobi Shoping Complex  ","Bayan Bata Nearest Kamtah ",
                "Kobi Foot Ball Field Behind Dispensary ","Kobi Street ","Nufawa","Wunti Street Malashe ","-","Winty Street ","Fadaman Mada Kkn Road House No. 6","State Low Cost ",
                "Anguwan Jaki/ Lungun Gidan Pali, Gidan Baba Nuru","Fadaman Mada","Yakubu Wanka Behind Zannuwa Pri School ","Kobi Football Field Bauchi ",
                "Near Babangida Square ","Ganjuwa","Nasarawa Street Near Shukrah Academy ","Ganjuwa Gidan Makama ","Jaja Street (Near Zonal Office)","Unguwar Jaki Alhazai House","Wunti Street ",
                "Karofin Madaki Street Near Masu Miya House  ",
                "Yakubu Wanka Beside Dawaki’s House ","Kandahar Nearest Sabin Shaguna ","Gidan Malamin Kasuwa C16 Kobi Street ","Karofin Madaki","Mabuga","Ilelah Street Bauchi ",
                "Karofi Madaki Gidan Gyada ","Railway Road Opp Zonal Office  ","Kofar Dumi",
                "Kobi","Unguwar Bauchin Bauchin Tsohuwar Makabarta","Jahun Garun Galadima Gindun Durumi ",
                "Nassarawa Street Near Shukrah Motors ","Gwallaga Jumuat Mosque ","Bakin Kura Near Rariya Primary Sch, Wajen Ali Mai Hayan Keke ","Tsohuwar Makabarta Jahun ",
                "Makera Wunti Street ", "Kobi Street ","Bayan Comprehensive Day Secondary School Bauchi (Makara Huta)","Jahun 1 Near Married Woman Sec. Sch.",
                "Ganjuwa Jahun Street ","Ganjuwa Behind Mass Transit","Behind Vocational","Behind Vocational ","Opp. Central Market Bauchi",
                "Railway Road Bauchi ","Kobi Street Near Makkah Specialist Eye Hospital ","Fadaman Mada",""};
        Hobby = new String[] { "Quran","Football","Reading","Chatting, Reading And To Be With Friends","Stunts And Drift","Ice Cream ","Quran Recitation ",
                "Reading, Music & To Be With My Friends At Sch ","Reading, Chatting And Dancing","Chocolate ", "English Novels","Reading Book","Body Building ",
                "Quranic Recitation","Keeping Smlling ","Eating And Sleeping ","Reading Quran And Novel ","Studying", "Quranic Recitation ","Honesty & Recitation Quran","Beans And Rice ",
                "Watching Film And Reading ","Football","Learning And Playing ","Electrical And Electronics Construction ", "Basket Ball And Reading ","Reading",
                "Recitation Of The Holy Quran ","My Luv ","Recitation Of The Holy Quran ","Rice And Beans ","Nufawa","Recitation Of The Holy Quran ","Reading / Hanging Out With Friends ",
                "Study And Love ","Hiphop", "Enjoying Movies, Series Especially Korean ","I Lyk Reading Faster And I Like Our Teachers","Football, Wrestling","Recitation ","Quran Sunnah ",
                "Playing Football ","Watching Football","Reciting Qur’an And Chatting, Sleeping ","Reading And Gardening ","Reading Quran","Reading","Research","Recitation Of Quran ",
                "Prophet Muh’d And My Parents","Al-Quran ","Quranic Recitation ","Honesty & Recitation Of Holy Quran ","Quranic","Reading News Paper ","Reading Quran ","Reading",
                "Quran Recitation","Games","Charting","Reading Quran ","Friendship And Money ","Quranic Recitation ", "Watching Films/ Driving Machine And Reading Quran ","Marriage",
                "Love","Rice And Beans ","Reading And Playing Games ","Cricket","Football","I Like Hip Hop Music","Soccer","Chatting",
                "Usually Peacekeeping","Quranic Recitation ","Performing Exercise ","Travelling And Sleeping ",""
        };
        Ambition = new String[] { "Medicine","Doctor","Midwifery","Health Professional","Nigerian Airforce", "Medical Doctor ","Medical Doctor ","Professional In Computer Sicence ",
                "Medical Doctor","Midwifery","Human Anatomy/Physiology","I Want To Be A Scholar Of Islam ","Soldier","Medical Doctor ",
                "Doctor","Nurse","Veterinary Technician ","Doctor","Pharmacist","Dentist","Medical Laboratory ","Computer Engineer ","To Be In Paradise ","Medical Doctor ",
                "Software Programmer ","Medical Doctor ","Medical Lab", "Gynecologist ","Gynecologist","To Be Computer Professional (Hacker) ","Medical Lab ","Nurse Or Midwifery ",
                "To Be A Custom Officer","Pharmacy/ Medicine ", "I Want To Be A Doctor ", "Architecture","Hits","Agric Engineer","Computer Engineering","Doctor","Medical Doctor ",
                "Medical Doctor ","Computer Engineer","Medical Doctor ", "Custom Service Security ","Computer Science","Doctor","Architecture","My Ambition Is To Be A Lecturer ",
                "Botanist/ Agriculturer  ","Nursing","Nurse","Nursing", "Midwifery Doctor ","To Be A Competent Medical Doctor","Doctor","Doctor","Medical Doctor","Medical Lab","Teaching",
                "Doctor","Nigerian Airforce ","Soldier","I Want To Be A Doctor Or A Business Man ","Doctor","Nurse","Medical Lab ", "Doctor ","Medical Doctor","I Want To Become A Doctor ",
                "Doctor","Pharmacist ","Pharmacology","Nurse","Pharmacist","Nurse","House Wife",""
        };
        Comment = new String[] { "Mashaallah","All The Best ","-","Sure I Miss My Mickey, My Friends My Teachers, My Form Master As Well As My School.","No Hurry In Life, Successful Life, Na Money Be Fine Bobo",
                "This Is My Love For You 2 Give You The Best Things I Can Afford, To Afford You The Best Time I Can Spare. Miss You All!!!","Thanks For The Best School ","-",
                "Sure I Miss My Friends My Teachers As Well As My School", "Alhamdulillah","I Would Like To Thank Everyone That Supported Me Especially My Parentsnd Teachers, Proud Of My School….",
                "I Wish All Class Made To Even You Want In Your Life ","Masha Allah ","I Love All My Class Mate ","Alhamdulillah","I Will Miss The Fun And My Friends There In Dr. Ibrahim Tahir ",
                "Wishing You Good Life And Peace Keeping ","Please Don’t Forget With Me Guys ",
                "Don’t Be Intimidated By Your Friends Success, The Sky Is Wide Enough For Birds To Fly Without Touching One Another. ","Education Is Our Successes ","-",
                "See Me See Trouble Allah Ka Shiryemu Gabadaya ","School Life Is The Best ","No Condition Is Permanent ",
                "I Wish My Parent Were Like Google, They Should Understand Me Even Before I Complete!!!","My Comments Is Always Be Kindness Because Kindness Produces Beauty & Lack Of U Result In Ugliness ",
                "I Wish All The Best ","Fly In The Plane Of Ambition And Land On The Airport Of Success, Luck Is Yours, Wish Is Mine May Ur Future Always Shine .With Lotz Love @Ss3 2019 Glorious Shiners And Also My Closer Friends ",
                "I Would Like To Thank Everyone Who Supported And Helped Me Especially My Father And Teachers Up Dr. Itcs Up My Fans ","Life Is Noting Without Friendship, School Life Is The Best. (Masha Allah) ",
                "Proud Of Dr. Itc Proud Of All My Fans & Teachers I Wil Like To Thanks Every One Who Love Me Up Dr. Itc Up Ibrahim Tahir ","Masha Allah ","School Life Is The Best ",
                "Education Is The Most Powerful Weapon Which You Can Use To Change The World, So Be Prepared To Learn ","Nothing Good Come Easy And Then Time Is Money ","I See No Changes ",
                "Let Your Smile Change The World Don’t Let The World Change Your Smile ","I Wish All The Students Of Dr Itcs To Be Punctual Everywhere They Go And I Pray For The Student Tove Good Result In Jamb,Waec And Neco",
                "Young Shall Grow","None","Always Our Life Is Going By Stages So There Is No Hurry In Life ",
                "Masha Allah I Miss You ","No Condition Is Permanent","Never Relent On Your Education Cox Its Where The Brightness Of The Future Lies ",
                "Allah Ya Taimake Mu  A Dukkan Lamuran Mu Na Rayuwan Duniya Da Lahira ","Masha Allah, Tabarakallah, May Allah Help Us","-","Shege Salmanu ",
                "Life Is Nothing Without Friendship ","Victory Is From Allah Alone, Wish You Among The Best Wherever You Are","Proud Of My Parent Proud Of My Teachers ",
                "If People Are Trying To Bring You Down It Only Means That You Are Above Them ","Education Is You Door To Your Future ","Don’t Say I Am Fine, Say Alhamdulillah They Is Ok !!!",
                "Being Honest Is The Only Way Of Trust Worthy And Sagacity Of Life ",
                "So Between Da Miles & Between Da Years Is Inevitable But Its True Dat Seasons May Change & People May Change But My Heart Always Belonged To U! Never Forget U. ",
                "I Wish You All The Best ","Really Missed My Teachers","I Wish You All The Best ",
                "Education Is The Better Safeguard ","-","School Life Is The Best, I Miss My Friends Wishes All The Best ","No Food For Lazy Man ",
                "I Was Very Happy Because These Year I Will Graduate Senior Sec Sch And I Will Cognate My Quranic Graduation ","Alhamdulillah","Insha-Allah ",
                "Proud Of Dr. Itc Proud Of All My Fans & Teachers Like To Thanks Every One Who Love Me Up Dr. Itc Up Ibrahim Tahir ",
                "-","The Best Among The Rest ","School Life Is The Best ","Masha Allah","May Allah Accept Our Prayer, Wish U All The My Friends",
                "May Allah Help Us Succeed In Our Exams Wish U D Best My Friends","Wishing U All The Best","I Wish Oy All Success Life & Joy ",
                "I Miss My Teachers And Also You My Guys ","Missing Ibrahim Tahir With Hot Boiling Tears",""};

        photo = new int[] {R.drawable.khadijaharuna, R.drawable.abdulwahabmuhammad, R.drawable.fatimaibrahimisah, R.drawable.salmanuabdullahi, R.drawable.mustaphamkabir,
                R.drawable.hafsatmuhdbawa, R.drawable.fatimamuhammadsani, R.drawable.alaminmsabo, R.drawable.aishatumuhammadrahma, R.drawable.muhammadhafsatsambo,
                R.drawable.hafsathussainiibrahim, R.drawable.muhammadsabo, R.drawable.abubakarmurtalaladan, R.drawable.aishatushuaibumusa,
                R.drawable.humairayusuf, R.drawable.fatimatahirahmad, R.drawable.hindatulawan, R.drawable.aishaaminuhabibi, R.drawable.umaribrahimjalo,
                R.drawable.fatimaahmadiscerh, R.drawable.habeebaumar, R.drawable.abdullahiilelah, R.drawable.umargrabagamawa,R.drawable.abdullahiumar,
                R.drawable.abdulmalikmanga, R.drawable.fatimaahmadlimanci, R.drawable.aminamuhammad, R.drawable.sanusiaisha, R.drawable.fatimatijjani,
                R.drawable.ahmadtijjani, R.drawable.khadijaibrahim, R.drawable.ummukultumabubakar, R.drawable.yahyausman,R.drawable.khairatibrahim,
                R.drawable.shafaatusalihu, R.drawable.abbasyusuf, R.drawable.khadijaabdullahi, R.drawable.mardiyyanuru, R.drawable.musaibrahim,
                R.drawable.rukayyaabubakar, R.drawable.abdurrashidabdulhamid, R.drawable.ahmadyahya, R.drawable.abubakarmuhd,
                R.drawable.hauwauhudu, R.drawable.ibrahimibrahim, R.drawable.fadilaaminu, R.drawable.halimaadamutatimu, R.drawable.muhammadkamalabubakar,
                R.drawable.abdulbasidalkasim, R.drawable.isyakasani, R.drawable.fatimasirajo,R.drawable.fatimainuwakasuwa, R.drawable.aminaibrahim,
                R.drawable.suwaibamuhammad, R.drawable.nurumahmood, R.drawable.maryamaliyusani, R.drawable.muniratsaleh, R.drawable.nafiadahiru,
                R.drawable.fatimaabbajabir, R.drawable.asiyanuru,R.drawable.hansasalmin, R.drawable.abdulfatahahmad,R.drawable.abdurrashidsalihu,
                R.drawable.abubakarsalihumuhd, R.drawable.aminaabdullahiishaq, R.drawable.fauziyayakubu, R.drawable.fatimasulaiman, R.drawable.aishahumaira,
                R.drawable.abdullahiibrahim, R.drawable.abubakarmuhammad,R.drawable.salihuinuwa, R.drawable.fatimamuhdgarba,
                R.drawable.aishamuhdgarba, R.drawable.abubakarfatimababa, R.drawable.maryammb, R.drawable.meenat, R.drawable.khadijausman,R.drawable.girl};

        // Locate the ViewPager in viewpager_main.xml
        viewPager = findViewById(R.id.pagerb);
        // Pass results to ViewPagerAdapter Class
        adapter = new ViewPagerAdapter(ClassB.this, Name, Nickname, Phonenumber, Address, Hobby, Ambition, Comment, photo);
        // Binds the Adapter to the ViewPager
        viewPager.setAdapter(adapter);


        /*After setting the adapter use the timer */
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                final int NUM_PAGES = Name.length;
                if (currentPage == NUM_PAGES-1) {
                    releaseMediaPlayer();
                    Intent intent = new Intent(ClassB.this, Categories.class);
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
            mMediaPlayer = MediaPlayer.create(ClassB.this, R.raw.classb);
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



