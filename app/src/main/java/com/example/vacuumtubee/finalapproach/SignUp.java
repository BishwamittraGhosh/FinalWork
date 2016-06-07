package com.example.vacuumtubee.finalapproach;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;

public class SignUp extends AppCompatActivity {

    ImageButton btnSignUp;
    EditText etname, etpassword, etphn, etlocation,etcnfrmpass;
    String name, password, phn, location;
    RadioButton rbemp,rbworker;
    int pageno;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    Intent nextActivity;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        init();
        /*ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if(networkInfo==null ||  !networkInfo.isConnected() ){
            Toast.makeText(getApplicationContext(),"No Internet",Toast.LENGTH_SHORT).show();
            btnSignUp.setEnabled(false);
        }*/
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=etname.getText().toString();
                String password=etpassword.getText().toString();
                String confirmPass=etcnfrmpass.getText().toString();
                SignUpValidate signUpValidate=new SignUpValidate(name, password, confirmPass);

                if (validate()) {
                    Log.d("background","validate true ");
                    name = etname.getText().toString();
                    password = etpassword.getText().toString();
                    phn = etphn.getText().toString();
                    location = etlocation.getText().toString();
                    nextActivity.putExtra("name",name);
                    nextActivity.putExtra("password",password);
                    nextActivity.putExtra("phn", phn);
                    nextActivity.putExtra("location", location);
                    if (pageno==1) {
                        Log.d("background", "Emp Login ");
                        final ProgressDialog progressDialog=new ProgressDialog(SignUp.this);
                        progressDialog.setIndeterminate(true);
                        progressDialog.setMessage("Please wait...");
                        progressDialog.show();
                        Background background = new Background(getApplicationContext());

                        background.execute("SignUp", name, password, phn, location);
                        Log.d("background", "backgroun execute hoise ");
                        progressDialog.dismiss();
                        finish();

                    }
                    else if(pageno==2){
                        final ProgressDialog progressDialog=new ProgressDialog(SignUp.this);
                        progressDialog.setIndeterminate(true);
                        progressDialog.setMessage("Please wait...");
                        progressDialog.show();
                        Background background = new Background(getApplicationContext());

                        background.execute("WorkerSignUp", name, password, phn, location);
                        Log.d("background","backgroun execute hoise ");
                        progressDialog.dismiss();
                        finish();
                    }
                    Log.d("background","new activity te jaitese ");
                    startActivity(nextActivity);
                }

                else Log.d("background","validate false");


            }
        });


    }

    private void init() {
        etname = (EditText) findViewById(R.id.etName);
        etpassword = (EditText) findViewById(R.id.etPassword);
        etcnfrmpass= (EditText) findViewById(R.id.etConfirmPassword);
        etphn = (EditText) findViewById(R.id.etPhone);
        etlocation = (EditText) findViewById(R.id.etSignUpLocation);
        btnSignUp = (ImageButton) findViewById(R.id.btnSignup_signup);
        rbemp= (RadioButton) findViewById(R.id.rBtn_signup_employer);
        rbemp= (RadioButton) findViewById(R.id.rBtn_signup_employer);


    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();
        name = etname.getText().toString();
        password = etpassword.getText().toString();
        phn = etphn.getText().toString();
        location = etlocation.getText().toString();
        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.rBtn_signup_employer:
                if (checked)
                    // Pirates are the best
                    nextActivity=new Intent(getApplicationContext(), MainActivity.class);
                    pageno=1;
                    break;
            case R.id.rBtn_signup_worker:
                if (checked)
                    // Ninjas rule
                    nextActivity=new Intent(getApplicationContext(), WorkerDetails.class);
                   // nextActivity=new Intent(getApplicationContext(), MainPage.class);
                    pageno=2;
                    break;
        }
    }
    public  boolean validate(){
        boolean ret=true;
        //Log.d("background","validate e dhukse");
        if(etpassword.getText().toString().length()<4){
            etpassword.setError("password atleast 4 characters");
            ret=false;
        }
        //Log.d("background","passwordcorrect");
        if(!etpassword.getText().toString().equals(etcnfrmpass.getText().toString())){
            etcnfrmpass.setError("password must be equal");
        }
        //Log.d("background","confm password correct");
        if(etname.getText().toString().length()==0){
            ret=false;
            etname.setError("Name Cant be blank");
        }
        //Log.d("background","namecorrct");
        /*if((!rbemp.isChecked()  && !rbworker.isChecked() ) || (rbemp.isChecked() && rbworker.isChecked() )  ){
            ret=false;
        //    Toast.makeText(getApplicationContext(),"Select One",Toast.LENGTH_SHORT).show();
        }*/
        return  ret;

    }
}



