package com.media.nsofttask.database;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.media.nsofttask.model.ContributorsDBModel;
import com.media.nsofttask.R;

import java.util.List;

public class ContributorsDBAdapter extends RecyclerView.Adapter<ContributorsDBAdapter.DataViewHolder> {

    Context context;
    public List<ContributorsDBModel> contributorsDBModels;


    public ContributorsDBAdapter(Context context, List<ContributorsDBModel> contributorsDBModels) {
        this.context = context;
        this.contributorsDBModels = contributorsDBModels;
    }


    @NonNull
    @Override
    public ContributorsDBAdapter.DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_contributors_helper, parent, false);
        return new DataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContributorsDBAdapter.DataViewHolder holder, int position) {

        final ContributorsDBModel contributorsDBModel = contributorsDBModels.get(position);

        Glide.with(context)
                .load(contributorsDBModel.getAvatarurl())
                .into(holder.contributor_url);

        holder.contributor_name.setText(contributorsDBModel.getContributor_name());


    }

    @Override
    public int getItemCount() {
        return contributorsDBModels.size();
    }

    public static class DataViewHolder extends RecyclerView.ViewHolder {

        ImageView contributor_url;
        TextView contributor_name;
        ImageView favorite;


        public DataViewHolder(@NonNull View itemView) {
            super(itemView);

            contributor_url= itemView.findViewById(R.id.contributor_url);
            contributor_name = itemView.findViewById(R.id.contributor_name);
            favorite = itemView.findViewById(R.id.favorite_image);
        }
    }
}
