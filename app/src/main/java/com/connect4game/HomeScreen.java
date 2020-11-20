package com.connect4game;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HomeScreen extends AppCompatActivity {

    public void changeActivity(View view){
        Intent intent = new Intent(getApplicationContext(),colorOption.class);
        startActivity(intent);
    }

    public void openRules(View view){
        Intent intent = new Intent(getApplicationContext(),rules.class);
        startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                finish();
                System.exit(0);
            }
        };
    }
}