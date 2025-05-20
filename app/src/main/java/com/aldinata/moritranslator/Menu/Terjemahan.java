package com.aldinata.moritranslator.Menu;

import static android.app.Activity.RESULT_OK;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.aldinata.moritranslator.Login.Login;
import com.aldinata.moritranslator.R;

import java.util.ArrayList;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Terjemahan#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Terjemahan extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    //SharedPreferences
    private SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "myPref";
    private static final String KEY_NAME = "name";

    private TextView tvNama, tvLogout;
    private ImageView ivLogout;

    protected static final int RESULT_SPEECH = 1;
    private TextView tvIndo, tvMori;
    private ImageButton btnSpeak, btnPlay;
    private TextToSpeech textToSpeech;
    private ArrayList<String> text;
    private String terjemahkan;
    private String[] kamus = new String[]{
            //indo , mori

//            "KATA SIFAT","KATA SIFAT VERSI MORI & beberapa kalimat"
            "siapa namamu","isea nangemu",//
            "saya tidak tahu","komba kuto'orio",//
            "seperti demikian","hele suoo",//
            "saya terjatuh","tedonta aku",//
            "ada apa","tembio",//
            "kamu sedang makan","ke kontongamumongga",//
            "saya ada dimana","kenderio aku",//
            "kamu pergi kemana","iko leko nderio",//
            "saya sedang sakit","kontonganggu mahaki",//
            "dimana pasanganmu","nderio nantaromu",//
            "saya tidak bisa","komba bisa aku",//
            "bertingkah sangat aneh","mompe henu henu",//
            "saya sangat takut","doito aku",//
            "saya tidak bisa bercerita","komba bisa aku mesarita",//
            "anak saya sedang tidur","ananggu kontongano moturi",//
            "pergi bersekolah","leko mesikola",//
            "kamu anak siapa","iko anani sea",//
            "berlagak bisa","mompepande pande",//
            "siapa ini","isea nudi",//
            "nanti saja","teinggapo ari",//
            "tuhan yesus memberkati","i ue berkati",//
            //total 21 contoh kalimat
            "terlantar","tetadi",//
            "konyol","konyolo",//
            "tepat","poianga",//
            "menarik","menarik",//
            "takut","doito",//
            "serasi","mengkona",//
            "hidup","tuwu",//
            "menakjubkan","tekosi",//
            "ambigu","ambigu",//
            "terhibur","sana'a",//
            "kuno","kunoo",//
            "marah","tekuda",//
            "menjengkelkan","mokokobibino",//
            "gelisah","komba sanaa",//
            "sombong","mpehenu",//
            "malu","mohehenuuo",//
            "mengagumkan","mengaggumkan",//
            "mengerikan","mongkokongiri",//
            "asli","asli",//
            "canggung","canggung",//
            "buruk","komba tekowali",//
            "kembali","mekule",//
            "kosong","molimpo",//
            "dasar","ambano",//
            "indah","tekosi",//
            "lebih baik","tekosi isuu",//
            "terbaik","tekosi",//
            "besar","besara",//
            "pahit","mopai",//
            "kosong","molimbo",//
            "buta","morawu",//
            "penuh kebahagiaan","buke mangugai",//
            "berani","umewa",//
            "membosankan","teosa",//
            "suka memerintah","ineheno meparenta",//
            "terang","mewanta",//
            "rusak","mosao",//
            "bergelombang","hempano diramai",//
            "sibuk","susio",//
            "tenang","sanaa",//
            "jujur","jujuru",//
            "riang","sanea",//
            "cermat","pande",//
            "ceroboh","mompepande",//
            "peduli","pedulio",//
            "waspada","pedagai",//
            "dingin","morini",//
            "tembem","tebem",//
            "bersih","morina",//
            "jelas","jelasio",//
            "berawan","menseru",//
            "tidak berpengetahuan","komba montoori",//
            "dingin","morini",//
            "warna-warni","warna warni",//
            "nyaman rumit","sana masusa",//
            "gila","mekombe",//
            "lembut","halusu",//
            "keriting","kiriting",//
            "berbahaya","masolaa",//
            "sayang","pehawao",//
            "gelap","komba tarang",//
            "yang tersayang","auu tekosi",//
            "dalam","larono",//
            "defensif","komba",//
            "lezat menyenangkan","moahi, sosananggu",//
            "deskriptif","deskriptik",//
            "berbeda","komba mengkena",//
            "sulit","masusa",//
            "kotor","mesune",//
            "disamarkan","petiliano",//
            "pusing","mokolili",//
            "dramatis","dramatis",//
            "kering","mokasi",//
            "membosankan","teosa",//
            "kusam","mowuru",//
            "dua kali lipat","orua lipa",//
            "jauh","olai",//
            "awal","ende",//
            "mudah","muruana",//
            "bergaul","membembe",//
            "elastis","elastis",//
            "kosong","moa",//
            "emosional","tekudatuu",//
            "terpesona","tepopoo",//
            "seluruh","mempiheno",//
            "sama","mengkena",//
            "penting","pereluo",//
            "sangat bahagia","tedoa meambo",//
            "jahat","mosaia",//
            "unggul","wawono",//
            "eksotik","eksotik",//
            "mahal","motungko",//
            "ahli","asli",//
            "etis","etis",//
            "anggun","anggun",//
            "menakjubkan","meambo",//
            "adil","adili",//
            "lemah","molema",//
            "setia","mopetoao",//
            "tiruan","meti",//
            "salah","sala",//
            "akrab","sambee",//
            "terkenal","tetoori",//
            "indah","tekosi",//
            "jauh","olai",//
            "cepat","magasi",//
            "tak kenal takut","komba montoori doito",//
            "baik","tekosi",//
            "datar","melombo",//
            "halus","halusu",//
            "bodoh","mehali",//
            "resmi","meambo",//
            "bebas","bebasi",//
            "lucu","moawati",//
            "keriting","kiriting",//
            "umum","mempiheno",//
            "murah hati","moleo haroa",//
            "lemah lembut","molusa meambo",//
            "berukuran raksasa","meukuruako raksasa",//
            "suram","harapa",//
            "berkilau","mangkilo",//
            "berterimakasih","tarimakasi",//
            "hebat","meumba",//
            "kotor","kotoro",//
            "pemarah,galak","maratukuda",//
            "merasa berdosa","kurasao tehala",//
            "mulia","mulia",//
            "glamor","glamoro",//
            "asli","asli",//
            "menyolok","talalu",//
            "cemerlang","mewanta",//
            "mudah tertipu","meruana tinipu",//
            "berbulu","mewulu",//
            "setengah","setengah",//
            "buatan tangan","wihowanno kae",//
            "tampan","montama",//
            "senang","sanaa",//
            "keras,kasar,kejam","montea kasara",//
            "gegabah","mempeuru",//
            "sehat","motea",//
            "tersembunyi","tewuni",//
            "mengerikan","mongkokongiri",//
            "tinggi","mentanao",//
            "riang","sanaa",//
            "jujur","jujuru",//
            "panas","mokula",//
            "lapar","mokowoto",//
            "rendah hati,sederhana","meuru aroa, pasi pasi",//
            "besar","langkai",//
            "dingin","meangi",//
            "ideal","ideal",//
            "sangat bagus","tedoa tekosi",//
            "sakit","mahaki",//
            "imajiner","imajiner",//
            "cacat","cacat",//
            "penting","perehu",//
            "mustahil","komba mentea",//
            "impresif","impresif",//
            "luar biasa","tedoa meambo",//
            "infromal,tidak resmi","opau kombamente",//
            "tidak bersalah","komba salahio",//
            "tidak aman","komba amano",//
            "gatal","mokokato",//
            "tidak beraturan","komba montoori atora",//
            "terisolasi","taisolasio",//
            "dalam","odalo",//
            "cerdas","pandeo",//
            "edukatif","edukatif",//
            "sepele","tekosi",//
            "mabuk","mokolili",//
            "macet","tetaha",//
            "berdenting","meuni",//
            "gemuruh","uuru",//
            "tanpa rahang","kombahinao ase",//
            "cemburu","tumopo",//
            "lelucon","moawati",//
            "gelisah","komba sosahaa",//
            "tidak punya pekerjaan","komba hafeo kerja",//
            "bersama","meronga",//
            "riang","sana",//
            "tanpa kegembiraan","komba sosana",//
            "menghakimi","moatora",//
            "berair","meuwoi",//
            "sangat besar","tedoa langkai",//
            "muda","mungura",//
            "remaja","tonia",//
            "bijaksana","salameambo",//
            "penjaga","podagai",//
            "utama,penting,pokok","utama",//
            "manis","megola",//
            "baik hati","meambo aroa",//
            "penyayang,murah hati","mompeohawa aroa",//
            "keriting","kriting",//
            "kusut","mowuru",//
            "menonjol","kantaraio",//
            "menyolok","menyolok",//
            "rumit","masuli",//
            "terkenal","telele",//
            "seperti raja","helemaraja",//
            "keluarga","sombori",//
            "menyala","melea",//
            "tanpa kunci","kombahinao kunsi",//
            "bersemangat","masuuolu",//
            "tajam,tekun","mondasusuu",//
            "genit","mokokato",//
            "lelah","kendo",//
            "hidup","tuwu",//
            "berlabel","lebio",//
            "tidak stabil,labil","komba somboo",//
            "lesu","molema",//
            "buruk"," komba bagus",//
            "tidak berlemak","komba mado",//
            "melelahkan","kendoo",//
            "sopan","atora",//
            "lebih besar","tedoa langkai",//
            "terakhir","pokotompa",//
            "terlambat","mohalaugi",//
            "terbaru","mompari",//
            "sah","sah",//
            "malas","molose",//
            "berdaun banyak","melewe hadio",//
            "gila","mekombe",//
            "agung","agung",//
            "utama","ambano",//
            "menakjubkan","menakjubkan",//
            "besar-besaran","langkai suu",//
            "licik","licik",//
            "merdu","merduio",//
            "berantakan","terawo",//
            "ringan","molite",//
            "tidak ada artinya","kombahinao tuduno",//
            "sedih","masusa ",//
            "diam","maro",//
            "aneh","mehali",//
            "banyak warna","hadio warna",//
            "gunung-gunung","toru-torukuno",//
            "malu","mohehenu",//
            "naif","naif",//
            "nakal","lambaka",//
            "dekat","orambi",//
            "rapih","rapio",//
            "perlu","perluo",//
            "meralat","tetunda",//
            "negatif","tepokoua",//
            "baru","baruo",//
            "gugup","mondende",//
            "berisik","morangai",//
            "penting","perluo",//
            "bergizi","megizi",//
            "pedas","molea",//
            "mati rasa","materasa",//
            "penting","perehu",//
            "tanpa berhenti","komba meharuana",//
            "gendut","mado",//
            "berminyak","melana",//
            "jelas","mompokotea",//
            "aneh","mehali",//
            "menghina","mohina",//
            "resmi","moia",//
            "tua","mosuo",//
            "lepas","lepasio",//
            "optimal","optimal",//
            "optimis","optimis",//
            "organik","argarik",//
            "tertib","teatora",//
            "asli","asili",//
            "terlambat","talaa",//
            "pemilik","na henuno",//
            "terlalu matang","para motaha",//
            "aneh","mehali",//
            "terkemuka","amba-ambano",//
            "memalukan","mohehenu",//
            "pudar","mousa oto",//
            "sejajar","merede",//
            "sebagian","henutorea",//
            "bergairah","tedoa sanaa",//
            "lampau","diipua",//
            "tenang","tenang",//
            "sempurna","pasi-pasi",//
            "pribadi","aso-asono",//
            "polos","polosi",//
            "ceria","sana",//
            "nyaman","sosana",//
            "mewah","torea",//
            "sopan","atora",//
            "miskin","mongkokolaro",//
            "positif","positif",//
            "mungkin","baraura",//
            "manjur","alapi",//
            "kini","hieno",//
            "cantik","tekosi",//
            "aneh","mehali",//
            "berkualitas","kualitasi",//
            "mual","moko melue",//
            "mengeluh","mengeluhio",//
            "cepat","tealo",//
            "tidak ribut","komba morangoi",//
            "unik","mesue-sue",//
            "seri","seri",//
            "tanpa gerak","nahi pelelu",//
            "mempertanyakan","pinesikonoako",//
            "dipertanyakan","pesikenoakeo",//
            "cerewet","mease",//
            "seperti ratu","helenaratu",//
            "berseri-seri","sosana",//
            "kasar","kasara",//
            "cepat","tealo",//
            "jarang","mola",//
            "terburu-buru","para mompari pari",//
            "mentah","momata",//
            "baru saja","hieno",//
            "ceroboh","kowewe",//
            "nyata","nyata",//
            "siap","patinda",//
            "masuk akal","wewisoakala",//
            "teratur","teatora",//
            "terpercaya","pino alo-alo",//
            "luar biasa","tedoa biasa",//
            "wajib","wajib",//
            "hormat","tabe",//
            "tanggung jawab","tanggulo",//
            "kaya","kaya",//
            "matang","motaha",//
            "busuk","mewo",//
            "sedih","sosalaro",//
            "aman","aman",//
            "asin","meohio",//
            "sama ","sama",//
            "tajam","mondasu",//
            "puas","puasi",//
            "mengerikan","mokodoito",//
            "wangi","mewangi",//
            "terlatih","telati",//
            "menengah","tongano",//
            "egois","egois",//
            "terpisah","teposia",//
            "dangkal","orempu",//
            "tidak tahu malu","komba montoori mohehenu",//
            "berkilau","tesangkilo",//
            "pendek","owa",//
            "sakit","mahaki",//
            "tebal","mokapa",//
            "tipis","tipisio",//
            "rapi","tekosi",//
            "bergemuruh","megunturu",//
            "mungil","kodei",//
            "lelah","molema",//
            "tabah","tabaio",//
            "tuntas","umari",//
            "tinggi","uudao",//
            "banyak bicara","hadio pauuo",//
            "jinak","monea",//
            "nyata","ekena",//
            "lezat","mesiu",//
            "membosankan","teoasa",//
            "tegang","tegang",//
            "hebat","meambo",//
            "berterimakasih","matarimakasih",//
            "haus","mokoranga",//
            "jelek","mosaa",//
            "pokok","ambano",//
            "tidak dapat diterima","kombamotarimao",//
            "tidak dapat dijelaskan","kombamojelasio",//
            "belum lahir","mbonohina",//
            "rahasia","rahasia",//
            "tidak tenang","komba sana",//
            "tidak rata","komba ratao",//
            "tidak adil","komba adili",//
            "tidak bahagia","komba bahagia",//
            "sungguh sial","tedoa siala",//
            "tidak dapat dilupakan","komba kulupeo",//
            "kurang sehat","komba motea",//
            "kejam","lausara",//
            "tidak diketahui","komba tinoori",//
            "sial","siala",//
            "tegak lurus","mentade momoli",//
            "optimis","optimis",//
            "kosong","mou",//
            "samar","samara",//
            "sangat berani","tedoa barani",//
            "sah","sah",//
            "berharga","meoli",//
            "lisan","binuri",//
            "serbaguna","bisaolowo",//
            "giat","moosu",//
            "jahat","masaa",//
            "kuat","moroso",//
            "berjaya","langkai",//
            "brutal","lansara",//
            "sangat penting","tedoa pinerelu",//
            "jelas","jelasi",//
            "fasih berbicara","mafasi mepau",//
            "sukarela","sukarela",//
            "sinting","sinti",//
            "pucat","momite",//
            "hangat","hangat",//
            "boros","borosi",//
            "waspada","pedagai",//
            "berair","meuwoi",//
            "bergelombang","megelomba",//
            "kaya","kaya",//
            "lemah","molema",//
            "lelah","kendo",//
            "tiap minggu","sompo minggu",//
            "aneh","aneh",//
            "basah","mowaka",//
            "seluruh","mimpihono",//
            "jahat ","mosata",//
            "lebar","molue",//
            "liar","moilo",//
            "bijaksana","bijaksanaa",//
            "usang","mosuo",//
            "lebih buruk","lalu mosaa",//
            "tiap tahun","sombo tau",//
            "beragi","metia",//
            "muda","monguro",//
            "menjijikan","mokotelue",//
            "lezat","mesiu",//
            "montok","motea",//
            "amat lucu","tedoa mokokoowa",//
            "tekun","tedoa",//
            "penuh semangat","tedoa sana",//~
            "mengasyikkan","tedoa meambo",//
            "berliku-liku","melengku-lengkuako",//
            "bergairah","tedoa sanaa",//
            "gampang lupa","tedoa kolupe",//
            "banyak pikiran","hadio pineowolo",//
            "terlalu dingin","paralahi morini",//
            "sangat kotor","tedoa kotoro",//
            "berabu","meawu",//

//            "KATA KERJA","KATA KERJA VERSI MORI"
            "meninggalkan","mobintango",//
            "menghapuskan","pasio",//
            "menyerap","mooso",//
            "menyalahgunakan","sala mealo",//
            "menerima","moutarima",//
            "mengakses","meaksesio",//
            "menampung","mompumpu",//
            "menemani","mebawa",//
            "menghitung","modoa",//
            "mengumpulkan","mompumpu",//
            "menuduh","montudu",//
            "mencapai","mohawe",//
            "mengakui","noakuio",//
            "memperoleh","mohawe",//
            "bertindak","pololakoo",//
            "mengaktifkan","poko aktifio",//
            "menyesuaikan","pesuengo",//
            "menambahkan","tambaiyo",//
            "meletakkan","nao",//
            "mengelolah","olao",//
            "mengagumi","ihihengku",//
            "mengakui","akuio",//
            "mengambil","aleo",//
            "mengajukan","pauo",//
            "mengiklankan","antaraio",//
            "menasihati","patuduo",//
            "menganjurkan","mompauako",//
            "mempengaruhi","no pongaruo",//
            "memberikan","meweakoo",//
            "menua","moopu",//
            "setuju","setuju",//
            "membantu","pokowalio",//
            "mengarahkan","patudua",//
            "waspada","medagai",//
            "mengatakan","pauakeo",//
            "menyediakan","sadiakeo",//
            "mengizinkan","no eheakeo",//
            "mengubah","robao",//
            "berjumlah","popio",//
            "menganalisa","mompeande-ande",//
            "mengumumkan","pale-le",//
            "menjawab","sangkio",//
            "mengharapkan","momperorohi",//
            "membandingkan","no banding-bandingkeo",//
            "muncul","meumbaako",//
            "menerapkan","patuduo",//
            "menunjuk","tiso'o",//
            "menghargai","menghargai",//
            "mendekati","umpeda",//
            "menyetujui","memoroko",//
            "membantah","umewa",//
            "timbul","lonto'o",//
            "mempersenjatai","sanjatakio",//
            "membangkitkan","mowangu",//
            "mengatur","atora",//
            "menangkap","rako'o",//
            "tiba","hawe",//
            "meminta","momohi",//
            "berkumpul","menggombo",//
            "menegaskan","tegasikio",//
            "menilai","monilai",//
            "menetapkan","amba-ambano",//
            "membantu","mompokowali",//
            "bergaul","mewali",//
            "menganggap","meanggapio",//
            "memastikan","memastikan",//
            "melampirkan","nongakeo",//
            "menyerang","serang",//
            "mencapai","japaio",//
            "berusaha","berusaha",//
            "menghadiri","haweko",//
            "menarik","tekosi",//
            "menghubungkan","tohaweakio",//
            "menghindari","polao",//
            "menunggu","masikori",//
            "memberikan hadiah","poweakeo hadiah",//
            "mengembalikan","poalaakeo",//
            "menyeimbangkan","timba-timbangio",//
            "melarang","eheyakeo",//
            "meletus","lumpa",//
            "dasar","ambano",//
            "menjadi","agagi",//
            "menanggung","tangguiyaku",//
            "mengalahkan","kalako",//
            "mengemis","moema",//
            "mulai","mulai",//
            "berkelakuan","megau",//
            "percaya","teale",//
            "termasuk","termasuk",//
            "membungkuk","peku",//
            "menikmati","mesiu",//
            "bertaruh","metaru",//
            "menawarkan","no tawario",//
            "mengikat","ongko'o",//
            "menggigit","kikio",//
            "menyalahkan","pongkosala",//
            "memblokir","no blokiro",//
            "meniup","purio",//
            "membanggakan","membanggakan",//
            "mendidih","rede",//
            "memesan","mopesan",//
            "mendorong","no tumbalao",//
            "lahir","limao",//
            "meminjam","pijao",//
            "mengganggu","mengganggu",//
            "melambung","mefumbu",//
            "terikat","teonggo",//
            "busur","pana",//
            "istirahat","istirahat",//
            "bernafas","meuwah",//
            "berkembang biak","teili",//
            "membawa","wawao",//
            "menyikat","bunduru",//
            "membangun","mowangu",//
            "membakar","sunuo",//
            "meledak","lumpa",//
            "mengubur","kaburuo",//
            "membeli","meoli",//
            "menghitung","modoa",//
            "memanggil","poboio",//
            "menenangkan","pokosanaa",//
            "kampanye","kampanye",//
            "membatalkan","no batalao",//
            "menangkap","rako'o",//
            "peduli","padulio",//
            "membawa","wawao",//
            "mengukir","mengukio",//
            "melemparkan","wuuo'o",//
            "menangkap","rako'o",//
            "memenuhi","penuhio",//
            "menyebabkan","menyebabkan",//
            "berhenti","pena'o nao",//
            "merayakan","marayakan",//
            "pusat","puuno",//
            "menantang","menetang",//
            "mengubah","no ubao",//
            "mencirikan","mancirikio",//
            "mengisi","moihi",//
            "mengejar","lulungo",//
            "mengobrol","mepau",//
            "memeriksa","moparesa",//
            "bersorak","mounde",//
            "memilih","mompilu",//
            "beredar","tepalele",//
            "mengutip","mangutip",//
            "klaim","klaim",//
            "menjelaskan","menjelasi",//
            "menggolongkan","meggombo",//
            "membersihkan","pokomorinao",//
            "mendaki","morake",//
            "melekat","metako",//
            "menutup","montutuwi",//
            "menggenggam","moraku",//
            "bertepatan","pasi-pasi",//
            "runtuh","teresa",//
            "mengumpulkan","mopumpu",//
            "mewarnai","mewarnai",//
            "menggabungkan","moasongo",//
            "datang","hawe",//
            "memerintah","moparenta",//
            "memulai","mompu'u",//
            "mengkomentari","komentario",//
            "meminta","momohi",//
            "melakukan","pololako'o",//
            "menyampaikan","mohaweako",//
            "membandingkan","mobandi-bandi",//
            "memaksa","mompasa",//
            "mengimbangi","no imbangio",//
            "bersaing","no sangio",//
            "menyusun","mosusu",//
            "mengeluh","mengelu",//
            "menyelesaikan","mokoumario",//
            "memenuhi","no ihio",//
            "meliputi","no patao",//
            "menyembunyikan","no fuuio",//
            "mengakui","no akuio",//
            "memahami","no pahamio",//
            "memusatkan","memusatkan",//
            "memperhatikan","no perhakano",//
            "menyimpulkan","simpuluo",//
            "mengutuk","no kutuko",//
            "mengadakan","no sadiao",//
            "berunding","terampu",//
            "mengaku","akuio",//
            "membatasi","no batasi",//
            "menegaskan","no tegasio",//
            "menyesuaikan","menyesuaikan",//
            "menghadapi","moasepi",//
            "membingungkan","kahali-hali",//
            "menghubungkan","no hubung - hubungakeo",//
            "mempertimbangkan","no tumbaugio",//
            "terdiri ","ambauo",//
            "merupakan",//,//
            "membangun","mowangu",//
            "berkonsultasi","berkonsultasi",//
            "memakan","no kaango",//
            "menghubungi","no hubungio",//
            "mengandung","morunggu",//
            "merenungkan","kokooro",//
            "melanjutkan","no liu akeo",//
            "mengontrak","kontarao",//
            "berbeda","mesue",//
            "menyumbang","sumbo'o",//
            "mengendalikan","no kendalio",//
            "mengubah","no ubao",//
            "menyampaikan","mo haweakeo",//
            "menghukum","no hukuo",//
            "meyakinkan","no yakinio",//
            "memasak","mo nahu",//
            "mendinginkan","pokorinio",//
            "mengatasi","atasio",//
            "menyalin","no salino",//
            "memperbaiki","no wowauo",//
            "sesuai","no sesuaio",//
            "seharga","na olino",//
            "menghitung","modoa",//
            "melawan","umewa",//
            "memasangkan","pasao",//
            "menutupi","tutuwio",//
            "retak","umete",//
            "jatuh","tedonta",//
            "merangkak","umola",//
            "menciptakan",//,//
            "merayap","meopo",//
            "mengkritik","no kriko",//
            "menyebrang","metonaako",//
            "menghancurkan","no hancuruo",//
            "menangis","umiie",//
            "mengeriting","no kriko",//
            "memotong","nopoleo",//
            "kerusakan","rusao",//
            "menari","lumense",//
            "berani","barani",//
            "tanggal","na tanggala",//
            "berurusan","me urusi",//
            "perdebatan","pe bobanta",//
            "memutuskan","mutusio",//
            "menyatakan",//,//
            "menurun","menurun",//
            "menghias","mo hiasi",//
            "mengurangi","no kurangio",//
            "membaktikan","no wanguo",//
            "menganggap","no anggapo",//
            "mengalahkan","no kalao",//
            "membela","no belao",//
            "menetapkan","menetapkan",//
            "menunda","no tundao",//
            "menyampaikan","mo hawe ako",//
            "permintaan","ni pomonio",//
            "mendemonstrasikan",//,//
            "menyangkal","no sangkalio",//
            "berangkat","pelungke",//
            "bergantung","teumbuno",//
            "menggambarkan","mo gambara",//
            "deposito","deposito",//
            "menghilangkan","no tadio",//
            "memperoleh","mo hawe",//
            "turun","meti",//
            "menggambarkan","megambara",//
            " berhak medapat","hal no mohawe",//
            "mendesain",//,//
            "keinginan","na ineheno",//
            "menghancurkan","no jacuruo",//
            "menemukan","no haweo",//
            "mengembangkan","no poko langkaio",//
            "merancang","na rancangano",//
            "mencerahkan","mewanta",//
            "mendikte","mendikte",//
            "mati","mate",//
            "berbeda","mesue",//
            "membedakan","mesue-sue",//
            "menggali","no mekeke",//
            "mengurangi","no kurangio",//
            "mencelupkan","no; emo'o",//
            "mengarahkan","no patuduo",//
            "berselisih","berselisi",//
            "menghilang","tetadi",//
            "melaksanakan","pololakoo",//
            "menyikapkan","patindao",//
            "menemukan","no haweo",//
            "membahas","no bahasio",//
            "membenci","na daluo",//
            "memberhentikan","Poko penao-ngo'o",//
            "memperlihatkan","no poponggiteo ",//
            "membuang","tadio",//
            "membubarkan","no bubaro",//
            "membedakan","no pasuengo",//
            "mendistribusikan",//,//
            "mengganggu","no gangguo",//
            "mengalihkan",//,//
            "membagi","notiao",//
            "melakukan","no atorao",//
            "mendominasi",//,//
            "dua kali lipat","Menggo oruo linopi",//
            "meragukan","nanta nanta",//
            "menyusun","no posunsuo",//
            "menyeret","nodio",//
            "menguras","urusio",//
            "menggambar","Mo gambara",//
            "mimpi","moipi",//
            "berpakaian","mesangko",//
            "melayang","mewun-e",//
            "minum","mo inu",//
            "mendorong","Tum bolao ",//
            "menjatuhkan","Dontaio",//
            "menenggelamkan","no potonduo",//
            "mengerikan","mong ko ko ngiri",//
            "membuang","notadio",//
            "mendapatkan","Pohawea",//
            "mempermudah","mompoko ruana",//
            "makan","mongga ",//
            "bergema","meuni",//
            "mengedit","mengedit",//
            "mendidik","mompatada",//
            "efek","efek",//
            "memilih","mompilei",//
            "menghapuskan","Palsio",//
            "memulai","pepuano",//
            "mewujudkan","mewujudkan",//
            "merangkul","merangkul",//
            "muncul","meumbaako",//
            "menekankan",//,//
            "mempekerjakan","mompoco angga",//
            "memungkinkan","Baraura",//
            "melampirkan","naakeo",//
            "menghadapi","Moarap",//
            "mendorong","Tumbalao",//
            "mengakhiri","Pokeo tompaano",//
            "mengesahkan","Mengasah kon",//
            "melaksanakan","no pokolo lakoo",//
            "mengikutsertakan","no potonde iro",//
            "mempertinggi",//,//
            "menikmati",//,//
            "menanyakan","sikenou",//
            "memastikan","nopastio",//
            "memerlukan","paraluo",//
            "masuk","Mewinsu",//
            "menghibur","meng liburu",//
            "membayangkan",//,//
            "melengkapi","mopoke Sombo",//
            "tegak","tegak",//
            "melarikan diri","molao",//
            "menetapkan","Pasi- pasi",//
            "memperkirakan","kira-kirao",//
            "mengevaluasi","mengevaluasi",//
            "berkembang",//,//
            "memeriksa","meparesa",//
            "melebihi","turea",//
            "menukar","tukurao",//
            "mengecualikan","no pesuengo",//
            "memaafkan","no ampunio",//
            "melaksanakan","atorao",//
            "berlatih","pelatih",//
            "menggunakan","ho anggao",//
            "menunjukkan","tiso.o",//
            "ada","hiua.o",//
            "memperluas","molue",//
            "mengharapkan","noperorohio",//
            "pengalaman","pengalaman",//
            "menjelaskan","jelaskanakeo",//
            "meledak","lumpa",//
            "mengeksploitasi","mengekspolitasi",//
            "menjelajah","menjelajah",//
            "ekspor","ekspor",//
            "menelanjangi","kolambe-lambe",//
            "mengekspresikan","mengekspresikan",//
            "memperpanjang","no pekomentao",//
            "mengekstrak","mengekstrak",//
            "berhadapan","mearopi",//
            "mempermudah","mempermuda",//
            "luntur","lunturu",//
            "gagal","gagal",//
            "jatuh","tedouta",//
            "menyukai","mekenggu",//
            "mendukung","buburi",//
            "takut","doito",//
            "ciri","ciri",//
            "makan","mongga",//
            "merasa","merasa",//
            "mengambil","umaleo",//
            "berjuang","berjuang",//
            "mencari","mo wusu",//
            "berkas","berkas ",//
            "mengisi","ihio",//
            "membiayai","biyayaio",//
            "menemukan","ho haloeo",//
            "mendenda","mendadak",//
            "menyelesaikan","no pokoumario",//
            "memecat","pelato",//
            "cocok","ba soso",//
            "memperbaiki","nopokomeambo",//
            "berkilau","mangkilo",//
            "melarikan diri","molaingkoroi",//
            "melemparkan","no wuko.o",//
            "mengapung","loutto",//
            "banjir","mowo",//
            "mengalir","teloa",//
            "terbang","mehe",//
            "fokus","fokus",//
            "melipat","lopio",//
            "mengikuti","ntoudao",//
            "melarang","no heakoko",//
            "memaksa","pakasao",//
            "lupa","kolopeo",//
            "memaafkan","ampuuio",//
            "membentuk","bentuko",//
            "merumuskan","merumuskan ",//
            "ditemukan","ho haloeo",//
            "bebas","lolosi",//
            "membekukan","ho pokebekuo",//
            "menakuti","doito",//
            "mengerut","tengkuru",//
            "bersetubuh","persetubuh",//
            "bersetubuh","bersetubuh",//
            "memenuhi","no penu hio",//
            "berfungsi","berfungsi",//
            "mendanai","no danaio",//
            "memperoleh","no haweo",//
            "mengumpulkan","no pulupungo",//
            "menatap","pengkiteo",//
            "menghasilkan","pehaloeano",//
            "mendapatkan","pehaloeano",//
            "memberikan","peweakeo",//
            "sekilas","sekilas",//
            "pergi","lumeko",//
            "akan","akan",//
            "memerintah","parentao",//
            "merebut","rampasio",//
            "menghadiahi","no hadia o",//
            "memahami","no pahamio",

//            "KATA BENDA","KATA BENDA VERSI MORI"
            "murid","Ana sekolal",//
            "ketua kelas","Pelewawo",//
            "teman kelas","Sambee",//
            "ruang kelas","Suang Kelas",//
            "papan tempel","dopitimpele",//
            "meja","meda",//
            "lem","pulu",//
            "nilai","nilai",//
            "stabilo","Stabilo",//
            "tip-ex","tip-ex",//
            "buku catatan","wunta poloura",//
            "kursi","gedera",//
            "papan tulis","dopi poburia",//
            "kapur","kapuru",//
            "spidol","spidol",//
            "pensil warna","patolo",//
            "klip",//,//
            "jam dinding","langkurini",//
            "laci","laci",//
            "tas","tasi",//
            "buku","wunta",//
            "pulpen","pena",//
            "pensil","potolo",//
            "penghapus","polupaisi",//
            "penggaris","pejaresi",//
            "mata pelajaran","mata pepagurua",//
            "jadwal pelajaran","tempo pepagurua",//
            "taplak meja","poalasi meda",//
            "vas bunga","poutoaano bunga",//
            "lemari","lemari",//
            "rak buku","poralouta",//
            "lantai","horo",//
            "atap","ito",//
            "jendela","pentangoa",//
            "pintu","bouso",//
            "gorden","gorden",//
            "tembok","rini",//
            "sapu","porahi",//
            "alat pel","alat-pel",//
            "kemoceng","komoceng",//
            "timba","poutambu",//
            "keset","keset",//
            "kipas angin","kipasi augi",//
            "proyektor","preyektor",//
            "ac","ac",//
            "jawaban","sumangki",//
            "pertanyaan",//,//
            "tugas","tugasi",//
            "ujian","meuji",//
            "pekerjaan rumah","angga.a isaka",//
            "bahasa indonesia","pau indonesia",//
            "bahasa inggris","pau ingrisi",//
            "bahasa jawa","pau jawa",//
            "matematika","mate-matika",//
            "kimia","kimia",//
            "tas punggung","tasi bungku",//
            "ikat pinggang","tasi pebo",//
            "pembatas buku","batasino louuta",//
            "topi","songko",//
            "kertas","lowuta",//
            "kotak pensil","Taku potole",//
            "rautan","Taku potole",//
            "tempat sampah","photaia rampo",//
            "jam tangan","langku kae",//
            "tinta","tiula",//
            "gunting","gouti",//
            "komputer","Kauputere.",//
            "papan ketik","dopi Pautindisi",//
            "mouse","mouse",//
            "alat cetak","alat cetak",//
            "kabel","kabele",//
            "modem","modem",//
            "monitor","umaloasi",//
            "laptop","laptop",//
            "gitar","kulele",//
            "drum","dero",//
            "piano","piano",//
            "perekam","porekam",//
            "pengeras suara","pokolangkai suara",//
            "microphone","microphone",//
            "kapet","kapet",//
            "buku notasi","loluta katasi",//
            "alat pendengar","sangka poonge",//
            "lampu","lampu",//
            "majalah","loluta",//
            "buku","loluta",//
            "koran","koran",//
            "rak buku","porak loluta",//
            "pembaca","mobasa",//
            "lapangan","na lapangan",//
            "bola","golu",//
            "voli","voli ",//
            "basket","basket",//
            "kolam ikan","peukuaro bou",//
            "bunga","na bunga",//
            "pohon","pu.uno",//
            "rumah pohon","rodoha pumuo",//
            "tembok","rere",//
            "alquran","alguran",//
            "sarung","hawu",//
            "mukena","mukena",//
            "kopyah","kopyah",//
            "sajadah","sajadah ",//
            "tasbih","tasbih",//
            "sepeda","supeda",//
            "sepeda","supeda",//
            "motor","motora",//
            "mobil","oto",//
            "tukang parkir","mompakiri",//
            "karcis","karcis",//
            "peluit","pompuri",//
            "satpam","modagai",//
            "makanan ringan","kauga molise",//
            "es","esi",//
            "air putih","uwoi moputeh",//
            "minuman","luka",//
            "kulkas","kulkas",//
            "plastik","plasiti",//
            "piring","sabo",//
            "garpu","garapu",//
            "sendok","siru",//
            "kasir","kasir",//
            "penjual makanan","moasa kauga",//
            "uang","doi",//
            "tanaman","pino paho",//
            "rumput","ewo",//
            "ayunan","nasor",//
            "pagar","naboute",//
            "bangku taman","petuudauga ataman",//
            "alat penyiram","putiuala posiwui",//
            "sekop","sikopa",//
            "tali jemuran","talipewera",//
            "pemangkas","pompangkasi",//
            "sofa","pentoroa",//
            "kursi","kadera",//
            "kursi goyang","kadera melelu",//
            "meja","meda",//
            "bangku","petuuaanga",//
            "karpet","karpet",//
            "akuarium","aguarium",//
            "televisi","televisi",//
            "radio","radio",//
            "foto","pata",//
            "penyedot debu","po.oso abu",//
            "kompor","komporo",//
            "pemanggang roti","posuuna anggara",//
            "wajan","kawali",//
            "panci","kuro",//
            "spatula","spatula",//
            "pisau","piso",//
            "gelas","toude",//
            "serbet","pambaisi",//
            "kendi","taku-taku",//
            "poci","poci o",//
            "botol","pido",//
            "sedotan","poosio",//
            "pemanak nasi","nouahua kinaa",//
            "sumpit","poawi",//
            "kaleng","bele",//
            "oven","oven",//
            "toples","toples",//
            "spons","spous",//
            "penyaring","pohtaudaa",//
            "parut","paru",//
            "telenan","pobatua",//
            "ceret","takea",//
            "alat panggang","popatiuda poutunua",//
            "sendok sayur","siru kulaa",//
            "sendok","siru",//
            "mangkok","mangko",//
            "bak mandi","bak pandiua",//
            "air","uwoi",//
            "sikat lantai","buuduru horo",//
            "kloset","kakusu",//
            "sampo","sampo",//
            "kran air","kran uwoi",//
            "sabun","sabo",//
            "sikat gigi","buuduru ngisi",//
            "pasta gigi","buuduru ngisi",//
            "sumur","iuambu",//
            "ember","embere",//
            "pancuran air","srikiuo ulooi",//
            "handuk","handuk",//
            "gayung","pouta",//
            "mesin cuci","masina polombo",//
            "tempat tidur","na mpoilanga poturia",//
            "bantal","na mpaulu",//
            "gantungan baju","poudaa lemba",//
            "bantal guling","pauwa liuduli",//
            "kasur","kasoro",//
            "selimut","halou",//
            "seprai","seperei",//
            "setrika","garusa",//
            "pengering rambut","mompokutui olou",//
            "telepon","telepon ",//
            "lemari","na lamari",//
            "jam alarm","langku toudoui",//
            "laci","na laci",//
            "boneka","boneka",//
            "parfum","mina-mina",//
            "tempat tidur tingkat","piyanga poturia mesuusu",//
            "sepeda motor","speda motoro",//
            "dongkrak","poutua",//
            "pemadam api","pompepate api",//
            "peralatan","popatiudaa",//
            "kompresor udara","kompresor lamowauda",//
            "alpukat","alpukat",//
            "apel","apel ",//
            "anggur","anggur",//
            "aprikot","aprikot",//
            "belimbing","tanggule",//
            "bluberi","bluberi",//
            "ceri","ceri",//
            "cokelat","coklat",//
            "delima","delima",//
            "duku","duku",//
            "durian","luria",//
            "jambu air","jampu uwoi",//
            "jambu batu","jampu walu",//
            "jambu mete","jampu mete",//
            "jeruk","lemo",//
            "kedondong","kaudoudong",//
            "kelapa","beua",//
            "kelengkeng","kelekeng",//
            "kurma","kurma",//
            "lemon","lemo",//
            "leci","leci",//
            "mangga","taipa",//
            "manggis","manggis",//
            "markisa","markisa",//
            "matoa","motoa",//
            "melon","melon",//
            "mengkudu","mengkudu",//
            "nanas","solike",//
            "nangka","nangka",//
            "naga","naga",//
            "pepaya","loka",//
            "pisang","tiloo",//
            "rambutan","rambutan",//
            "salak","salak",//
            "semangka","suai",//
            "sirsak","walauda",//
            "srikaya","walauda",//
            "stroberi","stroberi",//
            "tomat","tamate",//
            "ubi","dawa",//
            "labu","sumpare",//
            "bawang bombay","lasuka bombai",//
            "bawang merah","lasuka modulo",//
            "bawang putih","lasuka mompute",//
            "bayam","eu",//
            "brokoli","brokoli",//
            "buncis","buucis",//
            "cabe","lada",//
            "tomat","tamate",//
            "timun","timun",//
            "ubi ","dawa",//
            "wortel","woutel",//
            "topi","songko",//
            "kaos","kousu",//
            "rok","rok",//
            "celana","salupara",//
            "kaus kaki","kausu kawe",//
            "sepatu","sepatu",//
            "sandal","saudala",//
            "kemeja","kameja",//
            "celana dalam","caluara peape",//
            "sekolah","sekolah",//
            "taman hiburan","poyanga marowaa",//
            "rumah sakit","rowaha mohaki",//
            "toko roti","toko saunggara",//
            "kantor pos","ontoro pos",//
            "tempat cukur","poliongo materu",//
            "pasar","olu",//
            "perpustakaan","perpustakaan",//
            "pompa bensin","pompa bensin",//
            "restoran",//,//
            "kolam renang","libo pohaagoi",//
            "masjid","masigi",//
            "gereja","paminggua",//
            "pura","pura",//
            "pantai","pantai paute",//
            "pegunungan","torukuko",//
            "café","kave",//
            "stadion","stadion",//
            "bioskop","bioskop",//
            "taman","taman",//
            "hotel","hotel",//
            "bandara","bandara",//
            "pelabuhan","pelabuhan",//
            "stasiun kereta api","stasiun kereta api",//
            "terminal bus","terminal bus",//
            "kebun binatang","lareno pinatuwu",//
            "museum","meseum",//
            "kantor polisi","kantoro polisi",//
            "apotek","gotek",//
            "kebun","lere",//
            "butik","butic",//
            "jembatan","goubala",//
            "pabrik","pabrik",//
            "kafe","kafe",//
            "toko obat","toko pakuli",//
            "kantin","akutin",//
            "toko elektronik","toko elektroni",//
            "tempat cuci mobil","payongo powo oto",//
            "pemakaman","pekabarua",//
            "klinik","klinik",//
            "warteg","warteg",//
            "toko pakaian","poasa lemla",//
            "toko bunga","poasa bunga",//
            "sepeda","oto",//
            "mobil","oto",//
            "perahu","bangka",//
            "kereta","kereta",//
            "kapal","kapal",//
            "bus","bus",//
            "pesawat terbang","kapala mene",//
            "ambulans","ambulans",//
            "taksi","taksi",//
            "helikopter","helikopter",//
            "gerobak","gerobak rada",//
            "kapal pesiar","kapala modomigar",//
            "truk","terek",//
            "sepeda gunung","speda tarukan",//
            "rakit","nalantai",//
            "dokter","dotoro",//
            "perawat","perawar",//
            "petugas kebersihan","patugasi poberesi",//
            "pasien","pasieb",//
            "resepsionis",//,//
            "apoteker",//,//
            "ruang tunggu","poyanga posikoria",//
            "ruang operasi","poyanga operasi",//
            "toilet","hakakusu",//
            "alat bantu jalan","mompopo walimalempa",//
            "kursi roda","kadera roda",//
            "meja informasi","meda konte haula",//
            "obat","pakuli",//
            "termometer","termometer",//
            "jarum suntik","supe pinogu",//
            "stetoskop","steteskop",//
            "tas","tasi",//
            "monitor jantung","mompara jantung",//
            "baju rumah sakit","lemba rodoka mahaki",//
            "anti biotik","anti biotik",//
            "operasi","operasi",//
            "obat penghilang nyeri","pakuli mantoai mahaki",//
            "kasur","kasoro",//
            "kain kasa","halau kasa",//
            "kapas","kawu-kawu",//
            "antiseptik","antiseptik",//
            "sarung tangan karet","hawu kae keta",//
            "steril","beresi",//
            "air putih","owou kompute",//
            "dokumen","munta",//
            "monitor","monitor",//
            "mikroskop","mikrosop",//
            "selang","pooso",//
            "penguji","penguji",//
            "racun","rasu",//
            "penawar racun","konte wari rasu",//
            "virus","virusu",//
            "bakteri","bakteri",//
            "sakit","mahaki",//
            "tisu","tisu",//
            "perban","perban",//
            "monitor tekanan darah","monitor tekanan dara",//
            "gips","gips",//
            "tombol pemanggil suster","tombol poboi suster",//
            "penunjuk arah","poutiso sala",//
            "sarung tangan","hawa ngengkae",//
            "tongkat penopang","tudo poutaha",//
            "botol infus","pido impusu",//
            "keterangan pasien","pompaudu mia",//
            "masker","masker",//
            "pemeriksa telinga","pompaiga bire",//
            "pemeriksa bagian mata","pomparesa tempano",//
            "masker oksigen","masker oksigen",//
            "pisau bedah","piso kada",//
            "jahitan","sineu",//
            "ruang pasien","paianga pasien",//
            "suntikan","pinohu",//
            "kursi roda","kadera roda",//
            "direktur","direktur",//
            "manajer","menejer",//
            "asisten manajer","asisten menejer",//
            "pengawas","maudoro",//
            "karyawan","ata",//
            "departemen kepegawaian",//,//
            "pelayan laki-laki","maloari tama",//
            "pelayan perempuan","molayani beine",//
            "satpam","adogai",//
            "pelanggan","pelanggan",//
            "jam kerja","langku poangga",//
            "kerja lembur","langku molembur",//
            "tagihan","tagia",//
            "tempat parkir","poionggano",//
            "lobi kantor","lobi kantor",//
            "ruang pertemuan","poionggono tepotewoa",//
            "laporan","laporo",//
            "jam kerja","langku poongga",//
            "impor","ino loroi",//
            "ekspor","ino liwi",//
            "email","email",//
            "pemasok","pemasok",//
            "gaji","gauwi",//
            "cuti","cuti",//
            "bonus","bonusu",//
            "tunjangan hari raya","tunjangan oko langkai",//
            "pulpen","pena",//
            "pensil","potolo",//
            "penggaris","pogarisi",//
            "stempel","stempel",//
            "folder","floder",//
            "katalog","katalag",//
            "kartu nama","katuru neno",//
            "penanda buku","taudano buku",//
            "papan pengumuman","dopi pehawea",//
            "kalkulator","kalkulator",//
            "kalender","kalender",//
            "kemoceng","kemoceng",//
            "amplop","amplop",//
            "penghapus","pompahiki",//
            "map","map",//
            "nota","nota",//
            "buku catatan","wuta pohosia",//
            "kertas","wouuta",//
            "selotip","selotip",//
            "lakban","lakban",//
            "kardus","kardus",//
            "printer","printer",//
            "proyektor","proyektor",//
            "saklar","saklar",//
            "sendal","sandal",//
            "gelas","tonde"

    };


    @SuppressLint("MissingInflatedId")

    public Terjemahan() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Terjemahan.
     */
    // TODO: Rename and change types and number of parameters
    public static Terjemahan newInstance(String param1, String param2) {
        Terjemahan fragment = new Terjemahan();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_terjemahan, container, false);

        tvNama = rootView.findViewById(R.id.tvNama);
        tvLogout = rootView.findViewById(R.id.tvLogout);
        ivLogout = rootView.findViewById(R.id.ivLogout);

        //shared Preferences
        sharedPreferences = getActivity().getSharedPreferences(SHARED_PREF_NAME,getActivity().MODE_PRIVATE);
        String name = sharedPreferences.getString(KEY_NAME,null);

        if(name != null){
            tvNama.setText("Halo, " + name);
        }

        //loguout
        ivLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity().getApplicationContext(), "Keluar dari aplikasi", Toast.LENGTH_SHORT).show();

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("hasLoggedIn", false);
                editor.apply();

                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
            }
        });

        //text to speech
        btnPlay = rootView.findViewById(R.id.btnPlay);
        tvMori = rootView.findViewById(R.id.tvMori);

        textToSpeech = new TextToSpeech(getActivity(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS){
                    int lang = textToSpeech.setLanguage(Locale.forLanguageTag(String.valueOf(new Locale("id", "ID"))));
                }
            }
        });

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textToSpeech.speak(terjemahkan, TextToSpeech.QUEUE_FLUSH, null);
            }
        });

        //Speech to text
        tvIndo = rootView.findViewById(R.id.tvIndonesia);
        btnSpeak = rootView.findViewById(R.id.btnSpeak);

        btnSpeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent mic_google = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                mic_google.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                mic_google.putExtra(RecognizerIntent.EXTRA_LANGUAGE, new Locale("id", "ID"));

                try {
                    startActivityForResult(mic_google, RESULT_SPEECH);
                    tvIndo.setText(" ");
                }catch (ActivityNotFoundException e){
                    Toast.makeText(getActivity(), "Maaf, Device Kamu Tidak Support Speech To Text", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });


        return rootView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case RESULT_SPEECH:
                if (resultCode == RESULT_OK && data != null){
                    text = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    tvIndo.setText(text.get(0));

                    boolean isFound = false;
                    for (int i = 0; i < kamus.length; i++) {
                        if (kamus[i].equals(text.get(0))) {
                            terjemahkan = kamus[i+1];
                            tvMori.setText(terjemahkan);
                            textToSpeech.speak(terjemahkan, TextToSpeech.QUEUE_FLUSH, null);
                            isFound = true;
                            break;
                        }
                    }

                    if (!isFound) {
                        tvMori.setText("Kata Tidak Ditemukan...");
                        textToSpeech.speak("Maaf, Kata Tidak Ditemukan", TextToSpeech.QUEUE_FLUSH, null);
                    }

                }
                break;
        }
    }
}