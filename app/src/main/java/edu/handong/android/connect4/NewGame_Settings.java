package edu.handong.android.connect4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.ImageButton;

public class NewGame_Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_new_game_settings);
        //Go back to home page
        ImageButton home_Button = (ImageButton)findViewById(R.id.newgamehome);
        home_Button.setOnClickListener(view -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);}
        );
        //Advance to the actual game activity
        ImageButton game_Button = (ImageButton)findViewById(R.id.launchgame);
        game_Button.setOnClickListener(view -> {
            Intent intent = new Intent(this, game_activity.class);
            startActivity(intent);}
        );
    }
}