package com.trunghi.dangtrung.vivulearningenglish.ViewActivity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.trunghi.dangtrung.vivulearningenglish.R;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

public class MainActivity extends AppCompatActivity {
    ProgressBar progressBar;
    TextView txvverSion;
    public  static  boolean splashScreen= false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        progressBar = findViewById(R.id.progressbarhello);
        txvverSion = findViewById(R.id.txversion);

        if (!isConnected()){
            Toast.makeText(MainActivity.this,"Thiết bị không được kết nối Internet",Toast.LENGTH_LONG).show();
        }
        LoadDisplay();
    }


    private boolean isConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        }
        return false;
    }


    private void LoadDisplay(){
        if (!splashScreen){
            PackageInfo packageInfo = null;
            try {
                packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
                txvverSion.setText("Version: " + packageInfo.versionName.toString());
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intentMenu = new Intent(MainActivity.this, MenuActivity.class);
                        MainActivity.this.startActivity(intentMenu);
                        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
                        FirebaseDatabase.getInstance().getReference();
                        MainActivity.this.finish();
                    }
                }, 1000);

            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
            splashScreen = true;
        }else {
            Intent intent = new Intent(MainActivity.this,MenuActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
            startActivity(intent);
            finish();
        }
    }


}
