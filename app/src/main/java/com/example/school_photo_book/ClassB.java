package com.example.school_photo_book;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.os.Handler;

import java.util.Timer;
import java.util.TimerTask;

public class ClassB extends AppCompatActivity {
    int currentPage = 0;
    int NUM_PAGES = 10;
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
        setContentView(R.layout.activity_class_b);

        // Generate sample data
        Name = new String[] { "ASIYA nuru","abdullahi Mustapha ilelah","hansa’u al-amin","abdulfatah b. ahmad","abdurrashid salihu","umar garba gamawa","abubakar salihu Muhammad ",
                "Amina abdullahi ishaq", "abdullahi umar abdullahi ","fauziya yakubu ladan","Fatima Sulaiman","aisha humaira ahmad","abdulmalik adamu manga",
                "Fatima ahmad limanci","abdullahi Ibrahim marafa","abubakar Muhammad lawan","amina Muhammad danfulani ","Fatima sirajo","nuru Mahmood ","Khadija haruna waziri",
                "abdulwahab Muhammad bawa","Fatima ahmad iscerh","habeebah umar ","suwaiba Muhammad sunusi ","Maryam aliyu sani","sanusi Aisha tatimu","abdulbasid alkasim ahmad",
                "Fatima tijjani usman","ahmad tijjani Sani ","Fatima i. malamin kasuwa 	","Khadija Ibrahim jalo","ummul-kultum a. abubakar","yahaya usman","amina Ibrahim",
                "khairat Ibrahim abubakar","shafa’atu salihu","abbas yusuf","abdulmalik adamu manga","abdurrashid abdulhamid", "abubakar muhd giyade", "abubakar salihu muhd",
                "ahmad tijjani","ahmad yahya musa","fadila aminu","shafaatu salihu","hauwau adamu tatimu","hauwau hudu duguri","Ibrahim Ibrahim alfa","isyaka sani","Khadija abdullahi",
                "mardiyya nuru uba","Muhammad kamal abubakar", "Muhammad kamal abubakar","musa Ibrahim musa","nafia dahiru","	rukayya abubakar","Fatima abba jabir",
                "Fatima aliyu sani","Fatima Ibrahim isah","umar garba gamawa","salmanu abdullahi","Mustapha m kabir","salihu inuwa abubakar","hafsat muhd bawa","Fatima muhd sani",
                "Fatima muhd garba","aisha muhd garba" };

        Nickname = new String[] { "goruban zuciya","ilelah/ isco/ magaji","lady sabira","attacker/ oga","abu tarab","umar faruq (garbu)","chairman or S.A","amincy","abdul",
                "fauzee baby","fauzee baby ","humaira","mangha. 02 ","mooheebbats","lil, marafs, marafancy, marafan job","abba","fulani","feemah","atama/ lash babaji ","khadija",
                "shugaba, ligido, shabalee, shamatanzee ","hupteey","momeey","sumy girl","baby","alieeyshert, tertieeymurh, glounous queen", "malam abdul","teemah luv",
                "babban mallami (GR)","pharteey enuwarh/ homo","ummee","besty","yahaya","asseyah","khaerath – ummul","bride",
                "","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","" };
        Phonenumber = new String[] { "08106021216/ 08036475390","08039227458/ 08024942505","07033671494","09031821822", "07088189959", "08132800865/ 08088837565/ 08130123232",
                "08028564655/ 08021218759", "08162933802","08108155036","08165026021","08103610184","08145643570", "08062596918/ 08029786855",
                "08145159745", "09039412239","07033362609","09037860187","08069337627","08036406802/ 09064446569","08080618661","08131863942",
                "08166678944","07066773597","09020602235/ 0803136905/ 08025606090", "08164422399","07013287081","08032852129","08133531094/ 09071701473",
                "08027518544/ 08160494976","08106244717/ 07033800081","08065069048","08031369531/ 08023742134","08064139828","07055968870","08053444446",
                "07012020080","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","",""};

