package com.example.friendsearch;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Scanner;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        // grab the intent from MainActivity where name equals the tag stated in activity_main.xml
        Intent intent = getIntent();
        final String name = intent.getStringExtra("people_name");
        setPeopleName(name);

        // create a SharedPreferences to store ratings
        SharedPreferences pref = getApplicationContext().getSharedPreferences("Ratings", MODE_PRIVATE);
        final SharedPreferences.Editor editor = pref.edit();

        // check if a rating already exists and set a listener on the RatingBar
        RatingBar ratingBar = findViewById(R.id.rating_bar);
        ratingBar.setRating(pref.getFloat(name, 0));
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Toast.makeText(
                        DetailsActivity.this,
                        "You gave " + name + " " + rating + " star",
                        Toast.LENGTH_SHORT
                ).show();
                editor.putFloat(name, rating);
                editor.commit();
            }
        });
    }

    public void setPeopleName(final String name) {
        // changing "rachel" -> "R.drawable.rachel"
        String[] fullname = name.split(" ");
        int imageID = getResources().getIdentifier(fullname[0].toLowerCase(), "drawable", getPackageName());
        Toast.makeText(this, fullname[0], Toast.LENGTH_LONG).show();

        // changing "rachel" -> "R.raw.rachel"
        int fileID = getResources().getIdentifier(fullname[0].toLowerCase(), "raw", getPackageName());

        // grab the text
        Scanner scan1 = new Scanner(getResources().openRawResource(fileID));
        String fileText = scan1.nextLine();

        // Name of the people
        TextView tv1 = (TextView) findViewById(R.id.people_name);
        tv1.setText(name);

        // Picture of the selected people
        ImageView iv = (ImageView) findViewById(R.id.poeple_image);
        iv.setImageResource(imageID);

        // Details of the selected people
        TextView tv2 = (TextView) findViewById(R.id.people_textview);
        tv2.setText(fileText);
    }
}
