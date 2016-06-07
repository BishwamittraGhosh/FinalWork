package com.example.vacuumtubee.finalapproach;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Vacuum Tubee on 6/6/2016.
 */
public class SendMessage extends Fragment{
    View myView;
    ImageButton btn;
    String workerName, workerId;
    TextView tvReceiver;
    EditText etmsg;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.send_message, container, false);
        init();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //something
                String msg="To "+workerName+"\n"+etmsg.getText().toString()+"\n from "+Login.loginName;
                Calendar c = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String strDate = sdf.format(c.getTime());
                final ProgressDialog progressDialog=new ProgressDialog(myView.getContext());
                progressDialog.setIndeterminate(true);
                progressDialog.setMessage("Please wait...");
                progressDialog.show();
                Background background = new Background(getActivity().getApplicationContext());

                background.execute("Sendmsg", workerId, Login.loginId, msg, strDate);
                progressDialog.dismiss();

                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.content_frame
                                , new SearchWorker())
                        .commit();

            }
        });
        return myView;
    }

    private void init() {
        Bundle bundle = this.getArguments();
        workerName=bundle.getString("name");
        workerId=bundle.getString("id");
        tvReceiver=(TextView)myView.findViewById(R.id.tv_send_message_to);
        btn=(ImageButton)myView.findViewById(R.id.btn_sendMessage_send);
        tvReceiver.setText(workerName);
        etmsg=(EditText)myView.findViewById(R.id.etSend_mesage_txtMsg);
    }
}
