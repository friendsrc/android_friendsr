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
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Scanner;

import stanford.androidlib.SimpleActivity;
import stanford.androidlib.SimpleFragment;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailsFragment extends Fragment {

    private String currentName;

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

        final Activity activity = getActivity();

        // grab the intent from MainActivity where name equals the tag stated in activity_main.xml
        Intent intent = activity.getIntent();
        final String name = intent.getStringExtra("people_name");
        setPeopleName(name);
        currentName = name;

        // create a SharedPreferences to store ratings
        SharedPreferences pref = activity.getApplicationContext().getSharedPreferences("Ratings", MODE_PRIVATE);
        final SharedPreferences.Editor editor = pref.edit();

        // check if a rating already exists and set a listener on the RatingBar
        final RatingBar ratingBar = activity.findViewById(R.id.rating_bar);
        ratingBar.setRating(pref.getFloat(name, 0));
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                editor.putFloat(name, rating);
                editor.apply();
            }
        });

        // set on touch listener on the imageview
        // ?? (tried to set it on the scroll view and textview, didn't work)
        ImageView imageView = activity.findViewById(R.id.poeple_image);
        imageView.setOnTouchListener(new OnSwipeTouchListener(getContext()) {

            @Override
            public void onSwipeLeft(float dx) {
                super.onSwipeLeft(dx);
                setNextPerson(1);
            }

            @Override
            public void onSwipeRight(float dx) {
                super.onSwipeRight(dx);
                setNextPerson(5);
            }
        });
    }

    // update to the next person when swiped
    public void setNextPerson(int prevPersonRating) {
        // get the index for the next person
        String[] nameArray = getResources().getStringArray(R.array.friend_full_names);
        int currentIndex = Arrays.asList(nameArray).indexOf(currentName);
        int nextIndex = (currentIndex + 1) % nameArray.length;

        // update and save the rating for the current person
        RatingBar ratingBar = getActivity().findViewById(R.id.rating_bar);
        SharedPreferences pref = getActivity().getApplicationContext().getSharedPreferences("Ratings", MODE_PRIVATE);
        final SharedPreferences.Editor editor = pref.edit();
        ratingBar.setRating(prevPersonRating);
        editor.putFloat(currentName, prevPersonRating);
        editor.apply();

        // create a toast to notify that change had been made
        Toast.makeText(
                getActivity(),
                "You swiped " + currentName + " and left " + prevPersonRating + " star",
                Toast.LENGTH_SHORT
        ).show();

        // update name, photo, description to the next person
        currentName = nameArray[nextIndex];
        setPeopleName(currentName);
    }

    public void setPeopleName(String name) {
        Activity activity = getActivity();

        // update current name
        currentName = name;

        // check if rating already exist and update accordingly
        RatingBar ratingBar = activity.findViewById(R.id.rating_bar);
        SharedPreferences pref = activity.getApplicationContext().getSharedPreferences("Ratings", MODE_PRIVATE);
        ratingBar.setRating(pref.getFloat(name, 0));


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
