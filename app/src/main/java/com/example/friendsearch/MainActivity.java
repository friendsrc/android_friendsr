package com.example.friendsearch;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TableLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /* linking java and xml when the user click the photo -- moved to fragment

    public void peopleClick(View view) {
        ImageButton button = (ImageButton) view;
        String tag = button.getTag().toString();

        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra("people_name", tag);
        startActivity(intent);
    }

    */
}
