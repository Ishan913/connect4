package com.connect4game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class rules extends AppCompatActivity {

    public void goBack(View view){
        //Intent intent = new Intent(getApplicationContext(),HomeScreen.class);
        //startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules);
        TextView textView = findViewById(R.id.textView);
        String msg="This is a two-player game" +"\r\n"+ "Take turns dropping discs" +"\r\n"+ "First person to form a horizontal, vertical, or diagonal line of four of one's own discs wins the game";
        textView.setText(msg);
    }
}