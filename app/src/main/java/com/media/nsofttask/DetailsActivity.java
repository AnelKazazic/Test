package com.media.nsofttask;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.media.nsofttask.adapters.ContributorsAdapter;
import com.media.nsofttask.adapters.RepozitoriAdapter;
import com.media.nsofttask.model.ContributorsModel;
import com.squareup.picasso.Picasso;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DetailsActivity extends AppCompatActivity {

    ImageView arrow_image;
    ImageView image_user;
    ImageView image_favorite;
    TextView txt_login;
    ImageView image_html;
    TextView txt_branch;
    TextView txt_description;
    TextView txt_star;
    TextView txt_forks;
    TextView txt_issues;
    TextView txt_watcherss;
    TextView txt_created;
    TextView txt_updated;


    private static final String URL = "https://api.github.com/search/repositories?q=language:java&order=desc&sort=stars";
    private List<ContributorsModel> contributorsModels;
    RecyclerView recycler_contributors;
    private RecyclerView.Adapter adapter;


    @SuppressLint({"SetTextI18n", "NonConstantResourceId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        GetContributors();

        arrow_image = findViewById(R.id.arrow_image);
        image_user = findViewById(R.id.image_user);
        txt_login = findViewById(R.id.txt_login);
        image_html = findViewById(R.id.image_html);
        txt_description = findViewById(R.id.txt_description);
        //txt3 = findViewById(R.id.txt3);
        image_favorite = findViewById(R.id.image_favorite);
        txt_star = findViewById(R.id.txt_star);
        txt_forks = findViewById(R.id.txt_forks);
        txt_issues = findViewById(R.id.txt_issues);
        txt_watcherss = findViewById(R.id.txt_watchers);
        txt_branch = findViewById(R.id.txt_branch);
        txt_created = findViewById(R.id.txt_created);
        txt_updated = findViewById(R.id.txt_updated);


        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation_user);
        bottomNavigationView.bringToFront();

        contributorsModels = new ArrayList<>();

        recycler_contributors = findViewById(R.id.recycler_contributors);
        recycler_contributors.setHasFixedSize(true);
        recycler_contributors.setLayoutManager(new LinearLayoutManager(DetailsActivity.this));
        recycler_contributors.setAdapter(adapter);



        Intent intent = this.getIntent();

        final String avatar_url = intent.getStringExtra(RepozitoriAdapter.KEY_URL);
        final String owner = intent.getStringExtra(RepozitoriAdapter.KEY_OWNER);
        final String description = intent.getStringExtra(RepozitoriAdapter.KEY_DESCRIPTION);
        final String star = intent.getStringExtra(RepozitoriAdapter.KEY_STAR);
        final String forks = intent.getStringExtra(RepozitoriAdapter.KEY_FORKS);
        final String issues = intent.getStringExtra(RepozitoriAdapter.KEY_ISSUES);
        final String watchers = intent.getStringExtra(RepozitoriAdapter.KEY_WATCHERS);
        final String branch = intent.getStringExtra(RepozitoriAdapter.KEY_BRANCH);
        final String created = intent.getStringExtra(RepozitoriAdapter.KEY_CREATED);
        final String updated = intent.getStringExtra(RepozitoriAdapter.KEY_UPDATED);

        String url= Objects.requireNonNull(intent.getExtras()).getString(RepozitoriAdapter.KEY_HTML);

        Picasso.with(this)
                .load(avatar_url)
                .into(image_user);

        txt_login.setText(owner);
        //txt2.setText(name);
        txt_description.setText(description);
        txt_star.setText(star);
        txt_forks.setText(forks);
        txt_issues.setText(issues);
        txt_watcherss.setText(watchers);
        txt_branch.setText("Branch" + branch);
        txt_created.setText("Created:" + created);
        txt_updated.setText("Updated:" + updated);

        image_html.setOnClickListener(view -> {

            Intent htmlintent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(htmlintent);

        });


        arrow_image.setOnClickListener(view -> onBackPressed());

       bottomNavigationView.setOnItemSelectedListener(item -> {

           int id = item.getItemId();

           switch(id){
               case R.id.navigation_list:
                   Intent userintent = new Intent(DetailsActivity.this,MainActivity.class);
                   startActivity(userintent);
                   break;
               case R.id.navigation_favorites:
                   Intent favointent = new Intent(DetailsActivity.this,FavoriteTab.class);
                   startActivity(favointent);
                   break;
           }
           return true;
       });

    }


    private void GetContributors(){

       // boolean favorite = false;


        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading data...");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, s -> {
            progressDialog.dismiss();

            try {
                JSONObject jsonObject = new JSONObject(s);
                JSONArray array = jsonObject.getJSONArray("items");

                for (int i=0;i<array.length();i++){

                    JSONObject object = array.getJSONObject(i);
                    JSONObject data = object.getJSONObject("owner");
                    ContributorsModel list = new ContributorsModel(data.getString("avatar_url"),
                            data.getString("login"), false);
                    contributorsModels.add(list);

                }

                adapter = new ContributorsAdapter(contributorsModels,getApplicationContext());
                recycler_contributors.setAdapter(adapter);


            }catch (JSONException e){
                e.printStackTrace();
            }
        }, volleyError -> Toast.makeText(DetailsActivity.this, "Error", Toast.LENGTH_SHORT).show());

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }



}


