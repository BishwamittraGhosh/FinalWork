package com.example.vacuumtubee.finalapproach;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Vacuum Tubee on 6/6/2016.
 */
public class WorkerReview extends Fragment{

    View myView;
    String name,job,phn,rating,id;
    ListView lvList;
    ArrayList<WorkerReviewResult> items = new ArrayList<WorkerReviewResult>();
    ImageButton btnAddReview;
    TextView tbName,tbRating,tbPhn,tbjob;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.worker_review, container, false);
        init();

        btnAddReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // startActivity(new Intent(getActivity().getApplicationContext(), ReviewForm.class));
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.content_frame
                                , new ReviewForm())
                        .commit();
            }

        });

        lvList = (ListView) myView.findViewById(R.id.lvWorker_review_list);
        final AmaderAdapter adpt = new AmaderAdapter();
        lvList.setAdapter(adpt);



        lvList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });


        return myView;
    }

    private void init() {
        btnAddReview=(ImageButton)myView.findViewById(R.id.btnWorker_review_addReview);
        tbName=(TextView)myView.findViewById(R.id.tvWorker_review_name);
        tbPhn=(TextView)myView.findViewById(R.id.tvWorker_review_phone);
        tbRating= (TextView)myView.findViewById(R.id.tvWorker_review_rating);
        tbjob=(TextView)myView.findViewById(R.id.tvWorker_review_job);
        Bundle bundle = this.getArguments();
        name=bundle.getString("name");
        phn=bundle.getString("phn");
        id=bundle.getString("id");
        tbName.setText(name);
        tbPhn.setText(phn);
        tbjob.setText("");


        String Jsnstring= bundle.getString("Result");
        // String Jsnstring = intent.getStringExtra("Result");
        try {
            // JSONObject jsonObject=new JSONObject(Jsnstring);
            JSONArray jsonArray=new JSONArray(Jsnstring);
            for(int i=0;i<jsonArray.length();i++){
                JSONObject item=jsonArray.getJSONObject(i);
                String workername=item.getString("Worker_Name");
                String empname=item.getString("Emp_Name");
                String job=item.getString("Job_Name");
                String review=item.getString("Review");
                String rating=item.getString("Rating");
                String avg= item.getString("Avg");
                tbRating.setText(avg);
                WorkerReviewResult ob=new WorkerReviewResult(rating,review,empname,job,workername,avg);
                Log.d("background", ob.toString());
                items.add(ob);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public class AmaderAdapter extends ArrayAdapter<WorkerReviewResult>
    {
        public AmaderAdapter()
        {
            super(getActivity().getApplicationContext(), R.layout.worker_review_list, items);
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View amaderView = convertView;
            if (amaderView == null)
            {
                amaderView = getActivity().getLayoutInflater().inflate(R.layout.worker_review_list, parent, false);
            }

            TextView t1 = (TextView) amaderView.findViewById(R.id.tvWorker_review_list_review);
            TextView t2 = (TextView) amaderView.findViewById(R.id.tvWorker_review_list_rating);
            TextView t3 = (TextView) amaderView.findViewById(R.id.tvWorker_review_list_employer);
            TextView t4 = (TextView) amaderView.findViewById(R.id.tvWorker_review_list_Job);

            t1.setText(items.get(position).review);
            t2.setText(items.get(position).rating);
            t3.setText(items.get(position).employer);
            t3.setText(items.get(position).Job);
            return amaderView;
        }
    }
}
