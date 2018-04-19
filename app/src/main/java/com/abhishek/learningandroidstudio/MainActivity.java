package com.abhishek.learningandroidstudio;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    boolean isGameOver = false;
    //Gamestate if 0 then unplayed and 1 is played
    int[] gameState = {2,2,2,2,2,2,2,2,2};

    int[][] winningPositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    //Array of Winning Positions that need to be checked
    // 0 = Zero and 1 for cross
    int currentPlayer = 0;
    String nameOfPlayer = "";
    public void dropIn(View view){
        ImageView imageForDropIn = (ImageView) view;
        int currentStateNum = Integer.parseInt(imageForDropIn.getTag().toString());
        System.out.println(imageForDropIn.getTag().toString());
        // Only Run the Rest Code if the Player is Unplayed
        if(gameState[currentStateNum] == 2 && !isGameOver) {
                    gameState[currentStateNum] = currentPlayer;
                    imageForDropIn.setTranslationY(-1000f);
                    if (currentPlayer == 0) {
                        imageForDropIn.setImageResource(R.drawable.zero);
                        currentPlayer = 1;
                        nameOfPlayer="Zero";
                    }
                    else if(currentPlayer == 1){
                        imageForDropIn.setImageResource(R.drawable.cross);
                        currentPlayer = 0;
                        nameOfPlayer="Cross";
                    }
                    imageForDropIn.animate().translationYBy(1000f).setDuration(100);

                    for(int[] winningPosition : winningPositions){
                        if(gameState[winningPosition[0]] == gameState[winningPosition[1]] &&
                                gameState[winningPosition[1]] == gameState[winningPosition[2]] &&
                                gameState[winningPosition[0]] != 2 &&
                                gameState[winningPosition[1]] != 2 &&
                                gameState[winningPosition[2]] != 2 ){
                            Toast.makeText(getApplicationContext(),nameOfPlayer + " Won the Game",Toast.LENGTH_SHORT).show();
                            LinearLayout winningLayout = (LinearLayout)findViewById(R.id.winningLayout);
                            TextView winningText = findViewById(R.id.winningMessage);
                            winningText.setText(nameOfPlayer + " Won");
                            isGameOver = true;
                            if(isGameOver){
                                winningLayout.setVisibility(View.VISIBLE);
                            }
                        }else {
                            boolean gameIsOve = true;
                            for (int counter : gameState) {
                                if (counter == 2) gameIsOve = false;
                            }
                            if (gameIsOve) {
                                Toast.makeText(getApplicationContext(), "Draw Match", Toast.LENGTH_SHORT).show();
                                LinearLayout winningLayout = (LinearLayout) findViewById(R.id.winningLayout);
                                TextView winningText = findViewById(R.id.winningMessage);
                                winningText.setText("Match is Draw");
                                isGameOver = true;
                                if (isGameOver) {
                                    winningLayout.setVisibility(View.VISIBLE);
                                }
                            }
                        }
                    }
        }
        else if(gameState[currentStateNum] == 2){
            Toast.makeText(getApplicationContext(),"Please Don't Cheat",Toast.LENGTH_SHORT).show();
        }

    }// Dropin Ends Here

    public void playAgain(View view){
        LinearLayout winningLayout = findViewById(R.id.winningLayout);
        winningLayout.setVisibility(View.INVISIBLE);
        isGameOver = false;
        for (int i = 0;i<gameState.length;i++){
            gameState[i] = 2;
        }
        currentPlayer = 0;
        nameOfPlayer = "";
        ImageView image1 = findViewById(R.id.image1);
        ImageView image2 = findViewById(R.id.image2);
        ImageView image3 = findViewById(R.id.image3);
        ImageView image4 = findViewById(R.id.image4);
        ImageView image5 = findViewById(R.id.image5);
        ImageView image6 = findViewById(R.id.image6);
        ImageView image7 = findViewById(R.id.image7);
        ImageView image8 = findViewById(R.id.image8);
        ImageView image9 = findViewById(R.id.image9);
        image1.setImageResource(0);
        image2.setImageResource(0);
        image3.setImageResource(0);
        image4.setImageResource(0);
        image5.setImageResource(0);
        image6.setImageResource(0);
        image7.setImageResource(0);
        image8.setImageResource(0);
        image9.setImageResource(0);
        /* Not Working in the ApI so Hardcoded It
        GridLayout thegrid =  findViewById(R.id.grid);
        for (int i = 0; i<thegrid.getChildCount(); i++){
            ImageView image = (ImageView)thegrid.getChildAt(i);
            image.setImageResource(0);
        }
        */
    }// Play Again Ends Here

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
