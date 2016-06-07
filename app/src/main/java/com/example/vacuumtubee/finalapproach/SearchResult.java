package com.example.vacuumtubee.finalapproach;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ProgressDialog;
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
public class SearchResult extends Fragment {

    View myView;
    ListView lvList;
    Intent intent;
    ArrayList<WorkerSearchResult> items = new ArrayList<WorkerSearchResult>();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.search_result, container, false);
        init();

        lvList = (ListView) myView.findViewById(R.id.lvSearchResult_list);
        final AmaderAdapter adpt = new AmaderAdapter();
        lvList.setAdapter(adpt);



        lvList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        return myView;
    }

    public  void  init(){
        Bundle bundle = this.getArguments();
//        int myInt = bundle.getInt(key, defaultValue);
        String Jsnstring= bundle.getString("Result");
       // String Jsnstring = intent.getStringExtra("Result");
        try {
            // JSONObject jsonObject=new JSONObject(Jsnstring);
            JSONArray jsonArray=new JSONArray(Jsnstring);
            for(int i=0;i<jsonArray.length();i++){
                JSONObject item=jsonArray.getJSONObject(i);
                String name=item.getString("Worker_Name");
                String phn=item.getString("Worker_Mobileno");
                String loc=item.getString("Worker_Address");
                String id=item.getString("worker_id");
                items.add(new WorkerSearchResult(name,phn,loc,id));

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public class AmaderAdapter extends ArrayAdapter<WorkerSearchResult>
    {
        TextView t1,t2,t3;
        String id;
        public AmaderAdapter()
        {
            super(getActivity().getApplicationContext(), R.layout.worker_list_layout, items);
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View amaderView = convertView;
            if (amaderView == null)
            {
                amaderView = getActivity().getLayoutInflater().inflate(R.layout.worker_list_layout, parent, false);
            }

            t1 = (TextView) amaderView.findViewById(R.id.tvName);
             t2 = (TextView) amaderView.findViewById(R.id.tvPhone);
           t3 = (TextView) amaderView.findViewById(R.id.tvAddress);
            Button btnReview = (Button) amaderView.findViewById(R.id.btn_worker_result_seeReview);
            ImageButton btnSend = (ImageButton) amaderView.findViewById(R.id.btn_worker_list_sendMessage);

            btnSend.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentManager fragmentManager = getFragmentManager();
                    Fragment fragment=new SendMessage();
                    Bundle bundle=new Bundle();
                    bundle.putString("name", t1.getText().toString());
                    bundle.putString("id", id);
                    fragment.setArguments(bundle);
                    fragmentManager.beginTransaction()
                            .replace(R.id.content_frame
                                    , fragment)
                            .commit();
                }
            });
            btnReview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //startActivity(new Intent(getActivity().getApplicationContext(), WorkerReview.class));


                    final ProgressDialog progressDialog=new ProgressDialog(myView.getContext());
                    progressDialog.setIndeterminate(true);
                    progressDialog.setMessage("Please wait...");
                    progressDialog.show();

                    Background background = (Background) new Background(getActivity().getApplicationContext(), new AsyncResponse() {
                        @Override
                        public void processFinish(String output) {
                            progressDialog.dismiss();
                            Log.d("background", "rsponse from asyn\n" + output);
                            //  nextActivity.putExtra("Result",output);
                            //  startActivity(nextActivity);
                            FragmentManager fragmentManager = getFragmentManager();
                            Fragment fragment=new WorkerReview();
                            Bundle bundle=new Bundle();
                            bundle.putString("Result", output);
                            bundle.putString("name", t1.getText().toString());
                            bundle.putString("id", id);
                            bundle.putString("phn",t2.getText().toString());
                            fragment.setArguments(bundle);

                            fragmentManager.beginTransaction()
                                    .replace(R.id.content_frame
                                            , fragment)
                                    .commit();

                        }
                    }).execute("SearchReview", id);

                }
            });

            btnReview.setFocusable(false);

            t1.setText(items.get(position).name);
            t2.setText(items.get(position).phoneNumber);
            t3.setText(items.get(position).address);
            id=items.get(position).id;
            return amaderView;
        }
    }

}