package com.example.synerzip.androidgame;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class startScreen extends AppCompatActivity {
    private Button startButton;
    String player="A";
    static String playerAName;
    static String playerBName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void startGame(View view)
    {
        playerNameDialog();
    }

    public void playerNameDialog() {
        String playerNameLine;
        if(player.equals("A"))
        {
            playerNameLine= getString(R.string.playerANameLine);
        }
        else {
            playerNameLine= getString(R.string.playerBNameLine);
        }
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.custom_dialog, null);
        dialogBuilder.setView(dialogView);

        final EditText edt = (EditText) dialogView.findViewById(R.id.edit1);
        if(player.equals("A")) {
            edt.setHint(getString(R.string.playerA));
        }
        else {
            edt.setHint(getString(R.string.playerB));
        }


        dialogBuilder.setMessage(playerNameLine);
        dialogBuilder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                if (player.equals("A")) {
                    player = "B";
                    playerAName = edt.getText().toString().trim();
                    if (playerAName.trim().equals("")) {
                        playerAName= getString(R.string.playerA);
                        playerNameDialog();
                    } else {
                        player = "B";
                        playerNameDialog();
                    }
                } else {
                    playerBName = edt.getText().toString().trim();
                    if (playerAName.equalsIgnoreCase(playerBName)) {

                        new AlertDialog.Builder(startScreen.this)
                                .setIcon(android.R.drawable.ic_dialog_alert)
                                .setTitle(getString(R.string.errorTitle))
                                .setMessage(getString(R.string.errorMessage))
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        playerNameDialog();
                                    }
                                })
                                .show();
                    } else {
                        if (playerBName.trim().equals("")) {
                            playerBName=getString(R.string.playerB);
                            Intent i = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(i);

                        } else {
                            Intent i = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(i);
                        }
                    }
                }
            }
        });
        AlertDialog b = dialogBuilder.create();
        b.show();
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
            new AlertDialog.Builder(startScreen.this)
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
