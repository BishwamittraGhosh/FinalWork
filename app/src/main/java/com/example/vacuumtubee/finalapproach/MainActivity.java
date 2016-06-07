package com.example.vacuumtubee.finalapproach;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private Menu menu;
    DBHelper dbHelper;
    Fragment fragment;
    Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


       // Login.loginType

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        menu = navigationView.getMenu();

        navigationView.setNavigationItemSelectedListener(this);

        init();
    }

    private void init() {
        dbHelper=new DBHelper(this);
        ArrayList<UserDatabase> items = new ArrayList<UserDatabase>();
        items.clear();
        items.addAll(dbHelper.retrieveData());
        if(items.size()==0){
            MenuItem nav_log_out = menu.findItem(R.id.nav_log_out);
            nav_log_out.setTitle("Log in");
        }
        else{
            MenuItem nav_log_out = menu.findItem(R.id.nav_log_out);
            nav_log_out.setTitle("Log out");
        }
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.content_frame
                        , new SearchWorker())
                .commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentManager fragmentManager = getFragmentManager();


        if (id == R.id.nav_search_worker) {
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame
                            , new SearchWorker())
                    .commit();
            // Handle the camera action
        } else if (id == R.id.nav_inbox){
            String type;
            if(Login.loginType==1 || Login.loginType==0)type="Emp";
            else type="Worker";


            fragment = new InboxActivity();
            bundle = new Bundle();
            final ProgressDialog progressDialog=new ProgressDialog(MainActivity.this);
            progressDialog.setIndeterminate(true);
            progressDialog.setMessage("Please wait...");
            progressDialog.show();
            Background background = (Background) new Background(getApplicationContext(), new AsyncResponse() {
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
            }). execute("SearchMsg", type, Login.loginId);
        }
        else if (id == R.id.nav_updateAccount){
            String type;
            if(Login.loginType==1 || Login.loginType==0)type="Emp";
            else type="Worker";
            final ProgressDialog progressDialog=new ProgressDialog(MainActivity.this);
            progressDialog.setIndeterminate(true);
            progressDialog.setMessage("Please wait...");
            progressDialog.show();

            fragment = new UpdateAccount();
            bundle = new Bundle();

            Background background = (Background) new Background(getApplicationContext(), new AsyncResponse() {
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
            }). execute("AccountInfo", type, Login.loginId);
        }
        else if (id == R.id.nav_log_out){
            ArrayList<UserDatabase> items = new ArrayList<UserDatabase>();
            items.clear();
            items.addAll(dbHelper.retrieveData());
            if(items.size()==0){
                startActivity(new Intent(getApplicationContext(),Login.class));
            }


            Log.d("mainactivity", "before delete");
            dbHelper.deleteEntry(1);
            Log.d("mainactivity", "after delete");
            Login.loginType=0;
            MenuItem nav_log_out = menu.findItem(R.id.nav_log_out);
            nav_log_out.setTitle("Log in");

        }

        else if (id == R.id.nav_most_job_working){
            fragment = new Statistics();
            bundle = new Bundle();
            final ProgressDialog progressDialog=new ProgressDialog(MainActivity.this);
            progressDialog.setIndeterminate(true);
            progressDialog.setMessage("Please wait...");
            progressDialog.show();
            Background background = (Background) new Background(getApplicationContext(), new AsyncResponse() {
                @Override
                public void processFinish(String output) {
                    progressDialog.dismiss();
                    Log.d("background", "rsponse from asyn\n" + output);
                    //  nextActivity.putExtra("Result",output);
                    //  startActivity(nextActivity);
                    bundle.putString("Result", output);
                    bundle.putString("Header", "Most no of worker in  Job");
                    bundle.putInt("Value",4);
                    fragment.setArguments(bundle);
                    FragmentManager fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction()
                            .replace(R.id.content_frame
                                    , fragment)
                            .commit();

                }
            }). execute("Statistics");

        }
        else if (id == R.id.nav_rated_worker){
            fragment = new Statistics();
            bundle = new Bundle();
            final ProgressDialog progressDialog=new ProgressDialog(MainActivity.this);
            progressDialog.setIndeterminate(true);
            progressDialog.setMessage("Please wait...");
            progressDialog.show();
            Background background = (Background) new Background(getApplicationContext(), new AsyncResponse() {
                @Override
                public void processFinish(String output) {
                    progressDialog.dismiss();
                    Log.d("background", "rsponse from asyn\n" + output);
                    //  nextActivity.putExtra("Result",output);
                    //  startActivity(nextActivity);
                    bundle.putString("Result", output);
                    bundle.putString("Header", "Most Rated Worker");
                    bundle.putInt("Value",2);
                    fragment.setArguments(bundle);
                    FragmentManager fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction()
                            .replace(R.id.content_frame
                                    , fragment)
                            .commit();

                }
            }). execute("Statistics");

        }
        else if (id == R.id.nav_job_in_area){
            fragment = new Statistics();
            bundle = new Bundle();
            final ProgressDialog progressDialog=new ProgressDialog(MainActivity.this);
            progressDialog.setIndeterminate(true);
            progressDialog.setMessage("Please wait...");
            progressDialog.show();
            Background background = (Background) new Background(getApplicationContext(), new AsyncResponse() {
                @Override
                public void processFinish(String output) {
                    progressDialog.dismiss();
                    Log.d("background", "rsponse from asyn\n" + output);
                    //  nextActivity.putExtra("Result",output);
                    //  startActivity(nextActivity);
                    bundle.putString("Result", output);
                    bundle.putString("Header", "Most no of worker in  area");
                    bundle.putInt("Value",3);
                    fragment.setArguments(bundle);
                    FragmentManager fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction()
                            .replace(R.id.content_frame
                                    , fragment)
                            .commit();

                }
            }). execute("Statistics");

        }
        else if (id == R.id.nav_search_job){
            fragment = new Statistics();
            bundle = new Bundle();
            final ProgressDialog progressDialog=new ProgressDialog(MainActivity.this);
            progressDialog.setIndeterminate(true);
            progressDialog.setMessage("Please wait...");
            progressDialog.show();
            Background background = (Background) new Background(getApplicationContext(), new AsyncResponse() {
                @Override
                public void processFinish(String output) {
                    progressDialog.dismiss();
                    Log.d("background", "rsponse from asyn\n" + output);
                    //  nextActivity.putExtra("Result",output);
                    //  startActivity(nextActivity);
                    bundle.putString("Result", output);
                    bundle.putString("Header", "Most Searched Job");
                    bundle.putInt("Value",1);
                    fragment.setArguments(bundle);
                    FragmentManager fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction()
                            .replace(R.id.content_frame
                                    , fragment)
                            .commit();

                }
            }). execute("Statistics");

        }
        else if (id == R.id.nav_Search_area){
            fragment = new Statistics();
            bundle = new Bundle();
            final ProgressDialog progressDialog=new ProgressDialog(MainActivity.this);
            progressDialog.setIndeterminate(true);
            progressDialog.setMessage("Please wait...");
            progressDialog.show();
            Background background = (Background) new Background(getApplicationContext(), new AsyncResponse() {
                @Override
                public void processFinish(String output) {
                    Log.d("background", "rsponse from asyn\n" + output);
                    //  nextActivity.putExtra("Result",output);
                    //  startActivity(nextActivity);
                    progressDialog.dismiss();
                    bundle.putString("Result", output);
                    bundle.putString("Header", "Most Searched Area");
                    bundle.putInt("Value",0);
                    fragment.setArguments(bundle);
                    FragmentManager fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction()
                            .replace(R.id.content_frame
                                    , fragment)
                            .commit();

                }
            }). execute("Statistics");

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
