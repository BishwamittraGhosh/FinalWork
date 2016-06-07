package com.example.vacuumtubee.finalapproach;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by wasif on 5/4/16.
 */
public class DBHelper extends SQLiteOpenHelper {

    private String TAG ="DBHelper";


    public static String DATABASE_NAME ="user.db";
    public static String TABLE_NAME = "logInTable";
    public static String USER_NAME = "username";
    public static  String USER_TYPE ="usertype";
    public static String USER_ID = "userID";

    public DBHelper(Context context){
        super(context,DATABASE_NAME,null,1);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE IF NOT EXISTS logInTable (userID INTEGER PRIMARY KEY , username TEXT, usertype TEXT )";
        db.execSQL(query);
        Log.d(TAG, "table created successfully");

    }

    public void insertTask(UserDatabase userDatabase){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValue = new ContentValues();

        contentValue.put(USER_NAME,userDatabase.username);
        contentValue.put(USER_TYPE, userDatabase.userType);
        contentValue.put(USER_ID, userDatabase.userId);

        long rowId = db.insert(TABLE_NAME,null,contentValue);

        if(rowId!=-1){
            Log.d(TAG,"row inserted");
        }
        else{
            Log.d(TAG,"row not inserted");
        }


    }


    public ArrayList<UserDatabase> retrieveData(){
        String query = "SELECT* FROM logInTable";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Log.d(TAG,"got id");
        return convertCursorToArraylist(cursor);

    }

    private ArrayList<UserDatabase> convertCursorToArraylist(Cursor cursor) {
        cursor.moveToFirst();

        ArrayList<UserDatabase> allItems = new ArrayList<UserDatabase>();
        while(cursor.isAfterLast()==false){
            int Id = cursor.getInt(cursor.getColumnIndex(USER_ID));
            String userId= String.valueOf(Id);
            String userName = cursor.getString(cursor.getColumnIndex(USER_NAME));
            String userType = cursor.getString(cursor.getColumnIndex(USER_TYPE));
            UserDatabase userDatabase= new UserDatabase(userType,userName, userId);
            allItems.add(userDatabase);

            cursor.moveToNext();
        }

        return  allItems;
    }

    public void deleteEntry(int rowId){
        SQLiteDatabase db = this.getWritableDatabase();
        int numRow = db.delete(TABLE_NAME,"userID = ? ",new String[]{rowId+""});

        Log.d(TAG,"delete hoise");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
