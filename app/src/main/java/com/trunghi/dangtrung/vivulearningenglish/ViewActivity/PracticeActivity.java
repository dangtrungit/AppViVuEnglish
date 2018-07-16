package com.trunghi.dangtrung.vivulearningenglish.ViewActivity;


import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

import android.os.Handler;
import android.os.Parcelable;
import android.speech.tts.TextToSpeech;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.trunghi.dangtrung.vivulearningenglish.Models.DataLearnModel;
import com.trunghi.dangtrung.vivulearningenglish.Models.DataLessonDetailModel;
import com.trunghi.dangtrung.vivulearningenglish.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;


public class PracticeActivity extends AppCompatActivity  implements View.OnClickListener,RadioGroup.OnCheckedChangeListener{
    LinearLayout linearLayout0,linearLayout1,linearLayout2;
    int[] lnlayout = {R.id.lnpractice0,R.id.lnpractice1,R.id.lnpractice2};
    TextView  txvPhiendich2,textViewQuestion,txvScore,txvKieuloaitu0,txvnPhiendich0,txvNgonnguchinh1,txvNgonnguchinh0,txvScore1,txvScore0;
    EditText edtWrite0,edtWrite1;
    ImageButton imbtnSpeak1;
    Button btnCHeck,btnTieptuc,btnThongtintu,btnCheck0,btnTieptuc0,btnCheck1,btnTieptuc1,btnThongtin0,btnThongtin1;
    RadioGroup radioGroup;
    RadioButton rdchose1,rdchose2,rdchose3,rdchose4,rdchose5;
    DatabaseReference databaseReference;
    DataLearnModel dataLearnModel;
    ImageView imcheckBoxPractice,imCheckbox1,imCheckbox0;

