package com.media.nsofttask.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.media.nsofttask.database.ContributorsDBHelper;
import com.media.nsofttask.model.ContributorsModel;
import com.media.nsofttask.R;

import java.util.List;

public class ContributorsAdapter extends RecyclerView.Adapter<ContributorsAdapter.ContributorsViewHolder>{

    public final List<ContributorsModel> contributorsModels;
    public Context context;
    ContributorsDBHelper contributorsHelper;

    public ContributorsAdapter(List<ContributorsModel> contributorsModels, Context context) {
        this.contributorsModels = contributorsModels;
        this.context = context;
    }

    @NonNull
    @Override
    public ContributorsAdapter.ContributorsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.data_contributors, parent, false);
        return new ContributorsAdapter.ContributorsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContributorsAdapter.ContributorsViewHolder holder, int position) {

        final ContributorsModel contributors = contributorsModels.get(position);

        Glide.with(context)
                .load(contributors.getContributors())
                .into(holder.contributor_url);

        holder.contributor_name.setText(contributors.getContributor_name());

        holder.favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                contributorsHelper = new ContributorsDBHelper(context.getApplicationContext());

                final String avatar_ur = contributors.getContributors();
                final String owner = contributors.getContributor_name();

                contributorsHelper.InsertData(avatar_ur,owner);


            }
        });



    }

    @Override
    public int getItemCount() {
        return contributorsModels.size();
    }

    public static class ContributorsViewHolder extends RecyclerView.ViewHolder {

        ImageView contributor_url;
        TextView contributor_name;
        ImageView favorite;


        public ContributorsViewHolder(@NonNull View itemView) {
            super(itemView);

            contributor_url= itemView.findViewById(R.id.contributor_url);
            contributor_name = itemView.findViewById(R.id.contributor_name);
            favorite = itemView.findViewById(R.id.favorite_img);
        }
    }
}
