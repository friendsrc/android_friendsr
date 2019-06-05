package com.example.friendsearch;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MediaPlayer player = MediaPlayer.create(MainActivity.this, R.raw.friends_theme);
        player.start();
    }

    public void peopleClick(View view) {
        ImageButton button = (ImageButton) view;
        String tag = button.getTag().toString();

        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra("people_name", tag);
        startActivity(intent);
    }
}