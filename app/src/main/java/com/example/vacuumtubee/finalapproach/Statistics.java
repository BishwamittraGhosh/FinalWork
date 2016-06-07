package com.example.vacuumtubee.finalapproach;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Vacuum Tubee on 6/7/2016.
 */
public class Statistics extends Fragment {
    View myView;

    TextView tvName[] = new TextView[5];
    TextView tvValue[] = new TextView[5];
    TextView tvheader;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.statistics_layout, container, false);
        init();


        Bundle bundle = this.getArguments();
//        int myInt = bundle.getInt(key, defaultValue);
        String Jsnstring= bundle.getString("Result");
        String header=bundle.getString("Header");
        int value=bundle.getInt("Value");
        tvheader.setText(header);
        try {
            // JSONObject jsonObject=new JSONObject(Jsnstring);
            JSONArray jsonArray=new JSONArray(Jsnstring);
            for(int i=0;i<5;i++){
                JSONObject item=jsonArray.getJSONObject(value*5+i);
                String name=item.getString("Name");
                String val=item.getString("Value");
                tvName[i].setText(name);
                tvValue[i].setText(val);

                //  items.add(new WorkerSearchResult(name,phn,loc,id));

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return myView;
    }

    private void init() {
        tvheader = (TextView) myView.findViewById(R.id.tv_stat_header);
        tvName[0] = (TextView) myView.findViewById(R.id.tv_stat_name1);
        tvName[1] = (TextView) myView.findViewById(R.id.tv_stat_name2);
        tvName[2] = (TextView) myView.findViewById(R.id.tv_stat_name3);
        tvName[3] = (TextView) myView.findViewById(R.id.tv_stat_name4);
        tvName[4] = (TextView) myView.findViewById(R.id.tv_stat_name5);
        tvValue[0] = (TextView) myView.findViewById(R.id.tv_stat_value1);
        tvValue[1] = (TextView) myView.findViewById(R.id.tv_stat_value2);
        tvValue[2] = (TextView) myView.findViewById(R.id.tv_stat_value3);
        tvValue[3] = (TextView) myView.findViewById(R.id.tv_stat_value4);
        tvValue[4] = (TextView) myView.findViewById(R.id.tv_stat_value5);

    }

}
