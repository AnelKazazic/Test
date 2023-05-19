package com.media.nsofttask;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.media.nsofttask.Java.ContributorsAdapter;
import com.media.nsofttask.Java.JavaListAdapter;
import com.media.nsofttask.Model.ContributorsModel;
import com.squareup.picasso.Picasso;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserActivity extends AppCompatActivity {

    ImageView arrow_image;
    ImageView image_user;
    TextView txt1;
    ImageView image_html;
    TextView txt2;
    TextView txt3;
    ImageView img1;
    ImageView img2;
    ImageView img3;
    ImageView img4;
    ImageView img5;
    TextView txt_star;
    TextView txt_forks;
    TextView txt_issues;
    TextView txt_watcherss;
    TextView txt_branch;
    TextView txt_created;
    TextView txt_updated;

    private static final String TAG_LIST = "list";
    private static final String URL = "https://api.github.com/search/repositories?q=language:java&order=desc&sort=stars";
    private List<ContributorsModel> contributorsModels;
    RecyclerView recycler_contributors;
    private RecyclerView.Adapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        GetContributors();

        arrow_image = findViewById(R.id.arrow_image);
        image_user = findViewById(R.id.image_user);
        txt1 = findViewById(R.id.txt1);
        image_html = findViewById(R.id.image_html);
        txt2 = findViewById(R.id.txt2);
        txt3 = findViewById(R.id.txt3);
        img1 = findViewById(R.id.img1);
        img2 = findViewById(R.id.img2);
        img3 = findViewById(R.id.img3);
        img4 = findViewById(R.id.img4);
        img5 = findViewById(R.id.img5);
        txt_star = findViewById(R.id.txt_star);
        txt_forks = findViewById(R.id.txt_forks);
        txt_issues = findViewById(R.id.txt_issues);
        txt_watcherss = findViewById(R.id.txt_watchers);
        txt_branch = findViewById(R.id.txt_branch);
        txt_created = findViewById(R.id.txt_created);
        txt_updated = findViewById(R.id.txt_updated);


        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation_user);
        bottomNavigationView.bringToFront();

        contributorsModels = new ArrayList<>();

        recycler_contributors = findViewById(R.id.recycler_contributors);
        recycler_contributors.setHasFixedSize(true);
        recycler_contributors.setLayoutManager(new LinearLayoutManager(UserActivity.this));
        recycler_contributors.setAdapter(adapter);


        Intent intent = this.getIntent();

        final String avatar_url = intent.getStringExtra(JavaListAdapter.KEY_URL);
        final String owner = intent.getStringExtra(JavaListAdapter.KEY_OWNER);
        final String name = intent.getStringExtra(JavaListAdapter.KEY_NAME);
        final String description = intent.getStringExtra(JavaListAdapter.KEY_DESCRIPTION);
        final String star = intent.getStringExtra(JavaListAdapter.KEY_STAR);
        final String forks = intent.getStringExtra(JavaListAdapter.KEY_FORKS);
        final String issues = intent.getStringExtra(JavaListAdapter.KEY_ISSUES);
        final String watchers = intent.getStringExtra(JavaListAdapter.KEY_WATCHERS);
        final String branch = intent.getStringExtra(JavaListAdapter.KEY_BRANCH);
        final String created = intent.getStringExtra(JavaListAdapter.KEY_CREATED);
        final String updated = intent.getStringExtra(JavaListAdapter.KEY_UPDATED);

        String url= Objects.requireNonNull(intent.getExtras()).getString(JavaListAdapter.KEY_HTML);

        Picasso.with(this)
                .load(avatar_url)
                .into(image_user);

        txt1.setText(owner);
        txt2.setText(name);
        txt3.setText(description);
        txt_star.setText(star);
        txt_forks.setText(forks);
        txt_issues.setText(issues);
        txt_watcherss.setText(watchers);
        txt_branch.setText("Branch:" + branch);
        txt_created.setText("Created:" + created);
        txt_updated.setText("Updated:" + updated);

        image_html.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent htmlintent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(htmlintent);

            }
        });


        arrow_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onBackPressed();
            }
        });

       bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
           @Override
           public boolean onNavigationItemSelected(@NonNull MenuItem item) {

               int id = item.getItemId();

               switch(id){
                   case R.id.navigation_list:
                       Intent userintent = new Intent(UserActivity.this,MainActivity.class);
                       startActivity(userintent);
                       break;
                   case R.id.navigation_favorites:
                       Intent favointent = new Intent(UserActivity.this,FavoriteTab.class);
                       startActivity(favointent);
                       break;
               }
               return true;
           }
       });

    }


    private void GetContributors(){


        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading data...");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                progressDialog.dismiss();

                try {
                    JSONObject jsonObject = new JSONObject(s);
                    JSONArray array = jsonObject.getJSONArray("items");

                    for (int i=0;i<array.length();i++){

                        JSONObject object = array.getJSONObject(i);
                        JSONObject data = object.getJSONObject("owner");
                        ContributorsModel list = new ContributorsModel(data.getString("avatar_url"), data.getString("login"));
                        contributorsModels.add(list);

                    }

                    adapter = new ContributorsAdapter(contributorsModels,getApplicationContext());
                    recycler_contributors.setAdapter(adapter);


                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError volleyError) {

                Toast.makeText(UserActivity.this, "Error", Toast.LENGTH_SHORT).show();

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

}

