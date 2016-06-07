package com.example.vacuumtubee.finalapproach;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vacuum Tubee on 6/6/2016.
 */
public class SearchWorker extends Fragment implements AdapterView.OnItemSelectedListener {
    View myView;
    Spinner workerSpinner, locationSpinner, searchTYpe;
    ImageButton btnSearch;
    String Area, Job, MaxAmout,SearchType,Worker_Name;
    EditText etMaxAmount,etName;
    Intent nextActivity;
    Fragment fragment;
    Bundle bundle;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.search_worker, container, false);
        init();
        workerSpinnerMethod();
        locationSpinnerMethod();
        searchTypeSpinnerMethod();
        workerSpinner.setEnabled(false);
        locationSpinner.setEnabled(false);
        etName.setEnabled(false);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fragment = new SearchResult();
                bundle = new Bundle();

                //nextActivity=new Intent(getActivity().getApplicationContext(),  SearchResult.class);
                MaxAmout = etMaxAmount.getText().toString();
                Worker_Name=etName.getText().toString();
                Log.d("background", Area + Job + MaxAmout+SearchType);
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
                        bundle.putString("Result", output);
                        fragment.setArguments(bundle);
                        FragmentManager fragmentManager = getFragmentManager();
                        fragmentManager.beginTransaction()
                                .replace(R.id.content_frame
                                        , fragment)
                                .commit();

                    }
                }).execute("Search", MaxAmout, Area, Job,SearchType,Worker_Name);
            }
        });
        return myView;
    }

    private void init() {
        btnSearch = (ImageButton) myView.findViewById(R.id.btnSeach_workerSearch);
        etName=(EditText)myView.findViewById(R.id.et_SearchWorker_Name);
        etMaxAmount = (EditText) myView.findViewById(R.id.et_SearchWorker_MaxAmount);
        Area="";Job="";Worker_Name="";
    }

    private void workerSpinnerMethod() {
        // Spinner element
        workerSpinner = (Spinner) myView.findViewById(R.id.spnrWorkerType);

        // Spinner click listener
        workerSpinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("Servant");
        categories.add("Gateman");
        categories.add("Electrician");
        categories.add("WaterMechanic");
        categories.add("Sweeper");
        categories.add("Gardener");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), R.layout.spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(R.layout.spinner_item);

        // attaching data adapter to spinner
        workerSpinner.setAdapter(dataAdapter);
    }

    private void searchTypeSpinnerMethod() {
        // Spinner element
        searchTYpe = (Spinner) myView.findViewById(R.id.spnrSearchWorker_searchType);

        // Spinner click listener
        searchTYpe.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("JobArea");
        categories.add("Area");
        categories.add("Job");
        categories.add("Name");


        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), R.layout.spinner_item, categories);

        // Drop down layout style - list view with radio button
       // dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapter.setDropDownViewResource(R.layout.spinner_item);
        // attaching data adapter to spinner
        searchTYpe.setAdapter(dataAdapter);
    }

    private void locationSpinnerMethod() {
        // Spinner element
        locationSpinner = (Spinner) myView.findViewById(R.id.spnrLocation);

        // Spinner click listener
        locationSpinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("Malibag");
        categories.add("Mirput");
        categories.add("Uttora");
        categories.add("Bonani");
        categories.add("Rampura");
        categories.add("Farmgate");
        categories.add("Gulshan");
        categories.add("Dhanmondi");
        categories.add("NewMarket");
        categories.add("Azimpur");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), R.layout.spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(R.layout.spinner_item);

        // attaching data adapter to spinner
        locationSpinner.setAdapter(dataAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        if (parent.getId() == R.id.spnrLocation) {
            String item = parent.getItemAtPosition(position).toString();

            Area = item;
            // Showing selected spinner item
           // Toast.makeText(parent.getContext(), " location Selected: " + item, Toast.LENGTH_LONG).show();
        }
        else if(parent.getId()==R.id.spnrSearchWorker_searchType){
            String item = parent.getItemAtPosition(position).toString();

            SearchType = item;
            if(SearchType=="Name"){
                etName.setEnabled(true);

            }
            else if(SearchType=="Job"){
                workerSpinner.setEnabled(true);

            }
            else  if(SearchType=="Area"){
               locationSpinner.setEnabled(true);
            }
            else{
                workerSpinner.setEnabled(true);
                locationSpinner.setEnabled(true);
            }
        }
        else {
            String item = parent.getItemAtPosition(position).toString();

            Job = item;
            // Showing selected spinner item
         //   Toast.makeText(parent.getContext(), " worktype Selected: " + item, Toast.LENGTH_LONG).show();
        }

    }

    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }
}