    int [] score={0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
    Random random = new Random();
    DataLessonDetailModel dataLessonDetailModel;
    String answer;

    int ID;
    int temp;
    boolean checkChose;
    MediaPlayer  mediaPlayer;
    TextToSpeech textToSpeech;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_practice);
        FindViewByID();
        dataLearnModel = getIntent().getParcelableExtra("datatopractice");
        UpdateData();
        btnCHeck.setOnClickListener(this);
        btnTieptuc.setOnClickListener(this);
        btnThongtintu.setOnClickListener(this);
        radioGroup.setOnCheckedChangeListener(this);
        imbtnSpeak1.setOnClickListener(this);
        btnCheck1.setOnClickListener(this);
        btnCheck0.setOnClickListener(this);
        btnTieptuc0.setOnClickListener(this);
        btnTieptuc1.setOnClickListener(this);
        btnThongtin0.setOnClickListener(this);
        btnThongtin1.setOnClickListener(this);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        textToSpeech = new TextToSpeech(PracticeActivity.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status==TextToSpeech.SUCCESS){
                    textToSpeech.setLanguage(Locale.ENGLISH);
                }else {
                    Toast.makeText(getApplicationContext(),"Tính năng không hỗ trợ trên thiết bị của bạn!",Toast.LENGTH_LONG).show();
                }
            }
        });

    }


    @Override
    protected void onDestroy() {
        if (textToSpeech!=null){
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        super.onDestroy();
    }

    /*random linear layout*/
    private void RandomLinearLayout(){
         temp = random.nextInt(lnlayout.length);
        if (lnlayout[temp]==lnlayout[0]){
            linearLayout0.setVisibility(View.VISIBLE);
            linearLayout1.setVisibility(View.GONE);
            linearLayout2.setVisibility(View.GONE);
            isSpeak=false;
        }else
        if (lnlayout[temp]==lnlayout[1]){
            isSpeak=true;
            linearLayout0.setVisibility(View.GONE);
            linearLayout1.setVisibility(View.VISIBLE);
            linearLayout2.setVisibility(View.GONE);
        }else
        if (lnlayout[temp]==lnlayout[2]){
            isSpeak=false;
            linearLayout0.setVisibility(View.GONE);
            linearLayout1.setVisibility(View.GONE);
            linearLayout2.setVisibility(View.VISIBLE);
        }
    }
    /*cai dat mau thay doi theo diem so*/
    private void setColorTextScore(TextView textView){
        if (score[ID]>4){
            textView.setTextColor(getResources().getColor(R.color.colorGreen));
        }
        if (score[ID]<1){
            textView.setTextColor(getResources().getColor(R.color.colorOrangeLight));
        }
        if (score[ID]<-5){
            textView.setTextColor(getResources().getColor(R.color.colorRed));
        }
        if (score[ID]>0 && score[ID]<5)
        {
            textView.setTextColor(getResources().getColor(R.color.colorWhite));
        }
    }
    /*anh xa*/
    private void FindViewByID(){
       btnCHeck = findViewById(R.id.btncheck);
        imcheckBoxPractice = findViewById(R.id.imcheckbox);
        txvScore = findViewById(R.id.txvscore);
        textViewQuestion=findViewById(R.id.txvnnchinhpractice);
        rdchose1= findViewById(R.id.rdchose1);
        rdchose2= findViewById(R.id.rdchose2);
        rdchose3= findViewById(R.id.rdchose3);
        rdchose4= findViewById(R.id.rdchose4);
        rdchose5= findViewById(R.id.rdchose5);
        radioGroup =findViewById(R.id.rdgroup);
        btnTieptuc =findViewById(R.id.btntieptuc);
        btnThongtintu =findViewById(R.id.btnthongtin);
        linearLayout0 = findViewById(R.id.lnpractice0);
        linearLayout1 = findViewById(R.id.lnpractice1);
        linearLayout2 = findViewById(R.id.lnpractice2);
        txvKieuloaitu0 =findViewById(R.id.txvkieuloaitu0);
        txvnPhiendich0 =findViewById(R.id.txvphiendich0);
        txvPhiendich2 =findViewById(R.id.txvphiendich2);
        txvNgonnguchinh1 = findViewById(R.id.txvngonnguchinh1);
        edtWrite0 = findViewById(R.id.edtwrite0);
        edtWrite1 = findViewById(R.id.edtwrite1);
        imbtnSpeak1 = findViewById(R.id.imbtnspeak1);
        btnCheck0 = findViewById(R.id.btncheck0);
        btnCheck1 = findViewById(R.id.btncheck1);
        btnTieptuc0 = findViewById(R.id.btntieptuc0);
        btnTieptuc1 = findViewById(R.id.btntieptuc1);
        btnThongtin0 = findViewById(R.id.btnthongtin0);
        txvNgonnguchinh0 = findViewById(R.id.txvngonnguchinh0);
        btnThongtin1 = findViewById(R.id.btnthongtin1);
        txvScore1 = findViewById(R.id.txvscore1);
        imCheckbox1 = findViewById(R.id.imcheckbox1);
        imCheckbox0 = findViewById(R.id.imcheckbox0);
        txvScore0 = findViewById(R.id.txvscore0);
        RandomLinearLayout();
    }


    /*cai dat button kiem tra ln0*/
    private void setBtnCheck0(){
        HideKeyboard();

        String phiendich0 = textViewQuestion.getText().toString();
        String edtInput = edtWrite0.getText().toString();
        txvNgonnguchinh0.setVisibility(View.VISIBLE);
        btnTieptuc0.setVisibility(View.VISIBLE);
        btnCheck0.setVisibility(View.GONE);
        btnThongtin0.setVisibility(View.VISIBLE);
        if (edtInput.equals(phiendich0)){
            mediaPlayer = MediaPlayer.create(PracticeActivity.this,R.raw.correct);
            mediaPlayer.start();
            UpdateScoreUp(txvScore0,imCheckbox0);

        }else {
            mediaPlayer = MediaPlayer.create(PracticeActivity.this,R.raw.error);
            mediaPlayer.start();
            UpdateScoreDown(txvScore0);
        }
    }
    /*cai dat button tiep tuc ln0*/
    private void setContinue0(){
        if (score[ID]>4){
            RandomLinearLayout();
            UpdateData();
        }
        imCheckbox0.setVisibility(View.GONE);
        txvScore0.setVisibility(View.GONE);
        btnTieptuc0.setVisibility(View.GONE);
        txvNgonnguchinh0.setVisibility(View.GONE);
        RandomLinearLayout();
        UpdateData();
        btnTieptuc0.setVisibility(View.GONE);
        btnCheck0.setVisibility(View.VISIBLE);
        mediaPlayer.release();
        edtWrite0.setText("");
        btnThongtin0.setVisibility(View.INVISIBLE);
    }
    View view;

    private void HideKeyboard(){
        view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    /*cai dat button kiem tra ln1*/
    private void setBtnCheck1(){
        HideKeyboard();
        String language = txvNgonnguchinh1.getText().toString();
        String edtInput = edtWrite1.getText().toString();
        btnThongtin1.setVisibility(View.VISIBLE);
        txvNgonnguchinh1.setVisibility(View.VISIBLE);
        btnTieptuc1.setVisibility(View.VISIBLE);
        btnCheck1.setVisibility(View.GONE);
        if (edtInput.equals(language)){
            mediaPlayer = MediaPlayer.create(PracticeActivity.this,R.raw.correct);
            mediaPlayer.start();
            UpdateScoreUp(txvScore1,imCheckbox1);
        }else {
            mediaPlayer = MediaPlayer.create(PracticeActivity.this,R.raw.error);
            mediaPlayer.start();
            UpdateScoreDown(txvScore1);
        }
    }
    /*cai dat button tion tiep tuc ln1*/
    private void setContinue1(){
        if (score[ID]>4){
            RandomLinearLayout();
            UpdateData();
        }
        imCheckbox1.setVisibility(View.GONE);
        txvScore1.setVisibility(View.GONE);
        btnThongtin1.setVisibility(View.GONE);
        btnTieptuc1.setVisibility(View.GONE);
        btnCheck1.setVisibility(View.VISIBLE);
        edtWrite1.setText("");
        txvNgonnguchinh1.setVisibility(View.GONE);
        RandomLinearLayout();
        UpdateData();
        mediaPlayer.release();
    }

    private void setBtnCheck(){
        HideKeyboard();
        if (checkChose){
            mediaPlayer = MediaPlayer.create(PracticeActivity.this,R.raw.correct);
            mediaPlayer.start();
            UpdateScoreUp(txvScore,imcheckBoxPractice);
        }else {
            mediaPlayer = MediaPlayer.create(PracticeActivity.this,R.raw.error);
            mediaPlayer.start();
            UpdateScoreDown(txvScore);
        }
        btnCHeck.setVisibility(View.GONE);
        btnThongtintu.setVisibility(View.VISIBLE);
        txvScore.setVisibility(View.VISIBLE);
        btnTieptuc.setVisibility(View.VISIBLE);
        btnThongtintu.setVisibility(View.VISIBLE);
        txvPhiendich2.setVisibility(View.VISIBLE);
    }
    String text;
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnthongtin0:
                setBtnThongtin();
                break;
            case R.id.btntieptuc0:
                setContinue0();
                break;
            case R.id.btncheck0:
                setBtnCheck0();
                break;
            case R.id.btnthongtin1:
                setBtnThongtin();
                break;
            case R.id.imbtnspeak1:
                text = txvNgonnguchinh1.getText().toString();
                speak(text,0.4f,0.3f);
                break;
            case R.id.btntieptuc1:
                setContinue1();
                break;
            case R.id.btncheck1:
                setBtnCheck1();
                break;

            case R.id.btncheck:
                setBtnCheck();
                break;
            case R.id.btntieptuc:
                if (score[ID]>4){
                    RandomLinearLayout();
                    UpdateData();
                }
                mediaPlayer.release();
                imcheckBoxPractice.setVisibility(View.GONE);
                btnThongtintu.setVisibility(View.INVISIBLE);
                btnCHeck.setVisibility(View.VISIBLE);
                UpdateData();
                radioGroup.clearCheck();
                btnTieptuc.setVisibility(View.GONE);
                txvScore.setVisibility(View.INVISIBLE);
                txvPhiendich2.setVisibility(View.GONE);
                RandomLinearLayout();
                break;
            case R.id.btnthongtin:
               setBtnThongtin();
                break;
        }
    }
    /*Update diem so giam*/
    private void UpdateScoreDown(TextView textView){

       score[ID]--;
        setColorTextScore(textView);
        checkPassed();
        textView.setText("Điểm tích lũy: "+score[ID]);
        textView.setVisibility(View.VISIBLE);
    }
    /*Update diem so tang*/
    private void UpdateScoreUp(TextView textView,ImageView imageView){
        score[ID]++;
        setColorTextScore(textView);
        checkPassed();
        textView.setVisibility(View.VISIBLE);

        textView.setText("Điểm tích lũy :"+score[ID]);
        if (score[ID]>4){
            imageView.setVisibility(View.VISIBLE);
        }else {
            imageView.setVisibility(View.GONE);
        }
    }
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.rdchose1:
               checkCorrectQuest(rdchose1);
                break;
                case R.id.rdchose2:
                    checkCorrectQuest(rdchose2);
                    break;
                case R.id.rdchose3:
                    checkCorrectQuest(rdchose3);
                    break;
                case R.id.rdchose4:
                    checkCorrectQuest(rdchose4);
                    break;
                case R.id.rdchose5:
                    checkCorrectQuest(rdchose5);
                    break;
                    default: checkChose=false;
                    break;
        }
    }
