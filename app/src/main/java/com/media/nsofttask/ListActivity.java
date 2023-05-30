package com.media.nsofttask;


import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class ListActivity extends Fragment {

    RecyclerView rec_list;
    int id = 1;
    ArrayList<String> _id, url, owner, name, description, star, forks,
    list_issues, list_watchers;
    private SQLiteDatabase db;

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



        return rootView;
    }

}
