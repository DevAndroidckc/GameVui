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
    private TextView tv_tongCredit;
    private  String tongCredit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_menu);
       tv_tongCredit=findViewById(R.id.textView_tongCredit_lv);
       Intent intent=getIntent();
       tongCredit=intent.getStringExtra("credit");
       tv_tongCredit.setText(tongCredit);
        mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
    }

    public void Manager(View view) {
        Intent intent = new Intent(this, ManagerAccount.class);
        startActivity(intent);
    }

    public void Option(View view) {
        Intent intent = new Intent(this,ChoiGameActivity.class);
        intent.putExtra("credit_lv",tongCredit);
        startActivity(intent);
    }

    public void Buy_Credit(View view) {
        Intent intent = new Intent(this, BuyCredit.class);
        intent.putExtra("",tongCredit);
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
