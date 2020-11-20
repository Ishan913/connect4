package com.connect4game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class colorOption extends AppCompatActivity {

    String player1="";
    String player2="";

    public void selectColor(View view){
        ImageView color = (ImageView) view;
        player1 = color.getTag().toString();

        androidx.gridlayout.widget.GridLayout player1GridLayout = findViewById(R.id.player1GridLayout);
        for (int i=0; i<player1GridLayout.getChildCount();i++){
            ImageView counter = (ImageView) player1GridLayout.getChildAt(i);
            counter.setAlpha(0.5f);
        }
        color.setAlpha(1f);

    }

    public void selectColor2(View view){
        ImageView color = (ImageView) view;
        player2 = color.getTag().toString();

        androidx.gridlayout.widget.GridLayout player2GridLayout = findViewById(R.id.player2GridLayout);
        for (int i=0; i<player2GridLayout.getChildCount();i++){
            ImageView counter = (ImageView) player2GridLayout.getChildAt(i);
            counter.setAlpha(0.5f);
        }
        color.setAlpha(1f);

    }

    public void nextActivity(View view){
        if (player1.length()==0 || player2.length()==0){
            Toast.makeText(this, "Select discs", Toast.LENGTH_SHORT).show();
        }
        else if (player1.equals(player2)){
            Toast.makeText(this, "Select Different discs", Toast.LENGTH_SHORT).show();
        }else{
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            intent.putExtra("player1",player1);
            intent.putExtra("player2",player2);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_option);
    }
}