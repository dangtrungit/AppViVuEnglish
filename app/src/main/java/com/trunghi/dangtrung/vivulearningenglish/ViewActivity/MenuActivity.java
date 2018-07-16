package com.trunghi.dangtrung.vivulearningenglish.ViewActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.trunghi.dangtrung.vivulearningenglish.Fragments.GuideLearnFragment;
import com.trunghi.dangtrung.vivulearningenglish.Fragments.MethodLearnFragment;
import com.trunghi.dangtrung.vivulearningenglish.Fragments.RateFragment;
import com.trunghi.dangtrung.vivulearningenglish.Fragments.SettingFragment;
import com.trunghi.dangtrung.vivulearningenglish.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MenuActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,View.OnClickListener {
    Button btnLearn, btnSetting, btnPractice, btnIntroduceandguide;
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    ActionBarDrawerToggle actionBarDrawerToggle;
    LinearLayout linearLayout;
    FrameLayout linearLayoutmenudetail;
    NavigationView navigationView;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_menu);
        findViewByID();

        btnLearn.setOnClickListener(this);
//        btnSetting.setOnClickListener(this);
        btnPractice.setOnClickListener(this);
        btnIntroduceandguide.setOnClickListener(this);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.Open, R.string.Close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);

        toolbar.setTitle("Home");
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        actionBarDrawerToggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        fragmentManager = getSupportFragmentManager();
    }

    private void findViewByID() {
        navigationView = findViewById(R.id.namenu);
        toolbar = findViewById(R.id.toolbarmenu);
        linearLayout = findViewById(R.id.lncontainermenu);
        drawerLayout = findViewById(R.id.drawerlayoutmenu);
        btnLearn=findViewById(R.id.btnlearn);
        linearLayoutmenudetail=findViewById(R.id.lncontainmoremenudetail);
//        btnSetting=findViewById(R.id.btnsetting);
        btnPractice=findViewById(R.id.btnpractice);
        btnIntroduceandguide=findViewById(R.id.btnintroduceandguide);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        item.setIcon(R.color.colorYellow);
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void SettingFragmentA(FragmentTransaction transaction, Object fragment) {
        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.lncontainermenu, (Fragment) fragment);
        transaction.commit();
        drawerLayout.closeDrawer(Gravity.START);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        resetAllMenuItemsTextColor(navigationView);

        switch (item.getItemId()) {
            case R.id.ithome:
                drawerLayout.closeDrawers();
                Intent inMenu = new Intent(getApplicationContext(), MenuActivity.class);
                startActivity(inMenu);
                setTextColorForMenuItem(item,R.color.colorGreen);
                finish();
                break;
            case R.id.itphuongphap:
                setTextColorForMenuItem(item,R.color.colorGreen);
                linearLayoutmenudetail.setVisibility(View.GONE);
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                MethodLearnFragment methodLearnFragment = new MethodLearnFragment();
                SettingFragmentA(transaction, methodLearnFragment);
                toolbar.setTitle("Phương pháp học");
                break;
            case R.id.itguide:
                setTextColorForMenuItem(item,R.color.colorGreen);
                linearLayoutmenudetail.setVisibility(View.GONE);
                FragmentTransaction transaction1 = fragmentManager.beginTransaction();
                GuideLearnFragment guideLearnFragment = new GuideLearnFragment();
                SettingFragmentA(transaction1, guideLearnFragment);
                toolbar.setTitle("Giới thiệu & Hướng dẫn học");

                break;
              /*  case R.id.itsetting:
                setTextColorForMenuItem(item,R.color.colorGreen);
                linearLayoutmenudetail.setVisibility(View.GONE);
                FragmentTransaction transaction2 = fragmentManager.beginTransaction();
                SettingFragment settingFragment = new SettingFragment();
                SettingFragmentA(transaction2, settingFragment);
                toolbar.setTitle("Cài đặt");
                break;*/
            case R.id.itrate:
                setTextColorForMenuItem(item,R.color.colorGreen);
                linearLayoutmenudetail.setVisibility(View.GONE);
                FragmentTransaction transaction3 = fragmentManager.beginTransaction();
                RateFragment rateFragment = new RateFragment();
                SettingFragmentA(transaction3, rateFragment);
                toolbar.setTitle("Đánh giá");

                break;
        }

        return true;
    }


    boolean doubleBackToExitPressedOnce =false;
        @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }
     Toast.makeText(this, "Tiếp tục để thoát chương trình!", Toast.LENGTH_SHORT).show();
        this.doubleBackToExitPressedOnce = true;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                Intent inMenu = new Intent(getApplicationContext(), MenuActivity.class);
//                startActivity(inMenu);
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }

    int a = 148;
    int j = 2241;
    private  void addData(){
        int i =0;
        int y = j;
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        for (;i<25;i++){
            String q = String.valueOf(i);
            databaseReference.child("englishlesson").child(String.valueOf(a)).child(q).child("sotuvung").setValue(y);
            y++;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnlearn:
                Intent inLearn = new Intent(MenuActivity.this, LearnActivity.class);
                inLearn.putExtra("check",true);
                startActivity(inLearn);
                finish();
                break;
                case R.id.btnpractice:
                Intent inLearnAc = new Intent(MenuActivity.this, LearnActivity.class);
                inLearnAc.putExtra("check",false);
                startActivity(inLearnAc);
                    finish();
                break;
//            case R.id.btnsetting:
//                break;
                case R.id.btnintroduceandguide:
                    linearLayoutmenudetail.setVisibility(View.GONE);
                    FragmentTransaction transaction1 = fragmentManager.beginTransaction();
                    GuideLearnFragment guideLearnFragment = new GuideLearnFragment();
                    SettingFragmentA(transaction1, guideLearnFragment);
                    toolbar.setTitle("Giới thiệu & Hướng dẫn học");
                break;
        }
    }
    private void resetAllMenuItemsTextColor(NavigationView navigationView) {
        for (int i = 0; i < navigationView.getMenu().size(); i++)
            setTextColorForMenuItem(navigationView.getMenu().getItem(i), R.color.colorOrangeLight);
    }
    private void setTextColorForMenuItem(MenuItem menuItem, @ColorRes int color) {
        SpannableString spanString = new SpannableString(menuItem.getTitle().toString());
        spanString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this, color)), 0, spanString.length(), 0);
        menuItem.setTitle(spanString);
    }
}
