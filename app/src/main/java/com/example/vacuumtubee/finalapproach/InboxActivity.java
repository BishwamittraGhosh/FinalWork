package com.example.vacuumtubee.finalapproach;

import android.app.Fragment;
import android.app.FragmentManager;
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
import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Vacuum Tubee on 6/6/2016.
 */
public class InboxActivity extends Fragment {
    public  static  String inboxstring="";
    View myView;
    ListView lvList;
    ArrayList<MsgHepler> items = new ArrayList<MsgHepler>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.inbox_layout, container, false);

        Bundle bundle = this.getArguments();
//        int myInt = bundle.getInt(key, defaultValue);
        String Jsnstring= bundle.getString("Result");
        Log.d("background", "get res"+Jsnstring);

        try {
            // JSONObject jsonObject=new JSONObject(Jsnstring);
            JSONArray jsonArray=new JSONArray(Jsnstring);
            for(int i=0;i<jsonArray.length();i++){
                JSONObject item=jsonArray.getJSONObject(i);
                String msg=item.getString("Msg");
                String time=item.getString("Time");
                String id=item.getString("Sender_Id");
                String name=item.getString("Sender_Name");
                MsgHepler ob= new MsgHepler(time,msg,name,id);
                items.add(ob);
                ob.toString();

                //  items.add(new WorkerSearchResult(name,phn,loc,id));

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

       // items.add(new MsgHepler("12/12/12","You have got a job","1","2"));
        lvList = (ListView) myView.findViewById(R.id.lv_inbox_listview);
        final AmaderAdapter adpt = new AmaderAdapter();
        lvList.setAdapter(adpt);



        lvList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        return myView;
    }




    public class AmaderAdapter extends ArrayAdapter<MsgHepler> {

        String id;

        public AmaderAdapter() {
            super(getActivity().getApplicationContext(), R.layout.msg_list_layout, items);
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View amaderView = convertView;
            if (amaderView == null) {
                amaderView = getActivity().getLayoutInflater().inflate(R.layout.msg_list_layout, parent, false);
            }

            TextView t1 = (TextView) amaderView.findViewById(R.id.tv_msg_list_msg);
            TextView t2 = (TextView) amaderView.findViewById(R.id.tv_msg_list_date);

            ImageButton btnReply = (ImageButton) amaderView.findViewById(R.id.btn_msg_list_reply);

            btnReply.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //async task
                }
            });


            t1.setText(items.get(position).message);
            t2.setText(items.get(position).date);
            return amaderView;
        }
    }
}
