package com.media.nsofttask.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.media.nsofttask.FavoriteActivity;

public class FeedReaderDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "NsoftTask.db";

    public FeedReaderDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(SQL_CREATE_ENTRIES);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);

    }

    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + FavoriteActivity.FeedReaderContract.FeedEntry.TABLE_NAME + " (" +
                    FavoriteActivity.FeedReaderContract.FeedEntry._ID + " INTEGER PRIMARY KEY," +
                    FavoriteActivity.FeedReaderContract.FeedEntry.COLUMN_URL + " TEXT," +
                    FavoriteActivity.FeedReaderContract.FeedEntry.COLUMN_OWNER + " TEXT," +
                    FavoriteActivity.FeedReaderContract.FeedEntry.COLUMN_NAME + " TEXT," +
                    FavoriteActivity.FeedReaderContract.FeedEntry.COLUMN_DESCRIPTION + " TEXT," +
                    FavoriteActivity.FeedReaderContract.FeedEntry.COLUMN_STAR + " TEXT," +
                    FavoriteActivity.FeedReaderContract.FeedEntry.COLUMN_FORKS + " TEXT," +
                    FavoriteActivity.FeedReaderContract.FeedEntry.COLUMN_ISSUES + " TEXT," +
                    FavoriteActivity.FeedReaderContract.FeedEntry.COLUMN_WATCHERS + " TEXT)";

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + FavoriteActivity.FeedReaderContract.FeedEntry.TABLE_NAME;



}
