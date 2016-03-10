package com.example.synerzip.androidgame;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView playerAText;
    private TextView playerBText;
    private TextView playerATextAnswer;
    private TextView playerBTextAnswer;
    private TextView result;
    private TextView playerAScoreText;
    private TextView playerBScoreText;
    private Button buttonPlayerA;
    private Button buttonPlayerB;
    private RelativeLayout gameOverlayout;
    private LinearLayout gameLayout;
    private SharedPreferences sharedPreferences;
    private CountDownTimer countDownTimer;
    private int randomQuestionset;
    private int playerAScore = 0;
    private int playerBScore = 0;
    private int questionNumber;
    private String defaultScore = "0";
    private String playerAWinner = "PLAYER A WON";
    private String playerBWinner = "PLAYER B WON";
    private String draw = "IT'S A DRAW";
    private  String countryNationalAnimalQuestion = "Is National animal of a given country is correct??";
    private  String countryNationalBirdQuestion = "Is National bird of a given country is correct??";
    private  String countryNationalCurrencyQuestion = "Is currency of a given country is correct??";
    private int maxQuestionNumber=20;
    private static LinkedHashMap<String, String> questionMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        questionNumber = 0;
        sharedPreferences = this.getSharedPreferences("com.example.synerzip.androidgame", Context.MODE_PRIVATE);
        playerAText = (TextView) findViewById(R.id.playerATextView);
        playerBText = (TextView) findViewById(R.id.playerBTextView);
        playerATextAnswer = (TextView) findViewById(R.id.playerAANswers);
        playerBTextAnswer = (TextView) findViewById(R.id.playerBAnswers);
        playerAScoreText = (TextView) findViewById(R.id.playerAScore);
        playerBScoreText = (TextView) findViewById(R.id.playerBScore);
        result = (TextView) findViewById(R.id.result);
        buttonPlayerA = (Button) findViewById(R.id.playerAButton);
        buttonPlayerB = (Button) findViewById(R.id.playerBButton);
        gameOverlayout = (RelativeLayout) findViewById(R.id.gameOverlayout);
        gameLayout = (LinearLayout) findViewById(R.id.gameLayout);
        gameOverlayout.setVisibility(View.GONE);
        playerAScoreText.setText(defaultScore);
        playerBScoreText.setText(defaultScore);
        generateNewQuestionSet();
        nextQuestion();
    }

    /*
    This method will generate new set of questions everytime.
    i.e. whenever the game is finished and we click on play again button this method will get called and new set of questions will get generated.
    @param No params
    @return Nothing.
     */
    public void generateNewQuestionSet() {
        int questionSet = sharedPreferences.getInt("questionSet", 0);
        questionMap = new LinkedHashMap<String, String>();
        Random random = new Random();
        randomQuestionset = random.nextInt(4) + 1;

        if (randomQuestionset == questionSet && randomQuestionset < 4)
        {
            randomQuestionset++;
        }
        else if (randomQuestionset == questionSet && randomQuestionset == 4)
        {
            randomQuestionset--;
        }

        switch (randomQuestionset) {
            case 1:
                sharedPreferences.edit().putInt("questionSet", 1).apply();
                questionMap.clear();
                //question 1
                questionMap.put("Capital of India", "Delhi");//true
                //question 2
                questionMap.put("National Animal", "Tiger"); //true
                //question 3
                questionMap.put("Right Statement", "Water is yellow");//false
                //question 4
                questionMap.put("The currency notes are printed in", "Nasik");//true
                //question 5
                questionMap.put("The largest ocean in the world is", "The pacific Ocean");//true
                //question 6
                questionMap.put("Which is the Land of the Rising Sun", "London");//false  answer->Japan
                //question 7
                questionMap.put("Which crop is sown on the largest area in India", "Wheet");//false  answer->Rice
                //question 8
                questionMap.put("The value of Gold is determined in", "London");//true
                //question 9
                questionMap.put("The state which has the largest number of sugar mills in India is", "U.P");//true
                //question 10
                questionMap.put("First University in India was founded at", "Calcutta");//true
                //question 11
                questionMap.put("Tajmahal is on the banks of", "Ganga");//false  answer->Jamuna
                //question 12
                questionMap.put("Second World war began in", "1938");//true
                //question 13
                questionMap.put("capital of Afghanistan", "Herat");//false answer->Kabul
                //question 14
                questionMap.put("capital of United States of America", "New York");//false  answer-> Washington
                //question 15
                questionMap.put("capital of United Kingdom", "London");//true
                //question 16
                questionMap.put("capital of Italy", "Rome");//true
                //question 17
                questionMap.put("capital of Switzerland ", "Bern");//true
                //question 18
                questionMap.put("capital of Brazil", "Brasilia");//true
                //question 19
                questionMap.put("capital of Poland", "Radom");//false  answer->Warsaw
                //question 20
                questionMap.put("capital of Russia", "Samara");//false  answer->Moscow
                break;

            case 2:
                sharedPreferences.edit().putInt("questionSet", 2).apply();
                questionMap.clear();
                //question 1
                questionMap.put("National animal1", "Afghanistan-> Polo sheep");//false  answer->Snow Leopard
                //question 2
                questionMap.put("National animal2", "Argentina-> kodkod");//false  answer->Rufous Hornero
                //question 3
                questionMap.put("National animal3", "Australia-> Red kangaroo");//true
                //question 4
                questionMap.put("National animal4", "Bangladesh-> Royal Bengal tiger ");//true
                //question 5
                questionMap.put("National animal5", "Belgium-> Lion");//true
                //question 6
                questionMap.put("National animal6", "Bhutan->Druk");//true
                //question 7
                questionMap.put("National animal7", "Brazil-> otter");//false  answer->macaw
                //question 8
                questionMap.put("National animal8", "Canada-> North American beaver");//true
                //question 9
                questionMap.put("National animal9", "Cuba-> Cuban trogon");//true
                //question 10
                questionMap.put("National animal10", "France-> Gallic rooster");//true
                //question 11
                questionMap.put("National animal11", "Greece-> Phoenix");//true
                //question 12
                questionMap.put("National animal12", "Iceland-> Reindeer");//false  answer->Gyrfalcon
                //question 13
                questionMap.put("National animal13", "Iraq-> Golden eagle");//true
                //question 14
                questionMap.put("National animal14", "Malaysia-> Malayan tiger");//true
                //question 15
                questionMap.put("National animal15", "Mauritius-> Dodo");//true
                //question 16
                questionMap.put("National animal16", "Mexico-> Golden eagle");//true
                //question 17
                questionMap.put("National animal17", "Nepal-> Red Panda");//false  answer->Zebu
                //question 18
                questionMap.put("National animal18", "Netherlands-> Lion");//true
                //question 19
                questionMap.put("National animal19", "New Zealand-> kakapo parrot");//false  answer->Kiwi
                //question 20
                questionMap.put("National animal20", "Norway-> Lion");//true
                break;

            case 3:
                sharedPreferences.edit().putInt("questionSet", 3).apply();
                questionMap.clear();
                //question 1
                questionMap.put("National bird1", "Afghanistan-> Golden eagle");//true
                //question 2
                questionMap.put("National bird2", "Argentina-> Rufous hornero");//true
                //question 3
                questionMap.put("National bird3", "Australia-> Emu");//true
                //question 4
                questionMap.put("National bird4", "Austria-> Eagle");//false  answer->Barn swallow
                //question 5
                questionMap.put("National bird5", "Bahamas-> Duck");//false  answer->American flamingo
                //question 6
                questionMap.put("National bird6", "Bangladesh-> Oriental magpie-robin");//true
                //question 7
                questionMap.put("National bird7", "Belgium-> Common kestrel");//true
                //question 8
                questionMap.put("National bird8", "Bhutan-> Common raven");//true
                //question 9
                questionMap.put("National bird9", "Brazil-> Parrot");//false  answer->Rufous-bellied thrush
                //question 10
                questionMap.put("National bird10", "China-> Red-crowned crane");//true
                //question 11
                questionMap.put("National bird11", "Cuba-> Cuban trogon");//true
                //question 12
                questionMap.put("National bird12", "Egypt-> Golden eagle");//true
                //question 13
                questionMap.put("National bird13", "France-> Gallic rooster");//true
                //question 14
                questionMap.put("National bird14", "Germany-> Golden eagle");//true
                //question 15
                questionMap.put("National bird15", "Greece-> Little owl");//true
                //question 16
                questionMap.put("National bird16", "Iceland-> Gyrfalcon");//true
                //question 17
                questionMap.put("National bird17", "India-> Sparrow");//false  answer->peacock
                //question 18
                questionMap.put("National bird18", "Iran-> Common nightingale");//true
                //question 19
                questionMap.put("National bird19", "Iraq-> Chukar partridge");//true
                //question 20
                questionMap.put("National bird20", "Italy-> Flemingo");//false  answer->Italian sparrow
                break;

            case 4:
                sharedPreferences.edit().putInt("questionSet", 4).apply();
                questionMap.clear();
                //question 1
                questionMap.put("currency1", "United States-> Pound");//false   answer->us dollar
                //question 2
                questionMap.put("currency2", "Thailand-> Thai baht");//true
                //question 3
                questionMap.put("currency3", "Switzerland-> Swiss franc");//true
                //question 4
                questionMap.put("currency4", "Afghanistan-> Afghan Afghani");//true
                //question 5
                questionMap.put("currency5", "Cuba-> CUC Cuban convertible Peso");//true
                //question 6
                questionMap.put("currency6", "Egypt-> Egyptian dollar");//false  answer->Egyptian pound
                //question 7
                questionMap.put("currency7", "Australia-> Euro");//false   answer->Australian dollar
                //question 8
                questionMap.put("currency8", "India-> Indian rupee");//true
                //question 9
                questionMap.put("currency9", "Germany-> Euro");//true
                //question 10
                questionMap.put("currency10", "Hong Kong-> Hong Kong dollar");//true
                //question 11
                questionMap.put("currency11", "Greece-> Euro");//true
                //question 12
                questionMap.put("currency12", "Canada-> Canadian dollar");//true
                //question 13
                questionMap.put("currency13", "United Arab Emirates-> ruppe");//false  answer->UAE dirham
                //question 14
                questionMap.put("currency14", "Austria-> Euro");//true
                //question 15
                questionMap.put("currency15", "Banglades-> Bangladeshi taka");//true
                //question 16
                questionMap.put("currency16", "Belgium-> Euro");//true
                //question 17
                questionMap.put("currency17", "Italy-> Euro");//true
                //question 18
                questionMap.put("currency18", "Brazil-> Brazilian Euro");//false  answer->Brazilian real
                //question 19
                questionMap.put("currency19", "Macau-> Macanese pataca");//true
                //question 20
                questionMap.put("currency20", "Mexico-> Mexican peso");//true
                break;
        }
    }

    /*
    This method will generate new question after every two seconds Or whenever user clicks on button
    and after last question it will display game over screen with the winner and "play Again" button
    @param No params.
    @return Nothing.
     */
     public void nextQuestion() {
        gameLayout.setVisibility(View.VISIBLE);
        buttonPlayerA.setBackgroundColor(Color.YELLOW);
        buttonPlayerB.setBackgroundColor(Color.YELLOW);
        gameOverlayout.setVisibility(View.GONE);

        if (questionNumber < maxQuestionNumber) {
            buttonPlayerA.setEnabled(true);
            buttonPlayerB.setEnabled(true);
            final String que = (new ArrayList<String>(questionMap.keySet())).get(questionNumber);

            switch (randomQuestionset)
            {
                case 1:
                    playerAText.setText(que);
                    playerBText.setText(que);
                    break;

                case 2:
                    playerAText.setText(countryNationalAnimalQuestion);
                    playerBText.setText(countryNationalAnimalQuestion);
                    break;

                case 3:
                    playerAText.setText(countryNationalBirdQuestion);
                    playerBText.setText(countryNationalBirdQuestion);
                    break;

                case 4:
                    playerAText.setText(countryNationalCurrencyQuestion);
                    playerBText.setText(countryNationalCurrencyQuestion);
                    break;
            }
            countDownTimer = new CountDownTimer(2000, 100) {
                @Override
                public void onTick(long millisUntilFinished) {

                    Log.i("second left: ", String.valueOf(millisUntilFinished / 1000));
                    playerATextAnswer.setText(questionMap.get(que));
                    playerBTextAnswer.setText(questionMap.get(que));
                }
                public void onFinish() {
                    questionNumber++;
                    nextQuestion();
                }
            };
            countDownTimer.start();
        }
        else
        {
            gameLayout.setVisibility(View.GONE);
            gameOverlayout.setVisibility(View.VISIBLE);
            if (playerAScore < playerBScore)
            {
                result.setText(playerBWinner);
            }
            else if (playerAScore > playerBScore)
            {
                result.setText(playerAWinner);
            }
            else
            {
                result.setText(draw);
            }
        }
     }

    /*
    after the game is finished name of the winner is displayed along with "Play Again" button,
    and when user clicks on that button this method will get called and game starts.
    @param buttonView Unused.
    @return Nothing.
     */
    public void playAgain(View buttonView) {
        playerAScoreText.setText(defaultScore);
        playerBScoreText.setText(defaultScore);
        playerAScore = 0;
        playerBScore = 0;
        questionNumber = 0;
        if (countDownTimer != null)
        {
            countDownTimer.cancel();
            countDownTimer = null;
        }
        generateNewQuestionSet();
        nextQuestion();
    }

    /*
    Whenever "Player A" button is clicked this method get called.
    @param buttonView Unused.
    @return Nothing.
     */
    public void playerAClicked(View buttonview) {
        buttonPlayerA.setEnabled(false);
        buttonPlayerB.setEnabled(false);
        boolean answer = false;

        switch (randomQuestionset)
        {
            case  1:
                if (questionNumber == 0 || questionNumber == 1 || questionNumber == 3 || questionNumber == 4 || questionNumber == 7 || questionNumber == 8 || questionNumber == 9 || questionNumber == 11 || questionNumber == 14 || questionNumber == 15 || questionNumber == 16 || questionNumber == 17) {
                    answer = true;
                    playerAScore++;
                    playerAScoreText.setText(Integer.toString(playerAScore));
                } else if (questionNumber == 2 || questionNumber == 5 || questionNumber == 6 || questionNumber == 10 || questionNumber == 12 || questionNumber == 13 || questionNumber == 18 || questionNumber == 19) {
                    answer = false;
                    playerAScore--;
                    playerAScoreText.setText(Integer.toString(playerAScore));
                }
                break;

            case 2:
                if (questionNumber == 2 || questionNumber == 3 || questionNumber == 4 || questionNumber == 5 || questionNumber == 7 || questionNumber == 8 || questionNumber == 9 || questionNumber == 10 || questionNumber == 12 || questionNumber == 13 || questionNumber == 14 || questionNumber == 15 || questionNumber == 17 || questionNumber == 19) {
                    answer = true;
                    playerAScore++;
                    playerAScoreText.setText(Integer.toString(playerAScore));
                } else if (questionNumber == 0 || questionNumber == 1 || questionNumber == 6 || questionNumber == 11 || questionNumber == 16 || questionNumber == 18) {
                    answer = false;
                    playerAScore--;
                    playerAScoreText.setText(Integer.toString(playerAScore));
                }
                break;

            case 3:
                if (questionNumber == 0 || questionNumber == 1 || questionNumber == 2 || questionNumber == 5 || questionNumber == 6 || questionNumber == 7 || questionNumber == 9 || questionNumber == 10 || questionNumber == 11 || questionNumber == 12 || questionNumber == 13 || questionNumber == 14 || questionNumber == 15 || questionNumber == 17 || questionNumber == 18) {
                    answer = true;
                    playerAScore++;
                    playerAScoreText.setText(Integer.toString(playerAScore));
                } else if (questionNumber == 3 || questionNumber == 4 || questionNumber == 8 || questionNumber == 16 || questionNumber == 19) {
                    answer = false;
                    playerAScore--;
                    playerAScoreText.setText(Integer.toString(playerAScore));
                }
                break;

            case 4:
                if (questionNumber == 1 || questionNumber == 2 || questionNumber == 3 || questionNumber == 4 || questionNumber == 7 || questionNumber == 8 || questionNumber == 9 || questionNumber == 10 || questionNumber == 11 || questionNumber == 13 || questionNumber == 14 || questionNumber == 15 || questionNumber == 16 || questionNumber == 18 || questionNumber == 19) {
                    answer = true;
                    playerAScore++;
                    playerAScoreText.setText(Integer.toString(playerAScore));
                } else if (questionNumber == 0 || questionNumber == 5 || questionNumber == 6 || questionNumber == 12 || questionNumber == 17) {
                    answer = false;
                    playerAScore--;
                    playerAScoreText.setText(Integer.toString(playerAScore));
                }
                break;
        }
        if (answer)
        {
            setButtonColorGreen(buttonPlayerA);
        }
        else
        {
            setButtonColorRed(buttonPlayerA);
        }
        if (countDownTimer != null)
        {
            countDownTimer.cancel();
            countDownTimer = null;
        }
        setCountDownTimer();
    }


    /*
    Whenever "Player B" button is clicked this method get called.
    @param buttonView Unused
    @return Nothing.
     */
    public void playerBClicked(View buttonView) {
        buttonPlayerA.setEnabled(false);
        buttonPlayerB.setEnabled(false);
        boolean answer = false;

        switch (randomQuestionset)
        {
            case 1:
                if (questionNumber == 0 || questionNumber == 1 || questionNumber == 3 || questionNumber == 4 || questionNumber == 7 || questionNumber == 8 || questionNumber == 9 || questionNumber == 11 || questionNumber == 14 || questionNumber == 15 || questionNumber == 16 || questionNumber == 17) {
                    answer = true;
                    playerBScore++;
                    playerBScoreText.setText(Integer.toString(playerBScore));
                } else if (questionNumber == 2 || questionNumber == 5 || questionNumber == 6 || questionNumber == 10 || questionNumber == 12 || questionNumber == 13 || questionNumber == 18 || questionNumber == 19) {
                    answer = false;
                    playerBScore--;
                    playerBScoreText.setText(Integer.toString(playerBScore));
                }
                break;

            case 2:
                if (questionNumber == 2 || questionNumber == 3 || questionNumber == 4 || questionNumber == 5 || questionNumber == 7 || questionNumber == 8 || questionNumber == 9 || questionNumber == 10 || questionNumber == 12 || questionNumber == 13 || questionNumber == 14 || questionNumber == 15 || questionNumber == 17 || questionNumber == 19) {
                    answer = true;
                    playerBScore++;
                    playerBScoreText.setText(Integer.toString(playerBScore));

                } else if (questionNumber == 0 || questionNumber == 1 || questionNumber == 6 || questionNumber == 11 || questionNumber == 16 || questionNumber == 18) {
                    answer = false;
                    playerBScore--;
                    playerBScoreText.setText(Integer.toString(playerBScore));
                }
                break;

            case 3:
                if (questionNumber == 0 || questionNumber == 1 || questionNumber == 2 || questionNumber == 5 || questionNumber == 6 || questionNumber == 7 || questionNumber == 9 || questionNumber == 10 || questionNumber == 11 || questionNumber == 12 || questionNumber == 13 || questionNumber == 14 || questionNumber == 15 || questionNumber == 17 || questionNumber == 18) {
                    answer = true;
                    playerBScore++;
                    playerBScoreText.setText(Integer.toString(playerBScore));
                } else if (questionNumber == 3 || questionNumber == 4 || questionNumber == 8 || questionNumber == 16 || questionNumber == 19) {
                    answer = false;
                    playerBScore--;
                    playerBScoreText.setText(Integer.toString(playerBScore));
                }
                break;

            case 4:
                if (questionNumber == 1 || questionNumber == 2 || questionNumber == 3 || questionNumber == 4 || questionNumber == 7 || questionNumber == 8 || questionNumber == 9 || questionNumber == 10 || questionNumber == 11 || questionNumber == 13 || questionNumber == 14 || questionNumber == 15 || questionNumber == 16 || questionNumber == 18 || questionNumber == 19) {
                    answer = true;
                    playerBScore++;
                    playerBScoreText.setText(Integer.toString(playerBScore));
                } else if (questionNumber == 0 || questionNumber == 5 || questionNumber == 6 || questionNumber == 12 || questionNumber == 17) {
                    answer = false;
                    playerBScore--;
                    playerBScoreText.setText(Integer.toString(playerBScore));
                }
                break;
        }
        if (answer)
        {
            setButtonColorGreen(buttonPlayerB);
        }
        else
        {
            setButtonColorRed(buttonPlayerB);
        }
        if (countDownTimer != null)
        {
            countDownTimer.cancel();
            countDownTimer = null;
        }
        setCountDownTimer();
    }


    /*
    sets timer after button is clicked so that question appears for a while on the screen.
    @param No params
    @return Nothing.
     */
    public void setCountDownTimer() {
        new CountDownTimer(2000, 100) {
            @Override
            public void onTick(long millisUntilFinished) {
            }
            public void onFinish() {
                questionNumber++;
                nextQuestion();
            }
        }.start();
    }

    /*
    sets green color to the button
    @param buttonView is an view of a button which is clicked
    @return Nothing.
     */
    public void setButtonColorGreen(View buttonView) {
        buttonView.setBackgroundColor(Color.GREEN);
        new CountDownTimer(1000, 50) {
            @Override
            public void onTick(long arg0) {
            }
            @Override
            public void onFinish() {
            }
        }.start();
    }

/*
sets red color to the button
 @param buttonView is an view of a button which is clicked
 @return Nothing.
 */
    public void setButtonColorRed(View buttonView) {
        buttonView.setBackgroundColor(Color.RED);
        new CountDownTimer(1000, 50) {
            @Override
            public void onTick(long arg0) {
            }
            @Override
            public void onFinish() {
            }
        }.start();
    }

    @Override
    public void onBackPressed() {
         new AlertDialog.Builder(MainActivity.this)
                .setTitle("Are you sure???")
                .setMessage("You want to Exit this game??")
                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        System.exit(1);
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //this will exit the game if user clicks on yes button from dialogbox.
        if (id == R.id.exit)
        {
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("Are you sure???")
                    .setMessage("You want to Exit this game??")
                    .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            System.exit(1);
                        }
                    })
                    .setNegativeButton("No", null)
                    .show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
