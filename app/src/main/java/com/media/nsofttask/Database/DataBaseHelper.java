package com.media.nsofttask.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.widget.Toast;

public class DataBaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "repozitori.db";
    private static final int DATABASE_VERSION = 1;
    private final Context context;

    public DataBaseHelper(Context context){

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context=context;

    }

    private static final class FeedReaderContract{

        private FeedReaderContract(){}

        public static final class FeedEntry implements BaseColumns {

            public static final String TABLE_NAME = "REPOZITORI";
            public static final String COLUMN_ID = "id";
            public static final String COLUMN_URL = "avatar_url";
            public static final String COLUMN_OWNER = "owner";
            public static final String COLUMN_NAME = "name";
            public static final String COLUMN_DESCRIPTION = "description";
            public static final String COLUMN_STAR = "star";
            public static final String COLUMN_FORKS = "forks";
            public static final String COLUMN_ISSUES = "issues";
            public static final String COLUMN_WATCHERS = "watchers";

        }
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        final String query = " CREATE TABLE " + FeedReaderContract.FeedEntry.TABLE_NAME + "(" +
                FeedReaderContract.FeedEntry.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                FeedReaderContract.FeedEntry.COLUMN_URL + " TEXT, " +
                FeedReaderContract.FeedEntry.COLUMN_OWNER + " TEXT, " +
                FeedReaderContract.FeedEntry.COLUMN_NAME + " TEXT, " +
                FeedReaderContract.FeedEntry.COLUMN_DESCRIPTION + " TEXT, " +
                FeedReaderContract.FeedEntry.COLUMN_STAR + " TEXT, " +
                FeedReaderContract.FeedEntry.COLUMN_FORKS + " TEXT, " +
                FeedReaderContract.FeedEntry.COLUMN_ISSUES + " TEXT, " +
                FeedReaderContract.FeedEntry.COLUMN_WATCHERS + " TEXT)";

                sqLiteDatabase.execSQL(query);

    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void InsertData(String avatar_url, String owner, String name, String description, String star,
                           String forks, String issues, String watchers){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(FeedReaderContract.FeedEntry.COLUMN_URL, avatar_url);
        values.put(FeedReaderContract.FeedEntry.COLUMN_OWNER,owner);
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME, name);
        values.put(FeedReaderContract.FeedEntry.COLUMN_DESCRIPTION,description);
        values.put(FeedReaderContract.FeedEntry.COLUMN_STAR, star);
        values.put(FeedReaderContract.FeedEntry.COLUMN_FORKS, forks);
        values.put(FeedReaderContract.FeedEntry.COLUMN_ISSUES, issues);
        values.put(FeedReaderContract.FeedEntry.COLUMN_WATCHERS, watchers);

        long rowId = db.insertWithOnConflict(FeedReaderContract.FeedEntry.TABLE_NAME, null,
                values, SQLiteDatabase.CONFLICT_REPLACE);

        if(rowId == -1){

            Toast.makeText(context,"Failed", Toast.LENGTH_SHORT).show();

        }else {


            Toast.makeText(context,"Add successfully", Toast.LENGTH_SHORT).show();
        }


    }

    public Cursor ReadData(){

        SQLiteDatabase db = this.getReadableDatabase();

        String[] columns = new String[] {FeedReaderContract.FeedEntry.COLUMN_URL,
        FeedReaderContract.FeedEntry.COLUMN_OWNER,
        FeedReaderContract.FeedEntry.COLUMN_NAME,
        FeedReaderContract.FeedEntry.COLUMN_DESCRIPTION,
        FeedReaderContract.FeedEntry.COLUMN_STAR,
        FeedReaderContract.FeedEntry.COLUMN_FORKS,
        FeedReaderContract.FeedEntry.COLUMN_ISSUES,
        FeedReaderContract.FeedEntry.COLUMN_WATCHERS};

       Cursor cursor = db.query(FeedReaderContract.FeedEntry.TABLE_NAME, columns,null,
                null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        return cursor;

    }

    public void DeleteRow(String id){

        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(FeedReaderContract.FeedEntry.TABLE_NAME, FeedReaderContract.FeedEntry.COLUMN_ID +
                        "=" + id, null
                );

    }


}