/*cai dat button thong tin*/
    private void setBtnThongtin(){
        String id = String.valueOf(ID);
        String sotuvung = String.valueOf(dataLessonDetailModel.getSotuvung());

        Intent intent = new Intent(this,InfoDetailActivity.class);
        intent.putExtra("id",id);
        intent.putExtra("sotuvung",sotuvung);
        intent.putExtra("ahihidatalearnthongtin",dataLearnModel);
        intent.putParcelableArrayListExtra("datalist", (ArrayList<? extends Parcelable>) dataLessonDetailModels);
//        intent.putParcelableArrayListExtra("sdatatoinfodetail1", (ArrayList<? extends Parcelable>) dataLessonDetailModels);
//        Log.d("dsdss",dataLessonDetailModels.get(ID).getTuvung()+"");

        startActivity(intent);
    }
    /*cai dat kiem tra dap an o ln2*/
    private void checkCorrectQuest(RadioButton radioButton){
        if (radioButton.getText().equals(answer)) {
            checkChose=true;
        } else {
            checkChose=false;
        }
    }

    /*Update hinh anh*/
    private void UpdateImage(final DataLessonDetailModel dataLessonDetailModel){
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("images")
                .child(dataLessonDetailModel.getMalesson()).child(dataLessonDetailModel.getKeyID());
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                /*get ex*/
                    List<String> listimages = new ArrayList<>();
                    for (DataSnapshot valueimages : dataSnapshot.getChildren()){
                        listimages.add(valueimages.getValue(String.class));
                    }
                    dataLessonDetailModel.setImages(listimages);
//                Log.d("hihihi",dataLessonDetailModel.getImages()+"");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        databaseReference.addListenerForSingleValueEvent(valueEventListener);
    }

    /*Update list phien dich*/
    private void UpdateListTranslate(final DataLessonDetailModel dataLessonDetailModel){
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("translate")
                .child(dataLessonDetailModel.getMalesson()).child(dataLessonDetailModel.getKeyID());
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                /*get ex*/

                List<String> listtranslate = new ArrayList<>();
                for (DataSnapshot valuelisttranslate : dataSnapshot.getChildren()){
                    listtranslate.add(valuelisttranslate.getValue(String.class));
                }
                dataLessonDetailModel.setListtranslate(listtranslate);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        databaseReference.addListenerForSingleValueEvent(valueEventListener);
    }

    /*Update vi du tieng anh*/
    private void UpdateListExample(final DataLessonDetailModel dataLessonDetailModel){
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("exampledetail")
                .child(dataLessonDetailModel.getMalesson()).child(dataLessonDetailModel.getKeyID());
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                /*get ex*/

                    List<String> listexampledetail = new ArrayList<>();
                    for (DataSnapshot valuelistexampledetail : dataSnapshot.getChildren()){
                        listexampledetail.add(valuelistexampledetail.getValue(String.class));
                    }
                    dataLessonDetailModel.setListexample(listexampledetail);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        databaseReference.addListenerForSingleValueEvent(valueEventListener);
    }
    boolean isSpeak =true;
    /*Update du lieu tat ca tu firebase*/
    boolean ran=true;
    List<DataLessonDetailModel> dataLessonDetailModels ;
    private void UpdateData(){

       DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("englishlesson").child(dataLearnModel.getLesson());
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                dataLessonDetailModels  = new ArrayList<>();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){

                    dataLessonDetailModel = dataSnapshot1.getValue(DataLessonDetailModel.class);
                    dataLessonDetailModel.setMalesson(dataLearnModel.getLesson());

                    dataLessonDetailModel.setKeyID(dataSnapshot1.getKey());

                    UpdateListExample( dataLessonDetailModel);
                    UpdateListTranslate(dataLessonDetailModel);
                    UpdateImage(dataLessonDetailModel);
                    dataLessonDetailModels.add(dataLessonDetailModel);
                }

                dataLessonDetailModel = dataLessonDetailModels.get(random.nextInt(dataLessonDetailModels.size()));
                        rdchose1.setText(dataLessonDetailModel.getDapan1());
                        rdchose2.setText(dataLessonDetailModel.getDapan2());
                        rdchose3.setText(dataLessonDetailModel.getDapan3());
                        rdchose4.setText(dataLessonDetailModel.getDapan4());
                        rdchose5.setText(dataLessonDetailModel.getDapan5());
                        textViewQuestion.setText(dataLessonDetailModel.getTuvung());
                        txvKieuloaitu0.setText(dataLessonDetailModel.getKieuloai());
                        txvnPhiendich0.setText(dataLessonDetailModel.getPhiendich());
                        txvPhiendich2.setText(dataLessonDetailModel.getPhiendich());
                        txvNgonnguchinh1.setText(dataLessonDetailModel.getTuvung());
                        txvNgonnguchinh0.setText(dataLessonDetailModel.getTuvung());
                        answer = dataLessonDetailModel.getPhiendich();
                        ID = Integer.parseInt(dataLessonDetailModel.getKeyID());
                if (isSpeak){
                    String string = txvNgonnguchinh1.getText().toString();
                    Speak(string,0.9f,1.0f,Locale.ENGLISH);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        databaseReference.addListenerForSingleValueEvent(valueEventListener);
    }

    public int getRandomIntInBetween(int min, int max)
    {
        Random r = new Random();
        return min+r.nextInt(max-min);
    }
    private void speak(final String string, float setpitch,float speech){
        textToSpeech.setPitch(setpitch);
        textToSpeech.setSpeechRate(speech);
        textToSpeech.speak(string,TextToSpeech.QUEUE_FLUSH,null);
    }
    /*Text to speech*/
    private void Speak(final String string, final float pitch, final float speechrate, final Locale locale){
        textToSpeech = new TextToSpeech(PracticeActivity.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status!= textToSpeech.ERROR){
                    textToSpeech.setLanguage(locale);
                    textToSpeech.setPitch(pitch);
                    textToSpeech.setSpeechRate(speechrate);
                    textToSpeech.speak(string,TextToSpeech.QUEUE_FLUSH,null);

                }

            }
        });

