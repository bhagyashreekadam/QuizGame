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

    TextView playerAText;
    TextView playerBText;
    TextView playerATextAnswer;
    TextView playerBTextAnswer;
    TextView result;
    TextView playerAScoreText;
    TextView playerBScoreText;

    Button buttonPlayerA;
    Button buttonPlayerB;

    RelativeLayout layout;
    LinearLayout rLayout;

    SharedPreferences sharedPreferences;
    CountDownTimer countDownTimer;

    int randomQuestionset;
    int playerAScore = 0;
    int playerBScore = 0;
    int i;

    static LinkedHashMap<String, String> map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        i = 0;
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

        layout = (RelativeLayout) findViewById(R.id.layout);
        rLayout = (LinearLayout) findViewById(R.id.relativeLayout);

        layout.setVisibility(View.GONE);


        playerAScoreText.setText("0");
        playerBScoreText.setText("0");




        generateNewQuestionSet();
        nextQuestion();
    }


    /*
    This method will generate new set of questions everytime.
    i.e. whenever the game is finished and we click on play again button this method will get called and new set of questions will get generated.
     */
    public void generateNewQuestionSet() {

        int questionSet = sharedPreferences.getInt("questionSet", 0);

        map = new LinkedHashMap<String, String>();

        Random random = new Random();
        randomQuestionset = random.nextInt(4) + 1;

        if (randomQuestionset == questionSet && randomQuestionset < 4) {
            randomQuestionset++;

        } else if (randomQuestionset == questionSet && randomQuestionset == 4) {
            randomQuestionset--;
        }


        if (randomQuestionset == 1) {

            sharedPreferences.edit().putInt("questionSet", 1).apply();

            map.clear();

            //question 1
            map.put("Capital of India", "Delhi");//true

            //question 2
            map.put("National Animal", "Tiger"); //true

            //question 3
            map.put("Right Statement", "Water is yellow");//false

            //question 4
            map.put("The currency notes are printed in", "Nasik");//true

            //question 5
            map.put("The largest ocean in the world is", "The pacific Ocean");//true

            //question 6
            map.put("Which is the Land of the Rising Sun", "London");//false  answer->Japan

            //question 7
            map.put("Which crop is sown on the largest area in India", "Wheet");//false  answer->Rice

            //question 8
            map.put("The value of Gold is determined in", "London");//true

            //question 9
            map.put("The state which has the largest number of sugar mills in India is", "U.P");//true

            //question 10
            map.put("First University in India was founded at", "Calcutta");//true

            //question 11
            map.put("Tajmahal is on the banks of", "Ganga");//false  answer->Jamuna

            //question 12
            map.put("Second World war began in", "1938");//true

            //question 13
            map.put("capital of Afghanistan", "Herat");//false answer->Kabul

            //question 14
            map.put("capital of United States of America", "New York");//false  answer-> Washington

            //question 15
            map.put("capital of United Kingdom", "London");//true

            //question 16
            map.put("capital of Italy", "Rome");//true

            //question 17
            map.put("capital of Switzerland ", "Bern");//true

            //question 18
            map.put("capital of Brazil", "Brasilia");//true

            //question 19
            map.put("capital of Poland", "Radom");//false  answer->Warsaw

            //question 20
            map.put("capital of Russia", "Samara");//false  answer->Moscow

        } else if (randomQuestionset == 2) {

            sharedPreferences.edit().putInt("questionSet", 2).apply();

            map.clear();

            //question 1
            map.put("National animal1", "Afghanistan-> Polo sheep");//false  answer->Snow Leopard

            //question 2
            map.put("National animal2", "Argentina-> kodkod");//false  answer->Rufous Hornero

            //question 3
            map.put("National animal3", "Australia-> Red kangaroo");//true

            //question 4
            map.put("National animal4", "Bangladesh-> Royal Bengal tiger ");//true

            //question 5
            map.put("National animal5", "Belgium-> Lion");//true

            //question 6
            map.put("National animal6", "Bhutan->Druk");//true

            //question 7
            map.put("National animal7", "Brazil-> otter");//false  answer->macaw

            //question 8
            map.put("National animal8", "Canada-> North American beaver");//true

            //question 9
            map.put("National animal9", "Cuba-> Cuban trogon");//true

            //question 10
            map.put("National animal10", "France-> Gallic rooster");//true

            //question 11
            map.put("National animal11", "Greece-> Phoenix");//true

            //question 12
            map.put("National animal12", "Iceland-> Reindeer");//false  answer->Gyrfalcon

            //question 13
            map.put("National animal13", "Iraq-> Golden eagle");//true

            //question 14
            map.put("National animal14", "Malaysia-> Malayan tiger");//true

            //question 15
            map.put("National animal15", "Mauritius-> Dodo");//true

            //question 16
            map.put("National animal16", "Mexico-> Golden eagle");//true

            //question 17
            map.put("National animal17", "Nepal-> Red Panda");//false  answer->Zebu

            //question 18
            map.put("National animal18", "Netherlands-> Lion");//true

            //question 19
            map.put("National animal19", "New Zealand-> kakapo parrot");//false  answer->Kiwi

            //question 20
            map.put("National animal20", "Norway-> Lion");//true

        } else if (randomQuestionset == 3) {

            sharedPreferences.edit().putInt("questionSet", 3).apply();

            map.clear();

            //question 1
            map.put("National bird1", "Afghanistan-> Golden eagle");//true

            //question 2
            map.put("National bird2", "Argentina-> Rufous hornero");//true

            //question 3
            map.put("National bird3", "Australia-> Emu");//true

            //question 4
            map.put("National bird4", "Austria-> Eagle");//false  answer->Barn swallow

            //question 5
            map.put("National bird5", "Bahamas-> Duck");//false  answer->American flamingo

            //question 6
            map.put("National bird6", "Bangladesh-> Oriental magpie-robin");//true

            //question 7
            map.put("National bird7", "Belgium-> Common kestrel");//true

            //question 8
            map.put("National bird8", "Bhutan-> Common raven");//true

            //question 9
            map.put("National bird9", "Brazil-> Parrot");//false  answer->Rufous-bellied thrush

            //question 10
            map.put("National bird10", "China-> Red-crowned crane");//true

            //question 11
            map.put("National bird11", "Cuba-> Cuban trogon");//true

            //question 12
            map.put("National bird12", "Egypt-> Golden eagle");//true

            //question 13
            map.put("National bird13", "France-> Gallic rooster");//true

            //question 14
            map.put("National bird14", "Germany-> Golden eagle");//true

            //question 15
            map.put("National bird15", "Greece-> Little owl");//true

            //question 16
            map.put("National bird16", "Iceland-> Gyrfalcon");//true

            //question 17
            map.put("National bird17", "India-> Sparrow");//false  answer->peacock

            //question 18
            map.put("National bird18", "Iran-> Common nightingale");//true

            //question 19
            map.put("National bird19", "Iraq-> Chukar partridge");//true

            //question 20
            map.put("National bird20", "Italy-> Flemingo");//false  answer->Italian sparrow

        } else if (randomQuestionset == 4) {

            sharedPreferences.edit().putInt("questionSet", 4).apply();

            map.clear();

            //question 1
            map.put("currency1", "United States-> Pound");//false   answer->us dollar

            //question 2
            map.put("currency2", "Thailand-> Thai baht");//true

            //question 3
            map.put("currency3", "Switzerland-> Swiss franc");//true

            //question 4
            map.put("currency4", "Afghanistan-> Afghan Afghani");//true

            //question 5
            map.put("currency5", "Cuba-> CUC Cuban convertible Peso");//true

            //question 6
            map.put("currency6", "Egypt-> Egyptian dollar");//false  answer->Egyptian pound

            //question 7
            map.put("currency7", "Australia-> Euro");//false   answer->Australian dollar

            //question 8
            map.put("currency8", "India-> Indian rupee");//true

            //question 9
            map.put("currency9", "Germany-> Euro");//true

            //question 10
            map.put("currency10", "Hong Kong-> Hong Kong dollar");//true

            //question 11
            map.put("currency11", "Greece-> Euro");//true

            //question 12
            map.put("currency12", "Canada-> Canadian dollar");//true

            //question 13
            map.put("currency13", "United Arab Emirates-> ruppe");//false  answer->UAE dirham

            //question 14
            map.put("currency14", "Austria-> Euro");//true

            //question 15
            map.put("currency15", "Banglades-> Bangladeshi taka");//true

            //question 16
            map.put("currency16", "Belgium-> Euro");//true

            //question 17
            map.put("currency17", "Italy-> Euro");//true

            //question 18
            map.put("currency18", "Brazil-> Brazilian Euro");//false  answer->Brazilian real

            //question 19
            map.put("currency19", "Macau-> Macanese pataca");//true

            //question 20
            map.put("currency20", "Mexico-> Mexican peso");//true

        }
    }


    /*
    This method will generate new question after every two seconds Or whenever user clicks on button
    and after last question it will display game over screen with the winner and "play Again" button
     */

    public void nextQuestion() {
        rLayout.setVisibility(View.VISIBLE);


        buttonPlayerB.setBackgroundColor(Color.YELLOW);
        buttonPlayerA.setBackgroundColor(Color.YELLOW);

        layout.setVisibility(View.GONE);



        if (i < 20) {
            buttonPlayerA.setEnabled(true);
            buttonPlayerB.setEnabled(true);
            final String que = (new ArrayList<String>(map.keySet())).get(i);

            if (randomQuestionset == 1) {
                playerAText.setText(que);
                playerBText.setText(que);
            } else if (randomQuestionset == 2) {
                String countryNationalAnimalQuestion = "Is National animal of a given country is correct??";
                playerAText.setText(countryNationalAnimalQuestion);
                playerBText.setText(countryNationalAnimalQuestion);

            } else if (randomQuestionset == 3) {
                String countryNationalBirdQuestion = "Is National bird of a given country is correct??";
                playerAText.setText(countryNationalBirdQuestion);
                playerBText.setText(countryNationalBirdQuestion);

            } else if (randomQuestionset == 4) {
                String countryNationalCurrencyQuestion = "Is currency of a given country is correct??";
                playerAText.setText(countryNationalCurrencyQuestion);
                playerBText.setText(countryNationalCurrencyQuestion);

            }

            countDownTimer = new CountDownTimer(2000, 100) {
                @Override
                public void onTick(long millisUntilFinished) {

                    Log.i("second left: ", String.valueOf(millisUntilFinished / 1000));
                    playerATextAnswer.setText(map.get(que));
                    playerBTextAnswer.setText(map.get(que));
                }

                public void onFinish() {
                    i++;
                    nextQuestion();
                }

            };
            countDownTimer.start();

        } else {

            rLayout.setVisibility(View.GONE);
            layout.setVisibility(View.VISIBLE);

            if (playerAScore < playerBScore) {
                result.setText("Player B winner");

            } else if (playerAScore > playerBScore) {
                result.setText("Player A winner");
            } else {
                result.setText("Its a draw");
            }

        }

    }

