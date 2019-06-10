package com.example.friendsearch;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;

import stanford.androidlib.SimpleActivity;
import stanford.androidlib.SimpleFragment;

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

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Activity activity = getActivity();

        // check MainFragment for the comparison and idea (XML)
        TableLayout layout = (TableLayout) activity.findViewById(R.id.table_layout);
        for (int i = 0; i < layout.getChildCount(); i++) {
            TableRow row = (TableRow) layout.getChildAt(i);
            for (int j = 0; j < row.getChildCount(); j++) {
                LinearLayout ll = (LinearLayout) row.getChildAt(j);
                ImageButton button = (ImageButton) ll.getChildAt(0);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        peopleClick(view);
                    }
                });
            }
        }
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
            // this weirdo finally works ^^
            DetailsFragment frag = (DetailsFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.frag_details);
            frag.setPeopleName(tag);
        }
    }

}
