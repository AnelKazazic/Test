package com.media.nsofttask;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.media.nsofttask.Database.FeedReaderDbHelper;
import com.media.nsofttask.Java.JavaListAdapter;
import com.squareup.picasso.Picasso;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Objects;

public class FavoriteActivity extends Fragment {

    FeedReaderDbHelper dbHelper;
    RecyclerView rec_list;
    ArrayList<String> avatar_url, owner, name, description, star, forks, issues, watchers;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_repo, container, false);
        rec_list = rootView.findViewById(R.id.rec_view);


        dbHelper = new FeedReaderDbHelper(getContext());
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Intent intent = requireActivity().getIntent();

        final String avatar_url = intent.getStringExtra(JavaListAdapter.KEY_URL);
        final String owner = intent.getStringExtra(JavaListAdapter.KEY_OWNER);
        final String name = intent.getStringExtra(JavaListAdapter.KEY_NAME);
        final String description = intent.getStringExtra(JavaListAdapter.KEY_DESCRIPTION);
        final String star = intent.getStringExtra(JavaListAdapter.KEY_STAR);
        final String forks = intent.getStringExtra(JavaListAdapter.KEY_FORKS);
        final String issues = intent.getStringExtra(JavaListAdapter.KEY_ISSUES);
        final String watchers = intent.getStringExtra(JavaListAdapter.KEY_WATCHERS);

        //String url= Objects.requireNonNull(intent.getExtras()).getString(JavaListAdapter.KEY_HTML);

        //Picasso.with(this)
                //.load(avatar_url)
                //.into(image_user);

// Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(FeedReaderContract.FeedEntry.COLUMN_URL, avatar_url);
        values.put(FeedReaderContract.FeedEntry.COLUMN_OWNER, owner);
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME, name);
        values.put(FeedReaderContract.FeedEntry.COLUMN_DESCRIPTION, description);
        values.put(FeedReaderContract.FeedEntry.COLUMN_STAR, star);
        values.put(FeedReaderContract.FeedEntry.COLUMN_FORKS, forks);
        values.put(FeedReaderContract.FeedEntry.COLUMN_ISSUES, issues);
        values.put(FeedReaderContract.FeedEntry.COLUMN_WATCHERS, watchers);

// Insert the new row, returning the primary key value of the new row
        //long _ID = db.insert(FeedReaderContract.FeedEntry.TABLE_NAME, null, values);


        return  rootView;

}



    public static final class FeedReaderContract {
        // To prevent someone from accidentally instantiating the contract class,
        // make the constructor private.
        private FeedReaderContract() {}

        /* Inner class that defines the table contents */
        public static class FeedEntry implements BaseColumns {
            public static final String TABLE_NAME = "sqltask";
            public static final String COLUMN_ID = "_id";
            public static final String COLUMN_URL = "url";
            public static final String COLUMN_OWNER = "owner";
            public static final String COLUMN_NAME = "name";
            public static final String COLUMN_DESCRIPTION = "description";
            public static final String COLUMN_STAR = "star";
            public static final String COLUMN_FORKS = "forks";
            public static final String COLUMN_ISSUES = "issues";
            public static final String COLUMN_WATCHERS = "watchers";
        }
    }

}
