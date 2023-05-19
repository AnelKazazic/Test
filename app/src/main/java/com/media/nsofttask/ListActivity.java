package com.media.nsofttask;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.media.nsofttask.Database.FeedReaderDbHelper;
import com.media.nsofttask.Java.JavaListAdapter;

import java.util.ArrayList;

public class ListActivity extends Fragment {

    RecyclerView rec_list;
    FeedReaderDbHelper databaseHelper;
    ArrayList<String> _id, url, owner, name, description, star, forks,
    list_issues, list_watchers;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_list, container, false);

        rec_list = rootView.findViewById(R.id.rec_list);
        rec_list.setHasFixedSize(true);
        rec_list.setLayoutManager(new LinearLayoutManager(getActivity()));


        _id = new ArrayList<>();
        url = new ArrayList<>();
        owner = new ArrayList<>();
        name = new ArrayList<>();
        description = new ArrayList<>();
        star = new ArrayList<>();
        forks = new ArrayList<>();
        list_issues = new ArrayList<>();
        list_watchers = new ArrayList<>();

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
        values.put(FavoriteActivity.FeedReaderContract.FeedEntry.COLUMN_URL, avatar_url);
        values.put(FavoriteActivity.FeedReaderContract.FeedEntry.COLUMN_OWNER, owner);
        values.put(FavoriteActivity.FeedReaderContract.FeedEntry.COLUMN_NAME, name);
        values.put(FavoriteActivity.FeedReaderContract.FeedEntry.COLUMN_DESCRIPTION, description);
        values.put(FavoriteActivity.FeedReaderContract.FeedEntry.COLUMN_STAR, star);
        values.put(FavoriteActivity.FeedReaderContract.FeedEntry.COLUMN_FORKS, forks);
        values.put(FavoriteActivity.FeedReaderContract.FeedEntry.COLUMN_ISSUES, issues);
        values.put(FavoriteActivity.FeedReaderContract.FeedEntry.COLUMN_WATCHERS, watchers);

        return rootView;
    }



}
