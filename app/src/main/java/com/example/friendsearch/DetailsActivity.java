package com.example.friendsearch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.HashMap;

import static com.example.friendsearch.R.array.friend_details;

public class DetailsActivity extends AppCompatActivity {

    HashMap<String, Integer> peopleIndex;
    int[] photos = {
            R.drawable.boy1,
            R.drawable.boy2,
            R.drawable.boy3,
            R.drawable.girl1,
            R.drawable.boy4,
            R.drawable.boy5
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent intent = getIntent();
        String name = intent.getStringExtra("people_name");

        String[] friendsName = getResources().getStringArray(R.array.friend_names);
        int positionInArray = Arrays.asList(friendsName).indexOf(name);
        ImageView imageView = findViewById(R.id.details_imageview);
        imageView.setImageResource(photos[positionInArray]);

        TextView textView = (TextView) findViewById(R.id.details_textview);
        String[] friendDetails = getResources().getStringArray(friend_details);
        textView.setText(friendDetails[positionInArray]);

    }
}
