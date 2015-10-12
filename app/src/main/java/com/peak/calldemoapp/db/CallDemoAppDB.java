package com.peak.calldemoapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.peak.calldemoapp.model.CallInfo;
import com.peak.calldemoapp.model.CallingCard;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ly on 10/12/2015.
 */
public class CallDemoAppDB {

    /**
     * 数据库名
     */
    public  static final String DB_NAME="call_demo_app";

    /**
     * 数据库版本
     */
    public static final int VERSION=1;

    private static CallDemoAppDB callDemoAppDB;

    private SQLiteDatabase db;

    /**
     * 将构造方法私有化
     */
    private CallDemoAppDB(Context context){
        CallDemoAppOpenHelper dbHelper=new CallDemoAppOpenHelper(context,
                DB_NAME,null,VERSION);
        db=dbHelper.getWritableDatabase();
    }

    /**
     * 获取CallDemoAppDB的实例
     */
    public synchronized static CallDemoAppDB getInstance(Context context){

        if(callDemoAppDB==null){
            callDemoAppDB=new CallDemoAppDB(context);
        }

        return callDemoAppDB;
    }

    /**
     * 将CallInfo实例存储到数据库
     */
    public void saveCallInfo(CallInfo callInfo){
        if(callInfo!=null){
            ContentValues values=new ContentValues();
            values.put("msgtype",callInfo.getMsgType());
            values.put("time",callInfo.getTime());
            values.put("fromid",callInfo.getFromID());
            values.put("toid",callInfo.getToID());
            values.put("name",callInfo.getName());
            values.put("phone",callInfo.getPhone());
            values.put("company",callInfo.getCompany());
            values.put("position",callInfo.getPosition());
            values.put("longitude",callInfo.getLongitude());
            values.put("latitude",callInfo.getLatitude());
            values.put("sms",callInfo.getSms());
            db.insert("CallInfo",null,values);
        }
    }

    /**
     * 从数据库读取所有CallInfo的信息
     */
    public List<CallInfo> loadCallInfo(){
        List<CallInfo> list=new ArrayList<CallInfo>();
        Cursor cursor=db
                .query("CallInfo",null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                CallInfo callInfo = new CallInfo();
                callInfo.setId(cursor.getInt(cursor.getColumnIndex("id")));
                callInfo.setMsgType(cursor.getString(cursor
                        .getColumnIndex("msgtype")));
                callInfo.setTime(cursor.getString(cursor
                        .getColumnIndex("time")));
                callInfo.setFromID(cursor.getString(cursor
                        .getColumnIndex("fromid")));
                callInfo.setToID(cursor.getString(cursor
                        .getColumnIndex("toid")));
                callInfo.setName(cursor.getString(cursor
                        .getColumnIndex("name")));
                callInfo.setPhone(cursor.getString(cursor
                        .getColumnIndex("phone")));
                callInfo.setCompany(cursor.getString(cursor
                        .getColumnIndex("company")));
                callInfo.setPosition(cursor.getString(cursor
                        .getColumnIndex("position")));
                callInfo.setLongitude(cursor.getString(cursor
                        .getColumnIndex("longitude")));
                callInfo.setLatitude(cursor.getString(cursor
                        .getColumnIndex("latitude")));
                callInfo.setSms(cursor.getString(cursor
                        .getColumnIndex("sms")));

                list.add(callInfo);

            }while(cursor.moveToNext());
        }
        if (cursor!=null){
            cursor.close();
        }

        return list;
    }

    /**
     * 将CallingCard实例存储到数据库
     */
    public void saveCallingCard(CallingCard callingCard){
        if (callingCard!=null){
            ContentValues values=new ContentValues();
            values.put("name",callingCard.getName());
            values.put("phone",callingCard.getPhone());
            values.put("company",callingCard.getCompany());
            values.put("position",callingCard.getPosition());
            db.insert("CallingCard", null, values);
        }
    }

    /**
     * 从数据库读取所有CallingCard的信息
     */
    public List<CallingCard> loadCallingCards(){

        List<CallingCard> list=new ArrayList<CallingCard>();
        Cursor cursor=db
                .query("CallingCard",null,null,null,null,null,null);

        if (cursor.moveToFirst()){

            do{
                CallingCard callingCard=new CallingCard();
                callingCard.setId(cursor.getInt(cursor.getColumnIndex("id")));
                callingCard.setName(cursor.getString(cursor
                        .getColumnIndex("name")));
                callingCard.setPhone(cursor.getString(cursor
                        .getColumnIndex("phone")));
                callingCard.setCompany(cursor.getString(cursor
                        .getColumnIndex("company")));
                callingCard.setPosition(cursor.getString(cursor
                        .getColumnIndex("position")));

                list.add(callingCard);

            }while(cursor.moveToNext());





        }

        if (cursor!=null){
            cursor.close();
        }

        return list;

    }


}
