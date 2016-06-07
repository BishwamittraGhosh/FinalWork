package com.example.vacuumtubee.finalapproach;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by NIT on 5/9/2016.
 */
public class Background extends AsyncTask<String, Void, String> {

    private ProgressDialog progressDialog;


    String EmpAddInfo_url,LogIn_url,Worker_Signup_url,Sendmsg_url,SearchMsg_url,Additional_info_url,Search_url,SearchReview_url;
    String AccountInfo_url,statistics_url;
    private String urlConnection;
    public AsyncResponse delegate = null;
    boolean isret;
    public static  boolean correct;
    public Background(Context context,AsyncResponse delegate) {
        this.context = context;
        urlConnection="background";
        this.delegate = delegate;
        isret=true;

    }

    public Background(Context context) {
        this.context = context;
        urlConnection="background";
        isret=false;
    }

    Context context;
    @Override
    protected void onPreExecute() {

//        progressDialog=new ProgressDialog(this.getActivity().getWindow().getContext());
//        progressDialog.setIndeterminate(true);
//        progressDialog.setMessage("Please wait...");
//        progressDialog.show();
       // EmpAddInfo_url="http://easyhire.site88.net/EmpAddInfo.php";
       // LogIn_url="http://easyhire.site88.net/login.php";
//        EmpAddInfo_url="http://10.0.2.2/EasyHire/EmpAddInfo.php";
  //      LogIn_url = "http://10.0.2.2/EasyHire/login.php";

        String ip="172.16.193.8:80";
        //String ip="vacuumtube.comli.com";
        EmpAddInfo_url="http://"+ip+"/EasyHire/EmpAddInfo.php";
        LogIn_url = "http://"+ip+"/EasyHire/login.php";
        Worker_Signup_url = "http://"+ip+"/EasyHire/WorkerAddinfo.php";
        Additional_info_url="http://"+ip+"/EasyHire/AdditionalInfo.php";
        Search_url="http://"+ip+"/EasyHire/Search.php";
        SearchReview_url="http://"+ip+"/EasyHire/SearchReview.php";
        Sendmsg_url="http://"+ip+"/EasyHire/Sendmsg.php";
        SearchMsg_url="http://"+ip+"/EasyHire/SearchMsg.php";
        AccountInfo_url="http://"+ip+"/EasyHire/AccountInfo.php";
        statistics_url="http://"+ip+"/EasyHire/Statistics.php";

    }