        Address = new String[] { "unguwar bauchin bauchin tsohuwar makabarta","ilelah street behind garu microfinance bank Bauchi","jahun garin galadima gindun durumi",
                "nassarawa street near shukrah motors","gwallaga jumuat mosque","muda lawan behind azman oil ltd, works quarters","bakin kura near rariya primary sch, wajen ali mai hayan keke",
                "tsohuwar makabarta jahun","zonal education office along railway road Bauchi","makera wunti street","kobi street","bayan comprehensive day secondary school Bauchi (Makara huta)",
                "gombe road ","c293 wase raod Bauchi","jahun 1 near married woman sec sch","ganjuwa jahun street","nassarawa primary school","Kandahar nearest sabin shaguna",
                "ilelah street Bauchi","gwallaga opposite gwallaga mosque","kwallaga kusa da gidan sarkin dawaki","tsohon masallacin gwallaga","wunti street","mabuga",
                "karofi madaki gidan gyada","kobi street, limamin kobi house opposite kobi shoping complex", "karofin madaki street near masu miya house","bayan bata nearest kamtah",
                "kobi foot ball field behind dispensary","gidan malamin kasuwa c16 kobi street","kobi street","nufawa","wunti street malashe"," karofin madaki","","winty street",
                "","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","" };
        Hobby = new String[] { "charting","computer engineer","reading quran","friendship and money","quranic recitation", "football","watching films/ driving machine and reading quran",
                "marriage","learning and playing","love","rice and beans","reading and playing games","electricsl and electronics construction", "basket ball and reading","cricket",
                "football","reading", "Al-quran", "reading newspaper", "quran", "football", "honesty & recitation quran", "beans and rice", "quranic", "reading quran",
                "recitation of the holy quran", "recitation of quran", "my luv", "recitation of the holy quran", "quranic recitation", "rice and beans",
                "school life is the best in my life like it so much", "recitation of the holy quran", "honesty & recitation of holy quran", "reading / hanging out with frirnds",
                "study and love", "","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","",""};
        Ambition = new String[] { "teaching", "computer engineer", "doctor","Nigerian airforce","soldier","to be in paradise","I want to be a doctor or a business man",
                "doctor","medical doctor","nurse","medical lab","doctor","software programmer","medical doctor","medical doctor", "I want to become a doctor","medical lab", "nursing",
                "to be a competent medical doctor","medicine","doctor","dentist", "medical laboratory", "midwifery doctor", "doctor","gynaecologist", "my ambition is to be a lecturer",
                "gynedoctor","to be computer professional (hacker)","nurse","medical lab ","nurse or midwifery", "to be a custom","nursing","pharmacy/ medicine","I want to be a doctor",
                "","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","" };
        Comment = new String[] { "education is the better safequard", "see me see truble Allah ka shiryemu gabadaya","","school life is the best, I miss my friends wishes all the best",
                "no food for lazy man ","school life is the best ", "I was very happy because these year I will graduate senior sec sch and I will congrate mu quranic graduation",
                "Alhamdulillah", "no condition is permanent","insha-Allah","proud of Dr. ITC proud of all my fans & teachers like to thanks every one who love me up Dr. ITC up Ibrahim tahir",
                "","I wish my parent were like google, they should understand me even before I complete!!!", "Alhamdulillah", "no condition is permanent","insha-Allah",
                "proud of Dr. ITC proud of all my fans & teachers like to thanks every one who love me up Dr. ITC up Ibrahim tahir","",
                "I wish my parent were like google, they should understand me even before I complete!!!", "the best among the rest","school life is the best",
                "I wish all the best","proud of my parent proud of my teachers","beign honest is the only way of trust worthy and sagacity of life ","masaallah","all the best",
                "education is our successes","","don’t say I am fine, say Alhamdulillah they is ok !!!",
                "so btween da miles & btween da years is inevitable but its true dat seasons may change & people may change but my heart always belonged to u! never forget u.",
                "fly in the plane of ambition and land on the airport of success, luck is yours, wish is mine may ur future always shine …. With lotz love @ss3 2019 glourious shiners and also my closer friends",
                "life is nothing without friendship", "I would like to thank everyone who supported and helped me especially my father and teachers up Dr. ITCS up my fans ",
                "life is noting without friendship, school life is the best. (masha Allah)","If people are trying to bring you down it only means that you are above them ",
                "proud of Dr. ITC proud of all my fans & teachers I wil like to thanks every one who love me up Dr. ITC up Ibrahim tahir ","masha Allah","masha Allah",
                "education is you door to your future ","education is the most powerful weapon which you can use to change the world, so be prepared to learn ",
                "nothing good come easy and then time is money", "","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","" };

        photo = new int[] {R.drawable.asiyanuru, R.drawable.abdullahiilelah, R.drawable.hansasalmin, R.drawable.abdulfatahahmad, R.drawable.abdulrashisalihu,R.drawable.umargarba,
        R.drawable.abubakarsalihumuhd, R.drawable.aminaabudllahi, R.drawable.abdullahiumar, R.drawable.fauziyayakubu, R.drawable.fatimasulaiman, R.drawable.aishahumaira,
        R.drawable.abdulmalikmanga, R.drawable.fatimaahmad, R.drawable.abdullahiibrahim, R.drawable.abubakarmuhammad, R.drawable.aminamuhammad, R.drawable.girl, R.drawable.nurumahmood,
        R.drawable.khadijaharuna, R.drawable.abdulwahabmuhammad, R.drawable.fatimaahmad, R.drawable.habeebaumar, R.drawable.suwaibamuhammad, R.drawable.girl,
        R.drawable.sanusiaisha, R.drawable.girl, R.drawable.fatimatijjani, R.drawable.ahmadtijjani, R.drawable.girl, R.drawable.khadijaibrahim, R.drawable.ummulkultum, R.drawable.yahyausman,
        R.drawable.aminaibrahim, R.drawable.khairatibrahim, R.drawable.shafaatusalihu, R.drawable.abbasyusuf, R.drawable.abdulmalikmanga, R.drawable.abdurrashidabdulhamid,
        R.drawable.abubakarmuhd, R.drawable.abubakarsalisu, R.drawable.ahmadtijjani, R.drawable.ahmadyahya, R.drawable.fadilaaminu, R.drawable.shafaatusalihu, R.drawable.hauwauadamu,
        R.drawable.hauwauhudu, R.drawable.ibrahimibrahim, R.drawable.isyakasani, R.drawable.khadijaabdullahi1, R.drawable.mardiyyanuru, R.drawable.girl, R.drawable.girl,
        R.drawable.musaibrahim, R.drawable.nafiadahiru, R.drawable.rukayyaabubakar, R.drawable.fatimaabba,};

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
