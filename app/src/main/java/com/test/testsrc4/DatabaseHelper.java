package com.test.testsrc4;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Srh Dp on 30-Jun-17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {


    public static final String DBNAME="sample.sqlite";
    public static final String DBLOCATION="/data/data/com.test.testsrc4/databases/";
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public DatabaseHelper(Context context){
        super(context,DBNAME,null,1);
        this.mContext=context;
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void openDatabase(){
        String dbPath=mContext.getDatabasePath(DBNAME).getPath();
        if(mDatabase!=null && mDatabase.isOpen()){
            return;
        }
        mDatabase=SQLiteDatabase.openDatabase(dbPath,null,SQLiteDatabase.OPEN_READWRITE);
    }
    public void closeDatabase(){
        if(mDatabase !=null){
            mDatabase.close();
        }
    }

    public List<LotteryModel> getListLottery(String lotSearch){
        LotteryModel lotteryModel =null;
        List<LotteryModel> lotteryModelList =new ArrayList<>();
        openDatabase();
        String[] args={"%"+lotSearch+"%"};

        Cursor cursor=mDatabase.rawQuery("Select * From Product Where NAME Like ?",args);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            lotteryModel =new LotteryModel(cursor.getInt(0),cursor.getString(1),cursor.getString(2));
            lotteryModelList.add(lotteryModel);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return lotteryModelList;
    }

}
