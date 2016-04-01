package com.example.synerzip.androidgame;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView playerAText;
    private TextView playerBText;
    private TextView playerATextAnswer;
    private TextView playerBTextAnswer;
    private TextView playerAScoreText;
    private TextView playerBScoreText;
    private Button buttonPlayerA;
    private Button buttonPlayerB;
    private SharedPreferences sharedPreferences;
    private CountDownTimer countDownTimer;
    private CountDownTimer delayTimer;
    private CountDownTimer redButtonTimer;
    private CountDownTimer greenButtonTimer;
    private int randomQuestionset;
    static int playerAScore;
    static int playerBScore;
    private int questionNumber;
    private String defaultScore = "0";
    private int maxQuestionNumber=20;
    private JSONObject jsonRootObject;
    private String player;
    String question;
    String answer;
    Boolean trueOrFalse;
    Boolean answerTrueOrFalse;
    static Boolean buttonClicked=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        questionNumber = 0;
        playerAScore = 0;
        playerBScore = 0;
        playerAText = (TextView) findViewById(R.id.playerATextView);
        playerBText = (TextView) findViewById(R.id.playerBTextView);
        playerATextAnswer = (TextView) findViewById(R.id.playerAANswers);
        playerBTextAnswer = (TextView) findViewById(R.id.playerBAnswers);
        playerAScoreText = (TextView) findViewById(R.id.playerAScore);
        playerBScoreText = (TextView) findViewById(R.id.playerBScore);
        buttonPlayerA = (Button) findViewById(R.id.playerAButton);
        buttonPlayerB = (Button) findViewById(R.id.playerBButton);
        playerAScoreText.setText(defaultScore);
        playerBScoreText.setText(defaultScore);
        buttonPlayerA.setText(startScreen.playerAName);
        buttonPlayerB.setText(startScreen.playerBName);
        sharedPreferences = this.getSharedPreferences(getString(R.string.packageName), Context.MODE_PRIVATE);

        int questionSet = sharedPreferences.getInt("questionSet", 0);
        Random random = new Random();
        randomQuestionset = random.nextInt(4) + 1;
        switch (questionSet)
        {
            case 1:randomQuestionset=2;
                 break;
            case 2:randomQuestionset=3;
                break;
            case 3:randomQuestionset=4;
                break;
            case 4:randomQuestionset=1;
                break;
        }
        sharedPreferences.edit().putInt("questionSet", randomQuestionset).apply();
        try {
            jsonRootObject = new JSONObject(loadJSONFromAsset("questionSet"+randomQuestionset+".json"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        questionGenerator();
    }


    public String loadJSONFromAsset(String jsonFile) {
        String json = null;
        try {
            InputStream is = getApplication().getAssets().open(jsonFile);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    /*
    Whenever "Player A" button is clicked this method get called.
    @param buttonView Unused.
    @return Nothing.
     */
    public void playerAClicked(View buttonView) {
        buttonClicked=true;
        buttonPlayerA.setEnabled(false);
        buttonPlayerB.setEnabled(false);
      player=getString(R.string.playerA);
        if (countDownTimer != null)
        {
            countDownTimer.cancel();
            countDownTimer = null;
        }
       questionGenerator();

        if(answerTrueOrFalse) {
            setButtonColorGreen(buttonView);
            playerAScore++;
            playerAScoreText.setText(Integer.toString(playerAScore));
        }
        else
        {
           setButtonColorRed(buttonView);
            playerAScore--;
            playerAScoreText.setText(Integer.toString(playerAScore));

        }
        setCountDownTimer();
    }

    /*
    Whenever "Player B" button is clicked this method get called.
    @param buttonView Unused
    @return Nothing.
     */
    public void playerBClicked(View buttonView) {
        buttonClicked=true;
        buttonPlayerA.setEnabled(false);
        buttonPlayerB.setEnabled(false);
        boolean answer = false;
        player=getString(R.string.playerB);
        if (countDownTimer != null)
        {
            countDownTimer.cancel();
            countDownTimer = null;
        }

        questionGenerator();
        if(answerTrueOrFalse) {
            setButtonColorGreen(buttonView);
            playerBScore++;
            playerBScoreText.setText(Integer.toString(playerBScore));
        }
        else
        {
            setButtonColorRed(buttonView);
            playerBScore--;
            playerBScoreText.setText(Integer.toString(playerBScore));
        }
        setCountDownTimer();
    }

    public void questionGenerator()
    {
        buttonPlayerA.setBackgroundResource(R.drawable.shape);
        buttonPlayerB.setBackgroundResource(R.drawable.shape);
        String data = "";

        try {
            //Get the instance of JSONArray that contains JSONObjects
            JSONArray jsonArray = jsonRootObject.optJSONArray("questionSet"+randomQuestionset);

            //Iterate the jsonArray and print the info of JSONObjects
            if( questionNumber < jsonArray.length()){
                JSONObject jsonObject = jsonArray.getJSONObject(questionNumber);

                question = jsonObject.optString("question").toString();
                answer = jsonObject.optString("answer").toString();
                trueOrFalse=Boolean.parseBoolean(jsonObject.optString("trueOrFalse").toString());

                if(trueOrFalse)
                {
                    answerTrueOrFalse=true;
                }
                else {
                    answerTrueOrFalse=false;
                }

                countDownTimer = new CountDownTimer(5000, 4000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        buttonPlayerA.setEnabled(true);
                        buttonPlayerB.setEnabled(true);
                        playerAText.setText(question);
                        playerBText.setText(question);
                        playerATextAnswer.setText(answer);
                        playerBTextAnswer.setText(answer);
                    }
                    public void onFinish() {
                        questionNumber++;
                        questionGenerator();
                    }
                };
                countDownTimer.start();
            } else {
                Intent intent=new Intent(this,GameOver.class);
                startActivity(intent);
            }

        } catch (JSONException e) {e.printStackTrace();}
    }


    /*
    sets timer after button is clicked so that question appears for a while on the screen.
    @param No params
    @return Nothing.
     */
    public void setCountDownTimer() {
        if (countDownTimer != null)
        {
            countDownTimer.cancel();
            countDownTimer = null;
        }
        delayTimer=new CountDownTimer(2000, 100) {
            @Override
            public void onTick(long millisUntilFinished) {
            }
            public void onFinish() {
                questionNumber++;
                questionGenerator();
            }
        }.start();
    }

    /*
    sets green color to the button
    @param buttonView is an view of a button which is clicked
    @return Nothing.
     */
    public void setButtonColorGreen(View buttonView) {
        if (countDownTimer != null)
        {
            countDownTimer.cancel();
            countDownTimer = null;
        }
        buttonView.setBackgroundResource(R.drawable.greenshape);
        greenButtonTimer=new CountDownTimer(1000, 50) {
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
        if (countDownTimer != null)
        {
            countDownTimer.cancel();
            countDownTimer = null;
        }
        buttonView.setBackgroundResource(R.drawable.redshape);
        redButtonTimer=new CountDownTimer(1000, 50) {
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
                .setTitle(getString(R.string.exitTitle))
                .setMessage(getString(R.string.exitMessage))
                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finishAffinity();
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
        int id = item.getItemId();
        //this will exit the game if user clicks on yes button from dialogbox.
        if (id == R.id.exit)
        {
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle(getString(R.string.exitTitle))
                    .setMessage(getString(R.string.exitMessage))
                    .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finishAffinity();
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
