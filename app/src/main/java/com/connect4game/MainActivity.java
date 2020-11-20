package com.connect4game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Drawable drawable1;
    Drawable drawable2;
    String tagColorCode[] = new String[]{"rednew","yellownew","greennew","bluenew","pinknew","purplenew"};

    public void goBack(View view){
        Intent intent = new Intent(getApplicationContext(),HomeScreen.class);
        startActivity(intent);
        finish();
    }

    int currentPlayer = 1;
    int[][] state = new int[5][5];
    boolean gameActive= true;
    int movesCounter=0;

    public int checkDown(int row, int col){
        int x=row;
        while (x<5 && state[x][col]==0){
            x=x+1;
        }
        return x;
    }

    public void dropDown(View view){

        ImageView counter = (ImageView) view;
        int currTag = Integer.parseInt(counter.getTag().toString());
        int row = currTag/5;
        int col = currTag%5;


        if (state[row][col]==0 && gameActive) {
            row = checkDown(row,col) -1;
            currTag = (5*row) + col;
            int resID = getResources().getIdentifier("imageView"+(currTag+1),"id", getPackageName());
            counter = (ImageView) findViewById(resID);

            movesCounter++;
            state[row][col] = currentPlayer;
            counter.setTranslationY(-2000);
            int ans = 0;
            //horizontal check
            int count = 0;
            for (int i = 0; i < 5; i++) {
                if (state[row][i] == currentPlayer) {
                    count++;
                } else
                    count = 0;

                if (count >= 4) {
                    ans = 1;
                }
            }
            //Vertical check
            count = 0;
            for (int i = 0; i < 5; i++) {
                if (state[i][col] == currentPlayer) {
                    count++;
                } else
                    count = 0;

                if (count >= 4) {
                    ans = 1;
                }
            }

            // ascendingDiagonalCheck
            for (int i = 3; i < 5; i++) {
                for (int j = 0; j < 2; j++) {
                    if (state[i][j] == currentPlayer && state[i - 1][j + 1] == currentPlayer && state[i - 2][j + 2] == currentPlayer && state[i - 3][j + 3] == currentPlayer)
                        ans = 1;
                }
            }
            // descendingDiagonalCheck
            for (int i = 3; i < 5; i++) {
                for (int j = 3; j < 5; j++) {
                    if (state[i][j] == currentPlayer && state[i - 1][j - 1] == currentPlayer && state[i - 2][j - 2] == currentPlayer && state[i - 3][j - 3] == currentPlayer)
                        ans = 1;
                }
            }

            if (currentPlayer == 1) {
                counter.setImageDrawable(drawable1 );
                currentPlayer = 2;
            } else {
                counter.setImageDrawable(drawable2);
                currentPlayer = 1;
            }

            counter.animate().translationYBy(2000).rotation(3600).setDuration(300);
            if (ans == 1) {
                String winner;
                if (currentPlayer==1){
                    winner = "Player 2";
                }
                else{
                    winner = "Player 1";
                }

                gameActive=false;

                Button playAgainButton = (Button) findViewById(R.id.playAgainButton);
                TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);
                winnerTextView.setText(winner + " has Won!");
                playAgainButton.setVisibility(view.VISIBLE);
                winnerTextView.setVisibility(View.VISIBLE);
            }
            else if (movesCounter==25){
                Button playAgainButton = (Button) findViewById(R.id.playAgainButton);
                TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);
                winnerTextView.setText("It's a Tie!");
                playAgainButton.setVisibility(view.VISIBLE);
                winnerTextView.setVisibility(View.VISIBLE);
            }
        }

        else{
            Log.i("Info","ERROR ERROR ERROR ERROR");
        }
    }

    public void restart(View view){
        Button playAgainButton = (Button) findViewById(R.id.playAgainButton);
        TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);
        playAgainButton.setVisibility(view.INVISIBLE);
        winnerTextView.setVisibility(View.INVISIBLE);

        androidx.gridlayout.widget.GridLayout gridLayout = (androidx.gridlayout.widget.GridLayout) findViewById(R.id.gridLayout);
        for (int i=0; i<gridLayout.getChildCount();i++){
            ImageView counter = (ImageView) gridLayout.getChildAt(i);
            counter.setImageDrawable(null);
        }
        currentPlayer = 1;
        state = new int[5][5];
        gameActive= true;
        movesCounter=0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        p1 = getIntent().getExtras().getString("player1");
//        p2 = getIntent().getExtras().getString("player2");
        Resources res = getResources();
        String mDrawableName = tagColorCode[Integer.parseInt(getIntent().getExtras().getString("player1"))];
        int rID = res.getIdentifier(mDrawableName , "drawable", getPackageName());
        drawable1 = res.getDrawable(rID );

        String mDrawableName2 = tagColorCode[Integer.parseInt(getIntent().getExtras().getString("player2"))];
        int rID2 = res.getIdentifier(mDrawableName2 , "drawable", getPackageName());
        drawable2 = res.getDrawable(rID2 );

        Log.i("player1",tagColorCode[Integer.parseInt(getIntent().getExtras().getString("player1"))]);
        Log.i("player2",tagColorCode[Integer.parseInt(getIntent().getExtras().getString("player2"))]);

    }
}