package com.media.nsofttask;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.media.nsofttask.database.ContributorsDBAdapter;
import com.media.nsofttask.database.ContributorsDBHelper;
import com.media.nsofttask.model.ContributorsDBModel;
import java.util.ArrayList;
import java.util.List;

public class ContributorFragment extends Fragment {

    RecyclerView rec_contributors;
    private RecyclerView.Adapter adapter;
    List<ContributorsDBModel> contributorsDBModels;
    private Context context;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_contributor, container, false);

        rec_contributors = rootView.findViewById(R.id.rec_contributors);
        rec_contributors.setHasFixedSize(true);
        rec_contributors.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        contributorsDBModels = new ArrayList<>();

        GetData();

        return  rootView;
    }

    private void GetData() {

        ContributorsDBHelper contributorsDBHelper = new ContributorsDBHelper(this.getActivity());
        Cursor cursor = contributorsDBHelper.ReadData();

        if(cursor.getCount()==0){

            //Toast.makeText(context,"No entry exists", Toast.LENGTH_SHORT).show();

        }else {

            while(cursor.moveToNext()){

                ContributorsDBModel list = new ContributorsDBModel(cursor.getString(0),cursor.getString(1));

                contributorsDBModels.add(list);

            }
        }

        adapter = new ContributorsDBAdapter(getContext(), contributorsDBModels);
        rec_contributors.setAdapter(adapter);
    }

}
