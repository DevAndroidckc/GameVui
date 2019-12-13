package com.cdth17pm.doraiq;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Menu extends AppCompatActivity {
    private String sharedPrefFile = "com.cdth17pm.doraiq";
    private SharedPreferences mPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_menu);
        mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
    }

    public void Manager(View view) {
        Intent intent = new Intent(this, ManagerAccount.class);
        startActivity(intent);
    }

    public void Option(View view) {
        Intent intent = new Intent(this,ChoiGame.class);
        startActivity(intent);
    }

    public void Buy_Credit(View view) {
        Intent intent = new Intent(this, BuyCredit.class);
        startActivity(intent);
    }

    public void dangXuat(View view) {
        // Xoa token trong SharedPreferences
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.clear();
        editor.apply();

       Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
