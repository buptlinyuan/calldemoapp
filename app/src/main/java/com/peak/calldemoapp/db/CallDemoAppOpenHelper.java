package com.peak.calldemoapp.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ly on 9/16/2015.
 */
public class CallDemoAppOpenHelper extends SQLiteOpenHelper{

    /**
     * 名片（CallingCard）建表语句
     */
    public static final String CREATE_CALLINGCARD="create table CallingCard("
            +"id integer primary key autoincrement,"
            +"name text,"
            +"phone text,"
            +"company text"
            +"position text)";

    /**
     * 呼叫信息（CallInfo）建表语句
     */
    public static final String CREATE_CALLINFO="create table CallInfo("
            +"id integer primary key autoincrement,"
            +"msgtype text,"
            +"time text,"
            +"fromid text,"
            +"toid text"

            +"name text,"
            +"phone text,"
            +"company text"
            +"position text"

            +"longitude text"
            +"latitude text"
            +"sms text)";





    public CallDemoAppOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);

    }

    @Override
    public void onCreate(SQLiteDatabase db){

        db.execSQL(CREATE_CALLINFO);
        db.execSQL(CREATE_CALLINGCARD);
    }

    @Override
    public  void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

        switch(oldVersion){
            case 1:
                db.execSQL(CREATE_CALLINGCARD);
            case 2:

            default:

        }
    }


}