/*
after the game is finished name of the winner is displayed along with "Play Again" button,
and when user clicks on that button this method will get called and game starts.
 */
    public void playAgain(View view) {

        playerAScoreText.setText("0");
        playerBScoreText.setText("0");

        playerAScore = 0;
        playerBScore = 0;

        i = 0;
        if (countDownTimer != null) {
            countDownTimer.cancel();
            countDownTimer = null;
        }
        generateNewQuestionSet();
        nextQuestion();
    }

/*
Whenever "Player A" button is clicked this method get called.
 */
    public void playerAClicked(View view) {
        buttonPlayerA.setEnabled(false);
        buttonPlayerB.setEnabled(false);
        boolean answer = false;

        if (randomQuestionset == 1) {
            if (i == 0 || i == 2 || i == 3 || i == 4 || i == 7 || i == 8 || i == 9 || i == 11 || i == 14 || i == 15 || i == 16 || i == 17) {
                playerAScore++;
                playerAScoreText.setText(Integer.toString(playerAScore));

                answer = true;
            } else if (i == 1 || i == 5 || i == 6 || i == 10 || i == 12 || i == 13 || i == 18 || i == 19) {
                answer = false;
                playerAScore--;
                playerAScoreText.setText(Integer.toString(playerAScore));
            }
        } else if (randomQuestionset == 2) {
            if (i == 2 || i == 3 || i == 4 || i == 5 || i == 7 || i == 8 || i == 9 || i == 10 || i == 12 || i == 13 || i == 14 || i == 15 || i == 17 || i == 19) {
                playerAScore++;
                playerAScoreText.setText(Integer.toString(playerAScore));

                answer = true;
            } else if (i == 0 || i == 1 || i == 6 || i == 11 || i == 16 || i == 18) {
                answer = false;
                playerAScore--;
                playerAScoreText.setText(Integer.toString(playerAScore));
            }
        } else if (randomQuestionset == 3) {
            if (i == 0 || i == 1 || i == 2 || i == 5 || i == 6 || i == 7 || i == 9 || i == 10 || i == 11 || i == 12 || i == 13 || i == 14 || i == 15 || i == 17 || i == 18) {
                playerAScore++;
                playerAScoreText.setText(Integer.toString(playerAScore));

                answer = true;
            } else if (i == 3 || i == 4 || i == 8 || i == 16 || i == 19) {
                answer = false;
                playerAScore--;
                playerAScoreText.setText(Integer.toString(playerAScore));
            }
        } else if (randomQuestionset == 4) {
            if (i == 1 || i == 2 || i == 3 || i == 4 || i == 7 || i == 8 || i == 9 || i == 10 || i == 11 || i == 13 || i == 14 || i == 15 || i == 16 || i == 18 || i == 19) {
                playerAScore++;
                playerAScoreText.setText(Integer.toString(playerAScore));

                answer = true;
            } else if (i == 0 || i == 5 || i == 6 || i == 12 || i == 17) {
                answer = false;
                playerAScore--;
                playerAScoreText.setText(Integer.toString(playerAScore));
            }
        }


        if (answer) {

            setButtonColorGreen(buttonPlayerA);

        } else {

            setButtonColorRed(buttonPlayerA);

        }


        if (countDownTimer != null) {
            countDownTimer.cancel();
            countDownTimer = null;
        }
        setCountDownTimer();
    }




    /*
    Whenever "Player B" button is clicked this method get called.
     */
    public void playerBClicked(View view) {

        buttonPlayerA.setEnabled(false);
        buttonPlayerB.setEnabled(false);
        boolean answer = false;

        if (randomQuestionset == 1) {
            if (i == 0 || i == 2 || i == 3 || i == 4 || i == 7 || i == 8 || i == 9 || i == 11 || i == 14 || i == 15 || i == 16 || i == 17) {
                playerBScore++;
                playerBScoreText.setText(Integer.toString(playerBScore));

                answer = true;
            } else if (i == 1 || i == 5 || i == 6 || i == 10 || i == 12 || i == 13 || i == 18 || i == 19) {
                answer = false;
                playerBScore--;
                playerBScoreText.setText(Integer.toString(playerBScore));
            }
        } else if (randomQuestionset == 2) {
            if (i == 2 || i == 3 || i == 4 || i == 5 || i == 7 || i == 8 || i == 9 || i == 10 || i == 12 || i == 13 || i == 14 || i == 15 || i == 17 || i == 19) {
                playerBScore++;
                playerBScoreText.setText(Integer.toString(playerBScore));

                answer = true;
            } else if (i == 0 || i == 1 || i == 6 || i == 11 || i == 16 || i == 18) {
                answer = false;
                playerBScore--;
                playerBScoreText.setText(Integer.toString(playerBScore));
            }
        } else if (randomQuestionset == 3) {
            if (i == 0 || i == 1 || i == 2 || i == 5 || i == 6 || i == 7 || i == 9 || i == 10 || i == 11 || i == 12 || i == 13 || i == 14 || i == 15 || i == 17 || i == 18) {
                playerBScore++;
                playerBScoreText.setText(Integer.toString(playerBScore));

                answer = true;
            } else if (i == 3 || i == 4 || i == 8 || i == 16 || i == 19) {
                answer = false;
                playerBScore--;
                playerBScoreText.setText(Integer.toString(playerBScore));
            }
        } else if (randomQuestionset == 4) {
            if (i == 1 || i == 2 || i == 3 || i == 4 || i == 7 || i == 8 || i == 9 || i == 10 || i == 11 || i == 13 || i == 14 || i == 15 || i == 16 || i == 18 || i == 19) {
                playerBScore++;
                playerBScoreText.setText(Integer.toString(playerBScore));

                answer = true;
            } else if (i == 0 || i == 5 || i == 6 || i == 12 || i == 17) {
                answer = false;
                playerBScore--;
                playerBScoreText.setText(Integer.toString(playerBScore));
            }
        }

        if (answer) {

            setButtonColorGreen(buttonPlayerB);

        } else {

            setButtonColorRed(buttonPlayerB);
        }

        if (countDownTimer != null) {
            countDownTimer.cancel();
            countDownTimer = null;
        }
        setCountDownTimer();

    }






/*
sets timer after button is clicked so that question appears for a while on the screen.
 */
    public void setCountDownTimer() {

        new CountDownTimer(2000, 100) {

            @Override
            public void onTick(long millisUntilFinished) {


            }

            public void onFinish() {
                i++;
                nextQuestion();
            }

        }.start();

    }

/*
sets green color to the button
 */
    public void setButtonColorGreen(View view) {
        view.setBackgroundColor(Color.GREEN);
        new CountDownTimer(1000, 50) {

            @Override
            public void onTick(long arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onFinish() {

            }
        }.start();
    }

    /*
sets red color to the button
 */
    public void setButtonColorRed(View view) {
        view.setBackgroundColor(Color.RED);
        new CountDownTimer(1000, 50) {

            @Override
            public void onTick(long arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onFinish() {

            }
        }.start();
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
        if (id == R.id.exit) {

            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("Are you sure???")
                    .setMessage("You want to Exit this game??")
                    .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            System.exit(1);
                        }
                    })
                    .setNegativeButton("No",null)
                    .show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
