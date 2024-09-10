package com.jd_s4nd_b0x.getevenlucky.SQL_DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Base64;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "ctf.db";
    private static final int DATABASE_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Flags (id INTEGER PRIMARY KEY, flag TEXT);");
        String encodedFlag = "Y0ROdWRETTFkSHRrTkRjMFlqUTFNelZmTkc1a1gzZ3djbDh4TlY4MFh6WXdNR1JmWXpCdFlqRnVORGN4TUc1ZmNqRTJhRGQ5";
        String decodedFlag = decodeBase64(encodedFlag);
        db.execSQL("INSERT INTO Flags (id, flag) VALUES (1, '" + decodedFlag + "');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Flags");
        onCreate(db);
    }

    private String decodeBase64(String encodedString) {
        byte[] decodedBytes = Base64.decode(encodedString, Base64.DEFAULT);
        return new String(decodedBytes);
    }
}