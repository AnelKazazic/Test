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
import com.media.nsofttask.Database.DataBaseHelper;
import com.media.nsofttask.Database.DatabaseAdapter;
import com.media.nsofttask.Model.FavoriteModel;
import java.util.ArrayList;
import java.util.List;

public class FavoriteActivity extends Fragment {

    private RecyclerView.Adapter adapter;
    List<FavoriteModel> favoriteModels;
    RecyclerView rec_repo;
    private Context context;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_repo, container, false);

        rec_repo = rootView.findViewById(R.id.rec_repo);
        rec_repo.setHasFixedSize(true);
        rec_repo.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        favoriteModels = new ArrayList<>();

        GetData();


        return  rootView;


}

     public void GetData(){

         DataBaseHelper dataBaseHelper = new DataBaseHelper(this.getActivity());
         Cursor cursor = dataBaseHelper.ReadData();

         if(cursor.getCount()==0){

             //Toast.makeText(context,"No entry exists", Toast.LENGTH_SHORT).show();

         }else{

             while (cursor.moveToNext()){

                 FavoriteModel list = new FavoriteModel(cursor.getString(0),cursor.getString(1),
                         cursor.getString(2),cursor.getString(3),cursor.getString(4),
                         cursor.getString(5),cursor.getString(6),cursor.getString(7));

                 favoriteModels.add(list);


             }

         }

         adapter = new DatabaseAdapter(getContext(),favoriteModels);
         rec_repo.setAdapter(adapter);

}


}
