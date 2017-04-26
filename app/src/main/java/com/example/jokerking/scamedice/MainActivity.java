package com.example.jokerking.scamedice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.os.Handler;

import java.util.Random;



public class MainActivity extends AppCompatActivity {

    public static int userScore=0;
    public static int userTurnScore=0;
    public static int computerScore=0;
    public static int computerTurnScore=0;

    Handler timerHandler = new Handler();
    Runnable timerRunnable = new Runnable() {
        @Override
        public void run() {
            TextView text = (TextView)findViewById(R.id.computerScore);
            if(computerTurn() == true) {
                if (computerTurnScore < 20) {
                    timerHandler.postDelayed(this, 500);
                }
                else{
                    computerScore += computerTurnScore;
                    Button roll = (Button)findViewById(R.id.roll);
                    Button hold = (Button)findViewById(R.id.hold);
                    roll.setEnabled(true);
                    hold.setEnabled(true);
                    computerTurnScore = 0;
                }
            }
            else{
                computerTurnScore = 0;
                Button roll = (Button)findViewById(R.id.roll);
                Button hold = (Button)findViewById(R.id.hold);
                roll.setEnabled(true);
                hold.setEnabled(true);
            }
            text.setText("Computer Score: " + computerScore);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void rollDie(View view){
        int dieValue= randomValue();
        dieFace(dieValue);
        if(dieValue != 1)
            userTurnScore += dieValue;
        else {
            userTurnScore = 0;
            timerHandler.postDelayed(timerRunnable,1000);;
        }

    }

    public void resetDie(View view){
        TextView userText = (TextView)findViewById(R.id.yourScore);
        TextView compText = (TextView)findViewById(R.id.computerScore);
        userScore = 0;
        userTurnScore = 0;
        computerScore = 0;
        computerTurnScore = 0;
        userText.setText("Your score: " + userScore);
        compText.setText("Computer Score: " + computerScore);
    }

    public void holdDie(View view){
        TextView text = (TextView)findViewById(R.id.yourScore);
        userScore += userTurnScore;
        userTurnScore = 0;
        text.setText("Your score: " + userScore);
        timerHandler.postDelayed(timerRunnable,0);;
    }

    public boolean computerTurn(){

        int diceValue = randomValue();
        dieFace(diceValue);
        if(diceValue != 1) {
            computerTurnScore += diceValue;
            return true;
        }
        else {
            computerTurnScore = 0;
            return false;
        }



    }

    public void dieFace(int value){
        ImageView imageDie = (ImageView)findViewById(R.id.imageView);

        switch (value){
            case 1: imageDie.setImageResource(R.drawable.dice1);
                break;
            case 2: imageDie.setImageResource(R.drawable.dice2);
                break;
            case 3: imageDie.setImageResource(R.drawable.dice3);
                break;
            case 4: imageDie.setImageResource(R.drawable.dice4);
                break;
            case 5: imageDie.setImageResource(R.drawable.dice5);
                break;
            case 6: imageDie.setImageResource(R.drawable.dice6);
                break;
        }
    }

    public int randomValue(){
            Random rand = new Random();
            int value = rand.nextInt(6)+1;
            Log.i("tag",Integer.toString(value));
            return value;
    }
}
