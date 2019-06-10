package com.example.friendsearch;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Scanner;

import stanford.androidlib.SimpleActivity;
import stanford.androidlib.SimpleFragment;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailsFragment extends Fragment {
    public DetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Activity activity = getActivity();

        // grab the intent from MainActivity where name equals the tag stated in activity_main.xml
        Intent intent = activity.getIntent();
        final String name = intent.getStringExtra("people_name");
        setPeopleName(name);

        // create a SharedPreferences to store ratings
        SharedPreferences pref = activity.getApplicationContext().getSharedPreferences("Ratings", MODE_PRIVATE);
        final SharedPreferences.Editor editor = pref.edit();

        // check if a rating already exists and set a listener on the RatingBar
        RatingBar ratingBar = activity.findViewById(R.id.rating_bar);
        ratingBar.setRating(pref.getFloat(name, 0));
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Toast.makeText(
                        getActivity(),
                        "You gave " + name + " " + rating + " star",
                        Toast.LENGTH_SHORT
                ).show();
                editor.putFloat(name, rating);
                editor.apply();
            }
        });
    }

    public void setPeopleName(String name) {
        Activity activity = getActivity();

        // when the apps first loaded, name is null, default change into chandler
        if (name == null) {
            name = "Chandler Bing";
        }

        String[] fullname = name.split(" ");

        // changing "rachel" -> "R.drawable.rachel"
        int imageID = getResources().getIdentifier(fullname[0].toLowerCase(), "drawable", activity.getPackageName());

        // changing "rachel" -> "R.raw.rachel"
        int fileID = getResources().getIdentifier(fullname[0].toLowerCase(), "raw", activity.getPackageName());

        // grab the text
        Scanner scan1 = new Scanner(getResources().openRawResource(fileID));
        String fileText = scan1.nextLine();

        // Name of the people
        TextView tv1 = (TextView) activity.findViewById(R.id.people_name);
        tv1.setText(name);

        // Picture of the selected people
        ImageView iv = (ImageView) activity.findViewById(R.id.poeple_image);
        iv.setImageResource(imageID);

        // Details of the selected people
        TextView tv2 = (TextView) activity.findViewById(R.id.people_textview);
        tv2.setText(fileText);
    }
}
