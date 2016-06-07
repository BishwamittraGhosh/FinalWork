package com.example.vacuumtubee.finalapproach;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Vacuum Tubee on 6/6/2016.
 */
public class UpdateAccount extends Fragment {
    View myView;
    String  name,password,loc,phn;
    EditText etname, etpassword, etphn, etlocation,etcnfrmpass;
    ImageButton btn;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.update_layout, container, false);

        Bundle bundle = this.getArguments();
//        int myInt = bundle.getInt(key, defaultValue);
        String Jsnstring= bundle.getString("Result");
        Log.d("background", "get res" + Jsnstring);
        try {
            // JSONObject jsonObject=new JSONObject(Jsnstring);
            JSONArray jsonArray=new JSONArray(Jsnstring);
            for(int i=0;i<jsonArray.length();i++){
                JSONObject item=jsonArray.getJSONObject(i);
                name=item.getString("Name");
                 password=item.getString("Password");
                 loc=item.getString("Address");
                 phn=item.getString("Phn");

                Log.d("background", name+password+loc+phn);
                //  items.add(new WorkerSearchResult(name,phn,loc,id));

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        init();


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return myView;
    }

    private void init() {
        etname = (EditText) myView.findViewById(R.id.et_update_name);
        etpassword = (EditText) myView.findViewById(R.id.et_update_password);
        etcnfrmpass= (EditText) myView.findViewById(R.id.et_update_confirm_password);
        etphn = (EditText) myView.findViewById(R.id.et_update_phone);
        etlocation = (EditText) myView.findViewById(R.id.et_update_location);
        etname.setText(name);
        etpassword.setText(password);
        //etcnfrmpass.setText(password);
        etphn.setText(phn);
        etlocation.setText(loc);

        btn=(ImageButton)myView.findViewById(R.id.btnUpdate_Update);




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