    @Override
    protected String doInBackground(String... params) {

        //Toast.makeText(context,"backgroud",Toast.LENGTH_SHORT).show();

        String type,name,password,phn,location;
        type=params[0];
        if(type.equals("SignUp")) {

            name = params[1];
            password = params[2];
            phn = params[3];
            location = params[4];
            try {
                URL url = new URL(EmpAddInfo_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String data_str= URLEncoder.encode("Emp_Name","UTF-8")+"="+URLEncoder.encode(name,"UTF-8")+"&"+
                        URLEncoder.encode("Emp_Password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8")+"&"+
                        URLEncoder.encode("Emp_MobileNo","UTF-8")+"="+URLEncoder.encode(phn,"UTF-8")+"&"+
                        URLEncoder.encode("Emp_Address","UTF-8")+"="+URLEncoder.encode(location,"UTF-8");
                bufferedWriter.write(data_str);
                bufferedWriter.flush();
                InputStream inputStream=httpURLConnection.getInputStream();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                Log.d(urlConnection,"input stream theke data asse");
                String response="";
                String line="";
                //Log.d(urlConnection,"response dekhtesi");
                while ((line=bufferedReader.readLine()) !=null){
                    response=response+line;

                }
                Log.d(urlConnection,response);
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
               // Log.d(urlConnection,"url connection Successful");
                return  "SignUp successfully";

            }
            catch (Exception e){
                Log.d(urlConnection,"url connection failure");
                e.printStackTrace();
                return "Not Succeeded";
            }
        }
        else if(type.equals("WorkerSignUp")) {

            name = params[1];
            password = params[2];
            phn = params[3];
            location = params[4];
            try {
                URL url = new URL(Worker_Signup_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String data_str= URLEncoder.encode("Worker_Name","UTF-8")+"="+URLEncoder.encode(name,"UTF-8")+"&"+
                        URLEncoder.encode("Worker_Password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8")+"&"+
                        URLEncoder.encode("Worker_MobileNo","UTF-8")+"="+URLEncoder.encode(phn,"UTF-8")+"&"+
                        URLEncoder.encode("Worker_Address","UTF-8")+"="+URLEncoder.encode(location,"UTF-8");
                bufferedWriter.write(data_str);
                bufferedWriter.flush();
                InputStream inputStream=httpURLConnection.getInputStream();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                Log.d(urlConnection,"input stream theke data asse");
                String response="";
                String line="";
                //Log.d(urlConnection,"response dekhtesi");
                while ((line=bufferedReader.readLine()) !=null){
                    response=response+line;

                }
                Log.d(urlConnection, response);
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                // Log.d(urlConnection,"url connection Successful");
                return  "SignUp successfully";

            }
            catch (Exception e){
                Log.d(urlConnection,"url connection failure");
                e.printStackTrace();
                return "Not Succeeded";
            }
        }
        else if(type.equals("Sendmsg")) {

            String workerid = params[1];
            String empid = params[2];
            String msg = params[3];
            String time = params[4];
            try {
                URL url = new URL(Sendmsg_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String data_str= URLEncoder.encode("Emp_Id","UTF-8")+"="+URLEncoder.encode(empid,"UTF-8")+"&"+
                        URLEncoder.encode("Worker_Id","UTF-8")+"="+URLEncoder.encode(workerid,"UTF-8")+"&"+
                        URLEncoder.encode("Msg","UTF-8")+"="+URLEncoder.encode(msg,"UTF-8")+"&"+
                        URLEncoder.encode("Time","UTF-8")+"="+URLEncoder.encode(time,"UTF-8");
                bufferedWriter.write(data_str);
                bufferedWriter.flush();
                InputStream inputStream=httpURLConnection.getInputStream();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                Log.d(urlConnection,"input stream theke data asse");
                String response="";
                String line="";
                //Log.d(urlConnection,"response dekhtesi");
                while ((line=bufferedReader.readLine()) !=null){
                    response=response+line;

                }
                Log.d(urlConnection, response);
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                // Log.d(urlConnection,"url connection Successful");
                return  "Msg Sent Successfully";

            }
            catch (Exception e){
                Log.d(urlConnection,"url connection failure");
                e.printStackTrace();
                return "Not Succeeded";
            }
        }
        else if(type.equals("Additional")) {

            name = params[1];
            password = params[2];
            String minsal = params[3];
            String area = params[4];
            String job=params[5];
            try {
                URL url = new URL(Additional_info_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String data_str= URLEncoder.encode("Worker_Name","UTF-8")+"="+URLEncoder.encode(name,"UTF-8")+"&"+
                        URLEncoder.encode("Worker_Password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8")+"&"+
                        URLEncoder.encode("Min_Sal","UTF-8")+"="+URLEncoder.encode(minsal,"UTF-8")+"&"+
                        URLEncoder.encode("Area_Name","UTF-8")+"="+URLEncoder.encode(area,"UTF-8")+"&"+
                        URLEncoder.encode("Job_Name","UTF-8")+"="+URLEncoder.encode(job,"UTF-8");
                bufferedWriter.write(data_str);
                bufferedWriter.flush();
                InputStream inputStream=httpURLConnection.getInputStream();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                Log.d(urlConnection,"input stream theke data asse");
                String response="";
                String line="";
                //Log.d(urlConnection,"response dekhtesi");
                while ((line=bufferedReader.readLine()) !=null){
                    response=response+line;

                }
                Log.d(urlConnection,response);
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                // Log.d(urlConnection,"url connection Successful");
                return  "Info Added";

            }
            catch (Exception e){
                Log.d(urlConnection,"url connection failure");
                e.printStackTrace();
                return "Not Succeeded";
            }
        }
        else if(type.equals("LogIn")){
            name = params[1];
            password=params[2];
            String Type=params[3];
            try {
                Log.i(urlConnection,"LogIn e dhukse");
                URL url = new URL(LogIn_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                Log.d(urlConnection, "url connection for _login done");
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String data_str= URLEncoder.encode("Emp_Name","UTF-8")+"="+URLEncoder.encode(name,"UTF-8")+"&"+
                        URLEncoder.encode("Type","UTF-8")+"="+URLEncoder.encode(Type,"UTF-8")+"&"+
                        URLEncoder.encode("Emp_Password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8");

                bufferedWriter.write(data_str);
                Log.d(urlConnection, "data has been written using buffer writter");
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                Log.d(urlConnection, "now here");
                InputStream inputStream=httpURLConnection.getInputStream();
                Log.d(urlConnection,"input stream theke data asse naki");
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                Log.d(urlConnection,"input stream theke data asse");
                String response="";
                String line="";
                //Log.d(urlConnection,"response dekhtesi");
                while ((line=bufferedReader.readLine()) !=null){
                    response=response+line;

                }
                Log.d(urlConnection,response);
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                Log.d(urlConnection, "con close korlam");
                //Toast.makeText(context,response,Toast.LENGTH_LONG).show();
                if(response.equals("not found")){
                    return  "LogIn Data Not Found";
                }
                else {
                    Login.loginId=response;
                    return  "LogIn Successful";
                }
            } catch (Exception e) {
               // Log.d(urlConnection,e.printStackTrace());
                Log.d(urlConnection, "exception khaisi"+e.getMessage());
                return  "Error";
            }

        }
        else if(type.equals("SearchReview")) {
           String Worker_Id=params[1];
            try {
                Log.i(urlConnection, "Search e dhukse");
                URL url = new URL(SearchReview_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                Log.d(urlConnection, "url connection for _login done");
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String data_str = URLEncoder.encode("Worker_Id", "UTF-8") + "=" + URLEncoder.encode(Worker_Id, "UTF-8");

                bufferedWriter.write(data_str);
                Log.d(urlConnection, "data has been written using buffer writter");
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                Log.d(urlConnection, "input stream theke data asse");
                String response = "";
                String line = "";
                //Log.d(urlConnection,"response dekhtesi");
                while ((line = bufferedReader.readLine()) != null) {
                    response = response + line;

                }
                // Log.d(urlConnection,response);
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                Log.d(urlConnection, "con close korlam");
                //Toast.makeText(context,response,Toast.LENGTH_LONG).show();
                /*if(response.equals("not found")){
                    return  "LogIn Data Not Found";
                }*/
                return response;
            } catch (Exception e) {
                // Log.d(urlConnection,e.printStackTrace());
                Log.d(urlConnection, "exception khaisi");
                return "Error";
            }
        }
        else if(type.equals("SearchMsg")) {
            String id=params[2];
            String Type=params[1];
            try {
                Log.i(urlConnection, "SearchMsg e dhukse");
                URL url = new URL(SearchMsg_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                Log.d(urlConnection, id+Type);
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String data_str = URLEncoder.encode("Type","UTF-8")+"="+URLEncoder.encode(Type,"UTF-8")+"&"+

                        URLEncoder.encode("Id", "UTF-8") + "=" + URLEncoder.encode(id, "UTF-8");

                bufferedWriter.write(data_str);
                Log.d(urlConnection, "data has been written using buffer writter");
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                Log.d(urlConnection, "input stream theke data asse");
                String response = "";
                String line = "";
                //Log.d(urlConnection,"response dekhtesi");
                while ((line = bufferedReader.readLine()) != null) {
                    response = response + line;

                }
                // Log.d(urlConnection,response);
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                InboxActivity.inboxstring=response;
                Log.d(urlConnection, "con close korlam");
                //Toast.makeText(context,response,Toast.LENGTH_LONG).show();
                /*if(response.equals("not found")){
                    return  "LogIn Data Not Found";
                }*/
                return response;
            } catch (Exception e) {
                // Log.d(urlConnection,e.printStackTrace());
                Log.d(urlConnection, "exception khaisi");
                return "Error";
            }
        }
        else if(type.equals("AccountInfo")) {
            String id=params[2];
            String Type=params[1];
            try {
                Log.i(urlConnection, "AccountInfo e dhukse");
                URL url = new URL(AccountInfo_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                Log.d(urlConnection, id+Type);
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String data_str = URLEncoder.encode("Type","UTF-8")+"="+URLEncoder.encode(Type,"UTF-8")+"&"+

                        URLEncoder.encode("Id", "UTF-8") + "=" + URLEncoder.encode(id, "UTF-8");

                bufferedWriter.write(data_str);
                Log.d(urlConnection, "data has been written using buffer writter");
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                Log.d(urlConnection, "input stream theke data asse");
                String response = "";
                String line = "";
                //Log.d(urlConnection,"response dekhtesi");
                while ((line = bufferedReader.readLine()) != null) {
                    response = response + line;

                }
                // Log.d(urlConnection,response);
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                InboxActivity.inboxstring=response;
                Log.d(urlConnection, "con close korlam");
                //Toast.makeText(context,response,Toast.LENGTH_LONG).show();
                /*if(response.equals("not found")){
                    return  "LogIn Data Not Found";
                }*/
                return response;
            } catch (Exception e) {
                // Log.d(urlConnection,e.printStackTrace());
                Log.d(urlConnection, "exception khaisi");
                return "Error";
            }
        }
        else if(type.equals("Statistics")) {

            try {
                Log.i(urlConnection, "Search e dhukse");
                URL url = new URL(statistics_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                Log.d(urlConnection, "url connection for _login done");
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                Log.d(urlConnection, "input stream theke data asse");
                String response = "";
                String line = "";
                //Log.d(urlConnection,"response dekhtesi");
                while ((line = bufferedReader.readLine()) != null) {
                    response = response + line;

                }
                // Log.d(urlConnection,response);
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                Log.d(urlConnection, "con close korlam");
                //Toast.makeText(context,response,Toast.LENGTH_LONG).show();
                /*if(response.equals("not found")){
                    return  "LogIn Data Not Found";
                }*/
                return response;
            } catch (Exception e) {
                // Log.d(urlConnection,e.printStackTrace());
                Log.d(urlConnection, "exception khaisi" + e.getMessage());
                return "Error";
            }
        }
        else if(type.equals("Search")){
            String Min_Sal=params[1];
            String Area = params[2];
            String Job=params[3];
            String Search_Type=params[4];
            String Worker_Name=params[5];
            try {
                Log.i(urlConnection,"Search e dhukse");
                URL url = new URL(Search_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                Log.d(urlConnection,"url connection for _login done");
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String data_str= URLEncoder.encode("Min_Sal","UTF-8")+"="+URLEncoder.encode(Min_Sal,"UTF-8")+"&"+
                        URLEncoder.encode("Area_Name","UTF-8")+"="+URLEncoder.encode(Area,"UTF-8")+"&"+
                        URLEncoder.encode("Worker_Name","UTF-8")+"="+URLEncoder.encode(Worker_Name,"UTF-8")+"&"+
                        URLEncoder.encode("Search_Type","UTF-8")+"="+URLEncoder.encode(Search_Type,"UTF-8")+"&"+
                        URLEncoder.encode("Job_Name","UTF-8")+"="+URLEncoder.encode(Job,"UTF-8");

                bufferedWriter.write(data_str);
                Log.d(urlConnection,"data has been written using buffer writter");
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream=httpURLConnection.getInputStream();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                Log.d(urlConnection,"input stream theke data asse");
                String response="";
                String line="";
                //Log.d(urlConnection,"response dekhtesi");
                while ((line=bufferedReader.readLine()) !=null){
                    response=response+line;

                }
               // Log.d(urlConnection,response);
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                Log.d(urlConnection,"con close korlam");
                //Toast.makeText(context,response,Toast.LENGTH_LONG).show();
                /*if(response.equals("not found")){
                    return  "LogIn Data Not Found";
                }*/
                return  response;
            } catch (Exception e) {
                // Log.d(urlConnection,e.printStackTrace());
                Log.d(urlConnection,"exception khaisi"+e.getMessage());
                return  "Error";
            }


        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String result) {
//        progressDialog.dismiss();
        if(result.length()<30)   Toast.makeText(context,result,Toast.LENGTH_SHORT).show();

       if(isret) delegate.processFinish(result);
        Log.d(urlConnection,"Ret hoitese "+result);

    }



}

interface AsyncResponse {
    void processFinish(String output);
}
