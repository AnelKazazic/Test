package com.media.nsofttask.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.widget.Toast;

public class ContributorsDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "contributors.db";
    private static final int DATABASE_VERSION = 1;
    private final Context context;

    public ContributorsDBHelper(Context context){

        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        this.context=context;
    }

    public static final class FeedReaderContract{

        public static final class FeedEntry implements BaseColumns{

            public static final String TABLE_NAME = "CONTRIBUTORS";
            public static final String COLUMN_ID = "id";
            public static final String COLUMN_URL = "avatar_url";
            public static final String COLUMN_OWNER = "owner";

        }
    }




    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        final String query = " CREATE TABLE " + FeedReaderContract.FeedEntry.TABLE_NAME + "(" +
                FeedReaderContract.FeedEntry.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                FeedReaderContract.FeedEntry.COLUMN_URL+ " TEXT, " +
                FeedReaderContract.FeedEntry.COLUMN_OWNER + " TEXT)";

        sqLiteDatabase.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void InsertData (String avatar_url, String owner){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(FeedReaderContract.FeedEntry.COLUMN_URL, avatar_url);
        values.put(FeedReaderContract.FeedEntry.COLUMN_OWNER, owner);

        long rowID = db.insertWithOnConflict(FeedReaderContract.FeedEntry.TABLE_NAME, null, values,SQLiteDatabase.CONFLICT_REPLACE);

        if (rowID == -1){

            Toast.makeText(context,"Failed", Toast.LENGTH_SHORT).show();
        }else {

            Toast.makeText(context,"Add successfully", Toast.LENGTH_SHORT).show();
        }
    }

    public Cursor ReadData() {

        SQLiteDatabase db = this.getReadableDatabase();

        String[] columns = new String[] {FeedReaderContract.FeedEntry.COLUMN_URL, FeedReaderContract.FeedEntry.COLUMN_OWNER};

        Cursor cursor = db.query(FeedReaderContract.FeedEntry.TABLE_NAME, columns,
                null, null, null, null, null);

        if (cursor != null) {

            cursor.moveToFirst();
        }

            return cursor;
        }


}
