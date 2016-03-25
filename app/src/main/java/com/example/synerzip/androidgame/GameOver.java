package com.example.synerzip.androidgame;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class GameOver extends AppCompatActivity {
    private TextView result;
    private TextView playerALabel;
     private TextView playerBLabel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        result = (TextView) findViewById(R.id.result);
        playerALabel=(TextView)findViewById(R.id.plyerAscoreText);
        playerBLabel=(TextView)findViewById(R.id.plyerBscoreText);
        ImageView imageView=(ImageView)findViewById(R.id.imageView);

        playerALabel.setText(startScreen.playerAName.toUpperCase() + getString(R.string.score)+Integer.toString(MainActivity.playerAScore));
        playerBLabel.setText(startScreen.playerBName.toUpperCase() + getString(R.string.score)+Integer.toString(MainActivity.playerBScore));

        if(MainActivity.buttonClicked) {
            if (MainActivity.playerAScore < MainActivity.playerBScore) {
                result.setText(startScreen.playerBName.toUpperCase() + getString(R.string.won));
            } else if (MainActivity.playerAScore > MainActivity.playerBScore) {
                result.setText(startScreen.playerAName.toUpperCase() + getString(R.string.won));
            } else {
                result.setText(getString(R.string.draw));
            }
        }
        else {
            result.setText(getString(R.string.timedOut));
        }
    }

    /*
  after the game is finished,name of the winner is displayed along with "Play Again" button,
  and when user clicks on that button this method will get called and game starts.
  @param buttonView Unused.
  @return Nothing.
   */
    public void playAgain(View buttonView) {
       Intent intent=new Intent(this,startScreen.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(GameOver.this)
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
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //this will exit the game if user clicks on yes button from dialogbox.
        if (id == R.id.exit)
        {
            new AlertDialog.Builder(GameOver.this)
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
