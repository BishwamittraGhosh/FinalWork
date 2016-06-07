package com.example.vacuumtubee.finalapproach;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

/**
 * Created by Vacuum Tubee on 6/6/2016.
 */
public class ReviewForm extends Fragment {
    View myView;
    Button btnReviews;
    ImageButton btnSubmit;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.review_form, container, false);
        init();
        btnReviews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(getActivity().getApplicationContext(), WorkerReview.class));
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.content_frame
                                , new WorkerReview())
                        .commit();

            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(getActivity().getApplicationContext(), WorkerReview.class));
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.content_frame
                                , new WorkerReview())
                        .commit();

            }
        });
        return myView;
    }
    private void init() {

        btnReviews=(Button)myView.findViewById(R.id.btnReview_form_other_review);
        btnSubmit=(ImageButton)myView.findViewById(R.id.btnReview_form_submit);

    }
}
