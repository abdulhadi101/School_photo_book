package com.example.school_photo_book;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.os.Handler;

import java.util.Timer;
import java.util.TimerTask;
import java.util.jar.Attributes;

public class ClassA extends AppCompatActivity {

    int currentPage = 0;

    Timer timer;
    final long DELAY_MS = 500;//delay in milliseconds before task is to be executed
    final long PERIOD_MS = 3000; // time in milliseconds between successive task executions.



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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class);

        // Generate sample data
        Name = new String[] { "HAMISU, KHADIJA ABUBAKAR", "Ameenah Abdullahi Dogo", "Maryam Salisu ", "Usman Usman Tama", "Sam’anatu Shuaibu Abdulhamid ",
                "safwan Aminu", "hafsat Muhammad Danfulani", "Hajara Salihu Adam", "Nuhu Muhammad Malam", "Muhammad zakariyya", "Zainab Badamasi",
                "munirat usman Abubakar", "pherteemarh Muhammad zubair", "hauwa’u badamasi", "hauwa’u Muhammad kabir", "ummisalma Muhammad Kabir", "Khadija yakubu",
                "saadatu sani baraya","suwaiba nuru uba ", "sa’adatu ladan zailani ", "hafsat Ibrahim abdullahi", "salihu musa ghani", "usman baba shehu",
                "rukayyah haruna waziri", "rukayya haruna", "khadija hussaini idris", "Fatima bala baba", "Sulaiman danjuma adamu", "lawizah ayubah abubakar",
                "abubakar adamu sallama","abubakar a garba", "abubakar salisu abubakar", "AISHA abubakar kobi", "AMINA SAMINU LADAN", "BADIATU ABUBAKAR","DAUDA MUHAMMAD BABAJI",
                "FATIMA ABDULLAHI MUSA", "FATIMA ALIYU IBRAHIM", "FATIMA ALIYU ISAH", "FATIMA AMINU AHMAD", "FATIMA IDRIS","FATIMA S. AMINU", "HAFIZA ABDULLAHI LAUNI",
                "HALIMA ABBA JABIR", "HAUWA’U USMAN ABDULLAHI", "IBRAHIM AMINU vono", "KHADIJA ADAMU TATIMU ", "KHADIJA KABIR MU’AZU", "MARYAM ADAMU SAKWA",
                "MARYAM SULAIMAN MUHAMMAD", "MUSA ADAMU SALLAMA", "MUSA BALA WUNTI", "NABIL ABDULMAJID", "NAJA’ATU IBRAHIM HASSAN", "RUKAYYA HUSAINI UMAR",
                "RUKAYYA ZAKARIYYA", " SANI ISAH SABO", "SHU’AIBU MU’AZU", "SULAIMAN ALKASIM AHMAD", "UMAR ADAMU JIBRIN", "UMAR IBRAHIM","  YUSUF ADAMU MUHAMMAD",
                "ZAIDU NASIR", "ZAINAB AHMAD ALIYU", "UMMUKHULTHUM YERO AHMAD", "sa’adatu Muhammad umar","","" };

        Nickname = new String[] { "Nernerh","Ammad or Muhna","Yusmar","Abberh Tama","Baby Sam'anatu","S.yellow","Sweet Danfulani","Iyatu","Amir Alaramma",
                "Solves","zee baby","hanan","kayanta","mohiny","Jiddah","ummreery","hamra","kayanta","subeey","hafdat", "ameerah Abdoul Ahmad", "ghaneey",
                "tijey", "rukayyah", "freety", "mamee", "teema","sulaiman","ammeey"," ","abba", " ", "", "", "", "", "", "", "","" ,"", "", "", "", "",
                "", "dan doctor", "", "", "", "walida", "", "", "smally", "najah", "", "", "", "baffa", "", "", "", "", "", "", "Ummu", "dimple baby",""};

        Phonenumber = new String[] { "09060721690","08034026316","07062685235","07084552019/ 08163565849","07030412669","08140701900/ 09077316886","08129897337",
                "09065382912/ 09034832351", "08062670429","08148710044","07065898388","081028871246", "081028871246", "08168669192", "08130919198/ 08148804045",
                "09032363199", "09039984192", "09077267313", "07069418288/ 09079299827", "09038500610/ 07084815416","08038194147","08130089812","08135714343",
                "07068008155", "081734509009","08142739339","08101134727","08065439649","07053296653/ 081095450531", "","0816636580","","","","","","","","",
                "","","","","","","","","", "09066367483/ 08133335349","","","","08167499942/ 08168706415","","", "09069769454","08149426615","","","", "08160667241",
                "","","","","","07065821093","09034328164"};

        Address = new String[] { "FADAMAR MADA, BEHIND N.I.D.B","gwabbah","magaji quarters behind NNPC","filin kobi opp. Markaz","sabuwar kasuwa, railway road Bauchi ",
                "kobi street behind sharia court Bauchi state", "nasarawa","Nassarawa near gwallaga mosque Bauchi", "bayan fomwan ganjuwa, behind zonal education Bauchi",
                "nassarawa jahun, saleh saleh junction","fillin kwallon kobi Bauchi","yelwan makaranta", "ganjuwa lungun gidan ciroma opp gidan su abubakar assistant head boy",
                "fillin kwallon kobi, kobi Bauchi","ATB housing estate Bauchi near police station","ATB housing estate opp no. 3 house near police station Bauchi","kobi street",
                "late tafida house ganjuwa jahun road Bauchi, Bauchi state","unguwan jaki lungun gidan pali gidan baba nuru", "kobi street",
                "Madinah quarters, right hand coner from mai anguwa mosque ","shehu wunti estate house no. 7","nassarawa, behind gwallaga mosque","gwallaga opp gwallaga mosque",
                "yakubu wanka opp zaranda store", "nassarawa jahun isa yuguda crecent, Bauchi ","Karofin madaki street Bauchi","Nufawa","kobi behind school Bauchi, Bauchi state ",
                "","nassarawa street, nassarawa ‘yankifi","","","","","","","","","","","","","","","","bayan gidan bare-bari kofar ran Bauchi, opp. Nanee NASFAT village","","",
                "", "karofin madaki first transformer junction","","","B313 wunti street gidan kanwa"," unguwar jaki/ J/K quarters front of abdullahi sogiji house","","","",
                "fadan bayak behind sawaba pharmacy","","","","","","","nassarawa street near shukra academy","madina qtrs. Along turun raod Bauchi", ""};

        Hobby = new String[] {"Playing games, charting","recitation of qur’an ","islam","education","jallof rice and taliya","reading","reading","read hard","reading Qur’an and travelling",
                "seeing my friends all the time","Azkar","recitation of the holy Qur’an","playing","qur’anic reading","quranic recitation","quranic reading","loving allah and al-rasul",
                "recitation of the holy quran and reading house novel","I like my self to be educated","basket ball","reading novel and recitation of the quran",
                "reading, charting, playing games, drawing and music","football","quran","reading and work","reading and watching news","Reading Qur’an","Prophet Muh’d (S.A.W)",
                "recitation of the holy quran","","","","","","","","","","","","","","","","","prayer, playing, writing, reading and charting","","","","qur’an recitation and plying",
                "","","read Qur’an","games and chating with reciting qur’an","","","","parent and teacher","","","","","",""," reading, recitation of the holy book and chating",
               "reciting Qur’an","","" };

        Ambition = new String[] { "MEDICAL DOCTOR","doctor","doctor","I want to become a chemistry teacher","nurse","computer software programing","pharmacology","I want to be a barrister",
                "Alhamdulillah at any where I find my self","Nigerian Air force", "Medical Laboratory","nurse","nurse","I want to be a nurse","medical laboratory scientist",
                "I want to be ghaneey doctor","nurse","gynaecology", "veterinary doctor","I want to become a medical doctor","medical doctor or pharmacy","computer analyst ",
                "to be in paradise","medicine","medical doctor","medical doctor ","to be medical doctor ","electrical electronic engineer","I want to be a medical doctor","",
                "medical doctor/ computer operator","","","","","","","","","","","","","","","", "engineer, politician, marketer , business computer prof","","","",
                "medical doctor/ pharmacist","","","midwifery doctor","","","","I want to be a software engineer ","","","","","","", "medical doctor ",
                "to be a justice of the federal ( C. J. N)","","" };

        Comment = new String[] { "Cloud dance b’cox of wind, flowers sing b’cox of rain, grass grows b’cox of earth I live b’cox of u. u’re all the reason. Miss u all lovely frnds (ss 3 student) ",
                "inshaAllah u can make It well","mashaa Allah","mashaallah I thank god","MASHA-ALLAH","I am very found you are saft so interligent reading the Quran not anytime sometime I am reading",
                "I wish I see my English teacher before I leave","none has the right to be worshipped except Allah","I will never forget my friends In my life and those who show me their really love",
                "no hurry in life, school life is the best","missing u all is a terrible dease my classmate we are together forever insha Allah ",
                "masha’allah","mashaallah alhamdulillahi ala kulli halin, we have come the end of sec. school, wishing us all the best. Haert you so much frnds",
                "yesterday I ask my heart something is missing what happen to my body answered, there is not heart ! Some one took it !!..... I miss my sate mate, never forget u",
                "missing you could turn from pain to a pleasure, if a only knew that you were missing me too. Missing u my sweet sate mate",
                "sad to say sai watarana to my gangacious sate mate, never forget u in my life ","insha Allah",
                "every relationship has it problem but what makes It perfect is if u still want to be together when things go wrong ",
                "I wish my school to be the best among the other schools. And I wish my fellow colliques to be respect our elders in every where in the world ","hasbunallahu wani-imal Wakil",
                "hasbunallahu wani-imal Wakil","a word isn’t enough to say I miss you SS3 students", "school life is the best","Alhamdulillah", "masha allah",
                "bravery is not persist in you errors, but to acknowledge that u are wrong, and not to repeat the error again ", "Masha Allah.","no condition is permanet",
                "by god grace insha Allah.","","life is nothing without love and school is nothing without student ","","","","","","","","","","","","","","","",
                "sure I miss my Rahama, I miss my friends all, may make us to successful in what ever we want, I miss our school life thank God we come to end of Dr. ITCS may Allah bless the sch & candidate of 2019 masallah. I miss you",
                "","","", "may Allah grants us into jannatul Firdausi","","","I love school life, school life is the best",
                "Alhamdulillah May Allah help us in our future ","","","","school life is the best","","","","","","",
                "I miss you so much friends. Skull life is the best. SS 3 student neve give up, we the best among the rest",
                "missing you is not my wished my sweet set met; I’m going, I’m not going because I don’t want go missing ladies hauwar Muhammad khabir my besty","" };




        photo = new int[] {R.drawable.khadijahamisu, R.drawable.ameenahabdullahi, R.drawable.maryamsalihu, R.drawable.usmanusman, R.drawable.samanatu,
        R.drawable.safwanaminu, R.drawable.hafsatdanfulani, R.drawable.hajarasalihu, R.drawable.nuhumuhammad, R.drawable.muhammadzakariya,
        R.drawable.girl, R.drawable.muniratusman, R.drawable.fatimamuhammad, R.drawable.girl, R.drawable.girl, R.drawable.ummisalmakabir, R.drawable.khadijayakubu,
        R.drawable.saadatusani, R.drawable.suwaibanuru, R.drawable.saadatuladan, R.drawable.hafsaibrahim, R.drawable.salihumusa, R.drawable.usmanbaba,
        R.drawable.rukayyaharuna, R.drawable.rukayyaharuna, R.drawable.khadijahussaini, R.drawable.fatimabala, R.drawable.sulaimandanjuma,
        R.drawable.lawziyaayuba, R.drawable.abubakaradamu, R.drawable.abubakargarba, R.drawable.abubakarsalisu, R.drawable.aishaabubakar, R.drawable.aminasaminu,
        R.drawable.badiatuabubakar, R.drawable.daudamuhammad, R.drawable.fatimamusa, R.drawable.fatimaaliyu, R.drawable.fatimaaliyuisah, R.drawable.fatimaaminu,
        R.drawable.fatimaidris, R.drawable.fatimasaminu, R.drawable.hafizalauni, R.drawable.halimaabba, R.drawable.hauwau, R.drawable.girl, R.drawable.khadijakabir,
        R.drawable.maryamadamu, R.drawable.maryamsulaiman, R.drawable.musaadamu, R.drawable.musabala, R.drawable.nabilabdulmajid, R.drawable.najaatu,  R.drawable.rukayyahusaini,
        R.drawable.rukayyazakariyya, R.drawable.saniisah, R.drawable.shuaibu, R.drawable.sulaimanalkasim, R.drawable.umaradamu, R.drawable.umaribrahim, R.drawable.yusufadamu,
        R.drawable.zaidunasir, R.drawable.zainabahmad, R.drawable.ummukhultum, R.drawable.girl, R.drawable.girl, R.drawable.girl, R.drawable.girl};

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

