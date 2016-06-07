package com.example.vacuumtubee.finalapproach;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;

import java.util.ArrayList;

public class Login extends AppCompatActivity implements  AsyncResponse {

    ImageButton btnSignUp,btnLogin;
    EditText etname,etpassword;
    public static int loginType=0;
    public static String loginId="10";
    public static String loginName="tripto";
    ArrayList<UserDatabase> items = new ArrayList<UserDatabase>();
    DBHelper helper;
    String type;
    private String TAG ="LogIn";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.login);
        init();
       /* ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if(networkInfo==null ||  !networkInfo.isConnected() ){
            Toast.makeText(getApplicationContext(),"No Internet",Toast.LENGTH_SHORT).show();
            btnLogin.setEnabled(false);
        }*/
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SignUp.class));

            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name,password;
                name=etname.getText().toString();
                password=etpassword.getText().toString();
                final ProgressDialog progressDialog=new ProgressDialog(Login.this);
                progressDialog.setIndeterminate(true);
                progressDialog.setMessage("Please wait...");
                progressDialog.show();
                Background background = (Background) new Background(getApplicationContext(), new AsyncResponse() {
                    @Override
                    public void processFinish(String output) {
                        progressDialog.dismiss();
                        Log.d("background","rsponse from asyn "+output);
                        loginName=name;
                       if(output.equals("LogIn Successful")) {
                           Log.d(TAG, "before insert from login ");
                           helper.insertTask(new UserDatabase(type, name, "1"));

                           items.clear();
                           items.addAll(helper.retrieveData());

                           Log.d(TAG, "after insert from login    "+items.size());
                           startActivity(new Intent(getApplicationContext(), MainActivity.class));
                       }
                        else {
                           etname.setError("Try Again");
                           etpassword.setError("Wrong pass");
                       }
                    }
                }).execute("LogIn",name,password,type);
               // finish();

            }
        });

    }
    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.rBtn_login_employer:
                if (checked)
                    loginType=1;  //EMP
                    type="Emp";

                break;
            case R.id.rBtn_login_worker:
                if (checked)
                    loginType=2; //WORKER
                    type="Worker";
                break;
        }
    }

    private void init() {

        etname = (EditText) findViewById(R.id.etName);
        etpassword = (EditText) findViewById(R.id.etPassword);
        btnSignUp= (ImageButton)findViewById(R.id.btnLogin_singup);
        btnLogin= (ImageButton)findViewById(R.id.btnLogin_Login);
        helper = new DBHelper(this);
        Log.d(TAG, "before retrieve");
        items.addAll(helper.retrieveData());

        Log.d(TAG, "after retrieve "+items.size()+" ");

        if(items.size()>0){
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
        }

    }

    @Override
    public void processFinish(String output) {

    }
}
