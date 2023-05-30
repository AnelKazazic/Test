package com.media.nsofttask;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.media.nsofttask.Database.ConAdapter;
import com.media.nsofttask.Database.DataContributorsHelper;
import com.media.nsofttask.Model.ConModel;
import java.util.ArrayList;
import java.util.List;

public class ContributorActivity extends Fragment {

    RecyclerView rec_contributors;
    private RecyclerView.Adapter adapter;
    List<ConModel> conModels;
    private Context context;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_contributor, container, false);

        rec_contributors = rootView.findViewById(R.id.rec_contributors);
        rec_contributors.setHasFixedSize(true);
        rec_contributors.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        conModels = new ArrayList<>();

        GetData();

        return  rootView;
    }

    private void GetData() {

        DataContributorsHelper dataContributorsHelper = new DataContributorsHelper(this.getActivity());
        Cursor cursor = dataContributorsHelper.ReadData();

        if(cursor.getCount()==0){

            //Toast.makeText(context,"No entry exists", Toast.LENGTH_SHORT).show();

        }else {

            while(cursor.moveToNext()){

                ConModel list = new ConModel(cursor.getString(0),cursor.getString(1));

                conModels.add(list);

            }
        }

        adapter = new ConAdapter(getContext(),conModels);
        rec_contributors.setAdapter(adapter);
    }

}
