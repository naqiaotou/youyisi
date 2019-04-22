package com.yus.taobaoui.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public final static String DbName="youyisi";
    public DatabaseHelper(Context context){
        super(context,DbName,null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String strsql="create table if not exists shiti(ID text,password text,sex text,height text,weight text," +
                "jiankuan text,xiongwei text,yaowei text,tunwei text,tuiwei text,tuichang text);";
        db.execSQL(strsql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
