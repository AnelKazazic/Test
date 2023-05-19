package com.media.nsofttask;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.media.nsofttask.Java.JavaListAdapter;
import com.media.nsofttask.Model.DetailsModel;
import com.media.nsofttask.Model.ListModel;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;;
import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static final String URL = "https://api.github.com/search/repositories?q=language:java&order=desc&sort=stars";
    private RecyclerView rec_view;
    private RecyclerView.Adapter adapter;
    private List<ListModel> listModels;
    private List<DetailsModel> detailsModels;
    private ActionBar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = getSupportActionBar();
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        bottomNavigationView.bringToFront();
        rec_view = findViewById(R.id.rec_view);
        rec_view.setHasFixedSize(true);
        rec_view.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        listModels = new ArrayList<>();
        detailsModels = new ArrayList<>();
        GetData();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch(id){
                    case R.id.navigation_list:
                        Intent listinten = new Intent(MainActivity.this,MainActivity.class);
                        startActivity(listinten);
                        break;
                    case R.id.navigation_favorites:
                        Intent favintent = new Intent(MainActivity.this,FavoriteTab.class);
                        startActivity(favintent);
                        break;
                }
                return true;
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        return true;
    }

    private void GetData(){
        
        boolean favorite = false;


        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading data...");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onResponse(String s) {
                progressDialog.dismiss();

                try {
                    JSONObject jsonObject = new JSONObject(s);
                    JSONArray array = jsonObject.getJSONArray("items");
                    //SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy  hh:mm a");
                    SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy h:mm", Locale.getDefault());

                    DateTimeFormatter monthFormatter = DateTimeFormatter.ofPattern("MM/uuuu");
                    String dateformat = "2012-11-17T00:00:00.000-05:00";
                    OffsetDateTime dateTime = OffsetDateTime.parse(dateformat);


                    for (int i=0;i<array.length();i++){


                            JSONObject object = array.getJSONObject(i);
                            JSONObject image = object.getJSONObject("owner");
                            ListModel list = new ListModel(image.getString("avatar_url"),
                                    image.getString("login"), object.getString("full_name"
                            ), object.getString("language"), object.getString("stargazers_count"),
                                    object.getString("forks_count"), object.getString("open_issues"),
                                    object.getString("watchers_count"), favorite);

                            DetailsModel model =new DetailsModel(
                                    image.getString("avatar_url"),
                                    object.getString("name"), object.getString("full_name"
                            ), object.getString("language"), object.getString("stargazers_count"),
                                    object.getString("forks_count"), object.getString("open_issues"),
                                    object.getString("watchers_count"),object.getString("default_branch"),
                                    object.getString("created_at"),
                                    object.getString("updated_at"), object.getString("html_url"));


                           listModels.add(list);
                           detailsModels.add(model);

                    }

                    adapter = new JavaListAdapter(listModels, detailsModels, getApplicationContext());
                    rec_view.setAdapter(adapter);



                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError volleyError) {

                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }


}