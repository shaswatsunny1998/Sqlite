package com.example.android.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.StringTokenizer;

public class ContactsDB
{
    public static final String KeyRowid="_id";
    public static final String keyname="Person_Name";
    public static final String keymob="Mobile_Number";

    private final String database="ContactsDB";
    private final String tablename="Contactstable";
    private final int version=1;

    private DBhelper ourHelper;
    private final Context ourcontext;
    private SQLiteDatabase ourdatabase;

    public ContactsDB(Context context)
    {
        ourcontext=context;
    }

    private class DBhelper extends SQLiteOpenHelper
    {
        public DBhelper(Context context)
        {
            super(context,database,null,version);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
        {
            db.execSQL("DROP TABLE IF EXISTS "+tablename);
            onCreate(db);

        }
        @Override
        public void onCreate(SQLiteDatabase db)
        {
            /*
            CREATE TABLE CONTACTSTABLE (_id INTEGER PRIMARY KEY AUTOINCREMEN,
                                        Person_Name TEXT NOT NULL, MOBILE_NUMBER TEXT NOT NULL);

            */
            String sqlcode="CREATE TABLE "+tablename+" "+"("+KeyRowid+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                    keyname + " TEXT NOT NULL, "+keymob+" TEXT NOT NULL);";
            db.execSQL(sqlcode);

        }


    }
    public ContactsDB open() throws SQLException
    {
         ourHelper = new DBhelper(ourcontext);
         ourdatabase = ourHelper.getWritableDatabase();
         return this;
    }
    public  void close()
    {
        ourHelper.close();
    }

    public long createEntry(String name,String mob)
    {
        ContentValues cv=new ContentValues();
        cv.put(keyname,name);
        cv.put(keymob,mob);
        return ourdatabase.insert(tablename,null,cv);
    }

    public String getData()
    {
        String [] columns=new String[]{KeyRowid,keyname,keymob};
        Cursor c=ourdatabase.query(tablename,columns,null,null,null,null,null);
        String result="";
        int irowid=c.getColumnIndex(KeyRowid);
        int iname=c.getColumnIndex(keyname);
        int imob=c.getColumnIndex(keymob);

        for(c.moveToFirst();!c.isAfterLast();c.moveToNext())
        {
            result=result+c.getString(irowid)+": "+c.getString(iname)+" "+c.getString(imob)+"\n";
        }
        c.close();
        return result;
    }
    public long delteentry(String rowid)
    {
        return ourdatabase.delete(tablename,KeyRowid+"=?",new String[]{rowid});
    }
    public long updatentry(String rowid,String name,String mob)
    {
        return 0;
    }

}
