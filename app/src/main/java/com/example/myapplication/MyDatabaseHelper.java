package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    private Context context;
    private static final String DATABASE_NAME="Second.db";
    private static final int DATABASE_VERSION=1;
    private static final String TABLE_NAME="Second";
    private static final String COLUMN_ID="_id";
    private static final String COLUMN_TYPE="Type";
    private static final String COLUMN_TITLE="Title";
    private static final String COLUMN_Description="Description";
    private static final String Date="Date";
    private static final String Time="Time";

    public MyDatabaseHelper(@Nullable Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query=
                "CREATE TABLE " + TABLE_NAME+
                        " ("+ COLUMN_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                        COLUMN_TYPE+" TEXT, "+
                        COLUMN_TITLE+" TEXT, "+
                        COLUMN_Description + " TEXT, "+
                        Date + " TEXT, "+
                        Time+ " TEXT); ";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);
    }

    void addEvent(String title, String desc, String dateNtime,String type,String time){
        Log.d("hox","47x");
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();

        cv.put(COLUMN_TITLE,title);
        cv.put(COLUMN_TYPE,type);
        cv.put(Date,dateNtime);
        cv.put(Time,time);
        cv.put(COLUMN_Description,desc);
        long result=db.insert(TABLE_NAME,null,cv);
        Log.d("hox","56x");
        if (result==-1){
            Log.d("hox", String.valueOf(result));
            Toast.makeText(context,"Failed",Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readAllData(){
        String query = "SELECT * FROM "+ TABLE_NAME;
        SQLiteDatabase db=this.getReadableDatabase();

        Cursor cursor=null;
        if(db!=null){
            cursor=db.rawQuery(query,null);
        }
        return cursor;

    }

    public void delete(String row_id){
        System.out.println("htg2");
        SQLiteDatabase db=this.getWritableDatabase();
        long result=db.delete(TABLE_NAME,"_id=?",new String[]{row_id});
        if(result==-1){
            System.out.println("htg3");

        }else{
            System.out.println("htg");
        }
    }

}
