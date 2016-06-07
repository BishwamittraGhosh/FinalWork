package com.example.vacuumtubee.finalapproach;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;

public class WorkerDetails extends AppCompatActivity {

    String name,password,phn,location,minSal;
    String Job,Area;
    EditText minsal;
    CheckBox cbjob,cbloc;
    Intent intent;
    ImageButton btnsubmit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.worker_details);
        intent=getIntent();
        Log.d("background", "page Load hoise");
        init();
        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Additional",Job+"\n"+Area);
                minSal=minsal.getText().toString();

                final ProgressDialog progressDialog=new ProgressDialog(WorkerDetails.this);
                progressDialog.setIndeterminate(true);
                progressDialog.setMessage("Please wait...");
                progressDialog.show();
                Background background = new Background(getApplicationContext());

                background.execute("Additional", name, password, minSal,Area,Job);
                Log.d("background", "background execute hoise ");
                finish();

                progressDialog.dismiss();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));

            }
        });
    }


    public  void  init(){
        btnsubmit=(ImageButton)findViewById(R.id.btn_worker_details_submit);
        name=intent.getStringExtra("name");
        password=intent.getStringExtra("password");
        phn=intent.getStringExtra("phn");
        location=intent.getStringExtra("location");
        minsal= (EditText) findViewById(R.id.etWorkerDetails_min_salary);
        Log.d("background", name + password + location + phn);
        Job="";
        Area="";
    }
    public  void onWorkingAreaCheckboxClicked(View view){
        boolean checked = ((CheckBox) view).isChecked();

        switch(view.getId()) {
            case R.id.cb_worker_details_workingArea_azimpur:
                if (checked)
                    // Pirates are the best
                    Area=Area+ ((CheckBox)findViewById( R.id.cb_worker_details_workingArea_azimpur)).getText()+" ";
                break;
            case R.id.cb_worker_details_workingArea_dhanmondi:
                if (checked)
                    // Pirates are the best
                    Area=Area+ ((CheckBox)findViewById( R.id.cb_worker_details_workingArea_dhanmondi)).getText()+" ";
                break;
            case R.id.cb_worker_details_workingArea_bonani:
                if (checked)
                    // Pirates are the best
                    Area=Area+ ((CheckBox)findViewById( R.id.cb_worker_details_workingArea_bonani)).getText()+" ";
                break;
            case R.id.cb_worker_details_workingArea_uttara:
                if (checked)
                    // Pirates are the best
                    Area=Area+ ((CheckBox)findViewById( R.id.cb_worker_details_workingArea_uttara)).getText()+" ";
                break;
            case R.id.cb_worker_details_workingArea_gulshan:
                if (checked)
                    // Pirates are the best
                    Area=Area+ ((CheckBox)findViewById( R.id.cb_worker_details_workingArea_gulshan)).getText()+" ";
                break;
            case R.id.cb_worker_details_workingArea_newmarker:
                if (checked)
                    // Pirates are the best
                    Area=Area+ ((CheckBox)findViewById( R.id.cb_worker_details_workingArea_newmarker)).getText()+" ";
                break;
            case R.id.cb_worker_details_workingArea_mirpur:
                if (checked)
                    // Pirates are the best
                    Area=Area+ ((CheckBox)findViewById( R.id.cb_worker_details_workingArea_mirpur)).getText()+" ";
                break;
            case R.id.cb_worker_details_workingArea_malibag:
                if (checked)
                    // Pirates are the best
                    Area=Area+ ((CheckBox)findViewById( R.id.cb_worker_details_workingArea_malibag)).getText()+" ";
                break;
            case R.id.cb_worker_details_workingArea_farmgate:
                if (checked)
                    // Pirates are the best
                    Area=Area+ ((CheckBox)findViewById( R.id.cb_worker_details_workingArea_farmgate)).getText()+" ";
                break;
        }

    }
    public  void  onCheckboxClicked(View view){
        boolean checked = ((CheckBox) view).isChecked();
        switch(view.getId()) {
            case R.id.cb_worker_details_electrician:
                if (checked)
                    // Pirates are the best
                    Job=Job+((CheckBox)findViewById( R.id.cb_worker_details_electrician)).getText()+" ";
                    break;
            case R.id.cb_worker_details_Gardener:
                if (checked)
                    // Pirates are the best
                    Job=Job+((CheckBox)findViewById( R.id.cb_worker_details_Gardener)).getText()+" ";
                break;
            case R.id.cb_worker_details_gateman:
                if (checked)
                    // Pirates are the best
                    Job=Job+((CheckBox)findViewById( R.id.cb_worker_details_gateman)).getText()+" ";
                break;
            case R.id.cb_worker_details_Servent:
                if (checked)
                    // Pirates are the best
                    Job=Job+((CheckBox)findViewById( R.id.cb_worker_details_Servent)).getText()+" ";
                break;
            case R.id.cb_worker_details_Sweeper:
                if (checked)
                    // Pirates are the best
                    Job=Job+((CheckBox)findViewById( R.id.cb_worker_details_Sweeper)).getText()+" ";
                break;
            case R.id.cb_worker_details_watermechanic:
                if (checked)
                    // Pirates are the best
                    Job=Job+((CheckBox)findViewById( R.id.cb_worker_details_watermechanic)).getText()+" ";
                break;
        }
    }


}