//
    }
    /*Thoat activity Practice*/
    boolean doubleBackToExitPressedOnce=false;
    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }
        setResult(AppCompatActivity.RESULT_CANCELED);
        Toast.makeText(PracticeActivity.this, "Nếu bạn thoát điểm số sẽ không bảo lưu!", Toast.LENGTH_SHORT).show();
        this.doubleBackToExitPressedOnce = true;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 1500);

    }
    /*Tinh so diem de vuot qua*/
    private void checkPassed(){
        int SumScore =0;
            if (score[0]>0 && score[1] >0 && score[2] > 0 && score[3] > 0 && score[4] > 0&& score[5] > 0
                    && score[6] > 0&& score[7] > 0&& score[8] > 0 &&score[9]>0){
                showDialog();
            }else if (score[0]>0 && score[1] >0 && score[2] > 0 && score[3] > 0 && score[4] > 0&& score[5] > 0
                    && score[6] > 0&& score[7] > 0&& score[8]>0 &&score[9]>0 && score[10]>0
                    && score[11] >0 && score[12] > 0 && score[13] > 0 && score[14] > 0
                    && score[15] > 0 && score[16] > 0 && score[17] > 0 && score[18]>0 &&score[19]>0){
                showDialog();
            }else if (score[0]>0 && score[1] >0 && score[2] > 0 && score[3] > 0 && score[4] > 0&& score[5] > 0
                    && score[6] > 0&& score[7] > 0&& score[8]>0 &&score[9]>0 && score[10]>0
                    && score[11] >0 && score[12] > 0 && score[13] > 0 && score[14] > 0
                    && score[15] > 0 && score[16] > 0 && score[17] > 0 && score[18]>0 &&score[19]>0
                    && score[20] > 0 && score[21] > 0 && score[22] > 0 && score[23]>0 &&score[24]>0&& score[25]>0){
                showDialog();
            }
    }
    Dialog dialog;
    public void showDialog(){
        dialog = new Dialog(PracticeActivity.this);
        dialog.setTitle("aaaaaa");
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.showdialog);

        String id =dataLearnModel.getLesson();
        final int iid = Integer.parseInt(id);

        Button buttonOk = dialog.findViewById(R.id.btnOK);
        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        });
        dialog.show();
    }

   /* public void showAlertDialog() {
        AlertDialog.Builder aleBuilder = new AlertDialog.Builder(this);
        aleBuilder.setCancelable(false);

        aleBuilder.setPositiveButton("OK-PASSED", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        AlertDialog alertDialog = aleBuilder.create();
        alertDialog.show();
    }*/

}


