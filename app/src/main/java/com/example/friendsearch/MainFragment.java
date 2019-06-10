package com.example.friendsearch;


import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

public class MainFragment extends Fragment {
    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    public void peopleClick(View view) {
        ImageButton button = (ImageButton) view;
        String tag = button.getTag().toString();

        Activity activity = getActivity();
        if (activity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            Intent intent = new Intent(activity, DetailsActivity.class);
            intent.putExtra("people_name", tag);
            startActivity(intent);
        } else {
            // this causes an error without using the stanford library
            DetailsFragment frag = (DetailsFragment) ((FragmentActivity) activity).getSupportFragmentManager().findFragmentById(R.id.frag_details);
            frag.setPeopleName(tag);
        }

    }

}
